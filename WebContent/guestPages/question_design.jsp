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

		<s:form namespace="/Guest" action="QuestionAction_saveOrUpdate">
			<%-- 使用表单隐藏域保持相关数据 --%>
			<%-- 确认1：是否能够拿到值 --%>
			<%-- 确认2：是否能够注入到正确的位置 --%>
			<%-- 当前表单提交后，接收请求参数的对象是：Question对象 --%>
			<s:hidden name="questionId" />
			<s:hidden name="questionType" />
			<s:hidden name="bag.bagId" value="%{bagId}" />
			<%-- 作用是让Question对象知道自己是属于哪个Bag的 --%>
			<s:hidden name="surveyId" />
			<%-- 最终赋值给了BaseAction的surveyId属性，用于回到调查设计页面 --%>
			
			<div id="content">
				<div style="text-align: center">
					<span class="errorMsg" style="color: red"> 
						<s:if test="hasActionErrors()">
							<s:actionerror />
						</s:if>
					</span>
				</div>			
			<table>

				<tr>
					<th colspan="2">设计问题细节</th>
				</tr>

				<tr>
					<td>题型</td>
					<td>
						<!-- 此时栈顶对象是Question对象，其中已经被注入了questionType的值 --> <%-- 题型的整数值：<s:property value="questionType"/> --%>
						<s:property
							value="@cn.farquer.survey.utils.DataProcessUtils@QUESTION_TYPE_MAP.get(questionType)" />
					</td>
				</tr>

				<tr>
					<td>题干</td>
					<td><s:textfield name="questionName" cssClass="longInput" /></td>
				</tr>

				<%-- 以下内容视题型不同而有区别 --%>
				<s:if test="questionType <= 2">
					<%-- 单选、多选、下拉列表有选项 --%>
					<tr>
						<td>选项</td>
						<td><s:textarea name="options" cssClass="multiLineTextInput"
								value="%{optionsForShow}" /></td>
					</tr>

				</s:if>
				<s:if test="questionType <= 1">
					<%-- 单选、多选考虑是否有换行 --%>
					<tr>
						<td>是否在选项后换行</td>
						<td>
							<%-- 专门提交单个布尔值 --%> <s:checkbox id="brId" name="br" /> <label
							for="brId">勾选表示换行，不勾选不换行</label>
						</td>
					</tr>

					<%-- 单选、多选考虑是否有其他项 --%>
					<tr>
						<td>是否设置其他项</td>
						<td>
							<%-- 专门提交单个布尔值 --%> <s:checkbox id="hasOtherId" name="hasOther" />
							<label for="hasOtherId">勾选表示设置，不勾选不设置</label>
						</td>
					</tr>

					<%-- 这个tr，在页面刚刚加载时是隐藏的 --%>
					<tr id="otherTypeTr">
						<td>其他项形式</td>
						<td><s:radio list="#{'0':'与主题型一致','1':'文本框' }"
								name="otherType" /></td>
					</tr>
				</s:if>
				<s:if test="questionType >= 4">
					<%-- 任何矩阵式问题，都需要设计行标题组、列标题组 --%>
					<tr>
						<td>行标题组</td>
						<td><s:textarea name="matrixRowTitles"
								cssClass="multiLineTextInput" /></td>
					</tr>
					<tr>
						<td>列标题组</td>
						<td><s:textarea name="matrixColTitles"
								cssClass="multiLineTextInput" /></td>
					</tr>

					<s:if test="questionType == 6">

						<tr>
							<td>下拉列表选项</td>
							<td><s:textarea name="matrixOptions"
									cssClass="multiLineTextInput" /></td>
						</tr>

					</s:if>

				</s:if>
				<tr>
					<td colspan="2"><s:submit value="OK" id="question_design_commit"/></td>
				</tr>

			</table>
			</div>
		</s:form>

	</div>
	</div>
	</div>

	<s:include value="/resources_jsp/bottom.jsp" />

</body>
</html>