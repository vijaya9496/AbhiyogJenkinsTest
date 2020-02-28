jQuery(document).ready(
		function() {
			
			
			$(function() {
				$("#fromDate").datepicker({
					changeMonth : true,
					changeYear : true,
					dateFormat : 'yy-mm-dd'
				}).datepicker("setDate", new Date());
//				$( "#fromDate" ).datepicker( "setDate",new Date(new Date().getTime() + 24 * 60 * 60 * 1000));
//				$("#fromDate").datepicker().datepicker("setDate", new Date());

				$("#toDate").datepicker({
					changeMonth : true,
					changeYear : true,
					dateFormat : 'yy-mm-dd'
				}).datepicker("setDate", new Date());

			});

			$("#emailAlertLogSummary").jqGrid(
					{
						url : "/getAlertLogSummary",
						width : 1150,
						height : 250,
						colNames : [ 'Alert Id', 'To Email Id', 'Subject',
								'Created Date', 'Mail Sent Date',
								'Mail Status', 'View' ],
						colModel : [ {
							name : 'id',
							index : 'id',
							width : 100,
							align : 'center'
						}, {
							name : 'toEmailId',
							index : 'toEmailId',
							width : 100,
							align : 'center'
						}, {
							name : 'subject',
							index : 'subject',
							width : 100,
							align : 'center'
						}, {
							name : 'createdDate',
							index : 'toEmailId',
							width : 100,
							align : 'center'
						}, {
							name : 'mailSentDate',
							index : 'mailSentDate',
							width : 100,
							align : 'center'
						}, {
							name : 'mailStatus',
							index : 'mailStatus',
							width : 100,
							align : 'center'
						}, {
							name : 'view',
							index : 'view',
							width : 100,
							formatter : 'showlink',
							formatoptions : {
								baseLinkUrl : '/getMailDesc',
								target : '_blank'
							},
							align : 'center'
						} ],
						rowNum : 10,
						rowList : [ 5, 10, 20 ],
						pager : '#emailAlertLogSummarypager',
						datatype : 'json',
						loadonce : true,
						viewrecords : true,
						ignoreCase : true,
						cmTemplate : {
							sortable : false
						},

					});
			jQuery("#emailAlertLogSummary").jqGrid('filterToolbar', {
				defaultSearch : "cn",
				stringResult : true,
				searchOnEnter : false
			});
			
			$("#mailStatus").change(function(){
				getAlertLogBy();
			});
			
			
			
			$("#fromDate").datepicker({
				onSelect: function(dateText){
					console.log("Selected FromDate:: " +dateText);
					getAlertLogBy();
				}
			});
			
			$("#toDate").datepicker({
				onSelect: function(dateText){
					console.log("Selected ToDate:: " +dateText);
					getAlertLogBy();
				}
			});
		});

function getAlertLogBy() {
	var urlStr = "/getAlertLogBy?";
	var fromDate =jQuery.trim($("#fromDate").val()); 
	var toDate = jQuery.trim($("#toDate").val());
	var mailStatus = jQuery.trim($("#mailStatus").val());
	var requestData = {};
	urlStr = urlStr + "fromDate=" + fromDate + "&toDate=" + toDate
			+ "&mailStatus=" + mailStatus;
	$.ajax({
		url : urlStr,
		cache : false,
		async : false,
		data : requestData,
		dataType : 'json',

		success : function(response) {
			jQuery("#emailAlertLogSummary").jqGrid('setGridParam', {
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