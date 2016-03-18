package cn.farquer.survey.admin.entity;

public class Log {

	private Integer logId; // 日志Id
	private String operator; // 操作人
	private String operateTime; // 操作时间
	private String methodType; // 操作方法类型
	private String methodName; // 方法名
	private String methodArgs; // 方法参数
	private String methodReturnValue; // 方法返回值
	private String methodResultMsg; // 方法执行结果，方法执行成功还是失败，如果失败保存异常信息

	public Log() {
	}

	public Log(String operator, String operateTime, String methodType,
			String methodName, String methodArgs, String methodReturnValue,
			String methodResultMsg) {
		this.operator = operator;
		this.operateTime = operateTime;
		this.methodType = methodType;
		this.methodName = methodName;
		this.methodArgs = methodArgs;
		this.methodReturnValue = methodReturnValue;
		this.methodResultMsg = methodResultMsg;
	}

	public Log(Integer logId, String operator, String operateTime,
			String methodType, String methodName, String methodArgs,
			String methodReturnValue, String methodResultMsg) {
		this.logId = logId;
		this.operator = operator;
		this.operateTime = operateTime;
		this.methodType = methodType;
		this.methodName = methodName;
		this.methodArgs = methodArgs;
		this.methodReturnValue = methodReturnValue;
		this.methodResultMsg = methodResultMsg;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodArgs() {
		return methodArgs;
	}

	public void setMethodArgs(String methodArgs) {
		this.methodArgs = methodArgs;
	}

	public String getMethodReturnValue() {
		return methodReturnValue;
	}

	public void setMethodReturnValue(String methodReturnValue) {
		this.methodReturnValue = methodReturnValue;
	}

	public String getMethodResultMsg() {
		return methodResultMsg;
	}

	public void setMethodResultMsg(String methodResultMsg) {
		this.methodResultMsg = methodResultMsg;
	}

}
