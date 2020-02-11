jQuery(document).ready(function() {
	$("#entitySummary").jqGrid({
		url : "/getEntitySummary",
		width : 1150,
		height : 250,
		colNames : [ 'Id', 'Entity Name', 'Entity Head', 'Update' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 100,
			align : 'center',
			hidden : true
		}, {
			name : 'entityName',
			index : 'entityName',
			width : 100,
			align : 'center'
		}, {
			name : 'entityHead',
			index : 'entityHead',
			width : 100,
			align : 'center'
		}, {
			name : 'update',
			index : 'update',
			formatter:'showlink',
			formatoptions:{baseLinkUrl:'/updateEntity'},
			width : 100,
			align : 'center'
		} ],
		rowNum : 10,
		rowList : [ 5, 10, 20 ],
		pager : '#entitySummarypager',
		datatype : 'json',
		loadonce : true,
		viewrecords : true,
		ignoreCase : true,
		cmTemplate : {
			sortable : false
		},
	});
	jQuery("#entitySummary").jqGrid('filterToolbar', {
		defaultSearch : "cn",
		stringResult : true,
		searchOnEnter : false
	});
	
	$('#newEntityBtn').click(function(){
		var urlStr = "/newEntity";
		$("#entitySummaryForm").attr("action",urlStr);
		$("#entitySummaryForm").attr("method","GET");
		$("#entitySummaryForm").submit();
	});
});

