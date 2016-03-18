package cn.farquer.survey.utils;

/**
 * 定义了全局资源名称
 * 
 * @author farquer
 * 
 *         2016年2月17日下午4:49:40
 */
public class GlobalNames {

	/**
	 * 在sessionMap中存储user对象的key
	 */
	public static final String LOGIN_USER = "loginUser";

	/**
	 * 用于分页显示存储的page对象的key
	 */
	public static final String PAGE = "page";

	/**
	 * 用于显示最新top10个调查
	 */
	public static final String NEW_TEN_SURVEY = "newTenSurvey";

	/**
	 * 用于显示最热top10个调查
	 */
	public static final String HOT_TEN_SURVEY = "hotTenSurvey";

	/**
	 * 存入Session中Survey的key
	 */
	public static final String CURRENT_SURVEY = "currentSurvey";

	/**
	 * 存放所有bag的集合的map
	 */
	public static final String ALL_BAG_MAP = "allBagMap";

	/**
	 * 存放在requestMap中文本其他项的textList
	 */
	public static final String TEXT_LIST = "textList";

	/**
	 * 存放在requestMap中的Question对象
	 */
	public static final String QUESTION = "question";

	/**
	 * 保存在SessionMap中的admin对象，用来验证管理员用户
	 */
	public static final String LOGIN_ADMIN = "loginAdmin";

	/**
	 * 新创建的管理员用户集合
	 */
	public static final String NEW_ADMIN_LIST = "newAdminList";

	/**
	 * 所有资源的集合
	 */
	public static final String ALL_RES_LIST = "allResList";

	/**
	 * 所有权限的集合
	 */
	public static final String ALL_AUTH_LIST = "allAuthList";

	/**
	 * 所有角色的集合
	 */
	public static final String ALL_ROLE_LIST = "allRoleList";

	/**
	 * 当前关联的资源的集合
	 */
	public static final String CURRENT_RES_ID_LIST = "currentResIdList";

	/**
	 * 当前关联的权限的集合
	 */
	public static final String CURRENT_AUTH_ID_LIST = "currentAuthIdList";

	/**
	 * 当前关联的角色的集合
	 */
	public static final String CURRENT_ROLE_ID_LIST = "currentRoleIdList";

	public static final String LOG_TABLE_NAME_LIST = "logTableNameList";

	public static final String GLOBAL_MSG = "globalMsg";

}
