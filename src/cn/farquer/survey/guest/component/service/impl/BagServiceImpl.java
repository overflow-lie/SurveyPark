package cn.farquer.survey.guest.component.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.farquer.survey.base.impl.BaseServiceImpl;
import cn.farquer.survey.guest.component.dao.interfaces.BagDao;
import cn.farquer.survey.guest.component.dao.interfaces.QuestionDao;
import cn.farquer.survey.guest.component.service.interfaces.BagService;
import cn.farquer.survey.guest.entity.Bag;
import cn.farquer.survey.guest.entity.Question;
import cn.farquer.survey.guest.entity.Survey;
import cn.farquer.survey.utils.DataProcessUtils;

/**
 * 包裹的业务逻辑层实现类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午6:38:25
 */
@Service
public class BagServiceImpl extends BaseServiceImpl<Bag> implements BagService {

	@Autowired
	private BagDao bagDao;

	@Autowired
	private QuestionDao questionDao;

	@Override
	public void batchUpdateBagOrder(List<Bag> bagList) {

		bagDao.batchUpdateBagOrder(bagList);
	}

	@Override
	public void moveToThisSurvey(Integer bagId, Integer surveyId) {

		bagDao.moveToThisSurvey(bagId, surveyId);
	}

	@Override
	public void copyToThisSurvey(Integer bagId, Integer surveyId) {

		// 获取到源对象
		Bag sourceBag = bagDao.getEntityById(bagId);

		// 获取到复制出来的目标对象
		Bag targetBag = (Bag) DataProcessUtils.deeplyCopy(sourceBag);

		Survey survey = new Survey();
		survey.setSurveyId(surveyId);
		targetBag.setSurvey(survey);

		bagDao.saveEntity(targetBag);

		Set<Question> questions = targetBag.getQuestions();

		questionDao.batchSave(questions);

	}

	@Override
	public Bag getFirstBag(Integer surveyId) {
		return bagDao.getFirstBag(surveyId);
	}

	@Override
	public Bag getPrevBag(Integer surveyId, Integer bagId) {
		return bagDao.getPrevBag(surveyId, bagId);
	}

	@Override
	public Bag getNextBag(Integer surveyId, Integer bagId) {
		return bagDao.getNextBag(surveyId, bagId);
	}

}
