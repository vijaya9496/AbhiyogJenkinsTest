jQuery(document).ready(function() {
	$("#submitBtn").click(function() {
		var loginId = jQuery.trim($('#loginId').val());
		var password = jQuery.trim($('#password').val());
		if (loginId == '') {
			alert("Please Enter LoginId");
			return false;
		}
		if (password == '') {
			alert("Please Enter Password");
			return false;
		}

		$("#loginForm").submit();
	});
});