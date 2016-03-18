package cn.farquer.survey.guest.component.dao.interfaces;

import java.util.List;

import cn.farquer.survey.base.interfaces.BaseDao;
import cn.farquer.survey.guest.entity.Answer;
import cn.farquer.survey.guest.model.OptionStatisticsModel;

/**
 * 答案的持久化层的方法声明接口类
 * 
 * @author farquer
 * 
 *         2016年2月23日上午11:06:59
 */
public interface AnswerDao extends BaseDao<Answer> {

	/**
	 * 批量保存答案列表
	 * 
	 * @param answerList
	 */
	void batchSaveAnswerList(List<Answer> answerList);

	/**
	 * 获取参加调查的人数
	 * 
	 * @param questionId
	 * @return
	 */
	int getTotalEngagedCount(Integer questionId);

	/**
	 * 获取问题统计模型的列表
	 * 
	 * @param questionId
	 * @param optionsArray
	 * @return
	 */
	List<OptionStatisticsModel> getOsmList(Integer questionId,
			String[] optionsArray);

	/**
	 * 获取其他项的数量
	 * 
	 * @param questionId
	 * @return
	 */
	int getOtherCount(Integer questionId);

	/**
	 * 获取文本形式其他项的list集合
	 * 
	 * @param questionId
	 * @return
	 */
	List<String> getOtherTextList(Integer questionId);

	/**
	 * 获取简答题的答案列表
	 * 
	 * @param questionId
	 * @return
	 */
	List<String> getTextList(Integer questionId);

	/**
	 * 获取参与的选项的总数
	 * 
	 * @param questionId
	 * @param currentValue
	 * @return
	 */
	int getOptionEnagedCount(Integer questionId, String currentValue);

	/**
	 * 根据调查ID获取所有答案对象
	 * 
	 * @param surveyId
	 * @return
	 */
	List<Answer> getEngagedListBySurveyId(Integer surveyId);

}
