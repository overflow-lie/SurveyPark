package cn.farquer.survey.admin.component.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.farquer.survey.admin.component.service.interfaces.AdminService;
import cn.farquer.survey.admin.component.service.interfaces.RoleService;
import cn.farquer.survey.admin.entity.Admin;
import cn.farquer.survey.admin.entity.Role;
import cn.farquer.survey.base.impl.BaseAction;
import cn.farquer.survey.model.Page;
import cn.farquer.survey.utils.GlobalNames;

/**
 * 管理员基本操作的Action
 * 
 * @author farquer
 * 
 *         2016年3月1日下午6:40:02
 */
@Controller
@Scope("prototype")
public class AdminAction extends BaseAction<Admin> {

	// ***************************成员变量区*****************************

	private static final long serialVersionUID = 1L;

	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;

	private String pageNoStr;
	private List<Integer> roleIdList;
	private Integer adminId;

	// *************************Action方法区***************************
	
	/**
	 * 使用AJAX技术的massage方法
	 * 
	 * @return
	 */
	public Map<String, String> getMessage() {

		Map<String, String> map = new HashMap<>();
		map.put("message", "统一计算权限码成功！");

		return map;
	}

	/**
	 * 管理员注销登陆
	 * 
	 * @return
	 */
	public String logout() {
		this.sessionMap.remove(GlobalNames.LOGIN_ADMIN);
		return "toAdminLoginPage";
	}

	/**
	 * 统一计算权限码
	 * 
	 * @return
	 */
	public String calculationCode() {

		adminService.calculationCode();

		return "toCalculationCodeSuccessPage";
	}

	/**
	 * 角色管理
	 * 
	 * @return
	 */
	public String roleMng() {

		adminService.roleMng(adminId, roleIdList);

		return "toAdminListAction";
	}

	/**
	 * 去角色管理页面
	 * 
	 * @return
	 */
	public String toRoleMngPage() {

		List<Integer> currentRoleIdList = adminService
				.getCurrentRoleIdList(adminId);
		requestMap.put(GlobalNames.CURRENT_ROLE_ID_LIST, currentRoleIdList);

		List<Role> allRoleList = roleService.getAllRoleList();
		requestMap.put(GlobalNames.ALL_ROLE_LIST, allRoleList);

		return "toRoleMngPage";
	}

	/**
	 * 显示所有管理员
	 * 
	 * @return
	 */
	public String showAdmins() {

		Page<Admin> page = adminService.getPage(pageNoStr, 10);
		requestMap.put(GlobalNames.PAGE, page);

		return "toAdminsPage";
	}

	/**
	 * 生成管理员账户
	 * 
	 * @return
	 */
	public String generateAdmin() {

		List<Admin> adminList = adminService.generateAdminList();
		requestMap.put(GlobalNames.NEW_ADMIN_LIST, adminList);

		return "toNewAdminsPage";
	}

	/**
	 * 管理员登陆
	 * 
	 * @return
	 */
	public String login() {

		// 将根据用户名、密码查询得到的Admin对象返回，将来会作为判断是否具备权限的依据
		Admin admin = adminService.login(t);

		if (admin != null) {
			this.sessionMap.put(GlobalNames.LOGIN_ADMIN, admin);
			return "toAdminMainAction";
		} else {
			addActionError("用户名密码不正确！");
			return "toAdminLoginPage";
		}

	}

	/**
	 * 去管理员主页面
	 * 
	 * @return
	 */
	public String toAdminMain() {

		// 判断当前会话中是否有管理员登录
		Admin admin = (Admin) this.sessionMap.get(GlobalNames.LOGIN_ADMIN);

		if (admin == null) {

			addActionError("请登录后再操作！");
			return "toAdminLoginPage";

		} else {

			return "toAdminMainPage";
		}

	}
	
	public String toAdminMainPage(){
		return "toAdminMainPage";
	}

	// ***********************Getter、Setter区*************************

	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}

	public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

}
