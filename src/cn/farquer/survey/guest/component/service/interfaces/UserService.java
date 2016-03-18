package cn.farquer.survey.guest.component.service.interfaces;

import cn.farquer.survey.base.interfaces.BaseService;
import cn.farquer.survey.guest.entity.User;

/**
 * 用户的业务逻辑层接口声明类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午6:38:04
 */
public interface UserService extends BaseService<User> {

	/**
	 * 检查用户名是否可用
	 * 
	 * @param userName
	 * @return true:用户名已经被占用 false:用户名没有被占用
	 */
	boolean regist(User user);

	/**
	 * 用户登录
	 * 
	 * @param t
	 * @return
	 */
	User login(User t);

	/**
	 * 检查用户是否是VIP用户
	 * 
	 * @param vipDays
	 * @param t
	 * @return
	 */
	boolean vip(int vipDays, User t);
	
	void deletePayRole(Integer userId, Integer roleId);

}
