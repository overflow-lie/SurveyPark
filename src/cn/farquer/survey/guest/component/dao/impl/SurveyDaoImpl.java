package cn.farquer.survey.guest.component.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.farquer.survey.base.impl.BaseDaoImpl;
import cn.farquer.survey.guest.component.dao.interfaces.SurveyDao;
import cn.farquer.survey.guest.entity.Survey;
import cn.farquer.survey.guest.entity.User;

/**
 * 调查的DAO层实现类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:58:29
 */
@Repository
@SuppressWarnings("unchecked")
public class SurveyDaoImpl extends BaseDaoImpl<Survey> implements SurveyDao {

	@Override
	public int getUncompletedCount(User user) {

		String hql = "select count(*) from Survey s where s.completed=false and s.user=?";

		long totalRecord = (long) this.getSession().createQuery(hql)
				.setEntity(0, user).uniqueResult();

		return (int) totalRecord;
	}

	@Override
	public List<Survey> getUncompletedList(int pageNo, int pageSize, User user) {

		String hql = "from Survey s where s.completed=false and s.user=?";

		int index = (pageNo - 1) * pageSize;

		return this.getSession().createQuery(hql).setEntity(0, user)
				.setFirstResult(index).setMaxResults(pageSize).list();
	}

	@Override
	public int getCompletedCount(User user) {
		String hql = "select count(*) from Survey s where s.completed=true and s.user=?";

		long totalRecord = (long) this.getSession().createQuery(hql)
				.setEntity(0, user).uniqueResult();

		return (int) totalRecord;
	}

	@Override
	public List<Survey> getCompletedList(int pageNo, int pageSize, User user) {
		String hql = "from Survey s where s.completed=true and s.user=?";

		int index = (pageNo - 1) * pageSize;

		return this.getSession().createQuery(hql).setEntity(0, user)
				.setFirstResult(index).setMaxResults(pageSize).list();
	}

	@Override
	public List<Survey> findNewestTenSurvey() {

		String hql = "From Survey s Where s.completed=true order by completedTime desc";

		return this.getSession().createQuery(hql).setMaxResults(10).list();
	}

	@Override
	public List<Survey> findHotestTenSurvey() {
		
		String sql = "SELECT t.SURVEY_ID, t.TITLE, t.LOGO_PATH, t.COMPLETED, t.USER_ID, t.COMPLETED_TIME FROM t_survey t INNER JOIN (SELECT c.survey_id FROM (SELECT a.survey_Id FROM survey.t_answer a ) AS c GROUP BY c.survey_id ORDER BY COUNT(c.survey_Id) DESC) AS d ON t.survey_id=d.survey_id";
		
		List<Object[]> list = this.getSession().createSQLQuery(sql).setMaxResults(10).list();
		
		List<Survey> result = new ArrayList<>();
		
		Survey survey = null;
		Integer surveyId = null;
		String title = null;
		String logoPath = null;
		Boolean completed = null;
		Integer userId = null;
		User user = null;
		Date completedTime = null;
		
		for (Object[] objects : list) {
			surveyId = (Integer) objects[0];
			title = (String) objects[1];
			logoPath = (String) objects[2];
			completed = (Boolean) objects[3];
			userId = (Integer)objects[4];
			user = (User) this.getSession().createQuery("From User u Where u.userId=?").setInteger(0, userId).uniqueResult();
			completedTime = (Date) objects[5];
			survey = new Survey(surveyId, title, logoPath, user, completed, completedTime);
			result.add(survey);
		}
		
		return result;
		
	}

	@Override
	public void saveEngageMsg(Integer userId, Integer surveyId) {

		String sql = "INSERT INTO t_engage(user_id,survey_id) VALUES(?,?)";

		this.getSession().createSQLQuery(sql).setInteger(0, userId)
				.setInteger(1, surveyId).executeUpdate();

	}

	@Override
	public int getMyEngagedCount(Integer userId) {

		String sql = "select count(user_id) from t_engage where user_id=?";

		BigInteger count = (BigInteger) this.getSession().createSQLQuery(sql)
				.setInteger(0, userId).uniqueResult();

		return count.intValue();
	}

	@Override
	public List<Survey> getMyEngagedList(Integer userId, Integer pageNo,
			int pageSize) {

		String sql = "SELECT survey_id,TITLE,LOGO_PATH,COMPLETED_TIME FROM t_survey "
				+ "WHERE survey_id IN "
				+ "(SELECT engage.survey_id FROM t_engage engage WHERE engage.user_id=?) limit ?,?";

		List<Object[]> list = this.getSession().createSQLQuery(sql)
				.setInteger(0, userId).setInteger(1, (pageNo - 1) * pageSize)
				.setInteger(2, pageSize).list();

		List<Survey> surveyList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			Object[] objects = list.get(i);

			Integer surveyId = (Integer) objects[0];
			String title = (String) objects[1];
			String logoPath = (String) objects[2];
			Date comTime = (Date) objects[3];

			Survey survey = new Survey(surveyId, title, logoPath, comTime);
			surveyList.add(survey);
		}

		return surveyList;
	}

	@Override
	public int getCompletedCount() {
		String hql = "select count(*) from Survey s where s.completed=true";

		long totalRecord = (long) this.getSession().createQuery(hql)
				.uniqueResult();

		return (int) totalRecord;
	}

	@Override
	public List<Survey> getCompletedList(int pageNo, int pageSize) {
		String hql = "from Survey s where s.completed=true";

		int index = (pageNo - 1) * pageSize;

		return this.getSession().createQuery(hql).setFirstResult(index)
				.setMaxResults(pageSize).list();
	}

	@Override
	public int getTotalEngagedCount(Integer surveyId) {

		String sql = "SELECT COUNT(DISTINCT UUID) FROM t_answer WHERE SURVEY_ID=?";

		BigInteger result = (BigInteger) this.getSession().createSQLQuery(sql)
				.setInteger(0, surveyId).uniqueResult();

		return result.intValue();
	}

	@Override
	public void createEngageTable() {
		String sql = "CREATE TABLE IF NOT EXISTS `t_engage` "
				+ "(`user_id` INT NOT NULL,"
				+ "`survey_id` INT NOT NULL,"
				+ "PRIMARY KEY (`user_id`, `survey_id`))";
		this.getSession().createSQLQuery(sql).executeUpdate();		
	}
}