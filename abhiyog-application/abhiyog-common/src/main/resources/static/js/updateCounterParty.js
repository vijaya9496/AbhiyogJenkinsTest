jQuery(document).ready(function() {

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
	
	jQuery("#cancelBtn").click(function() {
		var urlStr = "/showCounterPartySummary";
		$("#addNewUserForm").attr("action", urlStr);
		$("#addNewUserForm").attr("method", "GET");
		$("#addNewUserForm").submit();
	});
});