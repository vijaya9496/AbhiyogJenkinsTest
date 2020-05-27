$(document).ready(function() {
	$(function() {
		$("#issueDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		});

		$("#receivedDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		});

		$("#noticeReplyDeadline").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		});

	});

	$("#documentSummary").jqGrid({
		url : "/getNoticeDocsDtls",
		width : 1150,
		height : 250,
		colNames : [ 'Id', 'Document', 'ShowCauseNoticeFormId', 'Comment', 'FileSize', 'Delete' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 100,
			align : 'center',
			hidden : true
		}, {
			name : 'document',
			index : 'document',
			width : 100,
			formatter : 'showlink',
			formatoptions : {
				baseLinkUrl : '/downloadDocument'
			},
			align : 'center'
		},{
			name : 'showCauseNoticeFormId',
			index : 'showCauseNoticeFormId',
			width : 100,
			align : 'center',
			hidden:true
		},{
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
				baseLinkUrl : '/deleteDocument'
			},
			align : 'center'
		} ],
		rowNum : 10,
		rowList : [ 5, 10, 20 ],
		pager : '#documentSummarypager',
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
	
	$("#cancelBtn").click(function(){
		var urlStr = "/showNoticeSummary";
		$("#updateNoticeForm").attr("action",urlStr);
		$("#updateNoticeForm").attr("method","GET");
		$("#updateNoticeForm").submit();
	});


	// Get the modal
	var modal = document.getElementById("myModal");

	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks the button, open the modal 
	btn.onclick = function() {
	  modal.style.display = "block";
	}

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	  modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
	}
	
	$("#modalResetBtn").click(function(){
		$("#uploadFile").val("");
		$("#commentsDoc").val("");
	});
	
	$("#zoneName").change(function(){
//		alert("Method called...");
		var requestData = {};

//		alert($('#zoneName :selected').text());
		requestData["zoneNameVal"] = $('#zoneName :selected').text();
		requestData["entityNameVal"] = $('#entityName :selected').text();
		var val = "Select";
		
		$.ajax({
			type : "get",
			url : "/getUnitLocationByZone",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
				response = $.parseJSON(response);
//				alert(response);
				var options;
				options = '<option value="' + val + '">' + val + '</option>';
				for(var i in response){
//					alert("UnitValue"+response[i].unitName);
					$('#unitName')
				    .find('option')
				    .remove();
					
					options += '<option value="' + response[i].unitName + '">' + response[i].unitName + '</option>';
					
//					$('#unitName').append(`<option value="${response[i].unitName}"> ${response[i].unitName} </option>`);
				    
				}
				$("#unitName").html(options);

			},
			error: function(){
				alert("Error while request");
			}
		})
	});
});

/*function reloadDocumentGrid() {
	var id = location.search.split('id=')[1];
	alert("param" + id);
	var urlStr = "/updateNotice";
	urlStr = urlStr + "id=" + id;
	alert("urlStr" + urlStr);
	jQuery("#documentSummary").jqGrid('setGridParam', {
		url : urlStr,
		ajaxGridOptions : {
			async : false
		},
		datatype : 'json',
		page : 1
	}).trigger("reloadGrid");
}*/