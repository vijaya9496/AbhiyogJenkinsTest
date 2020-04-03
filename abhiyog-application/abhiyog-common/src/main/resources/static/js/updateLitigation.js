$(document).ready(function(){
	$("#matterByAgainstId").multiselect();
	$(function() {
		$("#nextHearingDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		});

		$("#dateOfReceiptOfMatter").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		});
		$("#caseRelateFromDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		});

		$("#caseRelateToDate").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd'
		});

	});
	
	$("#cancelLitigationBtn").click(function(){
		var urlStr = "/showLitigationSummary";
		$("#updateLitigationForm").attr("action", urlStr);
		$("#updateLitigationForm").attr("method", "GET");
		$("#updateLitigationForm").submit();
	});
	
	$("#updateLitigationBtn").click(function(){
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
		
		if($("#storeOfficePremises").val() === "Select"){
			alert("Please Select StoreOfficePremises");
			return false;
		}
		/*if($("#matterByAgainst").val() === " "){
			alert("Please Select Matter By Against);
		}*/
		if($("#customerType").val() === "Select"){
			alert("Please Select CustomerType");
			return false;
		}
		if($("#counterParty").val() === "Select"){
			alert("Please Select CounterParty");
			return false;
		}
		if($("#categoryName").val() === "Select"){
			alert("please Select Category");
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
	});
	
	if($("#customerType").val() === 'Defendant'){
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
//				alert("Value: " +v);
				$("#counterPartyId").text(v);
				break;
			}
		}
	}
	
	// When the user clicks the button, open the modal
	var counterPartymodal = document.getElementById("addCounterPartyModal");
	var span = document.getElementsByClassName("close")[0];

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
	
	$("#addCounterPartyBtn").click(function(){
		var requestData = {};
		alert($("#modalcounterPartyNameId").val());
		requestData["counterPartyNameVal"] = $("#modalcounterPartyNameId").val();
		$.ajax({
			type : "post",
			url : "/addCounterPartyDtls",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
				alert(response);
				addCounterPartyDropdown(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
	
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
	
	$("#addUnderActBtn").click(function(){
		var requestData = {};
		alert($("#modalUnderActs").val());
		requestData["underActVal"] = $("#modalUnderActId").val();
		$.ajax({
			type : "post",
			url : "/addUnderAct",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
				alert(response);
				addUnderActDropdown(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
	
	// Start ADD Category
	var categoryModal = document.getElementById("addCategoryModal");
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
		$("#modalCategoryId").val("");
	});
	
	$("#modalAddCategoryCancelBtn").click(function() {
		categoryModal.style.display = "none";
	});
	
	$("#addCategoryBtn").click(function(){
		categoryModal.style.display = "none";
		$("#modalCategoryId").val("");
	});
	
	//End Add Category
	
	/*$("#addCategoryBtn").click(function(){
		var requestData = {};
		alert($("#modal_Category").val());
		
//		requestData["categoryVal"] = $("#categoryNameId").val();
		alert(document.getElementById("modal_Category"));
		requestData["categoryVal"] = document.getElementById("modal_Category");
		$.ajax({
			type : "post",
			url : "/addCategory",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
				alert(response);
				addCategoryDropdown(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
	});*/
	
	// Start ADD COURT TYPE
	var courtTypeModal = document.getElementById("addCourtTypeModal");
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
		$("#modalCourtTypeId").val("");
	});
	
	$("#modalAddCourtTypeCancelBtn").click(function() {
		courtTypeModal.style.display = "none";
	});
	
	$("#addCourtTypeBtn").click(function(){
		courtTypeModal.style.display = "none";
		$("#modalCourtTypeId").val("");
	});
	
	//End Court Type
	
	/*$("#addCourtTypeBtn").click(function(){
		var requestData = {};

		alert($("#modalCourtTypeId").val());
		alert(document.getElementById("modalCourtTypeId"));
		requestData["courtTypeVal"] = $("#modalCourtTypeId").val();
		$.ajax({
			type : "post",
			url : "/addCourtType",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
				alert(response);
				addCourtTypeDropdown(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
	});*/
	
	// Start ADD LAWFIRM
	var lawfirmModal = document.getElementById("addLawfirmModal");
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
		$("#modalLawfirmId").val("");
	});
	
	$("#modalAddLawfirmCancelBtn").click(function() {
		lawfirmModal.style.display = "none";
	});
	
	$("#addLawfirmBtn").click(function(){
		lawfirmModal.style.display = "none";
		$("#modalLawfirmId").val("");
	});
	
	//End Add Lawfirm
	
	/*$("#addLawfirmBtn").click(function(){
		alert("Method called...");
		var requestData = {};

		alert($('#modalLawfirmId').val());
		alert(document.getElementById("modalLawfirmId").value);
		console.log(document.getElementById("modalLawfirmId").value);
		requestData["lawfirmVal"] = $('#modalLawfirmId').val();
		$.ajax({
			type : "post",
			url : "/addLawfirm",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
				alert(response);
				addLawfirmDropdown(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
	});*/
	
	// start ADD MATTER BY AGAINST
	var matterByAgainstModal = document.getElementById("addMatterByAgainstModal");
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
		$("#modalcourtTypeName").val("");
	});
	
	$("#modalAddMatterByAgainstCancelBtn").click(function() {
		matterByAgainstModal.style.display = "none";
	});
	
	$("#addMatterByBtn").click(function(){
		matterByAgainstModal.style.display = "none";
		$("#modalmatterBy").val("");
	});
	//End Add Matter By Against
	
	$("#addMatterByBtn").click(function(){
		var requestData = {};
		alert($("#modalmatterById").val());
		requestData["matterByVal"] = $("#modalmatterById").val();
		$.ajax({
			type : "post",
			url : "/addMatterBy",
			cache : false,
			data : requestData,
			dataType : 'text',
			success : function(response) {
				alert(response);
				addMatterByAgainst(response);
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
	});
	
	//End Add UnitLocation
	
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
	
	$("#addUnitLocationBtn").click(function(){
		var requestData = {};
		alert($("#modalentityName").val());
		alert($("#modalzoneName").val());
		alert($("#modalunitName").val());
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
				alert(response);
				addUnitLocationDropdown(response);
			},
			error: function(){
				alert("Error while request");
			}
		})
	});
});
function addCounterPartyDropdown(data){
	alert("In called function");
	$('#counterParty').append(`<option value="${data}"> ${data} </option>`);
}
function addUnderActDropdown(data){
	alert("In called function");
	$('#underActName').append(`<option value="${data}"> ${data} </option>`); 
}
function addCategoryDropdown(data){
	alert("In called function");
	$('#categoryName').append(`<option value="${data}"> ${data} </option>`); 
}
function addCourtTypeDropdown(data){
	alert("In called function");
	$('#courtTypeName').append(`<option value="${data}"> ${data} </option>`); 
}
function addLawfirmDropdown(data){
	alert("In called function");
	$('#lawfirm').append(`<option value="${data}"> ${data} </option>`); 
}
function addMatterByAgainst(data){
	alert("In called function");
	$('#matterByAgainstId').append(`<option value="${data}"> ${data} </option>`);
}
function addUnitLocationDropdown(data){
	alert("In called function");
	$('#unitNameId').append(`<option value="${data}"> ${data} </option>`);
}

