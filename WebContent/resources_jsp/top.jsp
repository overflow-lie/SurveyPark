<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>


	<div id="header-wrapper">
		<div id="header">
			<div class="container">
				<div id="logo"> <!-- Logo -->
					<h1><s:a value="/index.jsp" >欢乐调查公园</s:a></h1>
					<span>如果感到快乐你就啪啪啪！</span>
				</div>
			</div>
			<div id="menu-wrapper">
				<div class="container">
					<nav id="nav">
						<s:if test="#session.loginUser == null">
							<ul>
								<li><s:a value="/index.jsp" >首页</s:a></li>
								<li><s:a value="/guestPages/user_login.jsp" >登录</s:a></li>

								<li><s:a value="/guestPages/user_regist.jsp" >注册</s:a></li>
							</ul>
							
						</s:if>
						
						<s:else>
							<ul>
								<li><s:a value="/index.jsp" >首页</s:a></li>
								<li><s:a action="ToPageAction_user_myCenter" namespace="/Guest" >个人中心</s:a></li>
								<s:if test="#session.loginUser.payStatus">
									<li><s:a action="ToPageAction_survey_create" >创建一个新的调查</s:a></li>
								</s:if>
								<li><s:a action="UserAction_logout" namespace="/Guest" >退出登录</s:a></li>
							</ul>
						</s:else>
					</nav>
				</div>
			</div>
		</div>
	</div>
			

				

				
