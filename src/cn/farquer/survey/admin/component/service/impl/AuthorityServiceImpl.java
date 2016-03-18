package cn.farquer.survey.admin.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.farquer.survey.admin.component.dao.interfaces.AuthorityDao;
import cn.farquer.survey.admin.component.service.interfaces.AuthorityService;
import cn.farquer.survey.admin.entity.Authority;
import cn.farquer.survey.base.impl.BaseServiceImpl;
import cn.farquer.survey.model.Page;
import cn.farquer.survey.utils.ValidateUtils;

@Service
public class AuthorityServiceImpl extends BaseServiceImpl<Authority> implements
		AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;

	@Override
	public void batchDelete(List<Integer> authIdList) {
		authorityDao.batchDelete(authIdList);
	}

	@Override
	public Page<Authority> getPage(String pageNoStr, int pageSize) {

		int totalRecordNo = authorityDao.getTotalCount();

		Page<Authority> page = new Page<>(pageNoStr, totalRecordNo, pageSize);

		List<Authority> list = authorityDao.getLimitedList(page.getPageNo(),
				pageSize);

		page.setList(list);

		return page;
	}

	@Override
	public List<Integer> getCurrentResIdList(Integer authorityId) {

		return authorityDao.getCurrentResIdList(authorityId);
	}

	@Override
	public void resMng(Integer authorityId, List<Integer> resIdList) {

		// 1.删除旧的资源id
		authorityDao.deleteOldRes(authorityId);

		if (!ValidateUtils.collectionValidate(resIdList)) {
			return;
		}

		// 2.保存新的资源id
		authorityDao.saveNewRes(authorityId, resIdList);

	}

	@Override
	public List<Authority> getAllAuthList() {
		return authorityDao.getAllAuthList();
	}

}
