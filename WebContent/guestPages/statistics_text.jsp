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
		<div class="locationDiv">显示答案详情</div>
	</div>

	<div class="block-div">
		<table class="dashedTable">
			<s:if
				test="#request.textList == null || #request.textList.size() == 0">
				<tr>
					<td>没有数据！</td>
				</tr>
			</s:if>
			<s:else>
				<s:iterator value="#request.textList">
					<tr>
						<td><s:property /></td>
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