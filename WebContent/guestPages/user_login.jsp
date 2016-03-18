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

		<s:form action="UserAction_login" namespace="/Guest">
			<div class="app-location">

				<span class="errorMsg" style="color:red">
					<s:if test="hasActionErrors()">
						<s:actionerror />
					</s:if>
				</span>

				<s:textfield name="userName" value="请输入用户名" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入用户名';}"/><br />

				<s:password name="userPwd" value="请输入密码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入密码';}"/><br />
				
				<s:submit value="登录" id="login_commit"/> &nbsp;
				
				<input id="regist" type="button" value="注册"
					onclick="window.location.href='${pageContext.request.contextPath }/guestPages/user_regist.jsp'">
			
					
			</div>
		</s:form>
			</div>
		</div>
	</div>

	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>