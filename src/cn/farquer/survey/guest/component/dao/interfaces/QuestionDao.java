package cn.farquer.survey.guest.component.dao.interfaces;

import java.util.List;
import java.util.Set;

import cn.farquer.survey.base.interfaces.BaseDao;
import cn.farquer.survey.guest.entity.Question;

/**
 * 问题的DAO层接口声明类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:57:51
 */
public interface QuestionDao extends BaseDao<Question> {

	/**
	 * 使用批处理存储question对象
	 * 
	 * @param questions
	 */
	void batchSave(Set<Question> questions);

	/**
	 * 根据调查ID获取所有Question对象
	 * 
	 * @param surveyId
	 * @return
	 */
	List<Question> getQuestionListBySurveyId(Integer surveyId);

}
