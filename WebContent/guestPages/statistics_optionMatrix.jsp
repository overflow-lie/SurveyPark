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
		<div class="locationDiv">
			<s:property value="#request.question.questionName" />
		</div>
	</div>

	<div class="block-div">
		<table class="dashedTable">

			<tr>
				<td></td>
				<s:iterator value="#request.question.matrixColTitlesArray">
					<td><s:property /></td>
				</s:iterator>
			</tr>

			<s:iterator value="#request.question.matrixRowTitlesArray"
				status="rowStatus">

				<tr>
					<td><s:property /></td>

					<s:iterator begin="1"
						end="#request.question.matrixColTitlesArray.length"
						status="colStatus">

						<td><img
							src="<s:url namespace="/Guest" action="StatisticsAction_showOptionMatrixChart?questionId=%{#request.question.questionId}&rowNumber=%{#rowStatus.index}&colNumber=%{#colStatus.index}"/>">
						</td>

					</s:iterator>

				</tr>

			</s:iterator>

		</table>
	</div>
	
	</div>
	</div>
	</div>
	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>