$(document).ready(function(){
	$("#cancelBtn").click(function(){
		var urlStr = "/showUnitLocationSummary";
		$("#addUnitLocationForm").attr("action",urlStr);
		$("#addUnitLocationForm").attr("method","GET");
		$("#addUnitLocationForm").submit();
	});
});