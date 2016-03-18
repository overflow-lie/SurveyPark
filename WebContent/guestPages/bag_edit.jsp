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

		<s:form namespace="/Guest" action="BagAction_update">

			<s:hidden name="surveyId" value="%{#parameters.surveyId}" />
			<s:hidden name="bagId" />

			<div id="content">
				<div style="text-align: center">
					<span class="errorMsg" style="color: red"> 
						<s:if test="hasActionErrors()">
							<s:actionerror />
						</s:if>
					</span>
				</div>
			<table>
				<tr>
					<th colspan="2" align="center">编辑包裹</th>
				</tr>
				<tr>
					<td>包裹名称</td>
					<td><s:textfield name="bagName" cssClass="longInput" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><s:submit value="更新" id="bag_edit_commit"/></td>
				</tr>
			</table>
			</div>
		</s:form>

	</div>
	</div>
	</div>

	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>