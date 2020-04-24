$(document).ready(function(){
	$(function() {
		$("#hearingDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		});
	});
	
	
	
	$("#cancelBtn").click(function(){
		var urlStr = "/showLitigationSummary";
		$("#updateHistoryDetailsForm").attr("action",urlStr);
		$("#updateHistoryDetailsForm").attr("method","GET");
		$("#updateHistoryDetailsForm").submit();
	});
});