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


		<s:form namespace="/Guest" action="QuestionAction_toQuestionDesign">
			<s:hidden name="bagId" />
			<s:hidden name="surveyId" />
			<s:hidden name="questionId" />
			<div id="content">
			<table>

				<tr>
					<th colspan="2">选择题型</th>
				</tr>
				<tr>
					<td>题型</td>
					<td style="text-align: left;"><input id="radio01" type="radio"
						name="questionType" value="0"
						<s:if test="questionType == 0">checked="checked"</s:if> />
						<label for="radio01">单选题</label> <br /> <input id="radio02"
						type="radio" name="questionType" value="1"
						<s:if test="questionType == 1">checked="checked"</s:if> />
						<label for="radio02">多选题</label> <br /> <input id="radio03"
						type="radio" name="questionType" value="2"
						<s:if test="questionType == 2">checked="checked"</s:if> />
						<label for="radio03">下拉列表选择题</label> <br /> <input id="radio04"
						type="radio" name="questionType" value="3"
						<s:if test="questionType == 3">checked="checked"</s:if> />
						<label for="radio04">简答题</label> <br /> <input id="radio05"
						type="radio" name="questionType" value="4"
						<s:if test="questionType == 4">checked="checked"</s:if> />
						<label for="radio05">矩阵单选题</label> <br /> <input id="radio06"
						type="radio" name="questionType" value="5"
						<s:if test="questionType == 5">checked="checked"</s:if> />
						<label for="radio06">矩阵多选题</label> <br /> <input id="radio07"
						type="radio" name="questionType" value="6"
						<s:if test="questionType == 6">checked="checked"</s:if> />
						<label for="radio07">矩阵下拉列表选择题</label> <br /></td>
				</tr>
				<tr>
					<td colspan="2"><s:submit value="OK" /></td>
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