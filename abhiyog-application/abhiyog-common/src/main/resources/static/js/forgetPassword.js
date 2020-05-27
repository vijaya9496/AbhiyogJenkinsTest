jQuery(document).ready(function(){
	$("#reset").click(function(){
		var loginId = jQuery.trim($('#loginId').val());
		if(loginId==''){
			alert("Please Enter LoginId");
			return false;
		}
		$("#forgetPswdForm").submit();
	});
	
	$("#cancel").click(function(){
		var urlStr = "/userLogin";
		$("#forgetPswdForm").attr("action",urlStr);
		$("#forgetPswdForm").attr("method","GET");
		$("#forgetPswdForm").submit();
	});
	
	$("#clear").click(function(){
		$('#loginId').val('');
	});
	
})