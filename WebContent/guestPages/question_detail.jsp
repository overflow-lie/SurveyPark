<%@ page language="java" contentType="text/html; chasrset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table class="dashedTable">
	<s:if test="questions == null || questions.size() == 0">
		<tr>
			<td>当前包裹还没有创建问题！</td>
		</tr>
	</s:if>
	<s:else>
		<s:iterator value="questions">
			<%-- 遍历问题集合 --%>
			<%-- 栈顶对象：Question --%>
			<%-- 一.Question的基本信息 --%>
			<tr>
				<td><s:property value="questionName" /></td>
				<td><s:a namespace="/Guest"
						action="QuestionAction_toTypeChoosen?questionId=%{questionId}&surveyId=%{surveyId}&bagId=%{bagId}">编辑问题</s:a>
					<s:a namespace="/Guest"
						action="QuestionAction_remove?questionId=%{questionId}&surveyId=%{surveyId}">删除问题</s:a>
				</td>
			</tr>
			<%-- 二.Question的详细信息 --%>
			<%-- 1.单选题[选项、是否换行、其他项] --%>
			<s:if test="questionType == 0">
				<tr>
					<td colspan="2">
						<%-- Question类会自动将options字符串根据逗号拆分为数组，遍历数组即可 --%> <s:iterator
							value="optionsArray">
							<%-- 栈顶是单选题的每一个选项 --%>
							<input type="radio" />
							<s:property />
							<s:if test="br">
								<br />
							</s:if>
						</s:iterator> <s:if test="hasOther">
							<s:if test="otherType == 0">
								<input type="radio" />其它</s:if>
							<s:if test="otherType == 1">
								<input type="text" class="shortInput" />其它</s:if>
						</s:if>
					</td>
				</tr>
			</s:if>

			<%-- 2.多选题 --%>
			<s:if test="questionType == 1">
				<tr>
					<td colspan="2">
						<%-- Question类会自动将options字符串根据逗号拆分为数组，遍历数组即可 --%> <s:iterator
							value="optionsArray">
							<%-- 栈顶是多选题的每一个选项 --%>
							<input type="checkbox" />
							<s:property />
							<s:if test="br">
								<br />
							</s:if>
						</s:iterator> <s:if test="hasOther">
							<s:if test="otherType == 0">
								<input type="checkbox" />其它</s:if>
							<s:if test="otherType == 1">
								<input type="text" class="shortInput" />其它</s:if>
						</s:if>
					</td>
				</tr>
			</s:if>

			<%-- 3.下拉列表 --%>
			<s:if test="questionType == 2">
				<tr>
					<td colspan="2"><select>
							<s:iterator value="optionsArray">
								<option>
									<s:property />
								</option>
							</s:iterator>
					</select></td>
				</tr>
			</s:if>

			<%-- 4.简答题 --%>
			<s:if test="questionType == 3">
				<tr>
					<td colspan="2"><input type="text" class="longInput" /></td>
				</tr>
			</s:if>

			<%-- 5.矩阵式问题 --%>
			<s:if test="questionType >= 4">

				<tr>
					<td colspan="2">
						<table class="dashedTable">

							<tr>
								<td>&nbsp;</td>
								<s:iterator value="matrixColTitlesArray">
									<td><s:property /></td>
								</s:iterator>
							</tr>

							<s:iterator value="matrixRowTitlesArray">

								<tr>
									<td><s:property /></td>
									<s:iterator begin="1" end="matrixColTitlesArray.length">
										<%-- 矩阵式问题的选项 --%>
										<td>
											<%-- 矩阵单选 --%> <s:if test="questionType == 4">
												<input type="radio" />
											</s:if> <%-- 矩阵多选 --%> <s:if test="questionType == 5">
												<input type="checkbox" />
											</s:if> <%-- 矩阵下拉 --%> <s:if test="questionType == 6">
												<select>
													<s:iterator value="matrixOptionsArray">
														<option><s:property /></option>
													</s:iterator>
												</select>
											</s:if>
										</td>
									</s:iterator>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
			</s:if>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
		</s:iterator>
	</s:else>
</table>


















