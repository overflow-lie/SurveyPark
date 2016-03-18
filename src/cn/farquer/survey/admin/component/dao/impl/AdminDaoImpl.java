package cn.farquer.survey.admin.component.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.farquer.survey.admin.component.dao.interfaces.AdminDao;
import cn.farquer.survey.admin.entity.Admin;
import cn.farquer.survey.base.impl.BaseDaoImpl;

@SuppressWarnings("unchecked")
@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{

	@Override
	public void batchSaveAdminList(List<Admin> adminList) {
		String sql = "insert into t_admin(admin_name,admin_pwd) values(?,?)";
		List<Object[]> params = new ArrayList<>();
		for (Admin admin : adminList) {
			Object [] param = new Object[2];
			param[0] = admin.getAdminName();
			param[1] = admin.getAdminPwd();
			params.add(param);
		}
		
		doBatchWork(sql, params);
	}

	@Override
	public int getTotalCount() {
		
		String sql = "select count(*) from t_admin";
		
		BigInteger count = (BigInteger)this.getSession().createSQLQuery(sql).uniqueResult();
		
		return count.intValue();
	}

	@Override
	public List<Admin> getLimitedList(Integer pageNo, int i) {
		
		String hql = "From Admin";
		
		return this.getSession().createQuery(hql).setFirstResult((pageNo-1)*i).setMaxResults(i).list();
	}

	@Override
	public List<Integer> getCurrentRoleIdList(Integer adminId) {
		String sql = "select role_id from t_admin_role_inner where admin_id=?";
		return this.getSession().createSQLQuery(sql).setInteger(0, adminId).list();
	}

	@Override
	public void deleteOldRole(Integer adminId) {
		String sql = "delete from t_admin_role_inner where admin_id=?";
		this.getSession().createSQLQuery(sql).setInteger(0, adminId).executeUpdate();
	}

	@Override
	public void saveNewRole(Integer adminId, List<Integer> roleIdList) {
		String sql = "insert into t_admin_role_inner(admin_id,role_id) values(?,?)";
		List<Object[]> params = new ArrayList<>();
		for (Integer roleId : roleIdList) {
			Object[] param = new Object[2];
			param[0] = adminId;
			param[1] = roleId;
			params.add(param);
		}
		doBatchWork(sql, params);
	}

	@Override
	public Admin checkAdminNameAndPwd(String adminName, String adminPwd) {
		
		String hql = "From Admin a where a.adminName=? and a.adminPwd=?";
		
		return (Admin) this.getSession().createQuery(hql).setString(0, adminName).setString(1, adminPwd).uniqueResult();
	}

}
