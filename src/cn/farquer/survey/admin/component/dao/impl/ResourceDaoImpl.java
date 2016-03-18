package cn.farquer.survey.admin.component.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.farquer.survey.admin.component.dao.interfaces.ResourceDao;
import cn.farquer.survey.admin.entity.Resource;
import cn.farquer.survey.base.impl.BaseDaoImpl;

@SuppressWarnings("unchecked")
@Repository
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao {

	@Override
	public Integer getMaxResPos() {
		
		String sql = "SELECT MAX(res_pos) FROM t_resource";
		
		Integer maxResPos = (Integer) this.getSession().createSQLQuery(sql).uniqueResult();
		
		return maxResPos;
	}

	@Override
	public Long getCurrentMaxResCode(Integer maxResPos) {
		
		String sql = "SELECT MAX(res_code) FROM t_resource WHERE res_pos=?";
		
		BigInteger maxCode = (BigInteger) this.getSession().createSQLQuery(sql).setInteger(0, maxResPos).uniqueResult();
		
		return maxCode.longValue();
	}

	@Override
	public boolean getResourceByActionName(String actionName) {
		
		String sql = "SELECT COUNT(*) FROM t_resource WHERE action_name=?";
		
		BigInteger count = (BigInteger) this.getSession().createSQLQuery(sql).setString(0, actionName).uniqueResult();
		
		return count.intValue() > 0;
	}

	@Override
	public int getTotalCount() {
		
		String sql = "select count(*) from t_resource";
		
		BigInteger count = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		
		return count.intValue();
	}

	@Override
	public List<Resource> getLimitedList(Integer pageNo, int pageSize) {
		String hql = "From Resource";
		return this.getSession().createQuery(hql).setFirstResult((pageNo - 1)*pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public void batchDelete(List<Integer> resIdList) {
		
		String sql = "delete from t_resource where resource_id=?";
		
		List<Object[]> params = new ArrayList<>();
		for (int i = 0; i < resIdList.size(); i++) {
			
			Integer id = resIdList.get(i);
			Object[] param = new Object[1];
			param[0] = id;
			params.add(param);
			
		}
		
		doBatchWork(sql, params);
		
	}

	@Override
	public List<Resource> getAllResList() {
		
		String hql = "From Resource r order by r.resourceName";
		
		return this.getSession().createQuery(hql).list();
	}

	@Override
	public Resource getResourceByTargetName(String actionName) {
		
		String hql = "From Resource r where r.actionName=?";
		
		return (Resource) this.getSession().createQuery(hql).setString(0, actionName).uniqueResult();
	}

}
