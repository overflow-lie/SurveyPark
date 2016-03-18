package cn.farquer.survey.admin.component.dao.interfaces;

import java.util.List;

import cn.farquer.survey.admin.entity.Resource;
import cn.farquer.survey.base.interfaces.BaseDao;


public interface ResourceDao extends BaseDao<Resource>{

	Integer getMaxResPos();

	Long getCurrentMaxResCode(Integer maxResPos);

	boolean getResourceByActionName(String actionName);

	int getTotalCount();

	List<Resource> getLimitedList(Integer pageNo, int pageSize);

	void batchDelete(List<Integer> resIdList);

	List<Resource> getAllResList();

	Resource getResourceByTargetName(String actionName);

}
