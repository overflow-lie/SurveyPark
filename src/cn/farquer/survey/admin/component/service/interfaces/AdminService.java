package cn.farquer.survey.admin.component.service.interfaces;

import java.util.List;

import cn.farquer.survey.admin.entity.Admin;
import cn.farquer.survey.base.interfaces.BaseService;
import cn.farquer.survey.model.Page;

/**
 * 
 * @author farquer
 * 
 *         2016年2月26日上午9:34:37
 */
public interface AdminService extends BaseService<Admin> {

	/**
	 * 管理员登陆方法，返回管理员登录对象
	 * 
	 * @param t
	 * @return
	 */
	Admin login(Admin t);

	/**
	 * 生成管理员帐号列表
	 * 
	 * @return
	 */
	List<Admin> generateAdminList();

	/**
	 * 分页获取管理员帐号
	 * 
	 * @param pageNoStr
	 * @param i
	 * @return
	 */
	Page<Admin> getPage(String pageNoStr, int i);

	/**
	 * 获取当前管理员用户所关联的角色集合
	 * 
	 * @param adminId
	 * @return
	 */
	List<Integer> getCurrentRoleIdList(Integer adminId);

	/**
	 * 角色管理
	 * 
	 * @param adminId
	 * @param roleIdList
	 */
	void roleMng(Integer adminId, List<Integer> roleIdList);

	/**
	 * 计算权限码
	 */
	void calculationCode();

}
