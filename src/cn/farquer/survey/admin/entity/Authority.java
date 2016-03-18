package cn.farquer.survey.admin.entity;

import java.util.Set;

/**
 * 权限的实体类
 * 
 * @author farquer
 * 
 *         2016年2月27日上午11:46:10
 */
public class Authority {

	private Integer authorityId; // 权限的ID

	String authorityName; // 权限的名称

	private Set<Resource> resources; // 权限包含的资源

	public Authority() {
		super();
	}

	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

}
