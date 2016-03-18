package cn.farquer.survey.admin.component.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.farquer.survey.admin.component.dao.interfaces.LogDao;
import cn.farquer.survey.admin.component.service.interfaces.LogService;
import cn.farquer.survey.admin.entity.Log;
import cn.farquer.survey.base.impl.BaseServiceImpl;
import cn.farquer.survey.model.Page;
import cn.farquer.survey.utils.DataProcessUtils;
import cn.farquer.survey.utils.ValidateUtils;

@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

	@Autowired
	private LogDao logDao;

	@Override
	public void createTable(String tableName) {
		logDao.createTable(tableName);
	}

	@Override
	public Page<Log> getPage(String pageNoStr, int pageSize) {

		int totalRecordNo = logDao.getTotalCount();

		Page<Log> page = new Page<>(pageNoStr, totalRecordNo, pageSize);

		List<Log> logList = logDao
				.getLimitedLogList(page.getPageNo(), pageSize);

		page.setList(logList);

		return page;
	}

	@Override
	public void saveLog(Log log) {
		logDao.saveLog(log);
	}

	@Override
	public List<String> getLogTableName() {

		List<String> allTableNames = logDao.getAllTableNames();

		if (!ValidateUtils.collectionValidate(allTableNames))
			return null;

		List<String> result = new ArrayList<>();

		for (String string : allTableNames) {

			String str = DataProcessUtils.str2LogTableName(string);

			result.add(str);
		}

		return result;
	}

	@Override
	public HSSFWorkbook generateWorkBook(String logDate) {
				
		List<Log> logList = logDao.getEntityByTableName(logDate);
		
		// 创建Excel文件对应的HSSFWorkBook对象
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		// 创建工作表对象：HSSFSheet
		HSSFSheet sheet = workbook.createSheet(logDate + "日志信息");
		
		if(!ValidateUtils.collectionValidate(logList)) {
			
			 HSSFRow row = sheet.createRow(0);
			 
			 HSSFCell cell = row.createCell(0);
			 cell.setCellValue("当月暂无数据");
			 
			return workbook;
		}
		
		// 创建第一行表头
		 HSSFRow row = sheet.createRow(0);

		 HSSFCell cell = row.createCell(0);
		 cell.setCellValue("LogId");
		 HSSFCell cell1 = row.createCell(1);
		 cell1.setCellValue("操作人");
		 HSSFCell cell2 = row.createCell(2);
		 cell2.setCellValue("操作时间");
		 HSSFCell cell3 = row.createCell(3);
		 cell3.setCellValue("方法类型");
		 HSSFCell cell4 = row.createCell(4);
		 cell4.setCellValue("方法名");
		 HSSFCell cell5 = row.createCell(5);
		 cell5.setCellValue("方法参数");
		 HSSFCell cell6 = row.createCell(6);
		 cell6.setCellValue("方法返回值");
		 HSSFCell cell7 = row.createCell(7);
		 cell7.setCellValue("方法执行结果");
		 
		 for (int i = 0; i < logList.size(); i++) {
			
			 Log log = logList.get(i);
			 
			 HSSFRow createRow = sheet.createRow(i + 1);
			 
			 HSSFCell createCell = createRow.createCell(0);
			 createCell.setCellValue(log.getLogId());
			 HSSFCell createCell1 = createRow.createCell(1);
			 createCell1.setCellValue(log.getOperator());
			 HSSFCell createCell2 = createRow.createCell(2);
			 createCell2.setCellValue(log.getOperateTime());
			 HSSFCell createCell3 = createRow.createCell(3);
			 createCell3.setCellValue(log.getMethodType());
			 HSSFCell createCell4 = createRow.createCell(4);
			 createCell4.setCellValue(log.getMethodName());
			 HSSFCell createCell5 = createRow.createCell(5);
			 createCell5.setCellValue(log.getMethodArgs());
			 HSSFCell createCell6 = createRow.createCell(6);
			 createCell6.setCellValue(log.getMethodReturnValue());
			 HSSFCell createCell7 = createRow.createCell(7);
			 createCell7.setCellValue(log.getMethodResultMsg());
			 
		}
		 
		for (int i = 0; i < 8; i++) {
				sheet.autoSizeColumn(i);
		}
		 
		return workbook;
	}

}
