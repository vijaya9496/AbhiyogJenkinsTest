jQuery(document).ready(
		function() {

			jQuery("#litigationSummary").jqGrid(
					{
						url : "/getLitigationSummary",
						width : 1150,
						height : 250,
						colNames : [ 'Loid', 'Lid Status', 'File No',
								'Entity (Unit/Location)', 'Country Party',
								'Case Number', 'Subject', 'Stage',
								'Hearing Date', 'Risk', 'Possible Claim',
								'Remark', 'Zone', 'Delete' ],
						colModel : [ {
							name : 'loid',
							index : 'loid',
							width : 100,
							align : 'center',
							hidden : true
						}, {
							name : 'lidstatus',
							index : 'lidstatus',
							width : 100,
							align : 'center'
						}, {
							name : 'filename',
							index : 'filename',
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
							formatter:'showlink',
							formatoptions:{baseLinkUrl:'/viewLitigationDetails'},
							width : 100,
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
							name : 'Hearingdate',
							index : 'Hearingdate',
							width : 100,
							align : 'center'
						}, {
							name : 'Risk',
							index : 'Risk',
							width : 100,
							align : 'center'
						}, {
							name : 'Possibleclaim',
							index : 'Possibleclaim',
							width : 100,
							align : 'center'
						}, {
							name : 'Remark',
							index : 'Remark',
							width : 100,
							align : 'center'
						}, {
							name : 'Zone',
							index : 'Zone',
							width : 100,
							align : 'center'
						},{
							name : 'delete',
							index : 'delete',
							formatter:'showlink',
							formatoptions:{baseLinkUrl:'/updateDeleteStatus'},
							width : 100,
							align : 'center'
						} ],
						rowNum : 10,
						rowList : [ 5, 10, 20 ],
						pager : '#litigationSummarypager',
						datatype : 'json',
						loadonce : true,
						viewrecords : true,
						ignoreCase : true,
						cmTemplate : {
							sortable : false
						},
						multiselect : true,
					});
			/*
			 * $grid.jqGrid('navGrid', '#litigationSummarypager', {edit: false,
			 * add: false, del: false, search: false}, {}, {}, {}, {
			 * multipleSearch: true, multipleGroup: true, recreateFilter: true
			 * });
			 */
			jQuery("#litigationSummary").jqGrid('filterToolbar', {
				defaultSearch : "cn",
				stringResult : true,
				searchOnEnter : false
			});
		});

function getLitigationSummaryData() {
//	alert("inside getLitigationSummaryData");
	var urlStr = "/getLitigationSummaryData?";
	var zone = jQuery.trim($('#zoneName').val());
	var format = jQuery.trim($('#format').val());
	var entity = jQuery.trim($('#entityName').val());
	var func = jQuery.trim($('#deptName').val());
	var counterParty = jQuery.trim($('#counterPartyName').val());
	var category = jQuery.trim($('#categoryName').val());
	var possibleClaim = jQuery.trim($('#claim').val());
	var state = jQuery.trim($('#stateName').val());
	var lawfirmIndividual = jQuery.trim($('#lawfirm').val());
	var courtType = jQuery.trim($('#courtTypeName').val());
	var underActs = jQuery.trim($('#underActName').val());
	var risk = jQuery.trim($('#risk').val());
	var status = jQuery.trim($('#status').val());
	var matterByAgainst = jQuery.trim($('#matterBy').val());
	var litigationByAgainst = jQuery.trim($('#litigationByAgainst').val());
	var unitLocation = jQuery.trim($('#unitLocation').val());
	var city = jQuery.trim($('#city').val());
	var policeStation = jQuery.trim($('#policeStation').val());
	var courtForum = jQuery.trim($('#courtForum').val());
	var requestData = {};
	urlStr = urlStr + "zoneName=" + zone + "&format=" + format + "&entityName="
			+ entity + "&deptName=" + func + "&counterPartyName=" + counterParty
			+ "&categoryName=" + category + "&claim=" + possibleClaim
			+ "&stateName=" + state + "&lawfirm=" + lawfirmIndividual
			+ "&courtTypeName=" + courtType + "&underActName=" + underActs + "&risk="
			+ risk + "&status=" + status + "&matterBy="
			+ matterByAgainst + "&litigationByAgainst=" + litigationByAgainst;

	$.ajax({
		type : "post",
		url : urlStr,
		cache : false,
		async : false,
		data : requestData,
		dataType : 'json',
		
		success : function(response){
			jQuery("#litigationSummary").jqGrid('setGridParam', {
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