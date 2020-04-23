$(document).ready(function(){
	$("#cancelBtn").click(function(){
		var urlStr = "/showUserSummary";
		$("#viewUserProfileForm").attr("action",urlStr);
		$("#viewUserProfileForm").attr("method","GET");
		$("#viewUserProfileForm").submit();
	});
});