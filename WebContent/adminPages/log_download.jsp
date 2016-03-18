<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/admin_head.jsp" />
</head>
<body>

	<s:include value="/resources_jsp/admin_top.jsp" />
	
		 <!-- Container Wrapper -->
        <div id="mws-container" class="clearfix">       
        	<!-- Main Container -->
            <div class="container">
            

	<div class="block-div" style="text-align: center;">
	
		<div id="content">
		<table>
		<s:if
			test="#request.logTableNameList == null || #request.logTableNameList.size() == 0">
			<th colspan="2" align="center">当前暂无日志</th>
		</s:if>
		<s:else>
			<s:form namespace="/Admin" action="LogAction_downloadLog">
				<th colspan="2" align="center">下载日志</th>
				<tr><td>请选择年月：</td><td><s:select list="#request.logTableNameList"  name="logDate" /></td></tr>
				<tr>
				<td colspan="2" align="center">
					<s:submit value="提交" />
				</td>
				</tr>
			</s:form>
		</s:else>
		</table>
		</div>
	</div>
	
	</div>
	</div>

	<s:include value="/resources_jsp/admin_bottom.jsp" />

</body>
</html>