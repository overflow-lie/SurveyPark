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

	<table class="invisibleTable">
		<tr>
			<td>
				<div class="top10Block">
					<span class="leftSpan">最热调查Top10</span> <span class="rightSpan">
						<s:a namespace="/Guest" action="SurveyAction_findAllAvailableSurvey">查看全部可以参与的调查</s:a>
					</span> 
					<br />
					<ul>
						<s:if test="#request.hotTenSurvey == null || #request.hotTenSurvey.size() == 0">
								现在还没有人参与调查！
						</s:if>
						<s:else>
							<s:iterator value="#request.hotTenSurvey">
								<li>
									<s:a namespace="/Guest" action="EngageAction_entry?surveyId=%{surveyId}">
										<s:property value="title" />
									</s:a>
								</li>
							</s:iterator>
						</s:else>
					</ul>
				</div>
			</td>
			<td>
				<div class="top10Block">
					<span class="leftSpan">最新调查Top10</span> <span class="rightSpan">
						<s:a namespace="/Guest" action="SurveyAction_findAllAvailableSurvey">查看全部可以参与的调查</s:a>
					</span>
					<br />
					<ul>
						<s:if test="#request.newTenSurvey == null || #request.newTenSurvey.size() == 0">
							现在还没有新创建的调查！
						</s:if>
						<s:else>
							<s:iterator value="#request.newTenSurvey">
								<li>
									<s:a namespace="/Guest" action="EngageAction_entry?surveyId=%{surveyId}">
										<s:property value="title" />
									</s:a>
								</li>
							</s:iterator>
						</s:else>
					</ul>
				</div>
			</td>
		</tr>
	</table>
	
	</div>
	</div>
	</div>
	

	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>