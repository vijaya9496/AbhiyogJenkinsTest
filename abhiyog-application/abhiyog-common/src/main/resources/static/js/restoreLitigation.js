jQuery(document).ready(function(){
	var allSelectedRows = [];
	jQuery("#restoreLitigationSummary").jqGrid(
			{
				url : "/getRestoreLitigationSummary",
				width : 1150,
				height : 250,
				colNames : [ 'Loid', 'Litigation ID', 'Entity',
						'Country Party', 'Case Number', 'Subject', 
						'UnderAct', 'Risk'],
				colModel : [ {
					name : 'loid',
					index : 'loid',
					width : 100,
					align : 'center',
					hidden : true
				}, {
					name : 'litigationId',
					index : 'litigationId',
					width : 100,
					align : 'center'
				}, {
					name : 'entity',
					index : 'entity',
					width : 100,
					align : 'center'
				}, {
					name : 'counterparty',
					index : 'counterparty',
					width : 100,
					align : 'center'
				}, {
					name : 'casenumber',
					index : 'casenumber',
					width : 100,
					align : 'center'
				}, {
					name : 'subject',
					index : 'subject',
					width : 100,
					align : 'center'
				}, {
					name : 'underAct',
					index : 'underAct',
					width : 100,
					align : 'center'
				}, {
					name : 'Risk',
					index : 'Risk',
					width : 100,
					align : 'center'
				}],
				
				onSelectRow: function(){
					var myGrid = $("#restoreLitigationSummary");
					var selectedRowId =  myGrid.jqGrid("getGridParam", 'selrow');
					alert(selectedRowId);
					allSelectedRows.push(selectedRowId);
					alert(allSelectedRows.length);
					
					
					var requestData = {};
					
					$("#restoreBtn").click(function(){
						
							var urlStr = "/updateRestoreLitigationData?";
							
//							urlStr = urlStr + "&loid=" + allSelectedRows[i];
							requestData["selectedGridRows"] = allSelectedRows.toString();
							$.ajax({
								type : "post",
								url : urlStr,
								cache : false,
								async : false,
								data : JSON.stringify(requestData),
								contentType : 'application/json; charset=utf-8',
								dataType : 'json',
								
								success : function(response){
									jQuery("#restoreLitigationSummary").setGridParam({rowNum:10,datatype:"json" }).trigger('reloadGrid');
									alert("Lititgation Restored Successfully");
									
								},
								error: function(){
									alert("error while request");
								}
								
							});
						
						
					});
//					var selectedRowData = myGrid.getRowData(selectedRowId);
//					alert(selectedRowData);
				},
				rowNum : 10,
				rowList : [ 5, 10, 20 ],
				pager : '#restoreLitigationSummarypager',
				datatype : 'json',
				loadonce : true,
				viewrecords : true,
				ignoreCase : true,
				cmTemplate : {
					sortable : false
				},
				multiselect : true,
			});
	
	jQuery("#restoreLitigationSummary").jqGrid('filterToolbar', {
		defaultSearch : "cn",
		stringResult : true,
		searchOnEnter : false
	});
	
	$("#entityName").change(function(){
		restoreLitigationSummaryData();
	});
	
	$("#counterPartyName").change(function(){
		restoreLitigationSummaryData();
	});
	
	$("#categoryName").change(function(){
		restoreLitigationSummaryData();
	});
	
	$("#underActName").change(function(){
		restoreLitigationSummaryData();
	});
	
	$("#courtTypeName").change(function(){
		restoreLitigationSummaryData();
	});
	
	$("#risk").change(function(){
		restoreLitigationSummaryData();
	});
});

function restoreLitigationSummaryData(){
	var urlStr = "/getRestoreLitigationSummaryData?";
	var entity = jQuery.trim($('#entityName').val());
	var counterParty = jQuery.trim($('#counterPartyName').val());
	var category = jQuery.trim($('#categoryName').val());
	var courtType = jQuery.trim($('#courtTypeName').val());
	var underActs = jQuery.trim($('#underActName').val());
	var risk = jQuery.trim($('#risk').val());
	
	var requestData = {};
	urlStr = urlStr + "&entityName="
			+ entity + "&counterPartyName=" + counterParty
			+ "&categoryName=" + category 
			+ "&courtTypeName=" + courtType + "&underActName=" + underActs + "&risk="
			+ risk;

	$.ajax({
		type : "post",
		url : urlStr,
		cache : false,
		async : false,
		data : requestData,
		dataType : 'json',
		
		success : function(response){
			jQuery("#restoreLitigationSummary").jqGrid('setGridParam', {
				url : urlStr,
				ajaxGridOptions : {
					async : false
				},
				datatype : 'json',
				page : 1
			}).trigger("reloadGrid");
			
		},
		error: function(){
			alert("error while request");
		}
		
	});
}