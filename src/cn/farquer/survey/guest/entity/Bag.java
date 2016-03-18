package cn.farquer.survey.guest.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * 包裹实体类，封装了包裹相关的属性和方法
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:52:27
 */
public class Bag implements Serializable {

	private static final long serialVersionUID = 761553122710167473L;

	private Integer bagId; // 包裹ID
	private String bagName; // 包裹名称
	private transient Survey survey; // 包裹所在调查,需要设置瞬时修饰，防止复制操作序列化survey对象
	private Set<Question> questions; // 包裹所包含的问题
	
	private int bagOrder; // 包裹排序

	public Bag() {
	}

	public Integer getBagId() {
		return bagId;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}

	public String getBagName() {
		return bagName;
	}

	public void setBagName(String bagName) {
		this.bagName = bagName;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public int getBagOrder() {
		return bagOrder;
	}

	public void setBagOrder(int bagOrder) {
		this.bagOrder = bagOrder;
	}

}
