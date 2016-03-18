package cn.farquer.survey.admin.entity;

import java.util.Set;

/**
 * @author farquer
 * 
 *         2016年2月26日上午9:03:24
 */
public class Admin {

	private Integer adminId; // 管理员ID
	private String adminName; // 管理员用户名
	private String adminPwd; // 管理员密码
	private Set<Role> roles; // 管理员所属的角色
	private String resCode; // 权限码，保存方法为数组转换的字符串

	public Admin() {
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

}
