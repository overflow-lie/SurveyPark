package cn.farquer.survey.admin.component.service.interfaces;

import java.util.List;

import cn.farquer.survey.admin.entity.Authority;
import cn.farquer.survey.base.interfaces.BaseService;
import cn.farquer.survey.model.Page;

public interface AuthorityService extends BaseService<Authority> {

	void batchDelete(List<Integer> authIdList);

	Page<Authority> getPage(String pageNoStr, int i);

	List<Integer> getCurrentResIdList(Integer authorityId);

	void resMng(Integer authorityId, List<Integer> resIdList);

	List<Authority> getAllAuthList();

}
