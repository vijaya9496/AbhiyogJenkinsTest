$(document).ready(
		function() {
			$("#showCauseNoticeSummary").jqGrid(
					{
						url : "/getShowCauseNoticeDtls",
						width : 1150,
						height : 250,
						colNames : [ 'Id', 'Unit/Location', 'Owner',
								'Received From (Statutory Authorities)',
								'Received Date', 'Notice Reply Deadline',
								'Comment/ActionTaken', 'Subject',
								'Reference No', 'Document', 'Added By',
								'Status', 'Zone', 'Format', 'Update' ],
						colModel : [ {
							name : 'id',
							index : 'id',
							width : 100,
							align : 'center',
							hidden : true
						}, {
							name : 'unitLocation',
							index : 'unitLocation',
							width : 100,
							editable: true,
							align : 'center'
						}, {
							name : 'owner',
							index : 'owner',
							width : 100,
							align : 'center'
						}, {
							name : 'receivedFrom',
							index : 'receivedFrom',
							width : 100,
							align : 'center'
						}, {
							name : 'receivedDate',
							index : 'receivedDate',
							cellattr: function () { return 'style="white-space: normal;"'; },
							width : 100,
							align : 'center'
						}, {
							name : 'noticeReplyDeadline',
							index : 'noticeReplyDeadline',
							cellattr: function () { return 'style="white-space: normal;"'; },
							width : 100,
							align : 'center'
						}, {
							name : 'commentActionTaken',
							index : 'commentActionTaken',
							width : 100,
							align : 'center'
						}, {
							name : 'subject',
							index : 'subject',
							width : 100,
							align : 'center'
						}, {
							name : 'referenceNo',
							index : 'referenceNo',
							width : 100,
							align : 'center'
						}, {
							name : 'document',
							index : 'document',
							width : 100,
							cellattr: function () { return 'style="white-space: normal;"'; },
							formatter : 'showlink',
							formatoptions : {
								baseLinkUrl : '/downloadDocument'
							},
							align : 'center'
						}, {
							name : 'addedBy',
							index : 'addedBy',
							width : 100,
							align : 'center'
						}, {
							name : 'status',
							index : 'status',
							width : 100,
							align : 'center'
						}, {
							name : 'zone',
							index : 'zone',
							width : 100,
							align : 'center'
						}, {
							name : 'format',
							index : 'format',
							width : 100,
							align : 'center'
						}, {
							name : 'update',
							index : 'update',
							width : 100,
							formatter : 'showlink',
							formatoptions : {
								baseLinkUrl : '/updateNotice'
							},
							align : 'center'
						} ],
						rowNum : 10,
						rowList : [ 5, 10, 20 ],
						pager : '#showCauseNoticeSummarypager',
						datatype : 'json',
						loadonce : true,
						viewrecords : true,
						ignoreCase : true,
						cmTemplate : {
							sortable : false
						}

					});
			jQuery("#showCauseNoticeSummary").jqGrid('filterToolbar', {
				defaultSearch : "cn",
				stringResult : true,
				searchOnEnter : false
			});
			
			$("#entity").change(function(){
				getShowCauseNoticeData();
			});
			$("#zone").change(function(){
				getShowCauseNoticeData();
			});
			$("#unit").change(function(){
				getShowCauseNoticeData();
			});
			$("#format").change(function(){
				getShowCauseNoticeData();
			});
			$("#status").change(function(){
				getShowCauseNoticeData();
			});
		});

function getShowCauseNoticeData() {
	var urlStr = "/getShowCauseNoticeSummaryBy?"
	var entity = jQuery.trim($('#entity').val());
	var zone = jQuery.trim($('#zone').val());
	var unit = jQuery.trim($('#unit').val());
	var status = jQuery.trim($('#status').val());
	var format = jQuery.trim($('#format').val());
	var requestData = {};
	urlStr = urlStr + "entity=" + entity + "&zone=" + zone + "&unit=" + unit + "&status=" + status + "&format=" +format;
	$.ajax({
		url : urlStr,
		cache : false,
		async : false,
		data : requestData,
		dataType : 'json',

		success : function(response) {
			jQuery("#showCauseNoticeSummary").jqGrid('setGridParam', {
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