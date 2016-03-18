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
            

                       

	<s:push value="#request.page">
	<div class="block-div">
		<table class="dashedTable">
			<s:if test="list == null || list.size() == 0">
				<tr>
					<th>现在还没有创建任何角色</th>
				</tr>
			</s:if>
			<s:else>
				<s:form action="RoleAction_batchDelete" namespace="/Admin">
				<tr>
					<th>角色ID</th>
					<th>角色名称</th>
					<th>分配权限</th>
					<th>操作</th>
				</tr>
				<s:iterator value="list" status="myStatus">
					<tr>
						<td>
							<s:property value="roleId"/>
						</td>
						<td>
							<s:if test="#myStatus.index < 5">
								<s:property value="roleName"/>
							</s:if>
							<s:else>
								<input title="<s:property value="roleId"/>" class="roleName longInput" type="text" name="roleName" value="<s:property value="roleName"/>"/>
							</s:else>
						</td>
						<td>
							<s:a namespace="/Admin" action="RoleAction_toAuthMngPage?roleId=%{roleId}">分配权限</s:a>
						</td>
						<td>
							<s:if test="#myStatus.index < 5">
								默认
							</s:if>
							<s:else>
								<input type="checkbox" name="roleIdList" value="<s:property value="roleId"/>" id="CheckBox<s:property value="roleId"/>"/>
								<label for="CheckBox<s:property value="roleId"/>">选择</label>
							</s:else>
						</td>
					</tr>
				
				</s:iterator>
				
				<tr>
					<td colspan="4" align="center">
						<s:submit value="确认删除"></s:submit>
					</td>
				</tr>
				
				<tr>
					<td colspan="5" align="center">
						<%-- 动态设置翻页超链接的URL地址：名称空间+ActionName --%>
						<%-- set标签在没有指定scope属性时，是直接保存到Map栈中 --%>
						<s:set value="'RoleAction_showRoles'" var="pageActionName"/>
						<s:set value="'/Admin'" var="pageNamespace"/>
						<s:include value="/resources_jsp/pageNavigator.jsp"/>
						<%-- <s:debug/> --%>
					</td>
				</tr>
				
				</s:form>
			</s:else>
		</table>
	</div>
	</s:push>
	
			</div>
		</div>
	<s:include value="/resources_jsp/admin_bottom.jsp"/>
</body>
</html>