package cn.farquer.survey.admin.component.service.interfaces;

import java.util.List;

import cn.farquer.survey.admin.entity.Resource;
import cn.farquer.survey.base.interfaces.BaseService;
import cn.farquer.survey.model.Page;


public interface ResourceService extends BaseService<Resource>{

	Integer getMaxResPos();

	Long getCurrentMaxResCode(Integer maxResPos);

	boolean getResourceByActionName(String actionName);

	Page<Resource> getPage(String pageNoStr, int i);

	void batchDelete(List<Integer> resIdList);

	List<Resource> getAllResList();

	Resource getResourceByTargetName(String actionName);

}
