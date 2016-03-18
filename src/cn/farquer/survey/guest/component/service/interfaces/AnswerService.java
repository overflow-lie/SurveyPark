package cn.farquer.survey.guest.component.service.interfaces;

import java.util.Map;

import cn.farquer.survey.base.interfaces.BaseService;
import cn.farquer.survey.guest.entity.Answer;

/**
 * 答案的业务逻辑层声明方法的接口类
 * 
 * @author farquer
 * 
 *         2016年2月23日上午11:01:19
 */
public interface AnswerService extends BaseService<Answer> {

	// 解析和保存答案，通过传入所有包裹的map，在方法内部解析和保存所提交的答案
	void parseAndSave(Map<Integer, Map<String, String[]>> allBagMap,
			Integer surveyId);

}
