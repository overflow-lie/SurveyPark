package cn.farquer.survey.admin.component.dao.interfaces;

import java.util.List;

import cn.farquer.survey.admin.entity.Log;
import cn.farquer.survey.base.interfaces.BaseDao;

public interface LogDao extends BaseDao<Log> {

	void createTable(String tableName);

	void saveLog(Log log);

	List<String> getAllTableNames();

	int getTotalCount();

	List<Log> getLimitedLogList(int pageNo, int pageSize);

	List<Log> getEntityByTableName(String logTableName);

}
