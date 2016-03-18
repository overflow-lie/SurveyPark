package cn.farquer.survey.admin.component.service.interfaces;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.farquer.survey.admin.entity.Log;
import cn.farquer.survey.base.interfaces.BaseService;
import cn.farquer.survey.model.Page;

public interface LogService extends BaseService<Log>{

	void createTable(String tableName);
	
	Page<Log> getPage(String pageNoStr, int pageSize);

	void saveLog(Log log);

	List<String> getLogTableName();

	HSSFWorkbook generateWorkBook(String logDate);

}
