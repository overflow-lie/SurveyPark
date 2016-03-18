package cn.farquer.survey.admin.component.interceptor;

import cn.farquer.survey.guest.component.interceptor.UserAware;
import cn.farquer.survey.guest.entity.User;
import cn.farquer.survey.utils.GlobalNames;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserAwareInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Object action = invocation.getAction();
		
		User user = (User) invocation.getInvocationContext().getSession().get(GlobalNames.LOGIN_USER);
		if(user != null && action instanceof UserAware) {
			UserAware ua = (UserAware) action;
			ua.setUser(user);
		}
		
		return invocation.invoke();
	}

}
