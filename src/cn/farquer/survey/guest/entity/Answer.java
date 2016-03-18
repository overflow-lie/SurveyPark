package cn.farquer.survey.guest.entity;

import java.util.Date;

/**
 * 答案的实体类
 * 
 * @author farquer
 * 
 *         2016年2月23日上午11:04:51
 */
public class Answer {

	private Integer answerId; // 答案的ID
	private Integer questionId; // 所答问题的ID
	private Integer surveyId; // 所属的调查的ID

	private Date answerTime; // 完成答案时间
	private String uuid; // 唯一标识，用来区分是否是同一次调查提交

	private String mainAnswers; // 保存答案数据的主要属性，包括“和主题型一致”的其他项数据

	private String otherAnswers; // 保存文本框形式的其他项

	public Answer() {
	}

	public Answer(Integer answerId, Integer questionId, Integer surveyId,
			Date answerTime, String uuid, String mainAnswers,
			String otherAnswers) {
		super();
		this.answerId = answerId;
		this.questionId = questionId;
		this.surveyId = surveyId;
		this.answerTime = answerTime;
		this.uuid = uuid;
		this.mainAnswers = mainAnswers;
		this.otherAnswers = otherAnswers;
	}

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMainAnswers() {
		return mainAnswers;
	}

	public void setMainAnswers(String mainAnswers) {
		this.mainAnswers = mainAnswers;
	}

	public String getOtherAnswers() {
		return otherAnswers;
	}

	public void setOtherAnswers(String otherAnswers) {
		this.otherAnswers = otherAnswers;
	}

}
