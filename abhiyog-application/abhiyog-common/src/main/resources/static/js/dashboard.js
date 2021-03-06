$(document).ready(function(){
	$("#dashboardSummary").jqGrid({
		url : "/dashboardSummary",
		width : 1150,
		height : 250,
		colNames : ['UnitOId','Region', 'Unit', 'Upcoming Hearing', 'Not Updated Hearing', 'Total' ],
		colModel : [{
			name : 'unitOId',
			index : 'unitOId',
			width : 100,
			align : 'center',
			key:true,
			hidden: true
		}, {
			name : 'region',
			index : 'region',
			width : 100,
			align : 'center',
		}, {
			name : 'unit',
			index : 'unit',
			width : 100,
			align : 'center'
		}, {
			name : 'upcomingHearing',
			index : 'upcomingHearing',
			width : 100,
			align : 'center'
		}, {
			name : 'notUpdatedHearing',
			index : 'notUpdatedHearing',
			width : 100,
			align : 'center'
		},{ 
			name : 'total',
			index : 'total',
			formatter:'showlink',
			formatoptions:{
				baseLinkUrl:'/getDashboardDetails',
				idName: "unitoid"
			},
			
			width : 100,
			align : 'center'
		} ],
		rowNum : 10,
		rowList : [ 5, 10, 20 ],
		pager : '#dashboardSummaryPager',
		datatype : 'json',
		loadonce : true,
		viewrecords : true,
		ignoreCase : true,
		cmTemplate : {
			sortable : false
		},
	});
	jQuery("#dashboardSummary").jqGrid('filterToolbar', {
		defaultSearch : "cn",
		stringResult : true,
		searchOnEnter : false
	});
});
