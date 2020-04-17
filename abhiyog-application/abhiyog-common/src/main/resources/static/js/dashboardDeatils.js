$(document).ready(function(){
	
	var urlParams = new URLSearchParams(window.location.search);
	var unioId=urlParams.get('unitoid');
	console.log("urlParams--"+urlParams+" unioId--"+unioId);
	var UrlStr ="/getDashboardDtlByUnitoId?unitoid="+unioId;
	  
	$("#dashboardDetailsData").jqGrid({
		url : UrlStr,
		width : 1300,
		height : 250,
		colNames : ['LitigationOId','L.ID/Status','File No.', 'Entity(Unit/Location)', 'Counter Party', 'Case number', 'Stage','Hearing Date','Risk','Posssible Claim','Remark'],
		colModel:[
			{name: 'loid', index: 'loid', width:100,align: 'center', hidden: true},
			{name: 'lIdStatus', index: 'lIdStatus', width:100,align: 'center'}, 				
			{name: 'fileNo', index: 'fileNo', width:100, align: 'center'},
			{name: 'entity', index: 'entity', width:100, align: 'center'},
			{name: 'counterParty', index: 'counterParty', width:100, align: 'center'},
			{name: 'caseNumber', index: 'caseNumber', width:100, align: 'center',
				formatter:'showlink',formatoptions:{baseLinkUrl:'/viewLitigationDetails'}},
			{name: 'stage', index: 'stage', width:100, align: 'center'},
			{name: 'hearingDate', index: 'hearingDate', width:100, align: 'center'},
			{name: 'risk', index: 'risk', width:100, align: 'center'},
			{name: 'possibleClaim', index: 'possibleClaim', width:100, align: 'center'},
			{name: 'remark', index: 'remark', width:100, align: 'center'}
		],
		rowNum : 10,
		rowList : [ 5, 10, 20 ],
		pager : '#dashboardDetailsDataPager',
		datatype : 'json',
		loadonce : true,
		viewrecords : true,
		ignoreCase : true,
		cmTemplate : {
			sortable : false
		},
	});
	jQuery("#dashboardDetailsData").jqGrid('filterToolbar', {
		defaultSearch : "cn",
		stringResult : true,
		searchOnEnter : false
	});
});
