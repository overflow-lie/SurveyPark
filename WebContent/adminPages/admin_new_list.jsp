<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:include value="/resources_jsp/admin_head.jsp"/>
</head>
<body>

	<s:include value="/resources_jsp/admin_top.jsp"/>
	
		 <!-- Container Wrapper -->
        <div id="mws-container" class="clearfix">       
        	<!-- Main Container -->
            <div class="container">
            
	
	
	<div class="block-div">
		<table class="dashedTable">
			<tr>
				<th>账号</th>
				<th>密码</th>
			</tr>
			<s:iterator value="#request.newAdminList">
				<tr>
					<td>
						<s:property value="adminName"/>
					</td>
					<td>
						<s:property value="adminPwd"/>
					</td>
				</tr>
			
			</s:iterator>
		</table>
	</div>
	
	</div>
	</div>
	
	<s:include value="/resources_jsp/admin_bottom.jsp"/>
</body>
</html>