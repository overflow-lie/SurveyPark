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
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>
										<table class="dashedTable">
											<s:if test="questions == null || questions.size() == 0">
												<tr>
													<td>当前包裹还没有创建问题！</td>
												</tr>
											</s:if>
											<s:else>
												<s:iterator value="questions">
													<%-- 遍历问题集合 --%>
													<%-- 栈顶对象：Question --%>
													<tr>
														<td><s:property value="questionName" /></td>
														<td><s:if
																test="questionType == 0 || questionType == 1 || questionType == 2">
																<s:a namespace="/Guest"
																	action="StatisticsAction_showNormalChart?questionId=%{questionId}"
																	target="_blank">显示统计结果</s:a>
																<s:if test="hasOther && otherType == 1">
																	<s:a namespace="/Guest"
																		action="StatisticsAction_showOtherTextList?questionId=%{questionId}"
																		target="_blank">以文本列表形式查看其他项的统计结果</s:a>
																</s:if>
															</s:if> <s:if test="questionType == 3">
																<s:a namespace="/Guest"
																	action="StatisticsAction_showTextList?questionId=%{questionId}"
																	target="_blank">以文本列表形式查看简答题的统计结果</s:a>
															</s:if> <s:if test="questionType == 4 || questionType == 5">
																<s:a namespace="/Guest"
																	action="StatisticsAction_showNormalMatrixPage?questionId=%{questionId}"
																	target="_blank">查看矩阵式问题的统计结果</s:a>
															</s:if> <s:if test="questionType == 6">
																<s:a namespace="/Guest"
																	action="StatisticsAction_showOptionMatrixPage?questionId=%{questionId}"
																	target="_blank">查看矩阵式问题的统计结果</s:a>
															</s:if></td>
													</tr>

												</s:iterator>
											</s:else>
										</table>
									</td>
								</tr>
							</s:iterator>
						</s:else>
					</table>
				</div>

			</div>
		</div>
	</div>

	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>