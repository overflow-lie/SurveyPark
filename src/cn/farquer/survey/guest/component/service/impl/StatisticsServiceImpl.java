package cn.farquer.survey.guest.component.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.farquer.survey.base.impl.BaseServiceImpl;
import cn.farquer.survey.guest.component.dao.interfaces.AnswerDao;
import cn.farquer.survey.guest.component.dao.interfaces.QuestionDao;
import cn.farquer.survey.guest.component.service.interfaces.StatisticsService;
import cn.farquer.survey.guest.entity.Question;
import cn.farquer.survey.guest.entity.Survey;
import cn.farquer.survey.guest.model.OptionStatisticsModel;
import cn.farquer.survey.guest.model.QuestionStatisticsModel;

@Service
public class StatisticsServiceImpl extends BaseServiceImpl<Survey> implements
		StatisticsService {

	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private AnswerDao answerDao;

	@Override
	public QuestionStatisticsModel getQsm(Integer questionId) {

		// 获取问题实体类
		Question question = questionDao.getEntityById(questionId);

		QuestionStatisticsModel qsm = new QuestionStatisticsModel();
		// 问题名称
		qsm.setQuestionName(question.getQuestionName());

		// 参与的总人次
		int totalCount = answerDao.getTotalEngagedCount(questionId);
		qsm.setTotalCount(totalCount);

		String[] optionsArray = question.getOptionsArray();
		List<OptionStatisticsModel> osmList = answerDao.getOsmList(questionId,
				optionsArray);

		if (question.isHasOther() && question.getOtherType() == 0) {
			int otherCount = answerDao.getOtherCount(questionId);
			OptionStatisticsModel osm = new OptionStatisticsModel();
			osm.setLabel("其它");
			osm.setCount(otherCount);
			osmList.add(osm);
		}

		qsm.setOsmList(osmList);

		return qsm;
	}

	@Override
	public List<String> getOtherTextList(Integer questionId) {
		return answerDao.getOtherTextList(questionId);
	}

	@Override
	public List<String> getTextList(Integer questionId) {

		return answerDao.getTextList(questionId);
	}

	@Override
	public List<OptionStatisticsModel> getOsmList(Integer questionId,
			int rowNumber) {

		List<OptionStatisticsModel> osmList = new ArrayList<>();

		// 根据questionId查询Question对象
		Question question = questionDao.getEntityById(questionId);

		// 获取行标题数组
		String[] rowArray = question.getMatrixRowTitlesArray();
		String rowTitle = rowArray[rowNumber];

		// 获取列标题数组
		String[] colArray = question.getMatrixColTitlesArray();
		for (int i = 0; i < colArray.length; i++) {
			String colTitle = colArray[i];
			String label = rowTitle + "_" + colTitle;
			String currentValue = rowNumber + "_" + i;
			int count = answerDao
					.getOptionEnagedCount(questionId, currentValue);

			if (count == 0)
				continue;

			OptionStatisticsModel osm = new OptionStatisticsModel();
			osm.setLabel(label);
			osm.setCount(count);

			osmList.add(osm);
		}

		return osmList;
	}

	@Override
	public List<OptionStatisticsModel> getOptionOsmList(Integer questionId,
			int rowNumber, int colNumber) {

		List<OptionStatisticsModel> osmList = new ArrayList<>();

		Question question = questionDao.getEntityById(questionId);

		//String[] rowArray = question.getMatrixRowTitlesArray();
		//String[] colArray = question.getMatrixColTitlesArray();
		String[] optionArray = question.getMatrixOptionsArray();

		for (int i = 0; i < optionArray.length; i++) {

			String label = optionArray[i];
			String currentValue = rowNumber + "_" + colNumber + "_" + i;

			int count = answerDao
					.getOptionEnagedCount(questionId, currentValue);

			if (count == 0)
				continue;

			OptionStatisticsModel osm = new OptionStatisticsModel();
			osm.setLabel(label);
			osm.setCount(count);

			osmList.add(osm);

		}

		return osmList;
	}

}
