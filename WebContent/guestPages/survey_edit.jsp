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

				<div>
					<s:form namespace="/Guest" action="SurveyAction_update"
						enctype="multipart/form-data">
						<s:hidden name="surveyId" />

						<div id="content">
							<div style="text-align: center">
								<span class="errorMsg" style="color: red"> <s:if
										test="hasActionErrors()">
										<s:actionerror />
									</s:if>
								</span>
							</div>
							<table>
								<tr>
									<th colspan="2" align="center">更新调查</th>
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
									<td>目前的LOGO是</td>
									<td><s:if test="isLogoExists(logoPath)">
											<img src="<s:url value="%{logoPath}"/>" />
										</s:if> <s:else>
											<img src="<s:url value="/resources_static/logo.png"/>">
										</s:else></td>
								</tr>
								<tr>
									<td>调查LOGO</td>
									<td><s:file name="logo" /></td>
								</tr>
								<tr>
									<td colspan="2" align="center"><s:submit value="更新"
											id="survey_edit_commit" /></td>
								</tr>
							</table>
						</div>
					</s:form>
				</div>

			</div>
		</div>
	</div>

	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>