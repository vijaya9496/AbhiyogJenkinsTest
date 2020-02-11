$(document).ready(function(){
	$("#unitLocationSummary").jqGrid({
		url : "/getUnitLocationSummary",
		width : 1150,
		height : 250,
		colNames : [ 'Id', 'Entity', 'Zone', 'Unit/Location Name','Unit/Location Heads', 'Update' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 100,
			align : 'center',
			hidden : true
		}, {
			name : 'entity',
			index : 'entity',
			width : 100,
			align : 'center'
		},
		{
			name : 'zone',
			index : 'zone',
			width : 100,
			align : 'center'
		},
		{
			name : 'unitLocationName',
			index : 'unitLocationName',
			width : 100,
			align : 'center'
		},
		{
			name : 'unitLocationHeads',
			index : 'unitLocationHeads',
			width : 100,
			align : 'center'
		},
		{
			name : 'update',
			index : 'update',
			width : 100,
			formatter:'showlink',
			formatoptions:{baseLinkUrl:'/updateUnitLocation'},
			align : 'center'
		}],
		rowNum : 10,
		rowList : [ 5, 10, 20 ],
		pager : '#unitLocationSummarypager',
		datatype : 'json',
		loadonce : true,
		viewrecords : true,
		ignoreCase : true,
		cmTemplate : {
			sortable : false
		}
	});
	jQuery("#unitLocationSummary").jqGrid('filterToolbar', {
		defaultSearch : "cn",
		stringResult : true,
		searchOnEnter : false
	});
	
	$("#entity").change(function(){
		getUnitLocationSummaryData();
	});
	$("#zone").change(function(){
		getUnitLocationSummaryData();
	});
	
	$("#newUnitLocation").click(function(){
		var urlStr = "/newUnitLocation";
		$("#unitLocationSummaryForm").attr("action",urlStr);
		$("#unitLocationSummaryForm").attr("method","GET");
		$("#unitLocationSummaryForm").submit();
	});
	$("#cancelBtn").click(function(){
		var urlStr = "/showUnitLocationSummary";
		$("#unitLocationSummaryForm").attr("action",urlStr);
		$("#unitLocationSummaryForm").attr("method","GET");
		$("#unitLocationSummaryForm").submit();
	});
});

function getUnitLocationSummaryData() {
	var urlStr = "/getUnitLocationSummaryBy?"
	var entity = jQuery.trim($('#entity').val());
	var zone = jQuery.trim($('#zone').val());
	var requestData = {};
	urlStr = urlStr + "entity=" + entity + "&zone=" + zone;

	$.ajax({
		url : urlStr,
		cache : false,
		async : false,
		data : requestData,
		dataType : 'json',

		success : function(response) {
			jQuery("#unitLocationSummary").jqGrid('setGridParam', {
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