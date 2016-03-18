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

				<!-- 当前栈顶对象：Survey对象 -->
				<div class="block-div navigatorDiv">
					<div class="locationDiv">
						<s:if test="isLogoExists(logoPath)">
							<img src="<s:url value="%{logoPath}"/>" />
						</s:if>
						<s:else>
							<img src="<s:url value="/resources_static/logo.png"/>">
						</s:else>

						<s:property value="title" />
					</div>
					<div class="backToIndexDiv">
						<s:a namespace="/Guest"
							action="ToPageAction_bag_add?surveyId=%{surveyId}">添加包裹</s:a>
						<s:a namespace="/Guest" action="SurveyAction_myUncompleted">返回未完成调查</s:a>
						<s:if test="bagSet.size > 0">
							<s:a namespace="/Guest"
								action="SurveyAction_adjustBagOrder?surveyId=%{surveyId}">调整包裹顺序</s:a>
						</s:if>
					</div>
				</div>

				<div class="block-div">
					<table class="dashedTable">
						<s:if test="bagSet == null || bagSet.size() == 0">
							<tr>
								<td>当前调查还没有创建包裹</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="bagSet">
								<!-- 遍历的过程中，栈顶对象是Bag -->
								<tr>
									<td><s:property value="bagName" /></td>
									<td><s:a namespace="/Guest"
											action="QuestionAction_toTypeChoosen?bagId=%{bagId}&surveyId=%{surveyId}">添加问题</s:a>
										<s:a namespace="/Guest"
											action="BagAction_edit?bagId=%{bagId}&surveyId=%{surveyId}">编辑包裹</s:a>
										<s:a namespace="/Guest"
											action="BagAction_remove?bagId=%{bagId}&surveyId=%{surveyId}">删除包裹</s:a>
										<s:a namespace="/Guest"
											action="BagAction_toMoveCopyPage?bagId=%{bagId}&surveyId=%{surveyId}">
											<span style="color: red">移动/复制</span>
										</s:a></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td><s:include value="/guestPages/question_detail.jsp" /></td>
								</tr>
							</s:iterator>
						</s:else>
						<tr>
							<td colspan="2" align="center"><s:a namespace="/Guest"
									action="SurveyAction_complete?surveyId=%{surveyId}">完成调查</s:a></td>
						</tr>
					</table>
				</div>

			</div>
		</div>
	</div>

	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>