$(document).ready(function(){
	
	$("#cancelBtn").click(function(){
		var urlStr = "/showFormatSummary";
		$("#updateFormatForm").attr("action",urlStr);
		$("#updateFormatForm").attr("method","GET");
		$("#updateFormatForm").submit();
	});
});