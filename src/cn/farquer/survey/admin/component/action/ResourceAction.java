package cn.farquer.survey.admin.component.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.farquer.survey.admin.component.service.interfaces.ResourceService;
import cn.farquer.survey.admin.entity.Resource;
import cn.farquer.survey.base.impl.BaseAction;
import cn.farquer.survey.model.Page;
import cn.farquer.survey.utils.GlobalNames;
import cn.farquer.survey.utils.ValidateUtils;

/**
 * 资源相关操作的Action
 * 
 * @author farquer
 * 
 *         2016年3月1日下午7:06:38
 */
@Controller
@Scope("prototype")
public class ResourceAction extends BaseAction<Resource> {

	// ***************************成员变量区*****************************

	private static final long serialVersionUID = 1L;

	@Autowired
	private ResourceService resourceService;

	private String pageNoStr;
	private List<Integer> resIdList;
	private Integer resourceId;

	// *************************Action方法区***************************

	/**
	 * 使用AJAX技术的massage方法
	 * 
	 * @return
	 */
	public Map<String, String> getMessage() {

		Map<String, String> map = new HashMap<>();
		map.put("message", "操作成功！");

		return map;
	}

	/**
	 * 更新资源名称的前置方法，获取资源放入栈顶用于回显
	 */
	public void prepareUpdate() {
		this.t = resourceService.getEntityById(resourceId);
	}

	/**
	 * 更新资源名称
	 * 
	 * @return
	 */
	public String update() {

		resourceService.updateEntity(t);

		return "updateSuccess";
	}

	/**
	 * 批量删除资源
	 * 
	 * @return
	 */
	public String batchDelete() {
		
		if(!ValidateUtils.collectionValidate(resIdList)){
			requestMap.put(GlobalNames.GLOBAL_MSG, "请选择需要删除的数据");
			return "globalErrMsg";
		}

		resourceService.batchDelete(resIdList);

		return "toAllResourcesAction";
	}

	/**
	 * 显示全部资源
	 * 
	 * @return
	 */
	public String showAllResources() {

		Page<Resource> page = resourceService.getPage(pageNoStr, 10);
		requestMap.put(GlobalNames.PAGE, page);

		return "toAllResourcesPage";
	}

	// ***********************Getter、Setter区*************************

	public void setResIdList(List<Integer> resIdList) {
		this.resIdList = resIdList;
	}

	public List<Integer> getResIdList() {
		return resIdList;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}
}
