<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/head.jsp" />
<script type="text/javascript">
	$(function() {

		$("#goBack").click(function() {

			window.history.back();

		});

	});
</script>
</head>
<body>

	<s:include value="/resources_jsp/top.jsp" />
	
	<div id="main-wrapper">
	<div class="container">
	<div id="main">

	<div class="textAlignCenter">
		<s:property value="getText(exception.class.name)" />
		<br />
		<s:if test="hasActionErrors()">
			<s:actionerror />
		</s:if>
		<s:if test="#request.globalMsg != null">
			<s:property value="#request.globalMsg" />
		</s:if>
		<s:else>
			<span style="color:red">对不起，系统出现问题，请重试！</span>
		</s:else>
		<br />
		<button id="goBack">返回上一个操作</button>
	</div>
	
	</div>
	</div>
	</div>

	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>