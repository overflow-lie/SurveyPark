package cn.farquer.survey.guest.component.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import cn.farquer.survey.base.impl.BaseDaoImpl;
import cn.farquer.survey.guest.component.dao.interfaces.AnswerDao;
import cn.farquer.survey.guest.entity.Answer;
import cn.farquer.survey.guest.model.OptionStatisticsModel;

/**
 * 答案的持久化层的接口实现类
 * 
 * @author farquer
 * 
 *         2016年2月23日上午11:07:32
 */
@SuppressWarnings("unchecked")
@Repository
public class AnswerDaoImpl extends BaseDaoImpl<Answer> implements AnswerDao {

	@Override
	public void batchSaveAnswerList(List<Answer> answerList) {

		String sql = "INSERT INTO t_answer(`QUESTION_ID`,`SURVEY_ID`,`ANSWER_TIME`,`UUID`,`MAIN_ANSWERS`,`OTHER_ANSWERS`) VALUES(?,?,?,?,?,?)";

		List<Object[]> params = new ArrayList<>();
		for (int i = 0; i < answerList.size(); i++) {

			Answer answer = answerList.get(i);
			Object[] param = new Object[6];
			param[0] = answer.getQuestionId();
			param[1] = answer.getSurveyId();
			param[2] = answer.getAnswerTime();
			param[3] = answer.getUuid();
			param[4] = answer.getMainAnswers();
			param[5] = answer.getOtherAnswers();

			params.add(param);
		}

		this.doBatchWork(sql, params);

	}

	@Override
	public int getTotalEngagedCount(Integer questionId) {

		String sql = "SELECT COUNT(DISTINCT UUID) FROM t_answer WHERE question_id=?";

		BigInteger result = (BigInteger) this.getSession().createSQLQuery(sql)
				.setInteger(0, questionId).uniqueResult();

		return result.intValue();
	}

	@Override
	public List<OptionStatisticsModel> getOsmList(Integer questionId,
			String[] optionsArray) {

		String sql = "SELECT COUNT(*) FROM t_answer WHERE question_id = ? AND CONCAT(',', main_answers, ',') LIKE ?";
		SQLQuery query = this.getSession().createSQLQuery(sql);

		List<OptionStatisticsModel> osmList = new ArrayList<>();

		for (int i = 0; i < optionsArray.length; i++) {
			OptionStatisticsModel osm = new OptionStatisticsModel();
			osm.setLabel(optionsArray[i]);

			BigInteger result = (BigInteger) query.setInteger(0, questionId)
					.setString(1, "%," + i + ",%").uniqueResult();

			int count = result.intValue();
			if (count == 0)
				continue;

			osm.setCount(count);

			osmList.add(osm);
		}

		return osmList;
	}

	@Override
	public int getOtherCount(Integer questionId) {

		String sql = "SELECT COUNT(*) FROM t_answer WHERE question_id = ? AND CONCAT(',', main_answers, ',') LIKE '%,other,%'";
		BigInteger result = (BigInteger) this.getSession().createSQLQuery(sql)
				.setInteger(0, questionId).uniqueResult();

		return result.intValue();
	}

	@Override
	public List<String> getOtherTextList(Integer questionId) {

		String sql = "SELECT OTHER_ANSWERS FROM t_answer WHERE question_id=? AND OTHER_ANSWERS IS NOT NULL AND OTHER_ANSWERS != ''";

		return this.getSession().createSQLQuery(sql).setInteger(0, questionId)
				.list();
	}

	@Override
	public List<String> getTextList(Integer questionId) {
		String sql = "SELECT main_answers FROM t_answer WHERE question_id=? AND main_answers IS NOT NULL AND main_answers != ''";

		return this.getSession().createSQLQuery(sql).setInteger(0, questionId)
				.list();
	}

	@Override
	public int getOptionEnagedCount(Integer questionId, String currentValue) {
		String sql = "SELECT COUNT(*) FROM t_answer WHERE question_id = ? AND CONCAT(',', main_answers, ',') LIKE ?";

		BigInteger result = (BigInteger) this.getSession().createSQLQuery(sql)
				.setInteger(0, questionId)
				.setString(1, "%," + currentValue + ",%").uniqueResult();

		return result.intValue();
	}

	@Override
	public List<Answer> getEngagedListBySurveyId(Integer surveyId) {

		String hql = "From Answer a Where a.surveyId = ?";

		return this.getSession().createQuery(hql).setInteger(0, surveyId)
				.list();
	}

}
