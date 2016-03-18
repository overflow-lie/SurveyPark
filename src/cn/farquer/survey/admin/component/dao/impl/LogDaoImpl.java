package cn.farquer.survey.admin.component.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.farquer.survey.admin.component.dao.interfaces.LogDao;
import cn.farquer.survey.admin.entity.Log;
import cn.farquer.survey.base.impl.BaseDaoImpl;
import cn.farquer.survey.utils.DataProcessUtils;
@SuppressWarnings("unchecked")
@Repository
public class LogDaoImpl extends BaseDaoImpl<Log> implements LogDao {

	@Override
	public void createTable(String tableName) {
		String sql = "CREATE TABLE IF NOT EXISTS " + tableName
				+ " LIKE `t_log`";
		this.getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public void saveLog(Log log) {
		String tableName = DataProcessUtils.generateTableName(0);
		String sql = "INSERT INTO " + tableName + "(" + "`OPERATOR`,"
				+ "`OPERATE_TIME`," + "`METHOD_TYPE`," + "`METHOD_NAME`,"
				+ "`METHOD_ARGS`," + "`METHOD_RETURN_VALUE`,"
				+ "`METHOD_RESULT_MSG`" + ") VALUES(?,?,?,?,?,?,?)";
		this.getSession().createSQLQuery(sql).setString(0, log.getOperator())
				.setString(1, log.getOperateTime())
				.setString(2, log.getMethodType())
				.setString(3, log.getMethodName())
				.setString(4, log.getMethodArgs())
				.setString(5, log.getMethodReturnValue())
				.setString(6, log.getMethodResultMsg()).executeUpdate();
	}

	
	@Override
	public List<String> getAllTableNames() {

		String sql = "SELECT table_name FROM information_schema.`TABLES` WHERE TABLE_NAME LIKE 't_log_%' AND TABLE_SCHEMA='survey'";

		return this.getSession().createSQLQuery(sql).list();
	}

	@Override
	public int getTotalCount() {

		List<String> tableNameList = getAllTableNames();
		String subSelect = DataProcessUtils.generateSubSelect(tableNameList);

		// SELECT COUNT(log_union.log_id) FROM (SELECT * FROM `logs` UNION
		// SELECT * FROM logs_2016_3) log_union

		String sql = "select count(log_union.log_id) from (" + subSelect
				+ ") log_union";

		BigInteger count = (BigInteger) this.getSession().createSQLQuery(sql)
				.uniqueResult();

		return count.intValue();
	}

	@Override
	public List<Log> getLimitedLogList(int pageNo, int pageSize) {

		List<Log> logList = new ArrayList<>();

		List<String> tableNameList = getAllTableNames();
		String subSelect = DataProcessUtils.generateSubSelect(tableNameList);

		String sql = "select log_union.`LOG_ID`," + "log_union.`OPERATOR`,"
				+ "log_union.`OPERATE_TIME`," + "log_union.`METHOD_TYPE`,"
				+ "log_union.`METHOD_NAME`," + "log_union.`METHOD_ARGS`,"
				+ "log_union.`METHOD_RETURN_VALUE`,"
				+ "log_union.`METHOD_RESULT_MSG`" + " from (" + subSelect
				+ ") log_union order by LOG_ID desc";

		int index = (pageNo - 1) * pageSize;

		List<Object[]> result = this.getSession().createSQLQuery(sql)
				.setFirstResult(index).setMaxResults(pageSize).list();

		for (Object[] objects : result) {
			Integer logId = (Integer) objects[0];
			String operator = (String) objects[1];
			String operateTime = (String) objects[2];
			String methodType = (String) objects[3];
			String methodName = (String) objects[4];
			String methodArgs = (String) objects[5];
			String methodReturnValue = (String) objects[6];
			String methodResultMsg = (String) objects[7];

			Log log = new Log(logId, operator, operateTime, methodType,
					methodName, methodArgs, methodReturnValue, methodResultMsg);

			logList.add(log);
		}

		return logList;
	}

	@Override
	public List<Log> getEntityByTableName(String logTableName) {
		
		List<Log> logList = new ArrayList<>();


		String sql = "select `LOG_ID`,"
				+ "`OPERATOR`,"
				+ "`OPERATE_TIME`,"
				+ "`METHOD_TYPE`,"
				+ "`METHOD_NAME`,"
				+ "`METHOD_ARGS`,"
				+ "`METHOD_RETURN_VALUE`,"
				+ "`METHOD_RESULT_MSG`"
				+ " from " + logTableName ;

		List<Object[]> result = this.getSession().createSQLQuery(sql).list();

		for (Object[] objects : result) {
			Integer logId = (Integer) objects[0];
			String operator = (String) objects[1];
			String operateTime = (String) objects[2];
			String methodType = (String) objects[3];
			String methodName = (String) objects[4];
			String methodArgs = (String) objects[5];
			String methodReturnValue = (String) objects[6];
			String methodResultMsg = (String) objects[7];

			Log log = new Log(logId, operator, operateTime, methodType,
					methodName, methodArgs, methodReturnValue, methodResultMsg);

			logList.add(log);
		}

		return logList;
	}

}
