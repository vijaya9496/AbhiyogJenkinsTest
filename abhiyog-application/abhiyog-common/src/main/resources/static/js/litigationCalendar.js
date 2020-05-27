jQuery(document).ready(function(){
	
	$(function() {
		$("#fromDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		}).datepicker("setDate", new Date());

		var d = new Date();
		d.setMonth(d.getMonth() + 3);
		
		$("#toDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		}).datepicker("setDate", d);
	});
	
	$("#fromDate").change(function(){
		var fromDt = $("#fromDate").val();
//		alert("FromDate:: " +fromDt);
		var frmDt = new Date(fromDt);
//		alert(frmDt);
//		alert(frmDt.getDate());
			
		$("#toDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		}).datepicker("setDate", new Date(frmDt.setMonth(frmDt.getMonth() + 3)));
	});
	
	
	$("#toDate").change(function(){
		var toDt = $("#toDate").val();
		var toDate = new Date(toDt);
			
		$("#fromDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		}).datepicker("setDate", new Date(toDate.setMonth(toDate.getMonth() - 3)));
	});

	
	jQuery("#litigationCalendarSummary").jqGrid(
			{
//				url : "/getUserSummary",
				width : 1150,
				height : 250,
				colNames : [ 'L.ID/Status', 'File No', 'Entity (Unit/Location)', 'Counterparty',
						'Case Number', 'Subject', 'Stage', 'Hearing Date', 'Risk', 'Possible Claim', 'Remark', 'Handled By' ],
				colModel : [ {
					name : 'lid',
					index : 'lid',
					width : 100,
					align : 'center',
					
				}, {
					name : 'fileNo',
					index : 'fileNo',
					width : 100,
					align : 'center'
				}, {
					name : 'entityUnitLcoation',
					index : 'entityUnitLcoation',
					width : 100,
					align : 'center'
				}, {
					name : 'counterParty',
					index : 'counterParty',
					width : 100,
					align : 'center'
				}, {
					name : 'caseNumber',
					index : 'caseNumber',
					width : 100,
					formatter : 'showlink',
					formatoptions : {
						baseLinkUrl : '/viewLitigationDetails'
					},
					align : 'center'
				}, {
					name : 'subject',
					index : 'subject',
					width : 100,
					align : 'center'
				}, {
					name : 'stage',
					index : 'stage',
					width : 100,
					align : 'center'
				}, {
					name : 'hearingDate',
					index : 'hearingDate',
					width : 100,
					align : 'center'
				}, {
					name : 'risk',
					index : 'risk',
					width : 100,
					align : 'center'
				},{
					name : 'possibleClaim',
					index : 'possibleClaim',
					width : 100,
					align : 'center'
				}, {
					name : 'remark',
					index : 'remark',
					width : 100,
					align : 'center'
				},{
					name : 'handledBy',
					index : 'handledBy',
					width : 100,
					align : 'center'
				} ],
				rowNum : 10,
				rowList : [ 5, 10, 20 ],
				pager : '#litigationCalendarSummarypager',
				datatype : 'json',
				loadonce : true,
				viewrecords : true,
				ignoreCase : true,
				cmTemplate : {
					sortable : false
				},
			// multiselect : true,
			});
	jQuery("#litigationCalendarSummary").jqGrid('filterToolbar', {
		defaultSearch : "cn",
		stringResult : true,
		searchOnEnter : false
	});
	
	
	
	$("#searchBtn").click(function(){
		var urlStr = "/getLitigationCalendarSummaryData?";
		var zone = jQuery.trim($('#zoneName').val());
		var format = jQuery.trim($('#format').val());
		var entity = jQuery.trim($('#entityName').val());
		var unitLocation = jQuery.trim($("#unitLocation").val());
		var counterParty = jQuery.trim($('#counterPartyName').val());
		var category = jQuery.trim($('#categoryName').val());
		var possibleClaim = jQuery.trim($('#claim').val());
		var state = jQuery.trim($('#stateName').val());
		var city = jQuery.trim($('#city').val());
		var courtForum = jQuery.trim($('#courtForum').val());
		var lawfirmIndividual = jQuery.trim($('#lawfirm').val());
		var policeStation = jQuery.trim($('#policeStation').val());
		var courtType = jQuery.trim($('#courtTypeName').val());
		var underActs = jQuery.trim($('#underActName').val());
		var risk = jQuery.trim($('#risk').val());
		var status = jQuery.trim($('#status').val());
		var matterByAgainst = jQuery.trim($('#matterBy').val());
		var fromDate = jQuery.trim($('#fromDate').val());
		var toDate = jQuery.trim($("#toDate").val());
		
		
		
		var requestData = {};
		urlStr = urlStr + "zoneName=" + zone + "&format=" + format + "&entityName="
				+ entity + "&unitLocation=" + unitLocation + "&counterPartyName=" + counterParty
				+ "&categoryName=" + category + "&claim=" + possibleClaim
				+ "&stateName=" + state + "&city=" + city + "&courtForum=" +courtForum + "&lawfirm=" + lawfirmIndividual
				+ "&policeStation=" + policeStation + "&courtTypeName=" + courtType + "&underActName=" + underActs + "&risk="
				+ risk + "&status=" + status + "&matterBy="
				+ matterByAgainst + "&fromDate=" + fromDate + "&toDate=" +toDate;

		$.ajax({
			type : "post",
			url : urlStr,
			cache : false,
			async : false,
			data : requestData,
			dataType : 'json',
			
			success : function(response){
				jQuery("#litigationCalendarSummary").jqGrid('setGridParam', {
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
	});
	
});