jQuery(document).ready(
		function() {

//			$(function() {

				var d = new Date();
				d.setMonth(d.getMonth() + 1);
				$("#fromDate").datepicker({
					changeMonth : true,
					changeYear : true,
					dateFormat : 'yy-mm-dd'
				}).datepicker("setDate", new Date());

				$("#toDate").datepicker({
					changeMonth : true,
					changeYear : true,
					dateFormat : 'yy-mm-dd'
				}).datepicker("setDate", d);

//			});

			var fromDate = jQuery.trim($('#fromDate').val());
			var toDate = jQuery.trim($('#toDate').val());
		/*alert(fromDate);
		alert(toDate);*/
		$("#causeListReportSummary").jqGrid({
						// url : "/causeListReportData?",
						url : "/causeListReportData?" + "fromDate=" + fromDate
								+ "&toDate=" + toDate,
						width : 1150,
						height : 250,
						colNames : [ 'LitigationOID', 'Litigation Id',
								'Entity-Unit/Location-Dept', 'Parties',
								'Case Number', 'File No', 'Subject',
								 'Under Section','Court/Forum',
								 'Next Hearing Dt', 'Stage',
								'Status' ],
						colModel : [ {
							name : 'litigationOId',
							index : 'litigationOId',
							width : 100,
							align : 'center',
							hidden : true
						}, {
							name : 'litigationId',
							index : 'litigationId',
							width : 100,
							align : 'center'
						}, {
							name : 'entityUnitLocation',
							index : 'entityUnitLocation',
							width : 100,
							align : 'center'
						}, {
							name : 'parties',
							index : 'parties',
							width : 100,
							align : 'center'
						}, {
							name : 'caseNumber',
							index : 'caseNumber',
							width : 100,
							align : 'center'
						}, {
							name : 'fileNo',
							index : 'fileNo',
							width : 100,
							align : 'center'
						}, {
							name : 'subject',
							index : 'subject',
							width : 100,
							align : 'center'
						}, {
							name : 'underSection',
							index : 'underSection',
							width : 100,
							align : 'center'
						}, {
							name : 'courtForum',
							index : 'courtForum',
							width : 100,
							align : 'center'
						}, {
							name : 'nextHearingDt',
							index : 'nextHearingDt',
							width : 100,
							align : 'center'
						}, {
							name : 'stage',
							index : 'stage',
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
						pager : '#causeListReportSummaryPager',
						datatype : 'json',
						loadonce : true,
						viewrecords : true,
						ignoreCase : true,
						cmTemplate : {
							sortable : false
						},
					});
			jQuery("#causeListReportSummary").jqGrid('filterToolbar', {
				defaultSearch : "cn",
				stringResult : true,
				searchOnEnter : false
			});

		});
function getReportData() {
	var urlStr = "/causeListReportData?";
	var fromDate = jQuery.trim($('#fromDate').val());
	var toDate = jQuery.trim($('#toDate').val());
	var status = jQuery.trim($('#status').val());
	var entityName = jQuery.trim($('#entityName').val());
	var unitName = jQuery.trim($('#unitName').val());
	var matterBy = jQuery.trim($('#matterBy').val());
	var litigationBy = jQuery.trim($('#litigationBy').val());
	var risk = jQuery.trim($('#risk').val());

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
			+ "&risk=" + risk;

	jQuery("#causeListReportSummary").jqGrid('setGridParam', {
		url : urlStr,
		ajaxGridOptions : {
			async : false
		},
		datatype : 'json',
		page : 1
	}).trigger("reloadGrid");
}