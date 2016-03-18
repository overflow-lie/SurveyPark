package cn.farquer.survey.admin.component.dao.interfaces;

import java.util.List;

import cn.farquer.survey.admin.entity.Role;
import cn.farquer.survey.base.interfaces.BaseDao;


public interface RoleDao extends BaseDao<Role>{

	int getTotalCount();

	List<Role> getLimitedList(Integer pageNo, int pageSize);

	void batchDelete(List<Integer> roleIdList);

	List<Integer> getCurrentAuthIdList(Integer roleId);

	void deleteOldAuth(Integer roleId);

	void saveNewAuth(Integer roleId, List<Integer> authIdList);

	List<Role> getAllRoleList();

	Role getRoleByName(String roleName);

}
