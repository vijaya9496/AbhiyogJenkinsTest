$(document)
		.ready(
				function() {

					var d = new Date();
					d.setMonth(d.getMonth() - 3);
					$("#fromDate").datepicker({
						changeMonth : true,
						changeYear : true,
						dateFormat : 'yy-mm-dd'
					}).datepicker("setDate", d);

					$("#toDate").datepicker({
						changeMonth : true,
						changeYear : true,
						dateFormat : 'yy-mm-dd'
					}).datepicker("setDate", new Date());

					var fromDate = jQuery.trim($('#fromDate').val());
					var toDate = jQuery.trim($('#toDate').val());
					/*
					 * alert(fromDate); alert(toDate);
					 */
					$("#metricsReportSummary")
							.jqGrid(
									{
										// url : "/causeListReportData?",
										url : "/metricsReportData?"
												+ "fromDate=" + fromDate
												+ "&toDate=" + toDate,
										width : 1150,
										height : 250,
										colNames : [
												'LitigationOID',
												'Litigation Id',
												'Zone',
												'Entity',
												'Unit/Location',
												'Format',
												'Matter By/Against',
												'Name of Plaintiff/Complainant/Petitioner/Appellant/Appeal By Company',
												'Name of Defendant/Respondant/Other/Appeal Against Company',
												'Under Act',
												'Under Section',
												'Other Under Act',
												'Parties',
												'Category',
												'Case Number',
												'File No',
												'Court/Forum',
												'Date of Receipt of Matter/Case',
												'City', 'State', 'Facts',
												'Last Hearing Dt', 'Stage',
												'Possible Claim(Range)',
												'Possible Claim',
												'Lawfirm/Individual', 'Remark',
												'Status' ],
										colModel : [
												{
													name : 'litigationOId',
													index : 'litigationOId',
													width : 100,
													align : 'center',
													hidden : true
												},
												{
													name : 'litigationId',
													index : 'litigationId',
													width : 100,
													align : 'center'
												},
												{
													name : 'zone',
													index : 'zone',
													width : 100,
													align : 'center'
												},
												{
													name : 'entity',
													index : 'entity',
													width : 100,
													align : 'center'
												},
												{
													name : 'unitLocation',
													index : 'unitLocation',
													width : 100,
													align : 'center'
												},
												{
													name : 'format',
													index : 'format',
													width : 100,
													align : 'center'
												},
												{
													name : 'matterByAgainst',
													index : 'matterByAgainst',
													width : 100,
													align : 'center'
												},
												{
													name : 'appealByCompany',
													index : 'appealByCompany',
													width : 100,
													align : 'center'
												},
												{
													name : 'appealAgainstCompany',
													index : 'appealAgainstCompany',
													width : 100,
													align : 'center'
												},
												{
													name : 'underAct',
													index : 'underAct',
													width : 100,
													align : 'center'
												},
												{
													name : 'underSection',
													index : 'underSection',
													width : 100,
													align : 'center'
												},
												{
													name : 'otherUnderAct',
													index : 'otherUnderAct',
													width : 100,
													align : 'center'
												},
												{
													name : 'parties',
													index : 'parties',
													width : 100,
													align : 'center'
												},
												{
													name : 'category',
													index : 'category',
													width : 100,
													align : 'center'
												},
												{
													name : 'caseNumber',
													index : 'caseNumber',
													width : 100,
													align : 'center'
												},
												{
													name : 'fileNo',
													index : 'fileNo',
													width : 100,
													align : 'center'
												},
												{
													name : 'courtForum',
													index : 'courtForum',
													width : 100,
													align : 'center'
												},
												{
													name : 'dateOfReceiptOfMatterCase',
													index : 'dateOfReceiptOfMatterCase',
													width : 100,
													align : 'center'
												},
												{
													name : 'city',
													index : 'city',
													width : 100,
													align : 'center'
												},
												{
													name : 'state',
													index : 'state',
													width : 100,
													align : 'center'
												},
												{
													name : 'facts',
													index : 'facts',
													width : 100,
													align : 'center'
												},
												{
													name : 'lastHearingDate',
													index : 'lastHearingDate',
													width : 100,
													align : 'center'
												},
												{
													name : 'stage',
													index : 'stage',
													width : 100,
													align : 'center'
												},
												{
													name : 'possibleClaimRange',
													index : 'possibleClaimRange',
													width : 100,
													align : 'center'
												},
												{
													name : 'possibleClaim',
													index : 'possibleClaim',
													width : 100,
													align : 'center'
												},
												{
													name : 'lawfirmIndividual',
													index : 'lawfirmIndividual',
													width : 100,
													align : 'center'
												}, {
													name : 'remark',
													index : 'remark',
													width : 100,
													align : 'center'
												}, {
													name : 'status',
													index : 'status',
													width : 100,
													align : 'center'
												} ],
										rowNum : 10,
										rowList : [ 5, 10, 20 ],
										pager : '#metricsReportSummaryPager',
										datatype : 'json',
										loadonce : true,
										viewrecords : true,
										ignoreCase : true,
										cmTemplate : {
											sortable : false
										},
									});
					jQuery("#metricsReportSummary").jqGrid('filterToolbar', {
						defaultSearch : "cn",
						stringResult : true,
						searchOnEnter : false
					});
				});

function getReportData() {
	var urlStr = "/metricsReportData?";
	var fromDate = jQuery.trim($('#fromDate').val());
	var toDate = jQuery.trim($('#toDate').val());
	var status = jQuery.trim($('#status').val());
	var entityName = jQuery.trim($('#entityName').val());
	var unitName = jQuery.trim($('#unitName').val());
	var matterBy = jQuery.trim($('#matterBy').val());
	var litigationBy = jQuery.trim($('#litigationBy').val());
	var risk = jQuery.trim($('#risk').val());
	var format = jQuery.trim($("#format").val());
	var zone =jQuery.trim($("#zoneName").val());

	alert(fromDate);

	if (fromDate == null || fromDate == '' || fromDate.length == 0) {
		alert("Please Select From date");
		return false;
	}

	if (toDate == null || toDate == '' || toDate.length == 0) {
		alert("Please Select End date");
		return false;
	}
	urlStr = urlStr + "fromDate=" + fromDate + "&toDate=" + toDate + "&status="
			+ status + "&entityName=" + entityName + "&unitName=" + unitName
			+ "&matterBy=" + matterBy + "&litigationBy=" + litigationBy
			+ "&risk=" + risk + "&format=" +format+ "&zone=" +zone;

	jQuery("#metricsReportSummary").jqGrid('setGridParam', {
		url : urlStr,
		ajaxGridOptions : {
			async : false
		},
		datatype : 'json',
		page : 1
	}).trigger("reloadGrid");
}
