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
		<div id="content">
		<s:form namespace="/Guest" action="SurveyAction_save"
			enctype="multipart/form-data">
				<div style="text-align: center">
				<span class="errorMsg" style="color:red">
					<s:if test="hasActionErrors()">
						<s:actionerror />
					</s:if>
				</span>
				</div>
			<table cellspacing="0">
				<tr>
					<th colspan="2" align="center">创建新的调查</th>
				</tr>
				<s:if test="hasFieldErrors()">
					<tr>
						<td colspan="2" align="center"><s:fielderror name="logo" /></td>
					</tr>
				</s:if>
				<tr>
					<td>调查标题</td>
					<td><s:textfield name="title" cssClass="longInput" /></td>
				</tr>
				<tr>
					<td>选择LOGO</td>
					<td><s:file name="logo" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><s:submit value="创建" id="survey_create_commit" /></td>
				</tr>
			</table>
		</s:form>
		</div>
	</div>
	</div>
	</div>

	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>