package cn.farquer.survey.guest.component.service.interfaces;

import java.util.List;

import cn.farquer.survey.base.interfaces.BaseService;
import cn.farquer.survey.guest.entity.Survey;
import cn.farquer.survey.guest.model.OptionStatisticsModel;
import cn.farquer.survey.guest.model.QuestionStatisticsModel;

/**
 * 统计调查信息的业务逻辑层接口声明类
 * 
 * @author farquer
 * 
 *         2016年2月23日下午8:03:35
 */
public interface StatisticsService extends BaseService<Survey> {

	/**
	 * 通过问题的ID获取到问题统计信息模型
	 * 
	 * @param questionId
	 * @return
	 */
	QuestionStatisticsModel getQsm(Integer questionId);

	/**
	 * 获取文本形式其他项的list集合
	 * 
	 * @param questionId
	 * @return
	 */
	List<String> getOtherTextList(Integer questionId);

	/**
	 * 显示简答题的list集合
	 * 
	 * @param questionId
	 * @return
	 */
	List<String> getTextList(Integer questionId);

	/**
	 * 获取选项统计模型的list集合，针对于矩阵的单选和多选，只传递行号即可
	 * 
	 * @param questionId
	 * @param rowNumber
	 * @return
	 */
	List<OptionStatisticsModel> getOsmList(Integer questionId, int rowNumber);

	/**
	 * 获取选项统计模型的list集合，针对于矩阵下拉列表，需要传递行号和列号参数
	 * 
	 * @param questionId
	 * @param rowNumber
	 * @param colNumber
	 * @return
	 */
	List<OptionStatisticsModel> getOptionOsmList(Integer questionId,
			int rowNumber, int colNumber);

}
