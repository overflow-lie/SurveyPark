package cn.farquer.survey.admin.component.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.farquer.survey.admin.component.service.interfaces.LogService;
import cn.farquer.survey.admin.entity.Log;
import cn.farquer.survey.base.impl.BaseAction;
import cn.farquer.survey.model.Page;
import cn.farquer.survey.utils.DataProcessUtils;
import cn.farquer.survey.utils.GlobalNames;

/**
 * 日志相关操作的Action
 * 
 * @author farquer
 * 
 *         2016年3月1日下午7:04:59
 */
@Controller
@Scope("prototype")
public class LogAction extends BaseAction<Log> {

	// ***************************成员变量区*****************************

	private static final long serialVersionUID = -3176904996264127579L;

	@Autowired
	private LogService logService;

	private String pageNoStr;

	private String logDate;
	
	private String fileName;

	private InputStream inputStream;
	private int contentLength;

	// *************************Action方法区***************************

	public String downloadLog() throws IOException {
		
		String tableName = DataProcessUtils.logTableName2Str(logDate);
		
		this.fileName = tableName;
		
		HSSFWorkbook workBook = logService.generateWorkBook(tableName);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		workBook.write(bos);

		// ②从字节数组输出流中获取字节数组
		byte[] byteArray = bos.toByteArray();

		// ③根据字节数组创建字节数组输入流对象，赋值给inputStream
		inputStream = new ByteArrayInputStream(byteArray);

		// 3.给其他相关属性赋值
		contentLength = inputStream.available();

		return "exportLog";
	}

	public String toDownloadLogsPage() {

		List<String> logTableNameList = logService.getLogTableName();

		requestMap.put(GlobalNames.LOG_TABLE_NAME_LIST, logTableNameList);

		return "toDownloadLogsPage";
	}

	/**
	 * 显示日志
	 * 
	 * @return
	 */
	public String showLogs() {

		Page<Log> page = logService.getPage(pageNoStr, 10);
		requestMap.put(GlobalNames.PAGE, page);

		return "toShowLogsPage";
	}

	// ***********************Getter、Setter区*************************

	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getFileName() {
		return fileName + ".xls";
	}

	public int getContentLength() {
		return contentLength;
	}

}
