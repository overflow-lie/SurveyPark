package cn.farquer.survey.guest.component.action;

import java.util.List;

import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.farquer.survey.base.impl.BaseAction;
import cn.farquer.survey.guest.component.service.interfaces.QuestionService;
import cn.farquer.survey.guest.component.service.interfaces.StatisticsService;
import cn.farquer.survey.guest.component.service.interfaces.SurveyService;
import cn.farquer.survey.guest.entity.Question;
import cn.farquer.survey.guest.entity.Survey;
import cn.farquer.survey.guest.model.OptionStatisticsModel;
import cn.farquer.survey.guest.model.QuestionStatisticsModel;
import cn.farquer.survey.utils.DataProcessUtils;
import cn.farquer.survey.utils.GlobalNames;

@Controller
@Scope("prototype")
public class StatisticsAction extends BaseAction<Survey> {

	// *******************************成员变量区*************************************

	private static final long serialVersionUID = 1L;

	private int width;
	private int height;

	private JFreeChart chart;
	private Integer questionId;

	private int rowNumber;
	private int colNumber;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private StatisticsService statisticsService;

	// *******************************Action方法区*************************************

	public String showOptionMatrixChart() {

		List<OptionStatisticsModel> osmList = statisticsService
				.getOptionOsmList(questionId, rowNumber, colNumber);

		this.chart = DataProcessUtils.generateChartByOsmList(osmList);

		this.width = 400;
		this.height = 300;

		return "toChartResult";
	}

	public String showOptionMatrixPage() {

		Question question = questionService.getEntityById(questionId);
		requestMap.put(GlobalNames.QUESTION, question);

		return "toOptionMatrixPage";
	}

	public String showNormalMatrixChart() {

		List<OptionStatisticsModel> osmList = statisticsService.getOsmList(
				questionId, rowNumber);

		this.chart = DataProcessUtils.generateChartByOsmList(osmList);

		this.width = 300;
		this.height = 200;

		return "toChartResult";
	}

	public String showNormalMatrixPage() {

		Question question = questionService.getEntityById(questionId);
		requestMap.put(GlobalNames.QUESTION, question);

		return "toNormalMatrixPage";
	}

	public String showTextList() {

		List<String> textList = statisticsService.getTextList(questionId);
		requestMap.put(GlobalNames.TEXT_LIST, textList);

		return "toTextListPage";
	}

	/**
	 * 显示文本形式其他项
	 * 
	 * @return
	 */
	public String showOtherTextList() {

		List<String> textList = statisticsService.getOtherTextList(questionId);
		requestMap.put(GlobalNames.TEXT_LIST, textList);

		return "toTextListPage";
	}

	/**
	 * 显示一般项目的统计报表
	 * 
	 * @return
	 */
	public String showNormalChart() {

		// 1.生成Chart对象赋值给this.chart
		QuestionStatisticsModel qsm = statisticsService.getQsm(questionId);

		this.chart = DataProcessUtils.generateChartByQsm(qsm);

		// 2.设置宽高
		this.width = 1024;
		this.height = 768;

		return "toChartResult";
	}

	/**
	 * 跳转到摘要的前置方法，将调查置入栈顶，用于前台显示
	 */
	public void prepareShowSummary() {
		this.t = surveyService.getEntityById(surveyId);
	}

	/**
	 * 跳转到摘要界面
	 * 
	 * @return
	 */
	public String showSummary() {

		return "toSummaryPage";
	}

	// *******************************getXxx()、setXxx()方法区*************************************

	public JFreeChart getChart() {
		return chart;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getColNumber() {
		return colNumber;
	}

	public void setColNumber(int colNumber) {
		this.colNumber = colNumber;
	}

}
