package cn.farquer.survey.guest.component.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.farquer.survey.base.impl.BaseServiceImpl;
import cn.farquer.survey.guest.component.dao.interfaces.AnswerDao;
import cn.farquer.survey.guest.component.dao.interfaces.QuestionDao;
import cn.farquer.survey.guest.component.dao.interfaces.SurveyDao;
import cn.farquer.survey.guest.component.service.interfaces.SurveyService;
import cn.farquer.survey.guest.entity.Answer;
import cn.farquer.survey.guest.entity.Bag;
import cn.farquer.survey.guest.entity.Question;
import cn.farquer.survey.guest.entity.Survey;
import cn.farquer.survey.guest.entity.User;
import cn.farquer.survey.model.Page;
import cn.farquer.survey.utils.DataProcessUtils;
import cn.farquer.survey.utils.ValidateUtils;

/**
 * 调查的业务逻辑层实现类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午6:38:52
 */
@Service
public class SurveyServiceImpl extends BaseServiceImpl<Survey> implements
		SurveyService {

	@Autowired
	private SurveyDao surveyDao;

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private QuestionDao questionDao;

	@Override
	public Page<Survey> getUncompletedPage(String pageNoStr, User user,
			int pageSize) {

		// 1.查询数据库得到总记录数
		int totalRecordNo = surveyDao.getUncompletedCount(user);

		// 2.创建Page对象
		Page<Survey> page = new Page<>(pageNoStr, totalRecordNo, pageSize);

		// 3.从Page对象中获取经过修正的pageNo值
		Integer pageNo = page.getPageNo();

		// 4.根据经过修正的pageNo值和pageSize查询list
		List<Survey> list = surveyDao
				.getUncompletedList(pageNo, pageSize, user);

		// 5.将list设置到page对象中
		page.setList(list);

		return page;
	}

	@Override
	public Page<Survey> getCompletedPage(String pageNoStr, User user,
			int pageSize) {
		// 1.查询数据库得到总记录数
		int totalRecordNo = surveyDao.getCompletedCount(user);

		// 2.创建Page对象
		Page<Survey> page = new Page<>(pageNoStr, totalRecordNo, pageSize);

		// 3.从Page对象中获取经过修正的pageNo值
		Integer pageNo = page.getPageNo();

		// 4.根据经过修正的pageNo值和pageSize查询list
		List<Survey> list = surveyDao.getCompletedList(pageNo, pageSize, user);

		// 5.将list设置到page对象中
		page.setList(list);

		return page;
	}

	@Override
	public boolean complete(Integer surveyId) {

		// 1.检查当前调查是否完整
		// 完整标准：Survey对象关联的Bag集合不为空，Bag集合中每一个Bag对象关联的Question集合也不为空
		// ①根据surveyId查询一个Survey对象
		Survey survey = surveyDao.getEntityById(surveyId);

		// ②检查关联的Bag集合
		Set<Bag> bagSet = survey.getBagSet();
		if (!ValidateUtils.collectionValidate(bagSet))
			return false;

		// ③遍历Bag集合，获取每一个Question集合
		for (Bag bag : bagSet) {
			// ④检查Question集合是否为空
			Set<Question> questions = bag.getQuestions();

			if (!ValidateUtils.collectionValidate(questions))
				return false;

		}

		// 2.如果完整，则将completed属性设置为true，并保存
		survey.setCompleted(true);
		survey.setCompletedTime(new Date());

		// 3.如果调查经检测是完整的，那么返回true
		return true;
	}

	@Override
	public List<Survey> findNewestTenSurvey() {

		return surveyDao.findNewestTenSurvey();
	}

	@Override
	public List<Survey> findHotestTenSurvey() {
		return surveyDao.findHotestTenSurvey();
	}

	@Override
	public Page<Survey> findAllAvailableSurvey(String pageNoStr, int pageSize) {

		int totalRecordNo = surveyDao.getCompletedCount();

		Page<Survey> page = new Page<>(pageNoStr, totalRecordNo, pageSize);

		List<Survey> dataList = surveyDao.getCompletedList(page.getPageNo(),
				pageSize);

		page.setList(dataList);

		return page;
	}

	@Override
	public void saveEngageMsg(Integer userId, Integer surveyId) {
		surveyDao.saveEngageMsg(userId, surveyId);
	}

	@Override
	public Page<Survey> getMyEngagedSurvey(User user, String pageNoStr,
			int pageSize) {

		int totalRecordNo = surveyDao.getMyEngagedCount(user.getUserId());

		Page<Survey> page = new Page<>(pageNoStr, totalRecordNo, pageSize);

		List<Survey> list = surveyDao.getMyEngagedList(user.getUserId(),
				page.getPageNo(), pageSize);

		page.setList(list);

		return page;
	}

	@Override
	public Page<Survey> getCompletedPage(String pageNoStr, int pageSize) {

		int totalRecord = surveyDao.getCompletedCount();

		Page<Survey> page = new Page<>(pageNoStr, totalRecord, pageSize);

		List<Survey> list = surveyDao.getCompletedList(page.getPageNo(),
				pageSize);

		page.setList(list);

		return page;
	}

	@Override
	public HSSFWorkbook generateWorkBook(Integer surveyId) {

		// 获取当前调查对象
		Survey survey = surveyDao.getEntityById(surveyId);

		// 获取当前参与总人数
		int engageCount = surveyDao.getTotalEngagedCount(surveyId);

		// 根据调查ID获取所有关联Answer对象
		List<Answer> answerList = answerDao.getEngagedListBySurveyId(surveyId);

		// 根据调查ID获取所有关联的问题对象
		List<Question> questionList = questionDao
				.getQuestionListBySurveyId(surveyId);

		// 创建Excel文件对应的HSSFWorkBook对象
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 创建工作表对象：HSSFSheet
		HSSFSheet sheet = workbook.createSheet(survey.getTitle() + "【" + engageCount + "人次参与】"); 	

		// 创建第一行表头
		HSSFRow row = sheet.createRow(0);

		// 如果没有人参加调查则显示提示
		if (engageCount == 0) {
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("当前调查还没有人参与！");
			return workbook;
		}

		// 添加问题名称表头
		for (int i = 0; i < questionList.size(); i++) {
			Question question = questionList.get(i);
			String questionName = question.getQuestionName();
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(questionName);
		}

		// bigMap是以UUID为key，以一个smallMap为Value，而smallMap则是以questionId为Key，以Content为Value
		Map<String, Map<Integer, String>> bigMap = convertAnswer(answerList);

		Set<Entry<String, Map<Integer, String>>> entrySet = bigMap.entrySet();

		List<Entry<String, Map<Integer, String>>> list = new ArrayList<>(
				entrySet);

		for (int i = 0; i < list.size(); i++) {

			Entry<String, Map<Integer, String>> entry = list.get(i);

			Map<Integer, String> smallMap = entry.getValue();

			HSSFRow createRow = sheet.createRow(i + 1);

			for (int j = 0; j < questionList.size(); j++) {

				Question question = questionList.get(j);

				String content = smallMap.get(question.getQuestionId());

				HSSFCell createCell = createRow.createCell(j);

				createCell.setCellValue(content);
			}

		}

		// 设置每一行都是自动行距
		for (int i = 0; i < questionList.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		return workbook;
	}

	/**
	 * 将Answer对象转换成Map<UUID, Map<QuestionId， Content>> 的形式，其中Content为答案内容
	 * 
	 * @param answerList
	 * @return
	 */
	public Map<String, Map<Integer, String>> convertAnswer(List<Answer> answers) {

		Map<String, Map<Integer, String>> bigMap = new HashMap<String, Map<Integer, String>>();

		for (Answer answer : answers) {

			String uuid = answer.getUuid();

			// key：questionId，value：答案内容
			Map<Integer, String> smallMap = bigMap.get(uuid);
			if (smallMap == null) {
				smallMap = new HashMap<>();
				bigMap.put(uuid, smallMap);
			}

			Integer questionId = answer.getQuestionId();

			String oldContent = smallMap.get(questionId);

			if (ValidateUtils.stringValidate(oldContent)) {
				oldContent = oldContent + ",";
			} else {
				oldContent = "";
			}

			String mainAnswers = answer.getMainAnswers();
			if (ValidateUtils.stringValidate(mainAnswers)) {
				oldContent = oldContent + mainAnswers;
			}

			String otherAnswers = answer.getOtherAnswers();
			if (ValidateUtils.stringValidate(otherAnswers)) {
				oldContent = oldContent + "[其他项：" + otherAnswers + "],";
			}

			oldContent = DataProcessUtils.removeLastComma(oldContent);

			smallMap.put(questionId, oldContent);

		}

		return bigMap;
	}

	@Override
	public void createEngageTable() {
		surveyDao.createEngageTable();
	}
}
