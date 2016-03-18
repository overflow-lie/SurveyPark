package cn.farquer.survey.guest.component.service.interfaces;

import java.util.List;

import cn.farquer.survey.base.interfaces.BaseService;
import cn.farquer.survey.guest.entity.Bag;

/**
 * 包裹的业务逻辑层接口声明类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午6:36:49
 */
public interface BagService extends BaseService<Bag> {

	/**
	 * 批量处理包裹排序的方法，使用SQL的批处理
	 * 
	 * @param bagList
	 */
	void batchUpdateBagOrder(List<Bag> bagList);

	/**
	 * 移动包裹到其他调查的方法
	 * 
	 * @param bagId
	 * @param surveyId
	 */
	void moveToThisSurvey(Integer bagId, Integer surveyId);

	/**
	 * 复制包裹 到其他调查的方法
	 * 
	 * @param bagId
	 * @param surveyId
	 */
	void copyToThisSurvey(Integer bagId, Integer surveyId);

	/**
	 * 获取第一个包裹的方法
	 * 
	 * @param surveyId
	 * @return
	 */
	Bag getFirstBag(Integer surveyId);

	/**
	 * 获取上一个包裹
	 * 
	 * @param surveyId
	 * @param bagId
	 * @return
	 */
	Bag getPrevBag(Integer surveyId, Integer bagId);

	/**
	 * 获取下一个包裹
	 * 
	 * @param surveyId
	 * @param bagId
	 * @return
	 */
	Bag getNextBag(Integer surveyId, Integer bagId);

}
