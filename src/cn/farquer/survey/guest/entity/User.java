package cn.farquer.survey.guest.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import cn.farquer.survey.admin.entity.Role;

/**
 * 用户实体类，封装了用户相关的属性和方法
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:53:35
 */
public class User {

	private Integer userId; // 用户ID
	private String userName; // 用户名
	private String userPwd; // 密码
	private String nickName; // 昵称
	private int balance; // 余额
	private boolean payStatus; // 付费状态
	private long endTime; // 会员到期时间
	private String email; // 邮箱
	private Set<Role> roles; // 用户所属的角色
	private String resCode; // 用户的权限码

	public User() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public boolean isPayStatus() {
		return payStatus;
	}

	public void setPayStatus(boolean payStatus) {
		this.payStatus = payStatus;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// 返回格式化后的截止日期
	public String getEndDate() {

		Date date = new Date(endTime);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分ss秒");

		return format.format(date);
	}

	// 提供一个方法用于在页面上显示当前付费用户会员剩余天数
	public int getLeftDays() {

		if (!payStatus)
			return 0;

		// 1.获取当前系统时间的时间戳
		long currentTime = new Date().getTime();

		// 2.用当前截止时间减去currentTime
		long leftMillionSeconds = endTime - currentTime;

		// 3.计算天数
		int days = (int) (leftMillionSeconds / 1000 / 60 / 60 / 24);

		return days;
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
