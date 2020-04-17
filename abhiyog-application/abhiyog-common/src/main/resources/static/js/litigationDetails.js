$(document).ready(function(){
	
	$(function() {
		$("#disposedDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		}).datepicker("setDate", new Date());
	});
	
	$("#editBtn").click(function (){
		alert("inside EditBtn");
		/*var urlStr = "/updateLtgn";
		$("#caseDetailsForm").attr("action", urlStr);
		$("#caseDetailsForm").attr("method", "GET");
		$("#caseDetailsForm").submit();*/
		window.location.href = "/updateLtgn";
	});
	
	$("#backBtn").click(function(){
		var urlStr = "/showLitigationSummary";
		$("#litigationDetailsForm").attr("action", urlStr);
		$("#litigationDetailsForm").attr("method", "GET");
		$("#litigationDetailsForm").submit();
	});
	
// Start Witness Modal
	$("#addWitnessBtn").click(function(){
		alert(" addWitnessBtn Method called...");
		var requestData = {};
		alert($("#modalWitnessName").val());
		requestData["witnessVal"] = $("#modalWitnessName").val();
		alert($("#litigationId").val());
		requestData["litigationIdVal"] = $("#litigationId").val();
		$.ajax({
			type : "post",
			url : "/addWitnessDetails",
			cache : false,
			data : requestData,
			dataType : 'text',
			async : false,
			success : function(response) {
				alert(response);
				witnessmodal.style.display = "none";
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
// End Witness Modal
// Start Connected Litigation Modal
	$("#addConnectedLtgnBtn").click(function(){
		alert(" addConnectedLtgnBtn Method called...");
		var requestData = {};

		alert($("#modalComments").val());
		requestData["commentsVal"] = $("#modalComments").val();
		alert($("#modalLitigationId").val());
		requestData["litigationIdVal"] = $("#modalLitigationId").val();
		$.ajax({
			type : "post",
			url : "/addConnectedLitigationDetails",
			cache : false,
			data : requestData,
			dataType : 'text',
			async : false,
			success : function(response) {
				alert(response);
				witnessmodal.style.display = "none";
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
// End ConnectedLitigation
	
// Start History Modal
	$("#modalAddNextHearingBtn").click(function(){
		alert(" modalAddNextHearingBtn Method called...");
		var requestData = {};
		alert($("#modalHearingDt").val());
		requestData["hearingDateVal"] = $("#modalHearingDt").val();
		alert($("#modalStage").val());
		requestData["stageVal"] = $("#modalStage").val();
		alert($("#modalStageDetails").val());
		requestData["stageDetailsVal"] = $("#modalStageDetails").val();
		$.ajax({
			type : "post",
			url : "/historyDetails",
			cache : false,
			data : requestData,
			dataType : 'text',
			async : false,
			success : function(response) {
				alert(response);
				witnessmodal.style.display = "none";
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
// End History
	
// Start LawfirmBilling  Modal
	$("#addLawfirmBillingBtn").click(function(){
		alert(" addLawfirmBillingBtn Method called...");
		$.ajax({
			type : "post",
			url : "/addLawfirmBilling",
			cache : false,
			data : new FormData(document.getElementById("billingForm")),
			enctype: 'multipart/form-data',
            processData: false, 
            contentType: false,
            async : false,
		
			success : function(response) {
				alert(response);
				witnessmodal.style.display = "none";
			},
			error: function(){
				
				alert("Error while request");
			}
		})
	});
// End ConnectedLitigation	
	$("#modalDisposedDateBtn").click(function(){
		alert(" modalDisposedDateBtn Method called...");
		var requestData = {};
		alert($("#modalResult").val());
		requestData["resultVal"] = $("#modalResult").val();
		alert($("#modalDisposedDate").val());
		requestData["disposedDateVal"] = $("#modalDisposedDate").val();
		alert($("#modalComments").val());
		requestData["commentsVal"] = $("#modalDisposedComments").val();
		$.ajax({
			type : "post",
			url : "/addDisposedDate",
			cache : false,
			data : requestData,
			dataType : 'text',
			async : false,
			success : function(response) {
				alert(response);
				witnessmodal.style.display = "none";
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
	
	$("#modalConnectedLtgnResetBtn").click(function(){
		$("#modalResult").val('');
		$("#modalDisposedDate").val('');
		$("#modalDisposedComments").val('');
	});
	
// Start UploadDocument  Modal
	$("#modalUploadDocumentBtn").click(function(){
		alert(" modalUploadDocumentBtn Method called...");
		$.ajax({
			type : "post",
			url : "/uploadDocument",
			cache : false,
			data : new FormData(document.getElementById("uploadDocumentForm")),
			enctype: 'multipart/form-data',
            processData: false, 
            contentType: false,
            async:false,
			success : function(response) {
				alert(response);
				witnessmodal.style.display = "none";
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
	
//navConnect Grid
	$("#navConnect").click(function(){
		$("#connectedLitigationSummary").jqGrid({
			url : "/getConnectedLitigationDtls",
			width : 1150,
			height : 250,	
			colNames : [ 'ConnectedLitigation ID', 'Comments'],
			colModel : [ {
				name : 'cid',
				index : 'cid',
				width : 100,
				align : 'center'
				
			}, {
				name : 'comments',
				index : 'comments',
				width : 100,
				align : 'center'
			}],
			rowNum : 10,
			rowList : [ 5, 10, 20 ],
			pager : '#connectedLitigationSummaryPager',
			datatype : 'json',
			loadonce : true,
			viewrecords : true,
			ignoreCase : true,
			cmTemplate : {
				sortable : false
			}
		});
		
		jQuery("#connectedLitigationSummary").jqGrid('filterToolbar', {
			defaultSearch : "cn",
			stringResult : true,
			searchOnEnter : false
		});
		
	});
	
//Witness Grid
	$("#navWitness").click(function(){
		$("#witnessSummary").jqGrid({
			url : "/getWitnessDtls",
			width : 1150,
			height : 250,	
			colNames : [ 'witnessID', 'WitnessName', 'LitigationId'],
			colModel : [ {
				name : 'id',
				index : 'id',
				width : 100,
				align : 'center'
				
			}, {
				name : 'witnessName',
				index : 'witnessName',
				width : 100,
				align : 'center'
			}, {
				name : 'lid',
				index : 'lid',
				width : 100,
				align : 'center'
			}],
			rowNum : 10,
			rowList : [ 5, 10, 20 ],
			pager : '#witnessSummaryPager',
			datatype : 'json',
			loadonce : true,
			viewrecords : true,
			ignoreCase : true,
			cmTemplate : {
				sortable : false
			}
		});
		
		jQuery("#witnessSummary").jqGrid('filterToolbar', {
			defaultSearch : "cn",
			stringResult : true,
			searchOnEnter : false
		});
	});
	
//Lawfirm Billing
	$("#navBilling").click(function(){
		$("#lawfirmBillingSummary").jqGrid({
			url : "/getLawfirmBillingDtls",
			width : 1150,
			height : 250,	
			colNames : [ 'Lawfirm Billing Id', 'Litigation ID', 'Billing Type' ,'Billing Amount', 'Billing Date', 'Doc Name', 'Remark'],
			colModel : [ {
				name : 'lawfrimBillingId',
				index : 'lawfrimBillingId',
				width : 100,
				align : 'center'
				
			}, {
				name : 'litigationId',
				index : 'litigationId',
				width : 100,
				align : 'center'
			}, {
				name : 'billingType',
				index : 'billingType',
				width : 100,
				align : 'center'
			}, {
				name : 'billingAmount',
				index : 'billingAmount',
				width : 100,
				align : 'center'
			}, {
				name : 'billingDate',
				index : 'billingDate',
				width : 100,
				align : 'center'
			}, {
				name : 'docName',
				index : 'docName',
				width : 100,
				align : 'center'
			}, {
				name : 'remark',
				index : 'remark',
				width : 100,
				align : 'center'
			}],
			rowNum : 10,
			rowList : [ 5, 10, 20 ],
			pager : '#lawfirmBillingSummaryPager',
			datatype : 'json',
			loadonce : true,
			viewrecords : true,
			ignoreCase : true,
			cmTemplate : {
				sortable : false
			}
		});
		
		jQuery("#lawfirmBillingSummary").jqGrid('filterToolbar', {
			defaultSearch : "cn",
			stringResult : true,
			searchOnEnter : false
		});
	});
	
	
//History Summary
	$("#navHistory").click(function(){
		$("#historySummary").jqGrid({
			url : "/getHistoryDetails",
			width : 1150,
			height : 250,	
			colNames : [ 'Hearing Id', 'Litigation ID', 'Hearing Date' ,'StageName', 'Stage Details', 'Event', 'AddedBy', 'Attended By', 'Document', 'Update', 'Delete'],
			colModel : [ {
				name : 'hearingId',
				index : 'hearingId',
				width : 100,
				align : 'center'
				
			}, {
				name : 'litigationId',
				index : 'litigationId',
				width : 100,
				align : 'center'
			}, {
				name : 'hearingDate',
				index : 'hearingDate',
				width : 100,
				align : 'center'
			}, {
				name : 'stageName',
				index : 'stageName',
				width : 100,
				align : 'center'
			}, {
				name : 'stageDetails',
				index : 'stageDetails',
				width : 100,
				align : 'center'
			}, {
				name : 'event',
				index : 'event',
				width : 100,
				align : 'center'
			}, {
				name : 'addedBy',
				index : 'addedBy',
				width : 100,
				align : 'center'
			}, {
				name : 'attendedBy',
				index : 'attendedBy',
				width : 100,
				align : 'center'
			}, {
				name : 'docName',
				index : 'docName',
				width : 100,
				align : 'center'
			}, {
				name : 'update',
				index : 'update',
				width : 100,
				formatter : 'showlink',
				formatoptions : {
					baseLinkUrl : '/updateHistoryDetails'
				},
				align : 'center'
			}, {
				name : 'delete',
				index : 'delete',
				width : 100,
				align : 'center'
			}],
			rowNum : 10,
			rowList : [ 5, 10, 20 ],
			pager : '#historySummaryPager',
			datatype : 'json',
			loadonce : true,
			viewrecords : true,
			ignoreCase : true,
			cmTemplate : {
				sortable : false
			}
		});
		
		jQuery("#historySummary").jqGrid('filterToolbar', {
			defaultSearch : "cn",
			stringResult : true,
			searchOnEnter : false
		});
	});
	
//Activity Log Summary
	
	$("#navActivity").click(function(){
		$("#activityLogSummary").jqGrid({
			url : "/getActivityLog",
			width : 1150,
			height : 250,	
			colNames : [ 'LogID', 'L History Id', 'Updated By' ,'Activity Type', 'Activity Description', 'Modified Date'],
			colModel : [ {
				name : 'logId',
				index : 'logId',
				width : 100,
				align : 'center'
				
			}, {
				name : 'lHistoryId',
				index : 'lHistoryId',
				width : 100,
				align : 'center'
			}, {
				name : 'updatedBy',
				index : 'updatedBy',
				width : 100,
				align : 'center'
			}, {
				name : 'activityType',
				index : 'activityType',
				width : 100,
				align : 'center'
			}, {
				name : 'activityDesc',
				index : 'activityDesc',
				width : 100,
				align : 'center'
			}, {
				name : 'modifiedDate',
				index : 'modifiedDate',
				width : 100,
				align : 'center'
			}],
			rowNum : 10,
			rowList : [ 5, 10, 20 ],
			pager : '#activityLogSummaryPager',
			datatype : 'json',
			loadonce : true,
			viewrecords : true,
			ignoreCase : true,
			cmTemplate : {
				sortable : false
			}
		});
		
		jQuery("#activityLogSummary").jqGrid('filterToolbar', {
			defaultSearch : "cn",
			stringResult : true,
			searchOnEnter : false
		});
	});
	
//Document Summary
	
	$("#navDocuments").click(function(){
		$("#documentSummary").jqGrid({
			url : "/getDocumentSummary",
			width : 1150,
			height : 250,	
			colNames : [ 'Litigation Doc Id', 'Hearing Date', 'Title' ,'Comment', 'File Size', 'Delete'],
			colModel : [ {
				name : 'docId',
				index : 'docId',
				width : 100,
				align : 'center',
				hidden: true
				
			}, {
				name : 'hearingDate',
				index : 'hearingDate',
				width : 100,
				align : 'center'
			}, {
				name : 'title',
				index : 'title',
				width : 100,
				formatter : 'showlink',
				formatoptions : {
					baseLinkUrl : '/downloadUploadedDoc'
				},
				align : 'center'
			}, {
				name : 'comment',
				index : 'comment',
				width : 100,
				align : 'center'
			}, {
				name : 'fileSize',
				index : 'fileSize',
				width : 100,
				align : 'center'
			}, {
				name : 'delete',
				index : 'delete',
				width : 100,
				formatter : 'showlink',
				formatoptions : {
					baseLinkUrl : '/deleteUploadedDocument'
				},
				align : 'center'
			}],
			rowNum : 10,
			rowList : [ 5, 10, 20 ],
			pager : '#documentSummaryPager',
			datatype : 'json',
			loadonce : true,
			viewrecords : true,
			ignoreCase : true,
			cmTemplate : {
				sortable : false
			}
		});
		
		jQuery("#documentSummary").jqGrid('filterToolbar', {
			defaultSearch : "cn",
			stringResult : true,
			searchOnEnter : false
		});
	});
	
	$("#historyReportExportToExcel").click(function(){
		window.location.href = "/historyReportExportToExcel";
	});

	
});

function getOutsideCounselData(data){
		alert(data);
		window.location.href = "/getOutsideCounselDtls/"+ data;
		
}
