package cn.farquer.survey.admin.component.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.farquer.survey.admin.component.service.interfaces.AuthorityService;
import cn.farquer.survey.admin.component.service.interfaces.RoleService;
import cn.farquer.survey.admin.entity.Authority;
import cn.farquer.survey.admin.entity.Role;
import cn.farquer.survey.base.impl.BaseAction;
import cn.farquer.survey.model.Page;
import cn.farquer.survey.utils.GlobalNames;
import cn.farquer.survey.utils.ValidateUtils;

/**
 * 角色相关操作的Action
 * 
 * @author farquer
 * 
 *         2016年3月1日下午7:08:37
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	// ***************************成员变量区*****************************

	private static final long serialVersionUID = 1L;

	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthorityService authorityService;

	private String pageNoStr;
	private List<Integer> roleIdList;
	private Integer roleId;
	private List<Integer> authIdList;

	// *************************Action方法区***************************

	/**
	 * 权限管理
	 * 
	 * @return
	 */
	public String authMng() {

		roleService.authMng(roleId, authIdList);

		return "toShowRolesAction";
	}

	/**
	 * 去权限管理的页面
	 * 
	 * @return
	 */
	public String toAuthMngPage() {

		List<Authority> authList = authorityService.getAllAuthList();
		requestMap.put(GlobalNames.ALL_AUTH_LIST, authList);

		List<Integer> currentAuthList = roleService
				.getCurrentAuthIdList(roleId);
		requestMap.put(GlobalNames.CURRENT_AUTH_ID_LIST, currentAuthList);

		return "toAuthMngPage";
	}

	/**
	 * 针对于AJAX的massage方法
	 * 
	 * @return
	 */
	public Map<String, String> getMessage() {

		Map<String, String> map = new HashMap<>();
		map.put("message", "操作成功！");

		return map;
	}

	/**
	 * 更新操作的前置方法，将对象放入栈顶用于回显
	 */
	public void prepareUpdate() {
		this.t = roleService.getEntityById(roleId);
	}

	/**
	 * 更新操作，更新角色名称
	 * 
	 * @return
	 */
	public String update() {

		roleService.updateEntity(t);

		return "updateSuccess";
	}

	/**
	 * 显示全部角色
	 * 
	 * @return
	 */
	public String showRoles() {

		Page<Role> page = roleService.getPage(pageNoStr, 10);
		requestMap.put(GlobalNames.PAGE, page);

		return "toShowRolesPage";
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String batchDelete() {
		
		if(!ValidateUtils.collectionValidate(roleIdList)){
			requestMap.put(GlobalNames.GLOBAL_MSG, "请选择需要删除的数据");
			return "globalErrMsg";
		}

		roleService.batchDelete(roleIdList);

		return "toShowRolesAction";
	}

	/**
	 * 保存角色
	 * 
	 * @return
	 */
	public String save() {

		roleService.saveEntity(t);

		return "toShowRolesAction";
	}

	// ***********************Getter、Setter区*************************

	public String toCreatePage() {
		return "toCreatePage";
	}

	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<Integer> getAuthIdList() {
		return authIdList;
	}

	public void setAuthIdList(List<Integer> authIdList) {
		this.authIdList = authIdList;
	}
}
