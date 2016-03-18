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
			<s:if test="#request.allResList == null || #request.allResList.size() == 0">
				<tr>
					<th>现在还没有创建任何资源对象</th>
				</tr>
			</s:if>
			<s:else>
				<s:form action="AuthorityAction_resMng" namespace="/Admin">
				<s:hidden name="authorityId"/>
				<s:iterator value="#request.allResList">
					<tr>
						<td>
							<input id="Checkbox<s:property value="resourceId"/>" 
								   type="checkbox" 
								   name="resIdList" 
								   value="<s:property value="resourceId"/>"
								   	<s:if test="#request.currentResIdList.contains(resourceId)">checked='checked'</s:if>
								   />
							<label for="Checkbox<s:property value="resourceId"/>">
								<s:property value="resourceName"/>
							</label>
						</td>
					</tr>
				
				</s:iterator>
				
				<tr>
					<td align="center">
						<s:submit value="确认分配"/>
					</td>
				</tr>
				
				</s:form>
			</s:else>
		</table>
	</div>
	
	</div>
	</div>
	
	<s:include value="/resources_jsp/admin_bottom.jsp"/>
</body>
</html>