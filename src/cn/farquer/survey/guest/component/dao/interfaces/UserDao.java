package cn.farquer.survey.guest.component.dao.interfaces;

import cn.farquer.survey.base.interfaces.BaseDao;
import cn.farquer.survey.guest.entity.User;

/**
 * 用户的DAO层接口声明类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:58:11
 */
public interface UserDao extends BaseDao<User> {

	/**
	 * 检查用户名是否存在
	 * 
	 * @param userName
	 * @return
	 */
	boolean checkUserName(String userName);

	/**
	 * 用户登录
	 * 
	 * @param t
	 * @return
	 */
	User login(User t);
	
	void deletePayRole(Integer userId, Integer roleId);

}
