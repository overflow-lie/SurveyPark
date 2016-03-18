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
					<div class="locationDiv">我发起的调查</div>
				</div>

				<%-- 将Page对象临时压入栈顶 --%>
				<s:push value="#request.page">
					<div class="block-div">
						<table class="dashedTable">
							<s:if test="list == null || list.empty">
								<tr>
									<td>您现在还没有已完成的调查，赶快去创建吧！</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<th>Logo</th>
									<th>调查标题</th>
									<th>调查完成的时间</th>
									<th>查看详情</th>
								</tr>
								<%-- iterator标签执行时，栈顶对象是：Page对象，所以list属性可以直接使用 --%>
								<%-- 遍历过程中栈顶是Survey对象 --%>
								<s:iterator value="list">
									<tr>
										<td><s:if test="isLogoExists(logoPath)">
												<img src="<s:url value="%{logoPath}"/>" />
											</s:if> <s:else>
												<img src="<s:url value="/resources_static/logo.png"/>">
											</s:else></td>
										<td><s:property value="title" /></td>
										<td><s:property value="formatedTime" /></td>
										<td><s:a namespace="/Guest"
												action="StatisticsAction_showSummary?surveyId=%{surveyId}">查看统计结果</s:a>
										</td>
									</tr>
								</s:iterator>
							</s:else>

							<tr>
								<td colspan="5" align="center">
									<%-- 动态设置翻页超链接的URL地址：名称空间+ActionName --%> <%-- set标签在没有指定scope属性时，是直接保存到Map栈中 --%>
									<s:set value="'SurveyAction_myUncompleted'"
										var="pageActionName" /> <s:set value="'/Guest'"
										var="pageNamespace" /> <s:include
										value="/resources_jsp/pageNavigator.jsp" /> <%-- <s:debug/> --%>
								</td>
							</tr>

						</table>
					</div>
				</s:push>

			</div>
		</div>
	</div>

	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>