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

	<div id="mws-login">
    	<h1>Login</h1>
        <div class="mws-login-lock"><img src="${pageContext.request.contextPath }/resources_static/admin/css/icons/24/locked-2.png" alt="" /></div>
    	<div id="mws-login-form">
        	<s:form action="AdminAction_login" namespace="/Admin" cssClass="mws-form">
                <div class="mws-form-row">
                	<div class="mws-form-item large">
                    	<s:textfield name="adminName" placeholder="username" cssClass="mws-login-username mws-textinput"/>
                    </div>
                </div>
                <div class="mws-form-row">
                	<div class="mws-form-item large">
                    	<s:password name="adminPwd" placeholder="password" cssClass="mws-login-password mws-textinput"/>
                    </div>
                </div>
                <div class="mws-form-row">
                	<s:submit value="Login" id="admin_login_commit" cssClass="mws-button green mws-login-button"/>
                </div>
            </s:form>
        </div>
    </div>
	<div style="padding:230px">
	<s:include value="/resources_jsp/admin_bottom.jsp"/>
	</div>

</body>
</html>