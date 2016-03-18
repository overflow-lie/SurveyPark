package cn.farquer.survey.guest.component.interceptor;

import cn.farquer.survey.guest.entity.User;

/**
 * 让需要User对象的目标Action类实现这个接口，在LoginInterceptor中，为其注入
 * 
 * @author farquer
 * 
 *         2016年1月27日下午9:32:32
 */
public interface UserAware {

	/**
	 * 在Action中实现这个接口，在拦截器中注入
	 * 
	 * @param user
	 */
	void setUser(User user);

}
