$(document).ready(function() {

	$(function() {
		$("#nextHearingDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		}).datepicker("setDate", new Date());

		$("#dateOfReceiptOfMatter").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		}).datepicker("setDate", new Date());

	});
	
	$("#matterByAgainstId").multiselect();
	
	$("#addLitigationBtn").click(function(){
		if($("#entityNameId").val()	=== "Select"){
			alert("Please Select EntityName");
			return false;
		}
		if($("#zoneNameId").val() === "Select"){
			alert("Please Select ZoneName");
			return false;
		}
		if($("#unitNameId").val() === "Select"){
			alert("Please Select UnitName");
			return false;
		}
		if($("#format").val() === "Select"){
			alert("Please Select Format");
			return false;
		}
		if($("#categoryNameId").val() === "Select"){
			alert("please Select Category");
			return false;
		}
		
		if($("#storeOfficePremises").val() === "Select"){
			alert("Please Select StoreOfficePremises");
			return false;
		}
		if($("#matterByAgainst").val() === "Select"){
			alert("Please Select Matter By Against");
			return false;
		}
		if($("#customerType").val() === "Select"){
			alert("Please Select CustomerType");
			return false;
		}
		if($("#counterPartyName").val() === "Select"){
			alert("Please Select CounterParty");
			return false;
		}
		
		if($("#caseNumber").val() == ""){
			alert("Please Enter CaseNumber");
			return false;
		}
		if($("#risk").val() ==="Select"){
			alert("Please Select Risk");
			return false;
		}
		
		if($("#claim").val() === "Select"){
			alert("Please Select Claim");
			return false;
		}
		
		if($("#underActName").val === "Select"){
			alert("Please Select UnderActs");
			return false;
		}
		
		if($("#courtTypeName").val() === "Select"){
			alert("Please Select CourtType");
			return false;
		}
		
		if($("#state").val() === "Select"){
			alert("Please Select StateName");
			return false;
		}
		if($("#cityName").val() === "Select"){
			alert("Please Select CityName");
			return false;
		}
		if($("#status").val() === "Select"){
			alert("Please Select Status");
			return false;
		}
		if($("#courtForum").val() === "Select"){
			alert("Please Select CourtForum");
			return false;
		}
		if($("#lawfirm").val() === "Select"){
			alert("Please Select Lawfirm/Individual");
			return false;
		}
		if($("#factsOfLitigation").val().length == 0){
			alert("Please Enter Facts Of Litigation");
			return false;
		}
	});
	
	
	//Button value Add
		$("#addLocationBtn").click(function(){
//			alert("Inside addUnitLocation Btn");
			if($("#entityNameId").val()	!= "Select" && $("#zoneNameId").val() != "Select" && $("#unitNameId").val() !="Select"){
				$("#entityRegionUnits").append("<tr><td>"+ $("#entityNameId").val() + "</td><td>" +$("#zoneNameId").val() + "</td><td>" + $("#unitNameId").val() +"</td><td> <a href=#> Remove </a></td></tr>")
			}else{
				alert("Please Select..");
			}
			
		});
	
		$("#resetLocationBtn").click(function(){
			$("#entityNameId").val("");
			$("#zoneNameId").val("");
			$("#unitNameId").val("");
		});
	
	// To remove a row in table
		$('table').on('click','tr a',function(e){
	         e.preventDefault();
	        $(this).parents('tr').remove();
	      });
	
	// to change label value after selection in dropdown list
		$("#customerType").change(function(){
//			alert("inside CustomerType Change Function");
			var map = new Map();
			map.set("Plaintiff", "Defendant");
			map.set("Defendant", "Plaintiff")
			map.set("Complainant", "Accused");
			map.set("Accused", "Complainant");
			map.set("Applicant", "Opponent");
			map.set("Opponent", "Applicant");
			map.set("Petitioner", "Respondant");
			map.set("Respondant", "Petitioner");
			map.set("Appelant", "Respondant");
			map.set("Appeal By Company", "Petitioner");
			map.set("Appeal Against Company", "Petitioner");
			for(let [k,v] of map){
				if($("#customerType").val() === k){
//					alert("Value: " +v);
					$("#counterPartyId").text(v);
					break;
				}
			}
			
		});
	
	
	$("#addLawfirmBtn").click(function(){
//		alert("Method called...");
		var requestData = {};

//		alert($("#modallawfirm").val());
		requestData["lawfirmVal"] = $("#modallawfirm").val();
		$.ajax({
			type : "post",
			url : "/addLawfirm",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
//				alert(response);
				addLawfirmDropdown(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
	
	
	
	$("#addCourtTypeBtn").click(function(){
		var requestData = {};

//		alert($("#modalcourtTypeName").val());
		requestData["courtTypeVal"] = $("#modalcourtTypeName").val();
		$.ajax({
			type : "post",
			url : "/addCourtType",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
//				alert(response);
				addCourtTypeDropdown(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
	
	$("#addCategoryBtn").click(function(){
		var requestData = {};
//		alert($("#modalcourtTypeName").val());
		requestData["categoryVal"] = $("#modalCategory").val();
		$.ajax({
			type : "post",
			url : "/addCategory",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
//				alert(response);
				addCategoryDropdown(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
	
	$("#addUnderActBtn").click(function(){
		var requestData = {};
//		alert($("#modalUnderActs").val());
		requestData["underActVal"] = $("#modalUnderActs").val();
		$.ajax({
			type : "post",
			url : "/addUnderAct",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
//				alert(response);
				addUnderActDropdown(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
	
	$("#addCounterPartyBtn").click(function(){
		var requestData = {};
//		alert($("#modalcounterPartyName").val());
		requestData["counterPartyNameVal"] = $("#modalcounterPartyName").val();
		$.ajax({
			type : "post",
			url : "/addCounterPartyDtls",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
//				alert(response);
				addCounterPartyDropdown(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
	
	$("#addMatterByBtn").click(function(){
		var requestData = {};
//		alert($("#modalmatterBy").val());
		requestData["matterByVal"] = $("#modalmatterBy").val();
		$.ajax({
			type : "post",
			url : "/addMatterBy",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
//				alert(response);
				addMatterByAgainst(response);
				
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
	
	$("#addUnitLocationBtn").click(function(){
		var requestData = {};
		/*alert($("#modalentityName").val());
		alert($("#modalzoneName").val());
		alert($("#modalunitName").val());*/
		requestData["entityVal"] = $("#modalentityName").val();
		requestData["zoneVal"] = $("#modalzoneName").val();
		requestData["unitVal"] = $("#modalunitName").val();
		
		$.ajax({
			type : "post",
			url : "/addUnitLocationDtls",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
//				alert(response);
				addUnitLocationDropdown(response);
				/*$("#modalentityName").val("");
				$("#modalzoneName").val("");
				$("#modalunitName").val("");*/
				modal.style.display = "none";
			},
			error: function(){
				alert("Error while request");
			}
		})
	});

	// ADD UNIT/LOCATION

	// Get the modal
	var modal = document.getElementById("addUnitLocationModal");

	// Get the button that opens the modal
	var btn = document.getElementById("addUnitLocation");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks the button, open the modal
	btn.onclick = function() {
		modal.style.display = "block";
	}

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}

	$("#modalAddUnitLocationResetBtn").click(function() {
		$("#modalentityName").val("");
		$("#modalzoneName").val("");
		$("#modalunitName").val("");
	});

	$("#modalAddUnitLocationcancelBtn").click(function() {
		modal.style.display = "none";
		$("#modalentityName").val("");
		$("#modalzoneName").val("");
		$("#modalunitName").val("");
	});
	
	
	//End Add UnitLocation

	// ADD COUNTERPARTY

	// When the user clicks the button, open the modal
	var counterPartymodal = document.getElementById("addCounterPartyModal");

	$("#addCounterParty").click(function() {
		counterPartymodal.style.display = "block";
	});

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		counterPartymodal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == counterPartymodal) {
			counterPartymodal.style.display = "none";
		}
	}
	
	$("#modalAddCounterPartyResetBtn").click(function(){
		$("#modalcounterPartyName").val("");
	});
	
	$("#modalAddCounterPartycancelBtn").click(function() {
		counterPartymodal.style.display = "none";
	});
	
	$("#addCounterPartyBtn").click(function(){
		counterPartymodal.style.display = "none";
		$("#modalcounterPartyName").val("");
	});
	//End Add Counter Party
	
	
	// Strat ADD UNDERACTS
	var underActmodal = document.getElementById("addUnderActsModal");
	var span = document.getElementsByClassName("close")[0];
	$("#addUnderActs").click(function() {
		underActmodal.style.display = "block";
	});
	
	span.onclick = function() {
		underActmodal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == underActmodal) {
			underActmodal.style.display = "none";
		}
	}
	
	$("#modalAddUnderActsResetBtn").click(function(){
		$("#modalUnderActs").val("");
	});
	
	$("#modalAddUnderActscancelBtn").click(function() {
		underActmodal.style.display = "none";
	});
	
	$("#addUnderActBtn").click(function(){
		underActmodal.style.display = "none";
		$("#modalUnderActs").val("");
	});
	
	//End Add UnderActs
	
	// Start ADD Category
	var categoryModal = document.getElementById("addCategoryModal");
	var span = document.getElementsByClassName("close")[0];
	$("#addCategory").click(function() {
		categoryModal.style.display = "block";
	});
	
	span.onclick = function() {
		categoryModal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == categoryModal) {
			categoryModal.style.display = "none";
		}
	}
	
	$("#modalAddCategoryResetBtn").click(function(){
		$("#modalCategory").val("");
	});
	
	$("#modalAddCategoryCancelBtn").click(function() {
		categoryModal.style.display = "none";
	});
	
	$("#addCategoryBtn").click(function(){
		categoryModal.style.display = "none";
		$("#modalCategory").val("");
	});
	
	//End Add Category
	
	// Start ADD COURT TYPE
	var courtTypeModal = document.getElementById("addCourtTypeModal");
	var span = document.getElementsByClassName("close")[0];
	$("#addCourtType").click(function() {
		courtTypeModal.style.display = "block";
	});
	
	span.onclick = function() {
		courtTypeModal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == courtTypeModal) {
			courtTypeModal.style.display = "none";
		}
	}
	
	$("#modalAddCourtTypeResetBtn").click(function(){
		$("#modalcourtTypeName").val("");
	});
	
	$("#modalAddCourtTypeCancelBtn").click(function() {
		courtTypeModal.style.display = "none";
	});
	
	$("#addCourtTypeBtn").click(function(){
		courtTypeModal.style.display = "none";
		$("#modalcourtTypeName").val("");
	});
	
	//End Court Type
	
	// Start ADD LAWFIRM
	var lawfirmModal = document.getElementById("addLawfirmModal");
	var span = document.getElementsByClassName("close")[0];
	$("#addLawfirm").click(function() {
		lawfirmModal.style.display = "block";
	});
	
	span.onclick = function() {
		lawfirmModal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == lawfirmModal) {
			lawfirmModal.style.display = "none";
		}
	}
	
	$("#modalAddLawfirmResetBtn").click(function(){
		$("#modalcourtTypeName").val("");
	});
	
	$("#modalAddLawfirmCancelBtn").click(function() {
		lawfirmModal.style.display = "none";
	});
	
	$("#addLawfirmBtn").click(function(){
		lawfirmModal.style.display = "none";
		$("#modallawfirm").val("");
	});
	
	//End Add Lawfirm
	
	// start ADD MATTER BY AGAINST
	var matterByAgainstModal = document.getElementById("addMatterByAgainstModal");
	var span = document.getElementsByClassName("close")[0];
	$("#addMatterByAgainst").click(function() {
		matterByAgainstModal.style.display = "block";
	});
	
	span.onclick = function() {
		matterByAgainstModal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == matterByAgainstModal) {
			matterByAgainstModal.style.display = "none";
		}
	}
	
	$("#modalAddMatterByAgainstResetBtn").click(function(){
		$("#modalmatterBy").val("");
	});
	
	$("#modalAddMatterByAgainstCancelBtn").click(function() {
		matterByAgainstModal.style.display = "none";
	});
	
	$("#addMatterByBtn").click(function(){
		matterByAgainstModal.style.display = "none";
		$("#modalmatterBy").val("");
	});
	
	//End Add Matter By Against
	

	// Start ADD CityName
	var cityNameModal = document.getElementById("addCityNameModal");
	var span = document.getElementsByClassName("close")[0];
	$("#addCityName").click(function() {
		if($("#state").val() === "Select"){
			alert("Please Select State..");
			return false;
		}
		cityNameModal.style.display = "block";
	});
	
	span.onclick = function() {
		cityNameModal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == categoryModal) {
			cityNameModal.style.display = "none";
		}
	}
	
	$("#modalAddCityResetBtn").click(function(){
		$("#modalCity").val("");
	});
	
	$("#modalAddCityCancelBtn").click(function() {
		cityNameModal.style.display = "none";
	});
	
	$("#addCityBtn").click(function(){
		cityNameModal.style.display = "none";
		$("#modalCity").val("");
	});
	
	//End Add CityName
	
	// Start ADD CourtForum
	var courtForumModal = document.getElementById("addCourtForumModal");
	var span = document.getElementsByClassName("close")[0];
	$("#addCourtForum").click(function() {
		if($("#cityName").val() === "Select"){
			alert("Please Select CityName");
			return false;
		}
		courtForumModal.style.display = "block";
	});
	
	span.onclick = function() {
		courtForumModal.style.display = "none";
	}
	window.onclick = function(event) {
		if (event.target == lawfirmModal) {
			courtForumModal.style.display = "none";
		}
	}
	
	$("#modalAddCourtForumResetBtn").click(function(){
		$("#modalcourtTypeName").val("");
	});
	
	$("#modalAddCourtForumCancelBtn").click(function() {
		courtForumModal.style.display = "none";
	});
	
	$("#addCourtForumBtn").click(function(){
		courtForumModal.style.display = "none";
		$("#modalCourtForum").val("");
	});
	
	//End Add Lawfirm
	
/*	$("#addCityBtn").click(function(){
		var requestData = {};

		alert($("#modalCity").val());
		alert($("#state").val())
		requestData["cityNameVal"] = $("#modalCity").val();
		requestData["stateVal"] = $("#state").val();
		$.ajax({
			type : "post",
			url : "/addCityDtls",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
				alert(response);
				addCityNameDropDown(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
	});*/
	
	$("#resetLitigationBtn").click(function(){
		$("#format").val("");
		$("#address").val("");
		$("#storeOfficePremises").val("");
		$("#coFormat").val("");
		$("#matterByAgainst").val("");
		$("#customerType").val("");
		$("#coZone").val("");
		$("#counterPartyNameId").val("");
		$("#coCounterParties").val("");
		$("#subject").val("");
		$("#categoryNameId").val("");
		$("#caseNumber").val("");
		$("#risk").val("");
		$("#claim").val("");
		$("#possibleClaim").val("");
		$("#pantaloonFileNo").val("");
		$("#underSection").val("");
		$("#otherUnderacts").val("");
		$("#underActName").val("");
		$("#judgeName").val("");
		$("#courtTypeNameId").val("");
		$("#state").val("");
		$("#cityName").val("");
		$("#status").val("");
		$("#firNo").val("");
		$("#courtForum").val("");
		$("#dateOfReceiptOfMatter").val("");
		$("#nextHearingDate").val("");
		$("#factsOfLitigation").val("");
		$("#stage").val("");
		$("#counselAssesment").val("");
		$("#remark").val("");
		$("#seniorCounsel").val("");
		$("#lawfirm").val("");
		
	});

	$("#cancelLitigationBtn").click(function(){
		var urlStr = "/showLitigationSummary";
		$("#addNewLitigationForm").attr("action", urlStr);
		$("#addNewLitigationForm").attr("method", "GET");
		$("#addNewLitigationForm").submit();
	});
	
	
	$("#entityRegionUnits").jqGrid({
		url : "",
		width : 1100,
		height : 50,
		colNames : [ 'Id', 'Entity', 'Region', 'Unit', 'Remove' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 100,
			align : 'center',
			hidden : true
		}, {
			name : 'entityName',
			index : 'entityName',
			width : 100,
			align : 'center'
		}, {
			name : 'regionName',
			index : 'regionName',
			width : 100,
			align : 'center'
		}, {
			name : 'regionName',
			index : 'regionName',
			width : 100,
			align : 'center'
		}, {
			name : 'Remove',
			index : 'Remove',
			formatter : 'showlink',
			formatoptions : {
				baseLinkUrl : '/updateEntity'
			},
			width : 100,
			align : 'center'
		} ],
		rowNum : 2,
		rowList : [ 5, 10, 20 ],
		pager : '#entityRegionUnitsPager',
		datatype : 'json',
		loadonce : true,
		viewrecords : true,
		ignoreCase : true,
		cmTemplate : {
			sortable : false
		},
	});
	
	$("#zoneNameId").change(function(){
//		alert("Method called...");
		var requestData = {};
		requestData["zoneNameVal"] = $('#zoneNameId :selected').text();
		requestData["entityNameVal"] = $('#entityNameId :selected').text();
		var val = "Select";
		
		$.ajax({
			type : "get",
			url : "/getUnitLocationDataByZone",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
				response = $.parseJSON(response);
//				alert(response);
				var options;
				options = '<option value="' + val + '">' + val + '</option>';
				for(var i in response){
//					alert("UnitValue"+response[i].unitName);
					$('#unitNameId')
				    .find('option')
				    .remove();
					
					options += '<option value="' + response[i].unitName + '">' + response[i].unitName + '</option>';
					
//					$('#unitName').append(`<option value="${response[i].unitName}"> ${response[i].unitName} </option>`);
				    
				}
				$("#unitNameId").html(options);

			},
			error: function(){
				alert("Error while request");
			}
		})
	});


	
});


function addLawfirmDropdown(data){
//	alert("In called function");
	$('#lawfirm').append(`<option value="${data}"> ${data} </option>`);
	$('#seniorCounsel').append(`<option value="${data}"> ${data} </option>`); 
}

function addCourtTypeDropdown(data){
//	alert("In called function");
	$('#courtTypeName').append(`<option value="${data}"> ${data} </option>`); 
}

function addCategoryDropdown(data){
//	alert("In called function");
	$('#categoryName').append(`<option value="${data}"> ${data} </option>`); 
}

function addUnderActDropdown(data){
//	alert("In called function");
	$('#underActName').append(`<option value="${data}"> ${data} </option>`); 
}

function addCounterPartyDropdown(data){
//	alert("In called function");
	$('#counterPartyName').append(`<option value="${data}"> ${data} </option>`);
}

function addUnitLocationDropdown(data){
//	alert("In called function");
	$('#unitNameId').append(`<option value="${data}"> ${data} </option>`);
}

function addMatterByAgainst(data){
//	alert("In called function");
	$('#matterByAgainstId').append(`<option value="${data}"> ${data} </option>`);
}
function addCityNameDropDown(data){
//	alert("In called function");
	$('#cityName').append(`<option value="${data}"> ${data} </option>`);
}

function addCourtForumDropDown(data){
//	alert("In called function");
	$('#courtForum').append(`<option value="${data}"> ${data} </option>`);
}

function addCityDtls(data){
	var requestData = {};

//	alert($("#modalCity").val());
//	alert($("#state").val())
//	alert(data);
	requestData["cityNameVal"] = data;
	requestData["stateVal"] = $("#state").val();
	$.ajax({
		type : "post",
		url : "/addCityDtls",
		cache : false,
		data : requestData,
		dataType : 'text',
		success : function(response) {
//			alert(response);
			addCityNameDropDown(response);
		},
		error: function(){
			alert("Error while request");
		}
	})
}

function addCourtForumDtls(data){
	var requestData = {};

//	alert($("#cityName").val());
//	alert($("#state").val())
//	alert(data);
	requestData["courtForumVal"] = data;
	requestData["cityNameVal"] = $("#cityName").val();
	$.ajax({
		type : "post",
		url : "/addCourtForumDtls",
		cache : false,
		data : requestData,
		dataType : 'text',
		success : function(response) {
//			alert(response);
			addCourtForumDropDown(response);
		},
		error: function(){
			alert("Error while request");
		}
	})
}



