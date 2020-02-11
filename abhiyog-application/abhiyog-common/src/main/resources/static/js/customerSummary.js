jQuery(document).ready(function() {
	$("#customerSummary").jqGrid({
		url : "/getCustomerSummary",
		width : 1150,
		height : 250,
		colNames : [ 'Id', 'Counter Party Name', 'Contact Person', 'Mobile', 'Address', 'Update' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 100,
			align : 'center',
			hidden : true
		}, {
			name : 'counterPartyName',
			index : 'counterPartyName',
			width : 100,
			formatter:'showlink',
			formatoptions:{baseLinkUrl:'/viewCounterParty'},
			align : 'center'
		}, {
			name : 'contactPerson',
			index : 'contactPerson',
			width : 100,
			align : 'center'
		},{
			name : 'mobile',
			index : 'mobile',
			width : 100,
			align : 'center'
		},{
			name : 'address',
			index : 'address',
			width : 100,
			align : 'center'
		},{
			name : 'update',
			index : 'update',
			formatter:'showlink',
			formatoptions:{baseLinkUrl:'/updateCounterParty'},
			width : 100,
			align : 'center'
		} ],
		rowNum : 10,
		rowList : [ 5, 10, 20 ],
		pager : '#customerSummaryPager',
		datatype : 'json',
		loadonce : true,
		viewrecords : true,
		ignoreCase : true,
		cmTemplate : {
			sortable : false
		},
	});
	jQuery("#customerSummary").jqGrid('filterToolbar', {
		defaultSearch : "cn",
		stringResult : true,
		searchOnEnter : false
	});
	
	$('#addCounterPartyBtn').click(function(){
		var urlStr = "/newCounterParty";
		$("#counterPartySummaryForm").attr("action",urlStr);
		$("#counterPartySummaryForm").attr("method","GET");
		$("#counterPartySummaryForm").submit();
	});
	
});