$(document).ready(function(){
	$("#outsideCounselSummary").jqGrid({
		url : "/getOutsideCounselSummary",
		width : 1150,
		height : 250,
		colNames : [ 'Id', 'Lawfirm/Individual', 'Partner/Counsel', 'Email ID', 'Mobile No', 'Address', 'Update' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 100,
			align : 'center',
			hidden : true
		}, {
			name : 'lawfirmIndividual',
			index : 'lawfirmIndividual',
			width : 100,
			formatter:'showlink',
			formatoptions:{baseLinkUrl:'/viewOutsideCounsel'},
			align : 'center'
		},{
			name : 'partnerCounsel',
			index : 'partnerCounsel',
			width : 100,
			align : 'center'
		},{
			name : 'emailId',
			index : 'emailId',
			width : 100,
			align : 'center'
		},{
			name : 'mobileNo',
			index : 'mobileNo',
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
			width : 100,
			formatter:'showlink',
			formatoptions:{baseLinkUrl:'/updateOutsideCounsel'},
			align : 'center'
		}],
		rowNum : 10,
		rowList : [ 5, 10, 20 ],
		pager : '#outsideCounselSummarypager',
		datatype : 'json',
		loadonce : true,
		viewrecords : true,
		ignoreCase : true,
		cmTemplate : {
			sortable : false
		}
	});
	jQuery("#outsideCounselSummary").jqGrid('filterToolbar', {
		defaultSearch : "cn",
		stringResult : true,
		searchOnEnter : false
	});
});