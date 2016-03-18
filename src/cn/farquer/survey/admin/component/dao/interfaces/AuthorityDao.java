package cn.farquer.survey.admin.component.dao.interfaces;

import java.util.List;

import cn.farquer.survey.admin.entity.Authority;
import cn.farquer.survey.base.interfaces.BaseDao;


public interface AuthorityDao extends BaseDao<Authority>{

	void batchDelete(List<Integer> authIdList);

	int getTotalCount();

	List<Authority> getLimitedList(Integer pageNo, int pageSize);

	List<Integer> getCurrentResIdList(Integer authorityId);

	void deleteOldRes(Integer authorityId);

	void saveNewRes(Integer authorityId, List<Integer> resIdList);

	List<Authority> getAllAuthList();

}
