$(document).ready(function(){
	$("#cancelBtn").click(function(){
		var urlStr = "/showEntitySummary";
		$("#addNewEntityForm").attr("action",urlStr);
		$("#addNewEntityForm").attr("method","GET");
		$("#addNewEntityForm").submit();
	});
	
	$("#addBtn").submit(function(){
		var urlStr = "/addNewEntity";
		$("#addNewEntityForm").attr("action",urlStr);
		$("#addNewEntityForm").attr("method","POST");
		$("#addNewEntityForm").submit();
	});
});