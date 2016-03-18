package cn.farquer.survey.guest.component.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.farquer.survey.base.impl.BaseAction;
import cn.farquer.survey.guest.component.service.interfaces.QuestionService;
import cn.farquer.survey.guest.entity.Question;

/**
 * 问题的Action类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午6:42:16
 */

@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {

	// *******************************成员变量区*************************************

	private static final long serialVersionUID = 4132876302395483796L;

	@Autowired
	private QuestionService questionService;

	private Integer bagId;

	private Integer questionId;

	// *******************************Action方法区*************************************

	/**
	 * 移除问题方法，删除问题
	 * 
	 * @return
	 */
	public String remove() {

		questionService.deleteEntity(t);

		return "toSurveyDesignAction";
	}

	/**
	 * 更新或保存操作
	 * 
	 * @return
	 */
	public String saveOrUpdate() {

		questionService.saveOrUpdateEntity(t);

		return "toSurveyDesignAction";
	}

	/**
	 * 跳转到问题设计页面的前置方法，如果存在questionId说明是更新问题
	 * 需要通过questionId获取到question对象放入栈顶，用于表单回显
	 */
	public void prepareToQuestionDesign() {
		if (questionId != null) {
			this.t = questionService.getEntityById(questionId);
		}
	}

	/**
	 * 跳转到问题设计页面
	 * 
	 * @return
	 */
	public String toQuestionDesign() {
		return "toQuestionDesignPage";
	}

	/**
	 * 跳转到问题类型选择页面
	 * 
	 * @return
	 */
	public String toTypeChoosen() {
		return "toTypeChoosenPage";
	}

	// *******************************getXxx()、setXxx()方法区*************************************

	public Integer getBagId() {
		return bagId;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

}
