package cn.farquer.survey.guest.component.dao.interfaces;

import java.util.List;

import cn.farquer.survey.base.interfaces.BaseDao;
import cn.farquer.survey.guest.entity.Bag;

/**
 * 包裹的DAO层接口声明类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:57:41
 */
public interface BagDao extends BaseDao<Bag> {

	/**
	 * 更新包裹排序的方法
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
	 * 获取第一个bag的方法
	 * 
	 * @param surveyId
	 * @return
	 */
	Bag getFirstBag(Integer surveyId);

	/**
	 * 获取上一个包裹的方法
	 * 
	 * @param surveyId
	 * @param bagId
	 * @return
	 */
	Bag getPrevBag(Integer surveyId, Integer bagId);

	/**
	 * 获取下一个包裹的方法
	 * 
	 * @param surveyId
	 * @param bagId
	 * @return
	 */
	Bag getNextBag(Integer surveyId, Integer bagId);

}
