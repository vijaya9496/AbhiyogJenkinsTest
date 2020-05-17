jQuery(document).ready(function(){
	
	$("#confpassword").change(function(){
		validatePwd();
	});
	
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
	
	$("#submitBtn").click(function(){
		if ($('#role :selected').text() === 'Select') {
			alert("Please Select Role");
			return false;
		}
	});
	
	jQuery("#resetBtn").click(function(){
		$("#loginId").val('');
		$("#role").val('ALL');
		$("#password").val('');
		$("#confpassword").val('');
		$("#firstName").val('');
		$("#middleName").val('');
		$("#lastName").val('');
		$("#address").val('');
		$("#city").val('');
		$("#phone").val('');
		$("#mobile").val('');
		$("#emailId").val('');
		$("#personalEmailId").val('');
	});
	
	jQuery("#cancelBtn").click(function(){
		
		var urlStr = "/showUserSummary";
		$("#addNewUserForm").attr("action",urlStr);
		$("#addNewUserForm").attr("method","GET");
		$("#addNewUserForm").submit();
	});
	
	
});


function validatePwd(){
//	alert("validating passwords");
	var password = jQuery.trim($('#password').val());
	var confPassword = jQuery.trim($('#confpassword').val());
	if(password.length < 6){
		alert("Password Length Should be 6");
		$("#submitBtn").attr({disabled:true});
		return false;
	}else if(password.match(/([!,%,&,@,#,$,^,*,?,_,~])/) == false || password.match(/([!,%,&,@,#,$,^,*,?,_,~])/) == '' || password.match(/([!,%,&,@,#,$,^,*,?,_,~])/) == null){
		alert("Password Should have atleast one special charcter");
		$("#submitBtn").attr({disabled:true});
		return false;
	}else if (password.match(/([a-zA-Z])/) == false || password.match(/([a-zA-Z])/) == '' || password.match(/([a-zA-Z])/) == null){
		alert("Password should have Atleast one Character");
		$("#submitBtn").attr({disabled:true});
		return false;
	}else if(password.match(/([0-9])/) == false || password.match(/([0-9])/) == '' || password.match(/([0-9])/) == null){
		alert("Password should have atleast one number");
		$("#submitBtn").attr({disabled:true});
		return false;
	}else if(password != confPassword){
		alert("Confirm password should be same as new password.");
		$("#submitBtn").attr({disabled:true});
		return false;
	}else{
		$("#submitBtn").attr({disabled:false}); //enabled
	}

}
	


function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		alert("please enter mobile No as numeric value only");
		return false;
	} else {
		return true;
	}
}