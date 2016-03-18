package cn.farquer.survey.base.impl;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.Map;





import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import cn.farquer.survey.utils.ValidateUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 所有Action的父类，封装了Action中常用的功能
 * 
 * @author farquer
 * 
 *         2016年1月27日下午6:39:58
 */

@SuppressWarnings("unchecked")
public class BaseAction<T> extends ActionSupport implements RequestAware,
		SessionAware, ApplicationAware, ParameterAware, ServletRequestAware,
		ServletContextAware, ModelDriven<T>, Preparable {

	private static final long serialVersionUID = -8687670854799939397L;
	private Class<T> entityType;
	protected T t;
	protected Map<String, Object> requestMap;
	protected Map<String, Object> sessionMap;
	protected Map<String, Object> applicationMap;
	protected Map<String, String[]> parametersMap;
	protected HttpServletRequest servletRequest;
	protected ServletContext servletContext;

	protected String inputPath;

	protected Integer surveyId; // 调查ID，因为许多Action中都需要，所以直接放入baseAction中

	// 通过反射创建泛型类对象，通过DrivenModel将t对象压入栈顶
	public BaseAction() {
		entityType = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		try {
			t = entityType.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void prepare() throws Exception {
	}

	@Override
	public T getModel() {
		return t;
	}

	@Override
	public void setServletContext(ServletContext context) {
		servletContext = context;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		servletRequest = request;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		parametersMap = parameters;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		applicationMap = application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		sessionMap = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		requestMap = request;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getInputPath() {
		return inputPath;
	}

	public boolean isLogoExists(String logoPath) {

		if (ValidateUtils.stringValidate(logoPath)) {
			String realPath = servletContext.getRealPath(logoPath);
			return new File(realPath).exists();
		}

		return false;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

}
