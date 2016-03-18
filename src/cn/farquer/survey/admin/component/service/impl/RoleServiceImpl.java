package cn.farquer.survey.admin.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.farquer.survey.admin.component.dao.interfaces.RoleDao;
import cn.farquer.survey.admin.component.service.interfaces.RoleService;
import cn.farquer.survey.admin.entity.Role;
import cn.farquer.survey.base.impl.BaseServiceImpl;
import cn.farquer.survey.model.Page;
import cn.farquer.survey.utils.ValidateUtils;


@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public Page<Role> getPage(String pageNoStr, int pageSize) {
		
		int totalRecordNo = roleDao.getTotalCount();
		
		Page<Role> page = new Page<>(pageNoStr, totalRecordNo, pageSize);
		
		List<Role> list = roleDao.getLimitedList(page.getPageNo(), pageSize);
		
		page.setList(list);
		
		return page;
	}

	@Override
	public void batchDelete(List<Integer> roleIdList) {
		roleDao.batchDelete(roleIdList);
	}

	@Override
	public List<Integer> getCurrentAuthIdList(Integer roleId) {
		return roleDao.getCurrentAuthIdList(roleId);
	}

	@Override
	public void authMng(Integer roleId, List<Integer> authIdList) {
		roleDao.deleteOldAuth(roleId);
		
		if (!ValidateUtils.collectionValidate(authIdList)) {
			return;
		}
		
		roleDao.saveNewAuth(roleId, authIdList);
	}

	@Override
	public List<Role> getAllRoleList() {
		return roleDao.getAllRoleList();
	}

	@Override
	public Role getRoleByName(String roleName) {
		return roleDao.getRoleByName(roleName);
	}

}
