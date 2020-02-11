jQuery(document).ready(function() {
	$("#reset").click(function(){
		var oldPswd = jQuery.trim($('#oldPassword').val());
		var newPswd = jQuery.trim($('#newPassword').val());
		var conNewPswd = jQuery.trim($('#confirmNewPassword').val());
		
		if(oldPswd == '' || oldPswd == null){
			alert("Please Enter OldPassword");
			return false;
		}else if(newPswd == '' || newPswd == null){
			alert("Please Enter NewPassword");
			return false;
		}else if(conNewPswd == '' || conNewPswd == null){
			alert("Please Enter Confirm New Password");
			return false;
		}else if(newPswd.length < 6){
			alert("Password Length Should be greater than 6");
			return false;
		}else if(newPswd.match(/([!,%,&,@,#,$,^,*,?,_,~])/) == false || newPswd.match(/([!,%,&,@,#,$,^,*,?,_,~])/) == '' || newPswd.match(/([!,%,&,@,#,$,^,*,?,_,~])/) == null){
			alert("Password Should have atleast one special charcter");
			return false;
		}else if (newPswd.match(/([a-zA-Z])/) == false || newPswd.match(/([a-zA-Z])/) == '' || newPswd.match(/([a-zA-Z])/) == null){
			alert("Password should have Atleast one Character");
			return false;
		}else if(newPswd.match(/([0-9])/) == false || newPswd.match(/([0-9])/) == '' || newPswd.match(/([0-9])/) == null){
			alert("Password should have atleast one number");
			return false;
		}else if(oldPswd == newPswd){
			alert("New Password should not be same as Old Password");
			return false;
		}else if (newPswd != conNewPswd){
			alert("New Password and Confirm New Password should be same");
			return false;
		}else{
			$("#changePswdForm").submit();
		}
	});
	
	$("#back").click(function(){
		var urlStr = "/userLogin";
		$("#changePswdForm").attr("action",urlStr);
		$("#changePswdForm").attr("method","GET");
		$("#changePswdForm").submit();
	});
	
	$("#cancel").click(function(){
		$("#oldPassword").val('');
		$("#newPassword").val('');
		$("#confirmNewPassword").val('');
	});
});

/*function validatePwd(){
	var oldPswd = jQuery.trim('#oldPassword').val();
	var newPswd = jQuery.trim('#newPassword').val();
	var conNewPswd = jQuery.trim('#confirmNewPassword').val();
	
	if(oldPswd == '' || oldPswd == null){
		alert("Please Enter OldPassword");
		return false;
	}else if(newPswd == '' || newPswd == null){
		alert("Please Enter NewPassword");
		return false;
	}else if(conNewPswd == '' || conNewPswd == null){
		alert("Please Enter Confirm New Password");
		return false;
	}else if(newPswd.length < 6){
		alert("Password Length Should be greater than 6");
		return false;
	}else if(newPswd.match(/([!,%,&,@,#,$,^,*,?,_,~])/) == false || newPswd.match(/([!,%,&,@,#,$,^,*,?,_,~])/) == '' || newPswd.match(/([!,%,&,@,#,$,^,*,?,_,~])/) == null){
		alert("Password Should have atleast one special charcter");
		return false;
	}else if (newPswd.match(/([a-zA-Z])/) == false || newPswd.match(/([a-zA-Z])/) == '' || newPswd.match(/([a-zA-Z])/) == null){
		alert("Password should have Atleast one Character");
		return false;
	}else if(newPswd.match(/([0-9])/) == false || newPswd.match(/([0-9])/) == '' || newPswd.match(/([0-9])/) == null){
		alert("Password should have atleast one number");
		return false;
	}else if(oldPswd == newPswd){
		alert("New Password should not be same as Old Password");
		return false;
	}else if (newPswd != conNewPswd){
		alert("New Password and Confirm New Password should be same");
		return false;
	}else{
		$("#changePswdForm").submit();
	}
	
}*/