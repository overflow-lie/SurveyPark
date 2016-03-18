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
					<div class="locationDiv">所有可用的调查</div>
				</div>
				<s:push value="#request.page">
					<div class="block-div">
						<table class="dashedTable">
							<s:if test="list == null || list.empty">
								<tr>
									<td>现在还没有可用的调查</td>
								</tr>
							</s:if>
							<s:else>
								<%-- iterator标签执行时，栈顶对象是：Page对象，所以list属性可以直接使用 --%>
								<%-- 遍历过程中栈顶是Survey对象 --%>
								<s:iterator value="list" status="myStatus">
									<%-- 每一个Survey对象对应一个单元格，单元格中只包括Logo图片和title --%>
									<s:if
										test="(#myStatus.index % 5 == 0) && (#myStatus.count < list.size())">
										<tr>
									</s:if>
									<td align="center">
										<%-- 【<s:property value="#myStatus.count"/>】【<s:property value="list.size()"/>】 --%>
										<s:a namespace="/Guest"
											action="EngageAction_entry?surveyId=%{surveyId}">
											<s:if test="isLogoExists(logoPath)">
												<img src="<s:url value="%{logoPath}"/>" />
											</s:if>
											<s:else>
												<img src="<s:url value="/resources_static/logo.png"/>">
											</s:else>
											<br />
											<s:property value="title" />
										</s:a>
									</td>
									<s:if
										test="(#myStatus.count % 5 == 0) || (#myStatus.count == list.size())">
										</tr>
									</s:if>
								</s:iterator>
							</s:else>

							<tr>
								<td colspan="5" align="center">
									<%-- 动态设置翻页超链接的URL地址：名称空间+ActionName --%> <%-- set标签在没有指定scope属性时，是直接保存到Map栈中 --%>
									<s:set value="'SurveyAction_findAllAvailableSurvey'"
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