package cn.farquer.survey.admin.component.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.farquer.survey.admin.component.service.interfaces.AuthorityService;
import cn.farquer.survey.admin.component.service.interfaces.ResourceService;
import cn.farquer.survey.admin.entity.Authority;
import cn.farquer.survey.admin.entity.Resource;
import cn.farquer.survey.base.impl.BaseAction;
import cn.farquer.survey.model.Page;
import cn.farquer.survey.utils.GlobalNames;
import cn.farquer.survey.utils.ValidateUtils;

/**
 * 权限管理的Action
 * 
 * @author farquer
 * 
 *         2016年3月1日下午6:43:48
 */
@Controller
@Scope("prototype")
public class AuthorityAction extends BaseAction<Authority> {

	// ***************************成员变量区*****************************

	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private ResourceService resourceService;

	private String pageNoStr;
	private List<Integer> authIdList;
	private Integer authorityId;

	private List<Integer> resIdList;

	// *************************Action方法区***************************

	/**
	 * 资源管理
	 * 
	 * @return
	 */
	public String resMng() {

		authorityService.resMng(authorityId, resIdList);

		return "toAllAuthorityAction";
	}

	/**
	 * 去资源管理的页面
	 * 
	 * @return 
	 */
	public String toResMngPage() {

		List<Resource> allResList = resourceService.getAllResList();
		requestMap.put(GlobalNames.ALL_RES_LIST, allResList);

		List<Integer> currentResIdList = authorityService
				.getCurrentResIdList(authorityId);
		requestMap.put(GlobalNames.CURRENT_RES_ID_LIST, currentResIdList);

		return "toResMngPage";
	}

	/**
	 * 保存一个权限
	 * 
	 * @return
	 */
	public String save() {

		authorityService.saveEntity(t);

		return "toAllAuthorityAction";
	}

	/**
	 * 去创建权限的页面
	 * 
	 * @return
	 */
	public String toCreatePage() {

		return "toCreatePage";
	}

	/**
	 * 针对于AJAX技术的massage方法
	 * 
	 * @return
	 */
	public Map<String, String> getMessage() {

		Map<String, String> map = new HashMap<>();
		map.put("message", "操作成功！");

		return map;
	}

	/**
	 * 更新权限的前置方法，把权限对象放入栈顶
	 */
	public void prepareUpdate() {
		this.t = authorityService.getEntityById(authorityId);
	}

	/**
	 * 更新权限
	 * 
	 * @return
	 */
	public String update() {

		authorityService.updateEntity(t);

		return "updateSuccess";
	}

	/**
	 * 批量删除权限
	 * 
	 * @return
	 */
	public String batchDelete() {

		
		if(!ValidateUtils.collectionValidate(authIdList)){
			requestMap.put(GlobalNames.GLOBAL_MSG, "请选择需要删除的数据");
			return "globalErrMsg";
		}
		authorityService.batchDelete(authIdList);

		return "toAllAuthorityAction";
	}

	/**
	 * 显示所有权限
	 * 
	 * @return
	 */
	public String showAuthorities() {

		Page<Authority> page = authorityService.getPage(pageNoStr, 40);
		requestMap.put(GlobalNames.PAGE, page);

		return "toAllAuthorityPage";
	}

	// ***********************Getter、Setter区*************************

	public void setAuthIdList(List<Integer> authIdList) {
		this.authIdList = authIdList;
	}

	public List<Integer> getAuthIdList() {
		return authIdList;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public List<Integer> getResIdList() {
		return resIdList;
	}

	public void setResIdList(List<Integer> resIdList) {
		this.resIdList = resIdList;
	}
}
