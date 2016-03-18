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

	<div class="textAlignCenter">
		<s:if test="hasActionErrors()">
			<s:actionerror />
			<s:a namespace="/Guest"
				action="SurveyAction_design?surveyId=%{surveyId}">继续完善</s:a>
		</s:if>
		<s:else>
			<script type="text/javascript">
				var num=3;
				function redirect(){
					num--;
					document.getElementById("num").innerHTML=num;
					if(num<0){
						document.getElementById("num").innerHTML=0;
						location.href="${pageContext.request.contextPath }/Guest/SurveyAction_top10";
						}
					}
				setInterval("redirect()", 1000);
			</script>
		
			调查创建成功！ <span id="num" style="color:red"></span>秒后自动跳转到主页
		</s:else>
	</div>
	
	</div>
	</div>
	</div>

	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>