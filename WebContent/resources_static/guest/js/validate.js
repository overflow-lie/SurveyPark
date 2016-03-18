$(function(){
	
	/*	表格样式	*/
	$("table tr:nth-child(odd)").addClass("odd-row");
	$("table td:first-child, table th:first-child").addClass("first");
	$("table td:last-child, table th:last-child").addClass("last");
	
	/*	注册验证	*/
	$("#regist_commit").click(function() {
		var username = $("[name = 'userName']").val();
		var userPwd = $("[name = 'userPwd']").val();
		var userPwdConfirm = $("[name = 'userPwdConfirm']").val();
		var email = $("[name = 'email']").val();
		var nickName = $("[name = 'nickName']").val();
		var usernameReg = /^[a-z0-9_-]{3,16}$/;
		var passwordReg = /^[a-z0-9_-]{6,18}$/;
		var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		if (!usernameReg.test(username)) {
			$(".errorMsg").text("请输入3-16位用户名，不要包含特殊字符");
			return false;
		}
		if (!passwordReg.test(userPwd)) {
			$(".errorMsg").text("请输入6-18位密码");
			return false;
		}
		if (nickName == "" || nickName == "请输入昵称") {
			$(".errorMsg").text("昵称请不要为空");
			return false;
		}	
		if (userPwd != userPwdConfirm) {
			$(".errorMsg").text("两次密码输入不一致！");
			return false;
		}
		if (!emailReg.test(email)) {
			$(".errorMsg").text("邮箱格式输入不正确！");
			return false;
		}
	});
	
	/*	登录验证	*/
	$("#login_commit").click(function() {
		var userName = $("[name = 'userName']").val();
		var userPwd = $("[name = 'userPwd']").val();
		var usernameReg = /^[a-z0-9_-]{3,16}$/;
		var passwordReg = /^[a-z0-9_-]{6,18}$/;
		if (!usernameReg.test(userName)) {
			$(".errorMsg").text("请输入3-16位用户名，不要包含特殊字符");
			return false;
		}
		if (!passwordReg.test(userPwd)) {
			$(".errorMsg").text("请输入6-18位密码");
			return false;
		}
	});
	
	/*	支付验证	*/
	$("#pay_commit").click(function() {
		var payAmount = $("[name = 'payAmount']").val();
		var payAmountReg = /^\+?[1-9][0-9]*$/;
		if (!payAmountReg.test(payAmount)) {
			$(".errorMsg").text("请输入正确的充值金额");
			return false;
		}
		if(payAmount>100000){
			$(".errorMsg").text("单笔交易请小于10万元");
			return false;
		}
	});
	
	/*	调整包裹顺序验证	*/
	$("#survey_bagOrder_commit").click(function(){
		var bagOrderReg = /^\d+(\.\d+)?$/;
		var arr = [];
		$(".middleInput").each(function(){
			arr.push($(this).val());
		});
		for(var i = 0 ; i < arr.length; i++){
			var bagOrder = arr[i];
			if (!bagOrderReg.test(bagOrder)) {
				$(".errorMsg").text("请输入正确的数字");
				return false;
			}
		};
	});
	
	/*	删除调查验证	*/
	$(".removeSurvey").click(function(){
		var title = $(this).parents("tr").children("td:eq(1)").text();
		title = $.trim(title);
		var flag = confirm("你真的要删除【"+title+"】这个调查吗？");
		if(!flag) {
			return false;
		}
	});
	
	/*	新建有其他项调查的验证	*/
	var isChecked = $("#hasOtherId").is(":checked");
	if(isChecked) {
		$("#otherTypeTr").show();
	}else{
		$("#otherTypeTr").hide();
	}
	$("#hasOtherId").click(function(){
		$("#otherTypeTr").toggle();
	});

	/*	添加调查标题验证	*/
	$("#survey_create_commit").click(function() {
		var title = $("[name = 'title']").val();
		if (title == "") {
			$(".errorMsg").text("请输入正确的调查标题");
			return false;
		}
	});
	
	/*	编辑调查标题验证	*/
	$("#survey_edit_commit").click(function() {
		var title = $("[name = 'title']").val();
		if (title == "") {
			$(".errorMsg").text("请输入正确的调查标题");
			return false;
		}
	});
	
	/*	编辑调查标题验证	*/
	$("#bag_add_commit").click(function() {
		var bagName = $("[name = 'bagName']").val();
		if (bagName == "") {
			$(".errorMsg").text("请输入正确的包裹名称");
			return false;
		}
	});
	
	
	/*	编辑调查标题验证	*/
	$("#bag_edit_commit").click(function() {
		var bagName = $("[name = 'bagName']").val();
		if (bagName == "") {
			$(".errorMsg").text("请输入正确的包裹名称");
			return false;
		}
	});

	/*	问题设计验证	*/
	$("#question_design_commit").click(function() {
		var questionName = $("[name = 'questionName']").val();
		var options = $("[name = 'options']").val();
		var matrixRowTitles = $("[name = 'matrixRowTitles']").val();
		var matrixColTitles = $("[name = 'matrixColTitles']").val();
		var matrixOptions = $("[name = 'matrixOptions']").val();
		if (questionName == "") {
			$(".errorMsg").text("请输入正确的问题题干");
			return false;
		}
		if (options == "") {
			$(".errorMsg").text("请输入正确的选项");
			return false;
		}
		if (matrixRowTitles == "") {
			$(".errorMsg").text("请输入正确的行标题组");
			return false;
		}
		if (matrixColTitles == "") {
			$(".errorMsg").text("请输入正确的列标题组");
			return false;
		}
		if (matrixOptions == "") {
			$(".errorMsg").text("请输入正确的下拉列表选项");
			return false;
		}
	});
});