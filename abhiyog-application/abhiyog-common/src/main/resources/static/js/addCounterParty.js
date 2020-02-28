jQuery(document).ready(function() {
	$('#cancelBtn').click(function() {
		var urlStr = "/showCustomerSummary";
		$("#addNewCounterPartyForm").attr("action", urlStr);
		$("#addNewCounterPartyForm").attr("method", "GET");
		$("#addNewCounterPartyForm").submit();
	});
	
	jQuery("#counterPartyName").keypress(function(e){
		var code = e.keyCode || e.which;
		if ((code < 65 || code > 90)
				&& (code < 97 || code > 122)
				&& code != 32 && code != 46) {
			alert("Only alphabates are allowed");
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
	
	
	
});

function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		alert("please enter mobile No as numeric value only");
		return false;
	} else {
		return true;
	}
}