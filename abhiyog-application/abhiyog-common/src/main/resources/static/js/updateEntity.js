$(document).ready(function(){
	
	$("#cancelBtn").click(function(){
		var urlStr = "/showEntitySummary";
		$("#updateEntityForm").attr("action",urlStr);
		$("#updateEntityForm").attr("method","GET");
		$("#updateEntityForm").submit();
	});
	
/*	$("#updateBtn").submit(function(){
		var urlStr = "/updateEntityDtls";
		$("#addNewEntityForm").attr("action",urlStr);
		$("#addNewEntityForm").attr("method","PUT");
		$("#addNewEntityForm").submit();
	});*/
});