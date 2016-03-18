package cn.farquer.survey.guest.component.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.farquer.survey.base.impl.BaseAction;
import cn.farquer.survey.guest.component.interceptor.UserAware;
import cn.farquer.survey.guest.component.service.interfaces.AnswerService;
import cn.farquer.survey.guest.component.service.interfaces.BagService;
import cn.farquer.survey.guest.component.service.interfaces.SurveyService;
import cn.farquer.survey.guest.entity.Bag;
import cn.farquer.survey.guest.entity.Survey;
import cn.farquer.survey.guest.entity.User;
import cn.farquer.survey.utils.DataProcessUtils;
import cn.farquer.survey.utils.GlobalNames;

/**
 * 包裹入口action
 * 
 * @author farquer
 * 
 *         2016年2月20日上午11:35:34
 */

@Controller
@Scope("prototype")
public class EngageAction extends BaseAction<Survey> implements UserAware {

	// ***************************成员变量区*****************************

	private static final long serialVersionUID = -4434535018524242654L;

	private Bag currentBag;
	private Integer bagId;
	@Autowired
	private BagService bagService;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private AnswerService answerService;

	private User user;

	// *************************Action方法区***************************

	/**
	 * 包裹翻页以及放弃和提交操作
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String doEngage() {

		// 0.将allBagMap从Session域中取出备用，在点击“上一个”、“下一个”、“完成”时都会提交当前包裹的答案数据
		// Action中接收到这些数据时应该立即保存到allBagMap中：合并答案
		Map<Integer, Map<String, String[]>> allBagMap = (Map<Integer, Map<String, String[]>>) this.sessionMap
				.get(GlobalNames.ALL_BAG_MAP);

		// 1.获取用户在当前请求中点击的提交按钮的name属性值
		String submitName = DataProcessUtils.getSubmitName(parametersMap);

		// 2.如果submitName是submit_prev则查询上一个包裹
		if ("submit_prev".equals(submitName)) {

			allBagMap.put(bagId, parametersMap);

			// 要保证是在同一个调查范围内，所以要传入surveyId
			// 要找当前包裹的上一个包裹，所以要传入当前包裹的id
			currentBag = bagService.getPrevBag(surveyId, bagId);

		}

		// 3.如果submitName是submit_next则查询下一个包裹
		if ("submit_next".equals(submitName)) {

			allBagMap.put(bagId, parametersMap);

			currentBag = bagService.getNextBag(surveyId, bagId);

		}

		// 4.如果submitName是submit_quit则清理Session域，回到首页
		if ("submit_quit".equals(submitName)) {

			this.sessionMap.remove(GlobalNames.CURRENT_SURVEY);
			this.sessionMap.remove(GlobalNames.ALL_BAG_MAP);

			return "toTop10Action";

		}

		// 5.如果submitName是submit_done则保存答案
		if ("submit_done".equals(submitName)) {

			allBagMap.put(bagId, parametersMap);

			answerService.parseAndSave(allBagMap, surveyId);

			try {
				// 如果不能保存，则说明数据已经存在，那么就无需再保存重复数据了
				surveyService.saveEngageMsg(user.getUserId(), surveyId);
			} catch (Exception e) {
			}

			return "toEngageSuccessPage";
		}

		return "toEngagePage";
	}

	/**
	 * 将Survey对象放入Session中并且查找第一个包裹
	 * 
	 * @return
	 */
	public String entry() {

		// 1.查询Survey对象
		Survey survey = surveyService.getEntityById(surveyId);

		// 2.将Survey对象保存到Session域中
		this.sessionMap.put(GlobalNames.CURRENT_SURVEY, survey);

		// 3.创建保持各个包裹答案数据的Map，并保存到Session域中
		Map<Integer, Map<String, String[]>> allBagMap = new HashMap<>();
		this.sessionMap.put(GlobalNames.ALL_BAG_MAP, allBagMap);

		// 4.查找第一个包裹
		currentBag = bagService.getFirstBag(surveyId);

		return "toEngagePage";
	}

	// ***********************Getter、Setter区*************************

	public Bag getCurrentBag() {
		return currentBag;
	}

	public void setCurrentBag(Bag currentBag) {
		this.currentBag = currentBag;
	}

	public Integer getBagId() {
		return bagId;
	}

	public void setBagId(Integer bagId) {
		this.bagId = bagId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
