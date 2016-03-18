package cn.farquer.survey.admin.component.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.farquer.survey.admin.component.dao.interfaces.RoleDao;
import cn.farquer.survey.admin.entity.Role;
import cn.farquer.survey.base.impl.BaseDaoImpl;

@SuppressWarnings("unchecked")
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

	@Override
	public int getTotalCount() {
		
		String sql = "select count(*) from t_role";
		
		BigInteger count = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		
		return count.intValue();
	}

	@Override
	public List<Role> getLimitedList(Integer pageNo, int pageSize) {
		
		String hql = "From Role";
		
		return this.getSession().createQuery(hql).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public void batchDelete(List<Integer> roleIdList) {
		
		String sql = "delete from t_role where role_id=?";
		
		List<Object[]> params = new ArrayList<>();
		for(int i = 0; i < roleIdList.size(); i++) {
			Object[] param = new Object[1];
			param[0] = roleIdList.get(i);
			params.add(param);
		}
		
		doBatchWork(sql, params);
	}

	@Override
	public List<Integer> getCurrentAuthIdList(Integer roleId) {
		
		String sql = "select auth_id from t_ROLE_AUTH_INNER where role_id=?";
		
		return this.getSession().createSQLQuery(sql).setInteger(0, roleId).list();
	}

	@Override
	public void deleteOldAuth(Integer roleId) {
		String sql = "delete from t_role_auth_inner where role_id=?";
		this.getSession().createSQLQuery(sql).setInteger(0, roleId).executeUpdate();
	}

	@Override
	public void saveNewAuth(Integer roleId, List<Integer> authIdList) {
		
		String sql = "insert into t_role_auth_inner(role_id,auth_id) values(?,?)";
		
		List<Object[]> params = new ArrayList<>();
		for (Integer authId : authIdList) {
			Object[] param = new Object[2];
			param[0] = roleId;
			param[1] = authId;
			params.add(param);
		}
		
		doBatchWork(sql, params);
		
	}

	@Override
	public List<Role> getAllRoleList() {
		
		String hql = "From Role";
		
		return this.getSession().createQuery(hql).list();
	}

	@Override
	public Role getRoleByName(String roleName) {
		String hql = "From Role r where r.roleName=?";
		return (Role) this.getSession().createQuery(hql).setString(0, roleName).uniqueResult();
	}

}
