$(function(){
	
	/*	表格样式	*/
	$("table tr:nth-child(odd)").addClass("odd-row");
	$("table td:first-child, table th:first-child").addClass("first");
	$("table td:last-child, table th:last-child").addClass("last");
	
	/*	管理员登陆验证	*/
	$("#admin_login_commit").click(function() {
		var adminName = $("[name = 'adminName']").val();
		var adminPwd = $("[name = 'adminPwd']").val();
		if (adminName == "请输入管理员账户" || adminName == "") {
			alert("请输入管理员账户");
			return false;
		}
		if (adminPwd == "请输入管理员密码" || adminPwd == "") {
			alert("请输入管理员密码");
			return false;
		}
	});
	
	/*	添加角色验证	*/
	$("#role_add_commit").click(function() {
		var roleName = $("[name = 'roleName']").val();
		if (roleName == "") {
			alert("请输入正确角色名称");
			return false;
		}
	});
	
	/*	添加权限验证	*/
	$("#authority_add_commit").click(function() {
		var authorityName = $("[name = 'authorityName']").val();
		if (authorityName == "") {
			alert("请输入正确权限名称");
			return false;
		}
	});
	
	/*	角色AJAX变更角色名称	*/
	$(".roleName").change(function(){
		var roleName = $.trim(this.value);
		if(roleName == "") {
			this.value = this.defaultValue;
			return ;
		}
		var roleId = this.title;
		var url = "${pageContext.request.contextPath }/Admin/RoleAction_update";
		var paramData = {"roleId":roleId,"roleName":roleName,"time":new Date()};
		var type = "json";
		var callBack = function(respData){
			alert(respData.message);
		};
		$.post(url,paramData,callBack,type);
	});
	
	/*	资源AJAX变更资源名称	*/
	$(".resourceName").change(function(){
		var resourceName = $.trim(this.value);
		if(resourceName == "") {
			this.value = this.defaultValue;
			return ;
		}
		var resourceId = this.title;
		var url = "${pageContext.request.contextPath }/Admin/ResourceAction_update";
		var paramData = {"resourceId":resourceId,"resourceName":resourceName,"time":new Date()};
		var type = "json";
		var callBack = function(respData){
			alert(respData.message);
		};
		$.post(url,paramData,callBack,type);
	});
	
	/*	权限AJAX变更权限名称	*/
	$(".authorityName").change(function(){
		var authorityName = $.trim(this.value);
		if(authorityName == "") {
			this.value = this.defaultValue;
			return ;
		}
		var authorityId = this.title;
		var url = "${pageContext.request.contextPath }/Admin/AuthorityAction_update";
		var paramData = {"authorityId":authorityId,"authorityName":authorityName,"time":new Date()};
		var type = "json";
		var callBack = function(respData){
			alert(respData.message);
		};
		$.post(url,paramData,callBack,type);
	});
	
	/* AJAX统一计算权限码	*/
	$("#calculationCodeId").click(function(){
		var url = "${pageContext.request.contextPath }/Admin/AdminAction_calculationCode";
		var paramData = null;
		var type = "json";
		var callBack = function(data){
			alert(data.message);
		};
		$.post(url,paramData,callBack,type);
		return false;
	});
	
	
	
});