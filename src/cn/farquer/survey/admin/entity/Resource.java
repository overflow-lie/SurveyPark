package cn.farquer.survey.admin.entity;

/**
 * 资源实体类
 * 
 * @author farquer
 * 
 *         2016年2月27日上午11:36:42
 */
public class Resource {

	private Integer resourceId; // 资源的ID

	private String actionName; // //目标资源的URL地址，在Struts2环境下就是目标Action的名字
	private String resourceName; // 汉译

	private int resPos; // 权限位
	private long resCode; // 权限码

	public Resource() {
	}

	public Resource(String actionName, String resourceName, int resPos,
			long resCode) {
		super();
		this.actionName = actionName;
		this.resourceName = resourceName;
		this.resPos = resPos;
		this.resCode = resCode;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public int getResPos() {
		return resPos;
	}

	public void setResPos(int resPos) {
		this.resPos = resPos;
	}

	public long getResCode() {
		return resCode;
	}

	public void setResCode(long resCode) {
		this.resCode = resCode;
	}

}
