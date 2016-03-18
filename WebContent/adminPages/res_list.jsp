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
					<th>现在还没有生成任何资源对象</th>
				</tr>
			</s:if>
			<s:else>
				<s:form action="ResourceAction_batchDelete" namespace="/Admin">
				<tr>
					<th>资源ID</th>
					<th>URL地址</th>
					<th>资源名称</th>
					<th></th>
				</tr>
				<s:iterator value="list">
					<tr>
						<td>
							<s:property value="resourceId"/>
						</td>
						<td>
							<s:property value="actionName"/>
						</td>
						<td>
							<input title="<s:property value="resourceId"/>" class="resourceName longInput" type="text" name="resourceName" value="<s:property value="resourceName"/>"/>
						</td>
						<td>
							<!-- 全部的id：1,2,3,4,5,6 -->
							<!-- 要删除的id：2,3,6 -->
							<!-- 在Action类中接收的方式：List<Integer> resIdList -->
							<input type="checkbox" name="resIdList" value="<s:property value="resourceId"/>" id="CheckBox<s:property value="resourceId"/>"/>
							<label for="CheckBox<s:property value="resourceId"/>">选择</label>
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
						<s:set value="'ResourceAction_showAllResources'" var="pageActionName"/>
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