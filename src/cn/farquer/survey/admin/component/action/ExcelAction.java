package cn.farquer.survey.admin.component.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.farquer.survey.base.impl.BaseAction;
import cn.farquer.survey.guest.component.service.interfaces.SurveyService;
import cn.farquer.survey.guest.entity.Survey;
import cn.farquer.survey.model.Page;
import cn.farquer.survey.utils.GlobalNames;

/**
 * 操作Excel的Action
 * 
 * @author farquer
 * 
 *         2016年3月1日下午7:03:33
 */
@Controller
@Scope("prototype")
public class ExcelAction extends BaseAction<Survey> {

	// ***************************成员变量区*****************************

	private static final long serialVersionUID = 1L;

	@Autowired
	private SurveyService surveyService;

	private String pageNoStr;

	private InputStream inputStream;
	private int contentLength;

	// *************************Action方法区***************************

	/**
	 * 下载Excel文件
	 * 
	 * @return
	 * @throws IOException
	 */
	public String exportExcel() throws IOException {

		// 1.生成HSSFWorkBook对象
		HSSFWorkbook workBook = surveyService.generateWorkBook(surveyId);

		// 2.将输出流转换为输入流
		// ①将工作吧数据写入到字节数组输出流中
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		workBook.write(bos);

		// ②从字节数组输出流中获取字节数组
		byte[] byteArray = bos.toByteArray();

		// ③根据字节数组创建字节数组输入流对象，赋值给inputStream
		inputStream = new ByteArrayInputStream(byteArray);

		// 3.给其他相关属性赋值
		contentLength = inputStream.available();

		return "exportExcel";
	}

	/**
	 * 显示所有调查
	 * 
	 * @return
	 */
	public String showAllSurvey() {

		int pageSize = 10;

		Page<Survey> page = surveyService.getCompletedPage(pageNoStr, pageSize);

		requestMap.put(GlobalNames.PAGE, page);

		return "toAllSurveyPage";
	}

	// ***********************Getter、Setter区*************************

	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getFileName() {
		return System.nanoTime() + ".xls";
	}

	public int getContentLength() {
		return contentLength;
	}
}
