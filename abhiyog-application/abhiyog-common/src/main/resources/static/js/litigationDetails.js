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
	var witnessmodal = document.getElementById("addWitnessModal");
	var span = document.getElementsByClassName("close")[0];
	$("#addNewWitness").click(function() {
		witnessmodal.style.display = "block";
	});
	span.onclick = function() {
		witnessmodal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == witnessmodal) {
			witnessmodal.style.display = "none";
		}
	}
	$("#modalAddWitnessResetBtn").click(function() {
		$("#modalWitnessName").val("");
	});

	$("#modalAddWitnesscancelBtn").click(function() {
		witnessmodal.style.display = "none";
	});
	
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
	var connectedLitigationmodal = document.getElementById("addConnectedLitigationModal");
	var span = document.getElementsByClassName("close")[0];
	$("#addConnectedLitigation").click(function() {
		connectedLitigationmodal.style.display = "block";
	});
	span.onclick = function() {
		connectedLitigationmodal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == connectedLitigationmodal) {
			connectedLitigationmodal.style.display = "none";
		}
	}
	$("#modalConnectedLtgnResetBtn").click(function() {
		$("#modalComments").val("");
		$("#modalLitigationId").val("");
	});

	$("#modalConnectedLtgncancelBtn").click(function() {
		connectedLitigationmodal.style.display = "none";
	});
	
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
			success : function(response) {
				alert(response);
				connectedLitigationmodal.style.display = "none";
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
// End ConnectedLitigation
	
// Start History Modal
	var nextHearingmodal = document.getElementById("addNextHearingDtModal");
	var span = document.getElementsByClassName("close")[0];
	$("#addNextHearingDt").click(function() {
		nextHearingmodal.style.display = "block";
	});
	span.onclick = function() {
		nextHearingmodal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == nextHearingmodal) {
			nextHearingmodal.style.display = "none";
		}
	}
	

	$("#modalAddNextHearingBtn").click(function() {
		nextHearingmodal.style.display = "none";
	});
	
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
			success : function(response) {
				alert(response);
				nextHearingmodal.style.display = "none";
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
// End History
	
	// Start LawfirmBilling  Modal
	var lawfirmBillingmodal = document.getElementById("addLawfirmBillingModal");
	var span = document.getElementsByClassName("close")[0];
	$("#addlawfirmBilling").click(function() {
		lawfirmBillingmodal.style.display = "block";
	});
	span.onclick = function() {
		lawfirmBillingmodal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == lawfirmBillingmodal) {
			lawfirmBillingmodal.style.display = "none";
		}
	}
	$("#modalAddLawfirmBillingResetBtn").click(function() {
		$("#modalBillingType").val("");
		$("#modalBillingAmount").val("");
		$("#modalBillingDate").val("");
		$("#modalRemarks").val("");
		$("#modalUploadFile").val("");
	});

	$("#modalAddLawfirmBillingCancelBtn").click(function() {
		lawfirmBillingmodal.style.display = "none";
	});
	
	$("#addLawfirmBillingBtn").click(function(){
		alert(" addLawfirmBillingBtn Method called...");
		var requestData = {};

		/*alert($("#modalBillingType").val());
		requestData["billingType"] = $("#modalBillingType").val();
		alert($("#modalBillingAmount").val());
		requestData["billingAmount"] = $("#modalBillingAmount").val();
		alert($("#modalBillingDate").val());
		requestData["billingDate"] = $("#modalBillingDate").val();
		alert($("#modalRemarks").val());
		requestData["remark"] = $("#modalRemarks").val();
		alert($("#modalUploadFile").val());
		requestData["uploadFile"] = $("#modalUploadFile").val();*/
		
		$.ajax({
			type : "post",
			url : "/addLawfirmBilling",
			cache : false,
			data : new FormData(document.getElementById("billingForm")),
			enctype: 'multipart/form-data',
            processData: false, 
            contentType: false,
//            dataType: 'json',
		
			success : function(response) {
				alert(response);
				
				lawfirmBillingmodal.style.display = "none";
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
			success : function(response) {
				alert(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
	
	
	// Start UploadDocument  Modal
	var uploadDocumentmodal = document.getElementById("uploadDocumentModal");
	var span = document.getElementsByClassName("close")[0];
	$("#uploadDocuments").click(function() {
		uploadDocumentmodal.style.display = "block";
	});
	span.onclick = function() {
		uploadDocumentmodal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == lawfirmBillingmodal) {
			uploadDocumentmodal.style.display = "none";
		}
	}
	$("#modalAddLawfirmBillingResetBtn").click(function() {
		$("#modalBillingType").val("");
		$("#modalBillingAmount").val("");
		$("#modalBillingDate").val("");
		$("#modalRemarks").val("");
		$("#modalUploadFile").val("");
	});

	$("#modaluploadDocumentCancelBtn").click(function() {
		uploadDocumentmodal.style.display = "none";
	});
	
	$("#modalUploadDocumentBtn").click(function(){
		alert(" modalUploadDocumentBtn Method called...");
		/*var requestData = {};

		alert($("#modalDocumentTitle").val());
		requestData["documentTitle"] = $("#modalDocumentTitle").val();
		alert($("#modalComments").val());
		requestData["comments"] = $("#modalComments").val();
		alert($("#modaluploadFile").val());
		requestData["uploadFile"] = $("#modaluploadFile").val();*/
		
		$.ajax({
			type : "post",
			url : "/uploadDocument",
			cache : false,
//			data : requestData,
			data : new FormData(document.getElementById("uploadDocumentForm")),
			enctype: 'multipart/form-data',
            processData: false, 
            contentType: false,
//            contentType: 'multipart/form-data',
//			dataType : 'json',
			success : function(response) {
				uploadDocumentmodal.style.display = "none";
				alert(response);
			},
			error: function(){
				uploadDocumentmodal.style.display = "none";
				alert("Error while request");
			}
		})
	});
	
	
	$("#historyTabId").click(function(){
		
		$.ajax({
			type : "get",
			url : "/getHistoryDetails",
			cache : false,
//			data : requestData,
//			data : new FormData(document.getElementById("uploadDocumentForm")),
//			enctype: 'multipart/form-data',
//            processData: false, 
//            contentType: false,
//            contentType: 'application/json',
//			dataType : 'json',
			success : function(response) {
				for(i in response){
					
					$("#historySummary").append('<tr><td>' +response[i].hearingId+'</td><td> '+response[i].litigationId+' </td><td>' + response[i].hearingDt +'</td> <td>'+ response[i].stage+'</td> <td>' +response[i].stageDetails+'</td><td>'+response[i].event+'</td><td>'+response[i].addedBy+'</td><td>'+response[i].attendedBy+'</td><td>'+"NA"+'</td><td><a href=/updateHistoryDetails/'+response[i].hearingId+'>'+"Update"+'</a></td><td> <a href=# target=_self>'+"Delete"+'</td></tr>')
				}
				
			},
			error: function(){
				uploadDocumentmodal.style.display = "none";
				alert("Error while request");
			}
		})
	});
	
	
	
	
	$("#witnessDtlsSummary").jqGrid(
			{
				url : "/getWitnessDetails",
				width : 1150,
				height : 250,
				colNames : [ 'Witness Id', 'Litigation Id', 'Witness Name' ],
				colModel : [ {
					name : 'wid',
					index : 'wid',
					width : 100,
					align : 'center'
				}, {
					name : 'lid',
					index : 'lid',
					width : 100,
					align : 'center'
				}, {
					name : 'wName',
					index : 'wName',
					width : 100,
					align : 'center'
				}],
				rowNum : 2,
				rowList : [ 5, 10, 20 ],
				pager : '#witnessDtlsSummarypager',
				datatype : 'json',
				loadonce : true,
				viewrecords : true,
				ignoreCase : true,
				cmTemplate : {
					sortable : false
				},

			});
	jQuery("#witnessDtlsSummary").jqGrid('filterToolbar', {
		defaultSearch : "cn",
		stringResult : true,
		searchOnEnter : false
	});
	
	
	
	
	
});

function getOutsideCounselData(data){
		alert(data);
		var requestData = {};
		requestData["lawfirmVal"] = data;
		
		$.ajax({
			type : "post",
			url : "/getOutsideCounselDetails",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
				alert(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
}
