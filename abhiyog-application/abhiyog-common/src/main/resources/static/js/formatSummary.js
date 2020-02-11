$(document).ready(function(){
	$("#formatSummary").jqGrid({
		url : "/getFormatSummary",
		width : 1150,
		height : 250,
		colNames : [ 'Id', 'Format', 'Update' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 100,
			align : 'center',
			hidden : true
		}, {
			name : 'format',
			index : 'format',
			width : 100,
			align : 'center'
		},
		{
			name : 'update',
			index : 'update',
			width : 100,
			formatter:'showlink',
			formatoptions:{baseLinkUrl:'/updateFormat'},
			align : 'center'
		}],
		rowNum : 10,
		rowList : [ 5, 10, 20 ],
		pager : '#formatSummarypager',
		datatype : 'json',
		loadonce : true,
		viewrecords : true,
		ignoreCase : true,
		cmTemplate : {
			sortable : false
		}
	});
	jQuery("#formatSummary").jqGrid('filterToolbar', {
		defaultSearch : "cn",
		stringResult : true,
		searchOnEnter : false
	});
	
	$("#cancelBtn").click(function(){
		$("#format").val('');
	});
});