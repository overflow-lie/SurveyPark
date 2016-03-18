package cn.farquer.survey.admin.tag;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.farquer.survey.admin.component.service.interfaces.ResourceService;
import cn.farquer.survey.admin.entity.Admin;
import cn.farquer.survey.admin.entity.Resource;
import cn.farquer.survey.guest.entity.User;
import cn.farquer.survey.utils.DataProcessUtils;
import cn.farquer.survey.utils.GlobalNames;

import com.opensymphony.xwork2.ActionContext;

/**
 * 自定义标签类，细粒度根据权限显示当前用户可参与的链接
 * 
 * @author farquer
 * 
 *         2016年2月29日下午4:43:44
 */
public class AuthTag extends SimpleTagSupport {

	private String targetRes;

	@Override
	public void doTag() throws JspException, IOException {

		// 获取IOC容器
		WebApplicationContext ioc = WebApplicationContextUtils
				.getWebApplicationContext(ServletActionContext
						.getServletContext());

		// 获取Session域中的Admin对象或者User对象
		Admin admin = (Admin) ActionContext.getContext().getSession()
				.get(GlobalNames.LOGIN_ADMIN);
		User user = (User) ActionContext.getContext().getSession()
				.get(GlobalNames.LOGIN_USER);

		// 超级管理员
		if (admin != null && "admin".equals(admin.getAdminName())) {
			//直接全部显示
			getJspBody().invoke(null);
			return;
		}

		// 创建游客可以访问的ActionName集合
		Set<String> visitorAllowedAction = new HashSet<>();
		visitorAllowedAction.add("UserAction_register");
		visitorAllowedAction.add("UserAction_login");
		visitorAllowedAction.add("UserAction_logout");
		visitorAllowedAction.add("SurveyAction_top10");
		visitorAllowedAction.add("AdminAction_toAdminMain");
		visitorAllowedAction.add("AdminAction_login");
		visitorAllowedAction.add("AdminAction_logout");

		if (visitorAllowedAction.contains(targetRes)) {
			//如果访问Action属于游客可以访问的集合，直接全部显示
			getJspBody().invoke(null);
			return;
		}

		// 如果User不为null(已经登录的用户)
		if (user != null) {
			// 获取当前登录用户的权限码
			String resCode = user.getResCode();
			
			ResourceService resourceService = ioc
					.getBean(ResourceService.class);
			
			// 根据当前访问Action获取对应资源
			Resource resource = resourceService
					.getResourceByTargetName(targetRes);

			// 检查是否有权限访问
			boolean checkResult = DataProcessUtils.checkAuthority(resource,
					resCode);
			if (checkResult) {
				getJspBody().invoke(null);
				return;
			}
		}

		// 管理员同上
		if (admin != null) {
			String resCode = admin.getResCode();
			ResourceService resourceService = ioc
					.getBean(ResourceService.class);
			Resource resource = resourceService
					.getResourceByTargetName(targetRes);

			boolean checkResult = DataProcessUtils.checkAuthority(resource,
					resCode);
			if (checkResult) {
				getJspBody().invoke(null);
				return;
			}
		}

	}

	public void setTargetRes(String targetRes) {
		this.targetRes = targetRes;
	}

}
