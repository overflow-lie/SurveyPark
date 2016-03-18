package cn.farquer.survey.admin.component.dao.interfaces;

import java.util.List;

import cn.farquer.survey.admin.entity.Admin;
import cn.farquer.survey.base.interfaces.BaseDao;


public interface AdminDao extends BaseDao<Admin>{

	void batchSaveAdminList(List<Admin> adminList);

	int getTotalCount();

	List<Admin> getLimitedList(Integer pageNo, int i);

	List<Integer> getCurrentRoleIdList(Integer adminId);

	void deleteOldRole(Integer adminId);

	void saveNewRole(Integer adminId, List<Integer> roleIdList);

	Admin checkAdminNameAndPwd(String adminName, String adminPwd);

}
