package cn.farquer.survey.admin.component.interceptor;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.farquer.survey.admin.component.service.interfaces.ResourceService;
import cn.farquer.survey.admin.entity.Admin;
import cn.farquer.survey.admin.entity.Resource;
import cn.farquer.survey.guest.entity.User;
import cn.farquer.survey.utils.DataProcessUtils;
import cn.farquer.survey.utils.GlobalNames;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Object action = invocation.getAction();
		WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		
		Admin admin = (Admin) invocation.getInvocationContext().getSession().get(GlobalNames.LOGIN_ADMIN);
		User user = (User) invocation.getInvocationContext().getSession().get(GlobalNames.LOGIN_USER);
		
		//超级管理员
		if(admin != null && "admin".equals(admin.getAdminName())) {
			return invocation.invoke();
		}
		
		//游客
		Set<String> visitorAllowedAction = new HashSet<>();
		visitorAllowedAction.add("UserAction_register");
		visitorAllowedAction.add("UserAction_login");
		visitorAllowedAction.add("UserAction_logout");
		visitorAllowedAction.add("SurveyAction_top10");
		visitorAllowedAction.add("AdminAction_toAdminMain");
		visitorAllowedAction.add("AdminAction_login");
		visitorAllowedAction.add("AdminAction_logout");
		
		ActionProxy proxy = invocation.getProxy();
		String actionName = proxy.getActionName();
		if(visitorAllowedAction.contains(actionName)) {
			return invocation.invoke();
		}
		
		String namespace = proxy.getNamespace();
		//前台
		if("/Guest".equals(namespace)) {
			
			if(user == null) {
				
				if(action instanceof ValidationAware) {
					ValidationAware va = (ValidationAware) action;
					va.addActionError("请登录后再操作！");
				}
				
				return "loginError";
			}else{
				
				//检查是否具备访问目标资源的权限
				//1.查询得到目标资源对象
				ResourceService resourceService = ioc.getBean(ResourceService.class);
				Resource resource = resourceService.getResourceByTargetName(actionName);
				
				//2.检查是否具备资源
				String resCode = user.getResCode();
				boolean checkResult = DataProcessUtils.checkAuthority(resource, resCode);
				
				if(checkResult) {
					return invocation.invoke();
				}else{
					
					if(action instanceof ValidationAware) {
						ValidationAware va = (ValidationAware) action;
						va.addActionError("您没有访问【"+resource.getResourceName()+"】这个资源的权限");
					}
					
					return "globalErrMsg";
				}
				
			}
			
		}
		
		//后台
		if("/Admin".equals(namespace)) {
			
			if(admin == null) {
				
				if(action instanceof ValidationAware) {
					ValidationAware va = (ValidationAware) action;
					va.addActionError("请登录后再操作！");
				}
				
				return "toAdminLoginPage";
			}else{
				
				//检查是否具备访问目标资源的权限
				//1.查询得到目标资源对象
				ResourceService resourceService = ioc.getBean(ResourceService.class);
				Resource resource = resourceService.getResourceByTargetName(actionName);
				
				//2.检查是否具备资源
				String resCode = admin.getResCode();
				boolean checkResult = DataProcessUtils.checkAuthority(resource, resCode);
				
				if(checkResult) {
					return invocation.invoke();
				}else{
					
					if(action instanceof ValidationAware) {
						ValidationAware va = (ValidationAware) action;
						va.addActionError("您没有访问【"+resource.getResourceName()+"】这个资源的权限");
					}
					
					return "globalErrMsg";
				}
				
			}
			
		}
		
		return invocation.invoke();
	}

}
