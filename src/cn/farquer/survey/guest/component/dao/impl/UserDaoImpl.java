package cn.farquer.survey.guest.component.dao.impl;

import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import cn.farquer.survey.admin.entity.Authority;
import cn.farquer.survey.admin.entity.Resource;
import cn.farquer.survey.admin.entity.Role;
import cn.farquer.survey.base.impl.BaseDaoImpl;
import cn.farquer.survey.guest.component.dao.interfaces.UserDao;
import cn.farquer.survey.guest.entity.User;

/**
 * 用户的DAO层实现类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:58:22
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public boolean checkUserName(String userName) {

		String hql = "select count(*) from User u where u.userName=?";

		long count = (long) this.getSession().createQuery(hql)
				.setString(0, userName).uniqueResult();

		return (count > 0);
	}

	@Override
	public User login(User t) {

		String hql = "From User u where u.userName=? and u.userPwd=?";

		User user = (User) this.getSession().createQuery(hql)
				.setString(0, t.getUserName()).setString(1, t.getUserPwd())
				.uniqueResult();

		if (user == null)
			return user;

		Set<Role> roles = user.getRoles();
		Hibernate.initialize(roles);

		for (Role role : roles) {
			Set<Authority> authorities = role.getAuthorities();
			Hibernate.initialize(authorities);
			for (Authority authority : authorities) {
				Set<Resource> resources = authority.getResources();
				Hibernate.initialize(resources);
			}
		}

		return user;
	}

	@Override
	public void deletePayRole(Integer userId, Integer roleId) {
		String sql = "delete from t_user_role_inner where user_id=? and role_id=?";
		this.getSession().createSQLQuery(sql).setInteger(0, userId)
				.setInteger(1, roleId).executeUpdate();
	}

}
