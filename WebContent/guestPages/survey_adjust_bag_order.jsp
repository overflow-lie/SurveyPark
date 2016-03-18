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

	<div class="block-div navigatorDiv">
		<div class="locationDiv">调整调查中包裹的顺序</div>
		<div class="backToIndexDiv">
			<s:a namespace="/Guest"
				action="SurveyAction_design?surveyId=%{surveyId}">返回设计调查页面</s:a>
			<s:a namespace="/Guest" action="SurveyAction_myUncompleted">返回我的调查列表</s:a>
		</div>
	</div>

	<div class="block-div navigatorDiv">
		<s:form namespace="/Guest" action="SurveyAction_updateBagOrder">
			<table class="dashedTable">
				<s:hidden name="surveyId" />
				<tr>
					<td colspan="2" align="center">
						<span class="errorMsg" style="color:red">
							<s:if test="hasActionErrors()">
								<s:actionerror />
							</s:if>
						</span>
					</td>
				</tr>
				<s:iterator value="bagSet" status="myStatus">
					<tr>
						<td><s:property value="bagName" /></td>
						<td><s:hidden name="bagList[%{#myStatus.index}].bagId"
								value="%{bagId}" />
								
							 <s:textfield name="bagList[%{#myStatus.index}].bagOrder" value="%{bagOrder}"
								cssClass="middleInput" /></td>
					</tr>
				</s:iterator>
				<tr>
					<td>&nbsp;</td>
					<td><s:submit value="提交" id="survey_bagOrder_commit"/></td>
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