function addLawfirmData(data){
	alert("Method called...");
	var requestData = {};
	alert(data);
	/*alert($('#modalLawfirmId').val());
	alert(document.getElementById("modalLawfirmId").value);
	console.log(document.getElementById("modalLawfirmId").value);
	requestData["lawfirmVal"] = $('#modalLawfirmId').val();*/
	requestData["lawfirmVal"] =data;
	$.ajax({
		type : "post",
		url : "/addLawfirm",
		cache : false,
		data : requestData,
		dataType : 'text',
		success : function(response) {
			alert(response);
			addLawfirmDropdown(response);
		},
		error: function(){
			alert("Error while request");
		}
	})
}

function addCourtTypeData(data){
	var requestData = {};
	alert(data);
	/*alert($("#modalCourtTypeId").val());
	alert(document.getElementById("modalCourtTypeId"));
	requestData["courtTypeVal"] = $("#modalCourtTypeId").val();*/
	requestData["courtTypeVal"] = data;
	$.ajax({
		type : "post",
		url : "/addCourtType",
		cache : false,
		data : requestData,
		dataType : 'text',
		success : function(response) {
			alert(response);
			addCourtTypeDropdown(response);
		},
		error: function(){
			alert("Error while request");
		}
	})
}

function addCategoryData(data){
	var requestData = {};
	alert(data);
//	alert($("#modal_Category").val());
	
//	requestData["categoryVal"] = $("#categoryNameId").val();
//	alert(document.getElementById("modal_Category"));
	requestData["categoryVal"] = data;
	$.ajax({
		type : "post",
		url : "/addCategory",
		cache : false,
		data : requestData,
		dataType : 'text',
		success : function(response) {
			alert(response);
			addCategoryDropdown(response);
		},
		error: function(){
			alert("Error while request");
		}
	})
}

function addCityNameDropDown(data){
	alert("In called function");
	$('#cityName').append(`<option value="${data}"> ${data} </option>`);
}

function addCourtForumDropDown(data){
	alert("In called function");
	$('#courtForum').append(`<option value="${data}"> ${data} </option>`);
}

function addCityDtls(data){
	var requestData = {};

//	alert($("#modalCity").val());
	alert($("#state").val())
	alert(data);
	requestData["cityNameVal"] = data;
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
}

function addCourtForumDtls(data){
	var requestData = {};

	alert($("#cityName").val());
//	alert($("#state").val())
	alert(data);
	requestData["courtForumVal"] = data;
	requestData["cityNameVal"] = $("#cityName").val();
	$.ajax({
		type : "post",
		url : "/addCourtForumDtls",
		cache : false,
		data : requestData,
		dataType : 'text',
		success : function(response) {
			alert(response);
			addCourtForumDropDown(response);
		},
		error: function(){
			alert("Error while request");
		}
	})
}