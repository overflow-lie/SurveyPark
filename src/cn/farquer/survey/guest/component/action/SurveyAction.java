package cn.farquer.survey.guest.component.action;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.farquer.survey.base.impl.BaseAction;
import cn.farquer.survey.exception.CascadDelSurveyException;
import cn.farquer.survey.guest.component.interceptor.UserAware;
import cn.farquer.survey.guest.component.service.interfaces.BagService;
import cn.farquer.survey.guest.component.service.interfaces.SurveyService;
import cn.farquer.survey.guest.entity.Bag;
import cn.farquer.survey.guest.entity.Survey;
import cn.farquer.survey.guest.entity.User;
import cn.farquer.survey.model.Page;
import cn.farquer.survey.utils.DataProcessUtils;
import cn.farquer.survey.utils.GlobalNames;
import cn.farquer.survey.utils.ValidateUtils;

/**
 * 调查的Action类
 * 
 * @author farquer
 * 
 *         2016年2月17日下午6:42:29
 */

@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware {

	// *******************************成员变量区*************************************
	private static final long serialVersionUID = -2018295513477578782L;

	private File logo; // 调查LOGO图片文件
	private String logoContentType; // LOGO图片文件类型
	private String logoFileName; // LOGO图片文件名

	private String pageNoStr; // 分页的当前页码数

	@Autowired
	private SurveyService surveyService; // 自动注入的surveyService，依赖泛型注入

	@Autowired
	private BagService bagService; // 自动注入的BagService，依赖泛型注入

	private User user; // 需要关联的User对象

	private List<Bag> bagList; // 为了实现包裹排序，保存包裹ID和排序ID的集合

	// *******************************Action方法区*************************************

	/**
	 * 我参与的调查
	 * 
	 * @return
	 */
	public String myEngagedSurvey() {

		Page<Survey> page = surveyService.getMyEngagedSurvey(user, pageNoStr,
				10);
		requestMap.put(GlobalNames.PAGE, page);

		return "toMyEngagedSurvey";
	}

	/**
	 * 查看所有可以参加的调查
	 * 
	 * @return
	 */
	public String findAllAvailableSurvey() {

		Page<Survey> page = surveyService.findAllAvailableSurvey(pageNoStr, 30);
		requestMap.put(GlobalNames.PAGE, page);

		return "toAllAvailableSurveyPage";
	}

	/**
	 * 获取最新和最热的top10的action
	 * 
	 * @return
	 */
	public String top10() {

		List<Survey> newestList = surveyService.findNewestTenSurvey();
		List<Survey> hotestList = surveyService.findHotestTenSurvey();

		requestMap.put(GlobalNames.NEW_TEN_SURVEY, newestList);
		requestMap.put(GlobalNames.HOT_TEN_SURVEY, hotestList);

		return "toTop10Page";
	}

	/**
	 * 更新排序的前置方法，通过surveyId把Survey对象获取到并且放入栈顶用于表单回显
	 */
	public void prepareUpdateBagOrder() {

		this.t = surveyService.getEntityById(surveyId);
	}

	/**
	 * 更新包裹排序的方法
	 * 
	 * @return
	 */
	public String updateBagOrder() {

		if (!ValidateUtils.bagListValidate(bagList)) {
			addActionError("请不要输入重复的包裹序列");
			return "toAdjustBagOrderPage";
		}
		bagService.batchUpdateBagOrder(bagList);

		return "toSurveyDesignAction";
	}

	/**
	 * 跳转修改包裹页面的前置方法，通过surveyId将survey对象取出放入栈顶
	 */
	public void prepareAdjustBagOrder() {
		this.t = surveyService.getEntityById(surveyId);
	}

	/**
	 * 跳转到修改包裹顺序页面
	 * 
	 * @return
	 */
	public String adjustBagOrder() {

		return "toAdjustBagOrderPage";
	}

	/**
	 * 检查当前调查是否完整
	 * 
	 * @return
	 */
	public String complete() {

		boolean completeResult = surveyService.complete(surveyId);

		if (!completeResult) {
			addActionError("调查还不完整！");
		}

		return "toSurveyMessagePage";
	}

	/**
	 * 将Survey对象从数据库中查询出来并放到栈顶，便于设计调查页面显示
	 */
	public void prepareDesign() {

		this.t = surveyService.getEntityById(surveyId);
	}

	/**
	 * 跳转设计页面
	 * 
	 * @return
	 */
	public String design() {
		return "toDesignPage";
	}

	/**
	 * 使用从数据库中取出的Survey对象来接收请求参数，从而保持userId不变
	 */
	public void prepareUpdate() {
		this.inputPath = "/guestPages/survey_edit.jsp";
		this.t = surveyService.getEntityById(surveyId);
	}

	/**
	 * 修改未完成的调查，更新调查
	 * 
	 * @return
	 */
	public String update() {

		this.inputPath = "/guestPages/survey_edit.jsp";

		// 声明图片上传后的虚拟路径
		String virtualPath = "/surveyLogos";

		// 将虚拟路径转换为真实路径
		String realPath = this.servletContext.getRealPath(virtualPath);

		// 实现文件复制，并返回一个可用的logoPath
		String logoPath = DataProcessUtils.resizeImages(logo, realPath);

		// 仅在logoPath有效的前提下才进行设置，否则保持默认值
		if (ValidateUtils.stringValidate(logoPath)) {

			String realPath2 = this.servletContext.getRealPath(t.getLogoPath());

			// 删除之前的图片文件，防止占用服务器资源
			new File(realPath2).delete();

			this.t.setLogoPath(logoPath);

		}

		// 此时栈顶对象是：this.t，实际上就是Survey对象，而且是已经注入了请求参数的Survey对象
		surveyService.updateEntity(t);

		return "toMyUncompletedAction";
	}

	/**
	 * 此时surveyId值的注入要求在prepare拦截器前面有params拦截器 所以要使用paramsPrepareParamsStack拦截器栈
	 * 通过surveryId获取持久化对象放入栈顶，用于表单回显
	 */
	public void prepareEditSurvey() {
		this.t = surveyService.getEntityById(surveyId);
	}

	/**
	 * 跳转到编辑调查页面
	 * 
	 * @return
	 */
	public String editSurvey() {
		return "toEditSurveyPage";
	}

	/**
	 * 移除调查，删除调查
	 * 
	 * @return
	 */
	public String remove() {

			
			// 从数据库中获取需要删除的surevy对象
			Survey survey = surveyService.getEntityById(surveyId);
			
			Set<Bag> bagSet = survey.getBagSet();
			
			if(bagSet == null || bagSet.size() == 0){
				
				// 获取图片的真实路径
				String realPath = this.servletContext.getRealPath(survey
						.getLogoPath());
				
				// 删除对象
				surveyService.deleteEntity(survey);
				
				// 创建LOGO的File对象并且删除
				new File(realPath).delete();
				
			}else{
				
				// 声明并且抛出一个自定义异常，声明级联删除出现问题
				throw new CascadDelSurveyException();
			}


		return "toMyUncompletedAction";
	}

	/**
	 * 到我发起的调查
	 * 
	 * @return
	 */
	public String myCompletedSurveyList() {

		Page<Survey> page = surveyService.getCompletedPage(pageNoStr, user, 5);
		requestMap.put(GlobalNames.PAGE, page);

		return "toCompletedSurveyList";
	}

	/**
	 * 到未完成的调查页面，存在分页显示
	 * 
	 * @return
	 */
	public String myUncompleted() {

		Page<Survey> page = surveyService
				.getUncompletedPage(pageNoStr, user, 5);
		requestMap.put(GlobalNames.PAGE, page);

		return "toUncompletedListPage";
	}

	/**
	 * 保存操作的前置方法
	 */
	public void prepareSave() {
		this.inputPath = "/guestPages/survey_create.jsp";
	}

	/**
	 * 保存调查的方法
	 * 
	 * @return
	 */
	public String save() {

		// 在Action方法中执行这个操作并不能生效，原因是：真正检测到上传错误时，不执行目标Action的目标方法
		// this.inputPath = "/guestPages/survey_create.jsp";

		// 遗留问题1：没有关联到User
		// 关联的方式：survey.setUser(user);
		// User的来源：Session域中——在实现UserAware接口后可以由LoginInterceptor拦截器主动注入
		// User user = (User) session.getAttribute(GlobalNames.LOGIN_USER);
		this.t.setUser(user);

		// 1.收集创建调查时需要提供的数据
		// ①调查的标题：已经被Struts2的模型驱动机制注入到栈顶的t对象值中
		// ②将来页面上显示图片时需要用到的图片路径：需要将原始的图片压缩后获取其可保存的路径值
		String virtualPath = "/surveyLogos";

		String realPath = this.servletContext.getRealPath(virtualPath);

		String logoPath = DataProcessUtils.resizeImages(logo, realPath);

		// 仅在logoPath有效的前提下才进行设置，否则保持默认值
		if (ValidateUtils.stringValidate(logoPath)) {
			this.t.setLogoPath(logoPath);
		}

		// 2.将收集了必要数据的Survey对象保存到数据库中
		this.surveyService.saveEntity(t);

		return "toMyUncompletedAction";
	}

	// *******************************getXxx()、setXxx()方法区*************************************
	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public String getLogoContentType() {
		return logoContentType;
	}

	public void setLogoContentType(String logoContentType) {
		this.logoContentType = logoContentType;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public void setPageNoStr(String pageNoStr) {
		this.pageNoStr = pageNoStr;
	}

	public List<Bag> getBagList() {
		return bagList;
	}

	public void setBagList(List<Bag> bagList) {
		this.bagList = bagList;
	}

}
