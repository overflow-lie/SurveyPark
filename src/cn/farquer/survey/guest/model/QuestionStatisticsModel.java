package cn.farquer.survey.guest.model;

import java.util.List;

/**
 * 问题统计模型类，封装了问题标题，总参与人数和选项统计模型
 * 
 * @author farquer
 * 
 *         2016年2月23日下午7:57:04
 */
public class QuestionStatisticsModel {

	private String questionName; // 问题的标题
	private int totalCount; // 问题的参与人数
	private List<OptionStatisticsModel> osmList; // 选项统计模型

	public QuestionStatisticsModel() {

	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<OptionStatisticsModel> getOsmList() {
		return osmList;
	}

	public void setOsmList(List<OptionStatisticsModel> osmList) {
		this.osmList = osmList;
	}

	@Override
	public String toString() {
		return "QuestionStatisticsModel [questionName=" + questionName
				+ ", totalCount=" + totalCount + ", osmList=" + osmList + "]";
	}

}
