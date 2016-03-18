package cn.farquer.survey.guest.entity;

import java.io.Serializable;

import cn.farquer.survey.utils.DataProcessUtils;

/**
 * 问题实体类，封装了问题先关的属性和方法
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:52:49
 */
public class Question implements Serializable {

	private static final long serialVersionUID = -8284072683789315568L;
	private Integer questionId; // 问题的ID
	private Bag bag; // 问题所属的包裹
	private String questionName; // 问题的标题
	private int questionType; // 问题的类型，类型详情在DataProcessUtils.QUESTION_TYPE_MAP中定义
	private boolean br; // 是否换行
	private boolean hasOther; // 是否存在其他项
	private int otherType; // 其他项形式 0：和主题型一致 1：文本框

	// =================需要特殊处理的属性=================

	private String options; // 单选、多选、下拉列表的选项
	private String matrixRowTitles; // 矩阵式问题的行标题组
	private String matrixColTitles; // 矩阵式问题的列标题组
	private String matrixOptions; // 矩阵式下拉列表问题的选项

	// ================常规属性的Setter和Getter=================

	public Question() {
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public boolean isBr() {
		return br;
	}

	public void setBr(boolean br) {
		this.br = br;
	}

	public boolean isHasOther() {
		return hasOther;
	}

	public void setHasOther(boolean hasOther) {
		this.hasOther = hasOther;
	}

	public int getOtherType() {
		return otherType;
	}

	public void setOtherType(int otherType) {
		this.otherType = otherType;
	}

	public void setBag(Bag bag) {
		this.bag = bag;
	}

	public Bag getBag() {
		return bag;
	}

	// =====================需要特殊处理的属性========================

	// ---------------------options------------------------

	// 1.常规的getXxx()方法
	public String getOptions() {
		return options;
	}

	// 2.用于保存数据的setXxx()方法
	public void setOptions(String options) {
		this.options = DataProcessUtils.processStrForSave(options);
	}

	// 3.用于回显数据的getXxxForShow()方法
	public String getOptionsForShow() {
		return DataProcessUtils.processStrForShow(options);
	}

	// 4.用于展示的getXxxArray()方法
	public String[] getOptionsArray() {
		return DataProcessUtils.convertStrToArr(options);
	}

	// ---------------------matrixRowTitles------------------------

	// 1.常规的getXxx()方法
	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}

	// 2.用于保存数据的setXxx()方法
	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = DataProcessUtils
				.processStrForSave(matrixRowTitles);
	}

	// 3.用于回显数据的getXxxForShow()方法
	public String getMatrixRowTitlesForShow() {
		return DataProcessUtils.processStrForShow(matrixRowTitles);
	}

	// 4.用于展示的getXxxArray()方法
	public String[] getMatrixRowTitlesArray() {
		return DataProcessUtils.convertStrToArr(matrixRowTitles);
	}

	// ---------------------matrixColTitles------------------------
	// 1.常规的getXxx()方法
	public String getMatrixColTitles() {
		return matrixColTitles;
	}

	// 2.用于保存数据的setXxx()方法
	public void setMatrixColTitles(String matrixColTitles) {
		this.matrixColTitles = DataProcessUtils
				.processStrForSave(matrixColTitles);
	}

	// 3.用于回显数据的getXxxForShow()方法
	public String getMatrixColTitlesForShow() {
		return DataProcessUtils.processStrForShow(matrixColTitles);
	}

	// 4.用于展示的getXxxArray()方法
	public String[] getMatrixColTitlesArray() {
		return DataProcessUtils.convertStrToArr(matrixColTitles);
	}

	// ---------------------matrixOptions------------------------
	// 1.常规的getXxx()方法
	public String getMatrixOptions() {
		return matrixOptions;
	}

	// 2.用于保存数据的setXxx()方法
	public void setMatrixOptions(String matrixOptions) {
		this.matrixOptions = DataProcessUtils.processStrForSave(matrixOptions);
	}

	// 3.用于回显数据的getXxxForShow()方法
	public String getMatrixOptionsForShow() {
		return DataProcessUtils.processStrForShow(matrixOptions);
	}

	// 4.用于展示的getXxxArray()方法
	public String[] getMatrixOptionsArray() {
		return DataProcessUtils.convertStrToArr(matrixOptions);
	}

}
