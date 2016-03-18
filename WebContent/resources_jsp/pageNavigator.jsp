<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	
	$(function(){
		
		$("#goPage").change(function(){
			
			//目标：让页面跳转到指定的页
			//1.获取当前用户填写的页码
			var pageNoStr = this.value;
			
			//2.验证页码
			//Not a number
			if(isNaN(pageNoStr)) {
				this.value = this.defaultValue;
				return ;
			}
			
			//3.跳转
			//xxx.jsp→翻译→xxx_jsp.java→编译→xxx_jsp.class→执行→HTML(包括JS代码)
			var targetUrl = "<s:url namespace='%{#pageNamespace}' action='%{#pageActionName}'/>?pageNoStr="+pageNoStr+"<s:property value='%{#extraParams}'/>";
			window.location.href=targetUrl;
		});
		
	});
	
</script>
<s:if test="#request.page == null || #request.page.dataList == null || #request.page.dataList.empty">
	没有数据
</s:if>
<s:else>
	<s:if test="#request.page.hasPrev">
		<s:a action="%{#pageActionName}?pageNoStr=1%{#extraParams}" namespace="%{#pageNamespace}">首页</s:a>
		<s:a action="%{#pageActionName}?pageNoStr=%{#request.page.prev}%{#extraParams}" namespace="%{#pageNamespace}">上一页</s:a>
	</s:if>
	
	共<s:property value="#request.page.totalRecordNo"/>条记录
	
	前往第<input type="text" id="goPage" value="<s:property value="#request.page.pageNo"/>" class="shortInput"/>页
	
	共<s:property value="#request.page.totalPageNo"/>页
	
	<s:if test="#request.page.hasNext">
		<s:a action="%{#pageActionName}?pageNoStr=%{#request.page.next}%{#extraParams}" namespace="%{#pageNamespace}">下一页</s:a>
		<s:a action="%{#pageActionName}?pageNoStr=%{#request.page.totalPageNo}%{#extraParams}" namespace="%{#pageNamespace}">末页</s:a>
	</s:if>
</s:else>