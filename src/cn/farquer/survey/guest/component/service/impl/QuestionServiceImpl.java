package cn.farquer.survey.guest.component.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.farquer.survey.base.impl.BaseServiceImpl;
import cn.farquer.survey.guest.component.dao.interfaces.QuestionDao;
import cn.farquer.survey.guest.component.service.interfaces.QuestionService;
import cn.farquer.survey.guest.entity.Question;

/**
 * 问题的业务逻辑层实现类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午6:38:30
 */
@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements
		QuestionService {

	@Autowired
	private QuestionDao questionDao;

}
