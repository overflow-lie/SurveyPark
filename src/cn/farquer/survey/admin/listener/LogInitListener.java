package cn.farquer.survey.admin.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import cn.farquer.survey.admin.component.service.interfaces.LogService;
import cn.farquer.survey.guest.component.service.interfaces.SurveyService;
import cn.farquer.survey.utils.DataProcessUtils;

/**
 * 日志系统初始化监听器
 * 
 * @author farquer
 * 
 *         2016年3月1日上午11:34:02
 */
@SuppressWarnings("rawtypes")
public class LogInitListener implements ApplicationListener {

	private LogService logService;

	private SurveyService surveyService;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {

		// 判断事件是否属于容器初始化(程序第一次执行)
		if (event instanceof ContextRefreshedEvent) {

			// 创建后三个月份的日志表
			String tableName = DataProcessUtils.generateTableName(0);
			logService.createTable(tableName);

			tableName = DataProcessUtils.generateTableName(1);
			logService.createTable(tableName);

			tableName = DataProcessUtils.generateTableName(2);
			logService.createTable(tableName);

			surveyService.createEngageTable();

		}
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

}
