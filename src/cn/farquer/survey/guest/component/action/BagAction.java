package cn.farquer.survey.guest.component.action;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.farquer.survey.base.impl.BaseAction;
import cn.farquer.survey.exception.CascadDelBagException;
import cn.farquer.survey.guest.component.interceptor.UserAware;
import cn.farquer.survey.guest.component.service.interfaces.BagService;
import cn.farquer.survey.guest.component.service.interfaces.SurveyService;
import cn.farquer.survey.guest.entity.Bag;
import cn.farquer.survey.guest.entity.Question;
import cn.farquer.survey.guest.entity.Survey;
import cn.farquer.survey.guest.entity.User;
import cn.farquer.survey.model.Page;
import cn.farquer.survey.utils.GlobalNames;

/**
 * 包裹的Action类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午6:42:01
 */

@Controller
@Scope("prototype")
public class BagAction extends BaseAction<Bag> implements UserAware {

	// *******************************成员变量区*************************************

	private static final long serialVersionUID = -8375757954171187164L;

	@Autowired
	private BagService bagService;

	@Autowired
	private SurveyService surveyService;

	private Integer bagId;

	private String pageNoStr;

	private User user;

	// *******************************Action方法区*************************************

	/**
	 * 复制调查的方法
	 * 
	 * @return
	 */
	public String copyToThisSurvey() {

		bagService.copyToThisSurvey(bagId, surveyId);

		return "toSurveyDesignAction";
	}

	/**
	 * 移动调查的方法
	 * 
	 * @return
	 */
	public String moveToThisSurvey() {

		bagService.moveToThisSurvey(bagId, surveyId);

		return "toSurveyDesignAction";
	}

	/**
	 * 跳转到移动和复制包裹页面
	 * 
	 * @return
	 */
	public String toMoveCopyPage() {

		Page<Survey> page = surveyService
				.getUncompletedPage(pageNoStr, user, 5);
		requestMap.put(GlobalNames.PAGE, page);

		return "toMoveCopyPage";
	}

	/**
	 * 更新包裹的方法
	 * 
	 * @return
	 */
	public String update() {

		Survey survey = new Survey();
		survey.setSurveyId(surveyId);
		t.setSurvey(survey);

		bagService.updateEntity(t);
		return "toSurveyDesignAction";
	}

	/**
	 * 跳转到编辑包裹的前置方法，需要通过bagId获取到bag对象放入栈顶来用于表单回显
	 */
	public void prepareEdit() {
		this.t = bagService.getEntityById(bagId);
	}

	/**
	 * 跳转到包裹的编辑页面
	 * 
	 * @return
	 */
	public String edit() {
		return "toEditPage";
	}

	/**
	 * 移除包裹，删除包裹
	 * 
	 * @return
	 */
	public String remove() {

			Bag bag = bagService.getEntityById(t.getBagId());
			
			Set<Question> questions = bag.getQuestions();
			
			if(questions == null || questions.size() == 0){
				
				bagService.deleteEntity(bag);
			}else{
				
				// 声明并且抛出一个自定义异常，声明级联删除出现问题
				throw new CascadDelBagException();
			}
	

		return "toSurveyDesignAction";
	}

	/**
	 * 包裹的保存操作
	 * 
	 * @return
	 */
	public String save() {

		Survey survey = new Survey();
		survey.setSurveyId(surveyId);
		t.setSurvey(survey);
		
		// 保存栈顶bag对象，此时保存之后bag对象就会有主键自增的bagID，但是由于不是在同一个session中，
		// 所以session级别的缓存，持久化对象更新不能自动发送update语句
		bagService.saveEntity(t);
		
		// 保存bag对象之后bag对象中含有自增的主键bagId，设置到bagOrder属性，防止全部置0无法跳转包裹
		t.setBagOrder(t.getBagId());
		
		// 更新bag对象，主要是更新bagOrder属性
		bagService.updateEntity(t);
		
		return "toSurveyDesignAction";
	}

	// *******************************getXxx()、setXxx()方法区*************************************

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}

}
