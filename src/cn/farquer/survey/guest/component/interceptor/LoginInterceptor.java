package cn.farquer.survey.guest.component.interceptor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.farquer.survey.guest.component.service.interfaces.UserService;
import cn.farquer.survey.guest.entity.User;
import cn.farquer.survey.utils.GlobalNames;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 对某些非法的action进行拦截，并且跳转回相关页面
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:41:18
 */
public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -4317602053389522401L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		//获取Action对象
		Object action = invocation.getAction();

		//创建一个set集合，泛型String，用于装入游客可以访问的actionName
		Set<String> visitorAllowedAction = new HashSet<>();
		visitorAllowedAction.add("UserAction_register");
		visitorAllowedAction.add("UserAction_login");
		visitorAllowedAction.add("SurveyAction_top10");

		//创建一个set集合，泛型String，用于装入登录用户可以访问的actionName
		Set<String> loginUserAllowedAction = new HashSet<>();
		loginUserAllowedAction.add("ToPageAction_user_myCenter");
		loginUserAllowedAction.add("UserAction_logout");
		loginUserAllowedAction.add("ToPageAction_user_pay");
		loginUserAllowedAction.add("UserAction_pay");
		loginUserAllowedAction.add("ToPageAction_user_vip");
		loginUserAllowedAction.add("UserAction_vip");
		loginUserAllowedAction.add("SurveyAction_top10");

		// 1.获取当前请求要访问的目标资源
		// 目标资源一定是一个Action请求，所以需要被保护的资源需要是一个Action请求才可以
		ActionProxy proxy = invocation.getProxy();

		// 当前请求要访问的目标Action的名字
		// UserAction_login
		// UserAction_pay
		// UserAction_vip
		// ……
		String actionName = proxy.getActionName();

		// 2.检查当前用户身份
		// ①获取Session对象
		HttpSession session = ServletActionContext.getRequest().getSession();

		// ②从Session对象中获取User对象
		User user = (User) session.getAttribute(GlobalNames.LOGIN_USER);

		// ③如果user对象为null，则说明当前用户是游客
		if (user == null) {

			// 目标ActionName是否在游客允许的集合中
			if (visitorAllowedAction.contains(actionName)) {

				// 放行
				return invocation.invoke();

			} else {

				if (action instanceof ValidationAware) {
					ValidationAware va = (ValidationAware) action;
					va.addActionError("这个操作需要登录后才可以使用！");
				}

				return "loginError";
			}

		} else {

			// 如果目标Action需要User对象，则为其注入
			if (action instanceof UserAware) {
				UserAware ua = (UserAware) action;
				ua.setUser(user);
			}

			// 检查用户是否是付费用户
			if (user.isPayStatus()) {

				// 检查当前会员是否到期
				long endTime = user.getEndTime();

				long time = new Date().getTime();

				if (endTime <= time) {
					user.setPayStatus(false);
					ServletContext sc = ServletActionContext
							.getServletContext();
					WebApplicationContext context = WebApplicationContextUtils
							.getWebApplicationContext(sc);
					UserService userService = context
							.getBean(UserService.class);
					userService.updateEntity(user);

					return "toVipPage";
				}

				// 放行
				return invocation.invoke();

			} else {

				// 检查是否是普通用户可以访问的资源
				if (loginUserAllowedAction.contains(actionName)) {
					return invocation.invoke();
				} else {

					if (action instanceof ValidationAware) {
						ValidationAware va = (ValidationAware) action;
						va.addActionError("这个操作需要开通VIP后才可以使用！");
					}

					return "toVipPage";
				}

			}

		}

	}

}
