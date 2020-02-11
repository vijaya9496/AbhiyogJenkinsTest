$(document).ready(function(){
	$("#regionsSummary").jqGrid({
		url : "/getRegionSummary",
		width : 1150,
		height : 250,
		colNames : [ 'Id', 'Zone Name', 'Update' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 100,
			align : 'center',
			hidden : true
		}, {
			name : 'zoneName',
			index : 'zoneName',
			width : 100,
			align : 'center'
		},
		{
			name : 'update',
			index : 'update',
			width : 100,
			formatter:'showlink',
			formatoptions:{baseLinkUrl:'/updateZone'},
			align : 'center'
		}],
		rowNum : 10,
		rowList : [ 5, 10, 20 ],
		pager : '#regionsSummarypager',
		datatype : 'json',
		loadonce : true,
		viewrecords : true,
		ignoreCase : true,
		cmTemplate : {
			sortable : false
		}
	});
	jQuery("#regionsSummary").jqGrid('filterToolbar', {
		defaultSearch : "cn",
		stringResult : true,
		searchOnEnter : false
	});
	
	$("#cancelBtn").click(function(){
		$("#zoneName").val('');
	});
});