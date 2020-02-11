$(document).ready(function(){
	
	jQuery("#firstName").keypress(function(e){
		var code = e.keyCode || e.which;
		if ((code < 65 || code > 90)
				&& (code < 97 || code > 122)
				&& code != 32 && code != 46) {
			alert("Only alphabates are allowed");
			return false;
		}
	});
	
	jQuery("#middleName").keypress(function(e){
		var code = e.keyCode || e.which;
		if ((code < 65 || code > 90)
				&& (code < 97 || code > 122)
				&& code != 32 && code != 46) {
			alert("Only alphabates are allowed");
			return false;
		}
	});
	
	jQuery("#lastName").keypress(function(e){
		var code = e.keyCode || e.which;
		if ((code < 65 || code > 90)
				&& (code < 97 || code > 122)
				&& code != 32 && code != 46) {
			alert("Only alphabates are allowed");
			return false;
		}
	});
	
jQuery("#cancelBtn").click(function(){
		
		var urlStr = "/showUserSummary";
		$("#updateUserForm").attr("action",urlStr);
		$("#updateUserForm").attr("method","GET");
		$("#updateUserForm").submit();
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