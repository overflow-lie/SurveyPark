package cn.farquer.survey.guest.entity;

import java.util.Date;
import java.util.Set;

/**
 * 调查实体类，封装了调查相关的属性和方法
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:53:05
 */
public class Survey {

	private Integer surveyId; // 调查的ID
	private String title; // 调查的标题
	private String logoPath = "/resources_static/logo.gif"; // 调查的LOGO图片，存在默认值
	private User user; // 调查所属的用户
	private boolean completed; // 调查的状态(完成/未完成)
	private Set<Bag> bagSet; // 调查包含的包裹
	private Date completedTime; // 完成调查提交的时间

	private Integer minOrder; // 本调查关联的包裹的排序序号最小值，虚拟数据，在hbm.xml中通过计算得出
	private Integer maxOrder; // 本调查关联的包裹的排序序号最大值，虚拟数据，在hbm.xml中通过计算得出

	public Survey() {
	}
	
	

	public Survey(Integer surveyId, String title, String logoPath, User user,
			boolean completed, Date completedTime) {
		super();
		this.surveyId = surveyId;
		this.title = title;
		this.logoPath = logoPath;
		this.user = user;
		this.completed = completed;
		this.completedTime = completedTime;
	}



	public Survey(Integer surveyId, String title, String logoPath,
			Date completedTime) {
		super();
		this.surveyId = surveyId;
		this.title = title;
		this.logoPath = logoPath;
		this.completedTime = completedTime;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Set<Bag> getBagSet() {
		return bagSet;
	}

	public void setBagSet(Set<Bag> bagSet) {
		this.bagSet = bagSet;
	}

	public Date getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
	}

	public Integer getMinOrder() {
		return minOrder;
	}

	public void setMinOrder(Integer minOrder) {
		this.minOrder = minOrder;
	}

	public Integer getMaxOrder() {
		return maxOrder;
	}

	public void setMaxOrder(Integer maxOrder) {
		this.maxOrder = maxOrder;
	}

}
