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
					<th>现在还没有日志信息</th>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<th>LogID</th>
					<th>操作人</th>
					<th>时间</th>
					<th>类型</th>
					<th>名称</th>
					<th>参数</th>
					<th>返回值</th>
					<th>结果</th>
				</tr>
				<s:iterator value="list">
					<tr>
						<td>
							<s:property value="logId"/>
						</td>
						<td>
							<s:property value="operator"/>
						</td>
						<td>
							<s:property value="operateTime"/>
						</td>
						<td>
							<s:property value="methodType"/>
						</td>
						<td>
							<s:property value="methodName"/>
						</td>
						<td>
							<s:property value="methodArgs"/>
						</td>
						<td>
							<s:property value="methodReturnValue"/>
						</td>
						<td>
							<s:property value="methodResultMsg"/>
						</td>
					</tr>
				
				</s:iterator>
				<tr>
					<td colspan="8" align="center">
						<%-- 动态设置翻页超链接的URL地址：名称空间+ActionName --%>
						<%-- set标签在没有指定scope属性时，是直接保存到Map栈中 --%>
						<s:set value="'LogAction_showLogs'" var="pageActionName"/>
						<s:set value="'/Admin'" var="pageNamespace"/>
						<s:include value="/resources_jsp/pageNavigator.jsp"/>
						<%-- <s:debug/> --%>
					</td>
				</tr>
				
			</s:else>
		</table>
	</div>
	</s:push>
	
	</div>
	</div>
	
	<s:include value="/resources_jsp/admin_bottom.jsp"/>
</body>
</html>