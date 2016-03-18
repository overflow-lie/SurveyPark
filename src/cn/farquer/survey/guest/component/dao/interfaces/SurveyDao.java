package cn.farquer.survey.guest.component.dao.interfaces;

import java.util.List;

import cn.farquer.survey.base.interfaces.BaseDao;
import cn.farquer.survey.guest.entity.Survey;
import cn.farquer.survey.guest.entity.User;

/**
 * 调查的DAO层接口声明类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:58:02
 */
public interface SurveyDao extends BaseDao<Survey> {

	/**
	 * 查询未完成的调查的总记录数
	 * 
	 * @param user
	 * @return
	 */
	int getUncompletedCount(User user);

	/**
	 * 查询未完成的调查的集合
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param user
	 * @return
	 */
	List<Survey> getUncompletedList(int pageNo, int pageSize, User user);

	/**
	 * 查询已完成的调查的总记录数
	 * 
	 * @return
	 */
	int getCompletedCount();

	/**
	 * 查询已完成的调查的集合
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param user
	 * @return
	 */
	List<Survey> getCompletedList(int pageNo, int pageSize, User user);

	/**
	 * 用于查找最新的十个调查Top10
	 * 
	 * @return
	 */
	List<Survey> findNewestTenSurvey();

	/**
	 * 用于查找最热的十个调查
	 * 
	 * @return
	 */
	List<Survey> findHotestTenSurvey();

	/**
	 * 存储用户和调查的关联关系
	 * 
	 * @param userId
	 * @param surveyId
	 */
	void saveEngageMsg(Integer userId, Integer surveyId);

	/**
	 * 获取我参与的调查的总数目
	 * 
	 * @param userId
	 * @return
	 */
	int getMyEngagedCount(Integer userId);

	/**
	 * 获取我参与的调查的列表，用于分页显示
	 * 
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Survey> getMyEngagedList(Integer userId, Integer pageNo, int pageSize);

	/**
	 * 获取到已完成调查的数量
	 * 
	 * @param user
	 * @return
	 */
	int getCompletedCount(User user);

	/**
	 * 获取已完成调查的列表，用于分页显示
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Survey> getCompletedList(int pageNo, int pageSize);

	/**
	 * 获取当前调查的所有参与人数
	 * 
	 * @param surveyId
	 * @return
	 */
	int getTotalEngagedCount(Integer surveyId);
	
	/**
	 * 初始化时候创建关联表
	 */
	void createEngageTable();
	
	
}
