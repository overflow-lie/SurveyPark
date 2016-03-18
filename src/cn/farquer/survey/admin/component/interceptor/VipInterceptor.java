package cn.farquer.survey.admin.component.interceptor;

import java.util.Date;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.farquer.survey.admin.component.service.interfaces.ResourceService;
import cn.farquer.survey.admin.component.service.interfaces.RoleService;
import cn.farquer.survey.admin.entity.Role;
import cn.farquer.survey.guest.component.service.interfaces.UserService;
import cn.farquer.survey.guest.entity.User;
import cn.farquer.survey.utils.DataProcessUtils;
import cn.farquer.survey.utils.GlobalNames;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class VipInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		//1.获取User对象
		User user = (User) invocation.getInvocationContext().getSession().get(GlobalNames.LOGIN_USER);

		//2.如果User对象为null，则放行
		if(user == null) return invocation.invoke();
		
		//3.如果User对象现在是普通用户，则放行
		if(!user.isPayStatus()) return invocation.invoke();
		
		//4.如果当前用户的VIP会员还没有到期，则放行
		long endTime = user.getEndTime();
		long currentTime = new Date().getTime();
		
		if(endTime > currentTime) return invocation.invoke();
		
		//5.VIP会员到期，应该将payStatus状态修改
		user.setPayStatus(false);
		
		//6.重新计算当前用户的权限码
		//①当前系统中最大的权限位的值
		WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		ResourceService resourceService = ioc.getBean(ResourceService.class);
		Integer maxResPos = resourceService.getMaxResPos();
		
		//②当前用户允许拥有的角色集合
		Set<Role> roles = user.getRoles();
		
		RoleService roleService = ioc.getBean(RoleService.class);
		Role role = roleService.getRoleByName("付费登录用户");
		
		roles.remove(role);
		
		//③计算
		String codeStr = DataProcessUtils.calculatCodeByRoles(roles, maxResPos);
		user.setResCode(codeStr);
		
		//④更新权限码
		UserService userService = ioc.getBean(UserService.class);
		userService.updateEntity(user);
		
		//⑤取消和“付费登录用户”角色的关联关系
		//role = roleService.getRoleByName("付费登录用户");
		//userService.deletePayRole(user.getUserId(), role.getRoleId());
		
		return invocation.invoke();
	}

}
