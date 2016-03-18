package cn.farquer.survey.guest.component.service.interfaces;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.farquer.survey.base.interfaces.BaseService;
import cn.farquer.survey.guest.entity.Survey;
import cn.farquer.survey.guest.entity.User;
import cn.farquer.survey.model.Page;

/**
 * 调查的业务逻辑层接口声明类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午6:37:23
 */

public interface SurveyService extends BaseService<Survey> {

	/**
	 * 未完成调查的分页
	 * 
	 * @param pageNoStr
	 * @param user
	 * @param pageSize
	 * @return
	 */
	Page<Survey> getUncompletedPage(String pageNoStr, User user, int pageSize);

	/**
	 * 已完成调查的分页
	 * 
	 * @param pageNoStr
	 * @param user
	 * @param pageSize
	 * @return
	 */
	Page<Survey> getCompletedPage(String pageNoStr, User user, int pageSize);

	/**
	 * 判断调查是否提交完成
	 * 
	 * @param surveyId
	 * @return
	 */
	boolean complete(Integer surveyId);

	/**
	 * 查找最新创建的十个调查Top10
	 * 
	 * @return
	 */
	List<Survey> findNewestTenSurvey();

	/**
	 * 用于查找最热的十个调查Top10
	 * 
	 * @return
	 */
	List<Survey> findHotestTenSurvey();

	/**
	 * 存储参加调查的对象和用户的关联信息
	 * 
	 * @param userId
	 * @param surveyId
	 */
	void saveEngageMsg(Integer userId, Integer surveyId);

	/**
	 * 查看所有可以参与的调查的分页显示
	 * 
	 * @param pageNoStr
	 * @param i
	 * @return
	 */
	Page<Survey> findAllAvailableSurvey(String pageNoStr, int i);

	/**
	 * 我参与的调查的分页
	 * 
	 * @param user
	 * @param pageNoStr
	 * @param pageSize
	 * @return
	 */
	Page<Survey> getMyEngagedSurvey(User user, String pageNoStr, int pageSize);

	/**
	 * 分页获取所有已完成的调查，不含用户信息
	 * 
	 * @param pageNoStr
	 * @param pageSize
	 * @return
	 */
	Page<Survey> getCompletedPage(String pageNoStr, int pageSize);

	/**
	 * 创建Excel文件方法
	 * 
	 * @param surveyId
	 * @return
	 */
	HSSFWorkbook generateWorkBook(Integer surveyId);

	/**
	 * 项目初始化用户和调查关联表
	 */
	void createEngageTable();

}
