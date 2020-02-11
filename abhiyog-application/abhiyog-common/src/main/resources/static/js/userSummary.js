jQuery(document).ready(
		function() {
			jQuery("#userSummary").jqGrid(
					{
						url : "/getUserSummary",
						width : 1150,
						height : 250,
						colNames : [ 'Id', 'LoginId', 'Emp Name', 'User Role',
								'Email Id', 'Mobile No', 'Lawfirm', 'Update' ],
						colModel : [ {
							name : 'id',
							index : 'id',
							width : 100,
							align : 'center',
							hidden : true
						}, {
							name : 'loginId',
							index : 'loginId',
							width : 100,
							align : 'center'
						}, {
							name : 'empName',
							index : 'empName',
							width : 100,
							formatter : 'showlink',
							formatoptions : {
								baseLinkUrl : '/viewUser'
							},
							align : 'center'
						}, {
							name : 'userRole',
							index : 'userRole',
							width : 100,
							align : 'center'
						}, {
							name : 'emailId',
							index : 'emailId',
							width : 100,
							align : 'center'
						}, {
							name : 'mobileNo',
							index : 'mobileNo',
							width : 100,
							align : 'center'
						}, {
							name : 'lawfirm',
							index : 'lawfirm',
							width : 100,
							align : 'center'
						}, {
							name : 'update',
							index : 'update',
							width : 100,
							formatter : 'showlink',
							formatoptions : {
								baseLinkUrl : '/updateUser'
							},
							align : 'center'
						} ],
						rowNum : 10,
						rowList : [ 5, 10, 20 ],
						pager : '#userSummarypager',
						datatype : 'json',
						loadonce : true,
						viewrecords : true,
						ignoreCase : true,
						cmTemplate : {
							sortable : false
						},
					// multiselect : true,
					});
			jQuery("#userSummary").jqGrid('filterToolbar', {
				defaultSearch : "cn",
				stringResult : true,
				searchOnEnter : false
			});
			
			$("#role").change(function(){
				getUserSummaryData();
			});
			$("#status").change(function(){
				getUserSummaryData();
			});
		});

function getUserSummaryData() {
	var urlStr = "/getUserSummaryBy?"
	var role = jQuery.trim($('#role').val());
	var status = jQuery.trim($('#status').val());
	var requestData = {};
	urlStr = urlStr + "role=" + role + "&status=" + status;

	$.ajax({
		type : "post",
		url : urlStr,
		cache : false,
		async : false,
		data : requestData,
		dataType : 'json',

		success : function(response) {
			jQuery("#userSummary").jqGrid('setGridParam', {
				url : urlStr,
				ajaxGridOptions : {
					async : false
				},
				datatype : 'json',
				page : 1
			}).trigger("reloadGrid");

		},
		error : function() {
			alert("error while request");
		}

	});

}