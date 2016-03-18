package cn.farquer.survey.admin.component.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.farquer.survey.admin.entity.Role;
import cn.farquer.survey.base.interfaces.BaseService;
import cn.farquer.survey.model.Page;


@Service
public interface RoleService extends BaseService<Role>{

	Page<Role> getPage(String pageNoStr, int pageSize);

	void batchDelete(List<Integer> roleIdList);

	List<Integer> getCurrentAuthIdList(Integer roleId);

	void authMng(Integer roleId, List<Integer> authIdList);

	List<Role> getAllRoleList();

	Role getRoleByName(String roleName);

}
