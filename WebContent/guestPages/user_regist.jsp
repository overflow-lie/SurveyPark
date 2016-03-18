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

		<s:form namespace="/Guest" action="UserAction_register">
		
			<div class="app-location">
							
				<span class="errorMsg" style="color:red">
					<s:if test="hasActionErrors()">
						<s:actionerror />
					</s:if>
				</span>
			
				<s:textfield name="userName" value="请输入3-16位用户名" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入3-16位用户名';}" />
				
				<s:password name="userPwd" value="请输入6-18位密码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入6-18位密码';}" />
				
				<s:password name="userPwdConfirm" value="请确认输入密码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请确认输入密码';}" />
				
				<s:textfield name="email" value="请输入E-mall" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入E-mall';}" />
				
				<s:textfield name="nickName" value="请输入昵称" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '请输入昵称';}" />
				
				<s:submit value="注册" id="regist_commit"/>
					
			</div>
				
		</s:form>

	</div>
	</div>
	</div>
	
	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>