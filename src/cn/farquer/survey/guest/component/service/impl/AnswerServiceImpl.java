package cn.farquer.survey.guest.component.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.farquer.survey.base.impl.BaseServiceImpl;
import cn.farquer.survey.guest.component.dao.interfaces.AnswerDao;
import cn.farquer.survey.guest.component.service.interfaces.AnswerService;
import cn.farquer.survey.guest.entity.Answer;
import cn.farquer.survey.utils.DataProcessUtils;

/**
 * 答案业务逻辑层的接口实现类
 * 
 * @author farquer
 * 
 *         2016年2月23日上午11:04:03
 */
@Service
public class AnswerServiceImpl extends BaseServiceImpl<Answer> implements
		AnswerService {

	@Autowired
	private AnswerDao answerDao;

	@Override
	public void parseAndSave(Map<Integer, Map<String, String[]>> allBagMap,
			Integer surveyId) {

		List<Answer> answerList = new ArrayList<>();

		// 提供保存Answer对象时需要的基本信息
		Date answerTime = new Date();
		String uuid = UUID.randomUUID().toString();

		// 将答案数据从allBagMap中取出，此时不关心某个答案属于哪个包裹，所以取allBagMap的values
		Collection<Map<String, String[]>> values = allBagMap.values();

		// 声明一个缓存矩阵单选的答案数据的Map
		Map<Integer, String> matrixRadioMap = new HashMap<>();

		// 遍历values
		for (Map<String, String[]> paramMap : values) {

			Set<Entry<String, String[]>> entrySet = paramMap.entrySet();
			for (Entry<String, String[]> param : entrySet) {

				// 每得到一个param都对应一个Answer对象
				Answer answer = new Answer(null, null, surveyId, answerTime,
						uuid, null, null);

				// 得到每一个请求参数
				String paramName = param.getKey();
				String[] paramValArr = param.getValue();

				// 要解析的答案请求参数的name值都是以question开头的，如果不是则不解析
				if (!paramName.startsWith("question"))
					continue;

				// 区分paramName的三种情况
				if (paramName.contains("Other")) {
					// 文本框形式的其他项
					// questionId
					// paramName:question27Other
					String questionIdStr = paramName.substring(8,
							paramName.lastIndexOf("Other"));
					Integer questionId = Integer.parseInt(questionIdStr);
					answer.setQuestionId(questionId);

					// otherAnswers
					String otherAnswers = paramValArr[0];
					answer.setOtherAnswers(otherAnswers);

					answerList.add(answer);

				} else if (paramName.contains("_")) {
					// 矩阵单选
					// 检测当前paramName中的questionId
					String questionIdStr = paramName.substring(8,
							paramName.lastIndexOf("_"));
					Integer questionId = Integer.parseInt(questionIdStr);

					// 根据questionId从matrixRadioMap中获取之前保存的旧的单选值
					String oldValue = matrixRadioMap.get(questionId);

					if (oldValue == null) {
						// 如果旧值是空的，说明是第一次保存，保存当前值即可
						oldValue = paramValArr[0];

					} else {
						// 如果旧值不为空，那么就将新的值追加到旧值后面，然后再保存
						oldValue = oldValue + "," + paramValArr[0];

					}

					// 会将原来的值覆盖
					matrixRadioMap.put(questionId, oldValue);

				} else {
					// 其他所有情况
					// 解析得到questionId
					// paramName:question+questionId
					String questionIdStr = paramName.substring(8);
					Integer questionId = Integer.parseInt(questionIdStr);
					answer.setQuestionId(questionId);

					// mainAnswers
					String mainAnswers = DataProcessUtils
							.convertArrToStr(paramValArr);
					answer.setMainAnswers(mainAnswers);

					answerList.add(answer);
				}
			}

		}

		Set<Entry<Integer, String>> entrySet = matrixRadioMap.entrySet();
		
		for (Entry<Integer, String> entry : entrySet) {
			
			Integer questionId = entry.getKey();
			String mainAnswers = entry.getValue();
			
			Answer answer = new Answer(null, questionId, surveyId, answerTime,
					uuid, mainAnswers, null);
			
			answerList.add(answer);
		}

		answerDao.batchSaveAnswerList(answerList);
	}

}
