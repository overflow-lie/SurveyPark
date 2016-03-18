package cn.farquer.survey.admin.advisor;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;

import cn.farquer.survey.admin.component.service.interfaces.LogService;
import cn.farquer.survey.admin.entity.Admin;
import cn.farquer.survey.admin.entity.Log;
import cn.farquer.survey.guest.entity.User;
import cn.farquer.survey.utils.GlobalNames;

import com.opensymphony.xwork2.ActionContext;

/**
 * 日志记录仪，配置切面AOP来进行日志操作
 * 
 * @author farquer
 * 
 *         2016年3月1日上午8:45:53
 */
public class LogAspect {

	private LogService logService;

	//使用环绕通知需要参数ProceedingJoinPoint joinPoint，其他通知可以使用JoinPoint
	public Object record(ProceedingJoinPoint joinPoint) {

		// 调用本类方法获取日志的操作人
		String operator = getOperator();
		
		// 获取当前操作时间，使用SimpleDateFormat进行格式化
		String operateTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss")
				.format(new Date());

		// 声明目标方法的类型
		String methodType = joinPoint.getSignature().getDeclaringTypeName();

		// 目标方法的方法名
		String methodName = joinPoint.getSignature().getName();

		// 传递给目标方法的参数列表
		Object[] args = joinPoint.getArgs();

		// 将目标方法的参数数组转换成字符串，用","连接
		String methodArgs = Arrays.asList(args).toString();// [1,2,3]

		String methodReturnValue = null;
		String methodResultMsg = null;

		Object result = null;

		try {
			// 执行被代理目标方法
			result = joinPoint.proceed(args);

			methodReturnValue = (result == null) ? "无" : result.toString();
			methodResultMsg = "成功";

		} catch (Throwable e) {

			methodResultMsg = "失败" + e.getMessage();

		} finally {
			// 创建日志对象
			Log log = new Log(operator, operateTime, methodType, methodName,
					methodArgs, methodReturnValue, methodResultMsg);
			// 存日志对象
			logService.saveLog(log);
		}

		return result;
	}

	public String getOperator() {

		//从Session域中获取当前登录的Admin或者User
		Admin admin = (Admin) ActionContext.getContext().getSession()
				.get(GlobalNames.LOGIN_ADMIN);
		User user = (User) ActionContext.getContext().getSession()
				.get(GlobalNames.LOGIN_USER);

		//三目运算符进行拼串来保存Admin或者User的Name
		String adminPart = (admin == null) ? "[]" : "[" + admin.getAdminName()
				+ "]";
		String userPart = (user == null) ? "[]" : "[" + user.getUserName()
				+ "]";

		return adminPart + userPart;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
