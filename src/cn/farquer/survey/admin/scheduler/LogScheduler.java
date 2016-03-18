package cn.farquer.survey.admin.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.farquer.survey.admin.component.service.interfaces.LogService;
import cn.farquer.survey.guest.component.service.interfaces.SurveyService;
import cn.farquer.survey.utils.DataProcessUtils;

/**
 * 石英调度类，需要继承QuartzJobBean类
 * 
 * @author farquer
 * 
 *         2016年3月1日下午6:28:10
 */
public class LogScheduler extends QuartzJobBean {

	private LogService logService;
	
	private SurveyService surveyService;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

		// 自动创建三个月的日志表
		String tableName = DataProcessUtils.generateTableName(1);
		logService.createTable(tableName);

		tableName = DataProcessUtils.generateTableName(2);
		logService.createTable(tableName);

		tableName = DataProcessUtils.generateTableName(3);
		logService.createTable(tableName);

		surveyService.createEngageTable();
		
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}
	
	
}
