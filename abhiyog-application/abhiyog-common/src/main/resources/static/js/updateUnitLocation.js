$(document).ready(function(){
	$("#unitHead").multiselect();
	
	$("#cancelBtn").click(function(){
		var urlStr = "/showUnitLocationSummary";
		$("#updateUnitLocationForm").attr("action",urlStr);
		$("#updateUnitLocationForm").attr("method","GET");
		$("#updateUnitLocationForm").submit();
	});
	
});