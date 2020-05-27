$(document)
		.ready(
				function() {
					/*
					 * $("#entity").show(); $("#format").show();
					 * $("$zone").show(); $("#unitLocation").show();
					 * $("#noticeCategory").show(); $("#receivedFrom").show();
					 * $("#issueDateDiv").show(); $("#receivedDateDiv").show();
					 * $("#noticeReplyDeadLineDiv").show();
					 * $("#subject").show(); $("#referenceNo").show();
					 * $("#comments").show(); $("#actionTaken").show();
					 * $("#uploadFile").show();
					 */

					$(function() {
						$("#issueDate").datepicker({
							changeMonth : true,
							changeYear : true,
							dateFormat : 'dd-mm-yy'
						});

						$("#receivedDate").datepicker({
							changeMonth : true,
							changeYear : true,
							dateFormat : 'dd-mm-yy'
						});

						$("#noticeReplyDeadline").datepicker({
							changeMonth : true,
							changeYear : true,
							dateFormat : 'dd-mm-yy'
						});

					});

					$("#noticeClassificationDiv").hide();
					$("#advocateNameDiv").hide();
					$("#advocateAddressDiv").hide();
					$("#allegationDiv").hide();
					$("#claimsDiv").hide();
					$("#dealerNameDiv").hide();
					$("#dealerAddressDiv").hide();
					$("#otherPartiesDiv").hide();
					$("#partyNoDiv").hide();
					$("#complaintNameDiv").hide();
					$("#complaintAddressDiv").hide();
					$("#vehicleModelNumberDiv").hide();
					$("#natureOfIpInfringementDiv").hide();
					$("#noticeSentAddressDiv").hide();
					$("#noticeSentToDiv").hide();
					$("#applicableSectionDiv").hide();

					$("#noticeCategoryName")
							.change(
									function() {
										if ($('#noticeCategoryName :selected')
												.text() === 'Legal Notice') {
											$("#noticeClassificationDiv")
													.show();
											$("#advocateNameDiv").show();
											$("#advocateAddressDiv").show();
											$("#allegationDiv").show();
											$("#claimsDiv").show();
										} else if ($(
												'#noticeCategoryName :selected')
												.text() === 'Show Cause Notice'
												|| $(
														'#noticeCategoryName :selected')
														.text() === 'Select') {
											$("#noticeClassificationDiv")
													.hide();
											$("#advocateNameDiv").hide();
											$("#advocateAddressDiv").hide();
											$("#allegationDiv").hide();
											$("#claimsDiv").hide();
											$("#dealerNameDiv").hide();
											$("#dealerAddressDiv").hide();
											$("#otherPartiesDiv").hide();
											$("#partyNoDiv").hide();
											$("#vehicleModelNumberDiv").hide();
											$("#receivedFromDiv").show();
											$("#complaintNameDiv").hide();
											$("#complaintAddressDiv").hide();
											$("#natureOfIpInfringementDiv")
													.hide();
											$("#noticeSentAddressDiv").hide();
											$("#noticeSentToDiv").hide();
											$("#applicableSectionDiv").hide();
											if ($("#receivedFrom").val() === '') {
												alert("Please Enter Received From");
												return false;
											}
										}
									});

					$("#noticeClassification")
							.change(
									function() {
										if ($('#noticeClassification :selected')
												.text() === 'Select'
												&& $(
														'#noticeCategoryName :selected')
														.text() === 'Legal Notice') {
											$("#noticeClassificationDiv")
													.show();
											$("#advocateNameDiv").show();
											$("#advocateAddressDiv").show();
											$("#allegationDiv").show();
											$("#claimsDiv").show();
											$("#dealerNameDiv").hide();
											$("#dealerAddressDiv").hide();
											$("#otherPartiesDiv").hide();
											$("#partyNoDiv").hide();
											$("#vehicleModelNumberDiv").hide();
											$("#complaintNameDiv").hide();
											$("#complaintAddressDiv").hide();
											$("#natureOfIpInfringementDiv")
													.hide();
											$("#noticeSentAddressDiv").hide();
											$("#noticeSentToDiv").hide();
											$("#applicableSectionDiv").hide();
										} else if ($(
												'#noticeClassification :selected')
												.text() === 'Received'
												&& $(
														'#noticeCategoryName :selected')
														.text() === 'Legal Notice') {
											$("#receivedFromDiv").hide();
											$("#advocateNameDiv").show();
											$("#advocateAddressDiv").show();
											$("#complaintNameDiv").show();
											$("#complaintAddressDiv").show();
											$("#allegationDiv").show();
											$("#claimsDiv").show();
											$("#dealerNameDiv").show();
											$("#dealerAddressDiv").show();
											$("#otherPartiesDiv").show();
											$("#partyNoDiv").show();
											$("#vehicleModelNumberDiv").show();
											$("#natureOfIpInfringementDiv")
													.hide();
											$("#applicableSectionDiv").hide();
										} else if ($(
												'#noticeClassification :selected')
												.text() === 'Sent'
												&& $(
														'#noticeCategoryName :selected')
														.text() === 'Legal Notice') {
											$("#advocateNameDiv").show();
											$("#advocateAddressDiv").show();
											$("#noticeSentAddressDiv").show();
											$("#noticeSentToDiv").show();
											$("#allegationDiv").show();
											$("#claimsDiv").show();
											$("#natureOfIpInfringementDiv")
													.show();
											$("#applicableSectionDiv").show();
											$("#receivedFromDiv").hide();
											$("#complaintNameDiv").hide();
											$("#complaintAddressDiv").hide();
											$("#otherPartiesDiv").hide();
											$("#partyNoDiv").hide();
											$("#vehicleModelNumberDiv").hide();
											$("#dealerAddressDiv").hide();
											$("#otherPartiesDiv").hide();
											$("#dealerNameDiv").hide();
										}
									});

					$("#submitBtn")
							.click(
									function() {
//										alert($("#issueDate").val().length);
//										alert($("#issueDate").val());

										if ($('#entityName :selected').text() === 'Select') {
											alert("Please Select EntityName");
											return false;
										}

										if ($('#formatName :selected').text() === 'Select') {
											alert("Please Select Format Name");
											return false;
										}

										if ($('#zoneName :selected').text() === 'Select') {
											alert("Please Select Zone Name");
											return false;
										}
										if ($('#unitName :selected').text() === 'Select') {
											alert("Please Select UnitLocation");
											return false;
										}
										if ($('#noticeCategoryName :selected')
												.text() === 'Select') {
											alert("Please Select NoticeCategory");
											return false;
										}
										if ($("#issueDate").val().length == 0) {
											alert("Please Select IssueDate");
											return false;
										}
										if ($("#receivedDate").val().length == 0) {
											alert("Please Select ReceivedDate");
											return false;
										}
										if ($("#noticeReplyDeadline").val().length == 0) {
											alert("Please Select Notice Reply Deadline");
											return false;
										}
										if ($('#noticeCategoryName :selected')
												.text() === 'Legal Notice'
												&& $(
														'#noticeClassification :selected')
														.text() === 'Select') {
											alert("Please Select Notice Classification");
											return false;
										}

									});

					$("#cancelBtn").click(function() {
						var urlStr = "/showNoticeSummary";
						$("#addNewNoticeForm").attr("action", urlStr);
						$("#addNewNoticeForm").attr("method", "GET");
						$("#addNewNoticeForm").submit();
					});

					$("#resetBtn").click(function() {
						$("#entityName").val("Select");
						$("#formatName").val("Select");
						$("#zoneName").val("Select");
						$("#unitName").val("Select");
						$("#noticeCategoryName").val("Select");
						$("#noticeClassification").val("Select");
						$("#issueDate").val("dd-mm-yyyy");
						$("#receivedDate").val("dd-mm-yyyy");
						$("#noticeReplyDeadline").val("dd-mm-yyyy");
						$("#subject").val("");
						$("#referenceNo").val("");
						$("#comments").val("");
						$("#receivedFrom").val("");
						$("#advocateName").val("");
						$("#advocateAddress").val("");
						$("#allegation").val("");
						$("#claims").val("");
						$("#dealerName").val("");
						$("#dealerAddress").val("");
						$("#otherParties").val("");
						$("#partyNo").val("");
						$("#complaintName").val("");
						$("#complaintAddress").val("");
						$("#vehicleModelNumber").val("");
						$("#natureOfIpInfringement").val("");
						$("#noticeSentAddress").val("");
						$("#noticeSentTo").val("");
						$("#applicableSection").val("");
						$("#actionTaken").val("");
						$("#uploadFile").val("");
					});
					
					$("#zoneName").change(function(){
//						alert("Method called...");
						var requestData = {};

//						alert($('#zoneName :selected').text());
						requestData["zoneNameVal"] = $('#zoneName :selected').text();
						requestData["entityNameVal"] = $('#entityName :selected').text();
						var val = "Select";
						
						$.ajax({
							type : "get",
							url : "/getUnitLocationByZone",
							cache : false,
							data : requestData,
							dataType : 'text',
							success : function(response) {
								response = $.parseJSON(response);
//								alert(response);
								var options;
								options = '<option value="' + val + '">' + val + '</option>';
								for(var i in response){
//									alert("UnitValue"+response[i].unitName);
									$('#unitName')
								    .find('option')
								    .remove();
									
									options += '<option value="' + response[i].unitName + '">' + response[i].unitName + '</option>';
									
//									$('#unitName').append(`<option value="${response[i].unitName}"> ${response[i].unitName} </option>`);
								    
								}
								$("#unitName").html(options);

							},
							error: function(){
								alert("Error while request");
							}
						})
					});

				});

