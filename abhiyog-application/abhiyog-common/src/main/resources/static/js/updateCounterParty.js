jQuery(document).ready(function() {

	$("#submitBtn").click(function(){
		if($("#mobile1").val().length != 10){
			alert("Please Enter 10 Digits Mobile Number..");
			$("#mobile1").focus();
			return false;
		}
		
		if($("#mobile2").val().length != 10){
			alert("Please Enter 10 Digits Mobile Number..");
			$("#mobile2").focus();
			return false;
		}
	});
	
	jQuery("#contactPerson1").keypress(function(e){
		var code = e.keyCode || e.which;
		if ((code < 65 || code > 90)
				&& (code < 97 || code > 122)
				&& code != 32 && code != 46) {
			alert("Only alphabates are allowed");
			return false;
		}
	});
	jQuery("#contactPerson2").keypress(function(e){
		var code = e.keyCode || e.which;
		if ((code < 65 || code > 90)
				&& (code < 97 || code > 122)
				&& code != 32 && code != 46) {
			alert("Only alphabates are allowed");
			return false;
		}
	});
	
	jQuery("#designation1").keypress(function(e){
		var code = e.keyCode || e.which;
		if ((code < 65 || code > 90)
				&& (code < 97 || code > 122)
				&& code != 32 && code != 46) {
			alert("Only alphabates are allowed");
			return false;
		}
	});
	
	jQuery("#designation2").keypress(function(e){
		var code = e.keyCode || e.which;
		if ((code < 65 || code > 90)
				&& (code < 97 || code > 122)
				&& code != 32 && code != 46) {
			alert("Only alphabates are allowed");
			return false;
		}
	});
	
	$('#cancelBtn').click(function() {
		var urlStr = "/showCustomerSummary";
		$("#updateCounterPartyForm").attr("action", urlStr);
		$("#updateCounterPartyForm").attr("method", "GET");
		$("#updateCounterPartyForm").submit();
	});
});