<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp" />
</head>
<body>

	<s:include value="/resources_jsp/top.jsp" />
	
	<div id="main-wrapper">
	<div class="container">
	<div id="main">
	
		<s:form action="UserAction_pay" namespace="/Guest">
			<%-- 直接注入到栈顶的User对象中 --%>
			<%-- <s:hidden name="userId" value="%{#session.loginUser.userId}"/> --%>
			<div class="app-location">
			
				<span class="errorMsg" style="color:red">
					<s:if test="hasActionErrors()">
						<s:actionerror />
					</s:if>
				</span>
				
				
				<s:textfield name="payAmount" value="请输入充值金额" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入充值金额';}" />

				<s:submit value="充值" id="pay_commit"/>
				
				<input id="regist" type="button" value="返回"
					onclick="window.location.href='${pageContext.request.contextPath }/Guest/ToPageAction_user_myCenter'">
			</div>
			
		</s:form>
	
	</div>
	</div>
	</div>
	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>