package cn.farquer.survey.guest.component.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.farquer.survey.base.impl.BaseDaoImpl;
import cn.farquer.survey.guest.component.dao.interfaces.QuestionDao;
import cn.farquer.survey.guest.entity.Question;

/**
 * 问题的DAO层实现类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:58:34
 */
@SuppressWarnings("unchecked")
@Repository
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements
		QuestionDao {

	@Override
	public void batchSave(Set<Question> questions) {

		String sql = "INSERT INTO t_question(" + "`BAG_ID`,"
				+ "`QUESTION_NAME`," + "`QUESTION_TYPE`," + "`OPTIONS`,"
				+ "`BR`," + "`HAS_OTHER`," + "`OTHER_TYPE`,"
				+ "`MATRIX_ROW_TITLES`," + "`MATRIX_COL_TITLES`,"
				+ "`MATRIX_OPTIONS`) " + "VALUES(?,?,?,?,?,?,?,?,?,?)";

		List<Object[]> list = new ArrayList<Object[]>();

		for (Question question : questions) {

			Object[] param = new Object[10];
			param[0] = question.getBag().getBagId();
			param[1] = question.getQuestionName();
			param[2] = question.getQuestionType();
			param[3] = question.getOptions();
			param[4] = question.isBr();
			param[5] = question.isHasOther();
			param[6] = question.getOtherType();
			param[7] = question.getMatrixRowTitles();
			param[8] = question.getMatrixColTitles();
			param[9] = question.getMatrixOptions();

			list.add(param);

		}

		this.doBatchWork(sql, list);

	}

	@Override
	public List<Question> getQuestionListBySurveyId(Integer surveyId) {

		String hql = "From Question q Where q.bag.survey.surveyId=?";

		return this.getSession().createQuery(hql).setInteger(0, surveyId)
				.list();
	}

}
