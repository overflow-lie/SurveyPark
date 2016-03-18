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
            
	
	<div>
		<s:form action="AuthorityAction_save" namespace="/Admin">
			<div id="content">
			<table>
				<tr>
					<th colspan="2" align="center">添加权限</th>
				</tr>
				<s:if test="hasActionErrors()">
					<tr>
						<td colspan="2" align="center">
							<s:actionerror/>
						</td>
					</tr>
				</s:if>
				<tr>
					<td>权限名称</td>
					<td>
						<s:textfield name="authorityName" cssClass="longInput"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<s:submit value="确认添加" id="authority_add_commit"/>
					</td>
				</tr>
			</table>
			</div>
		</s:form>
	</div>
	
	</div>
	</div>
	
	<s:include value="/resources_jsp/admin_bottom.jsp"/>
</body>
</html>