package com.fg.ss.abhiyog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.fg.ss.abhiyog.common.model.City;
import com.fg.ss.abhiyog.common.model.CourtCity;
import com.fg.ss.abhiyog.common.model.CourtType;
import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.model.LtgnCaseType;
import com.fg.ss.abhiyog.common.model.LtgnRepresentativeMaster;
import com.fg.ss.abhiyog.common.model.UnderAct;
import com.fg.ss.abhiyog.common.service.ILitigationService;
import com.fg.ss.abhiyog.common.vo.AddLitigationVO;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.CityStateVO;
import com.fg.ss.abhiyog.common.vo.ConnectedLitigationVO;
import com.fg.ss.abhiyog.common.vo.CourtTypeVO;
import com.fg.ss.abhiyog.common.vo.CustomerTypeStatusVO;
import com.fg.ss.abhiyog.common.vo.LitigationSummaryVO;
import com.fg.ss.abhiyog.common.vo.LtgnCategoryVO;
import com.fg.ss.abhiyog.common.vo.LtgnMatterByVO;
import com.fg.ss.abhiyog.common.vo.RiskClaimVO;
import com.fg.ss.abhiyog.common.vo.UnderActVO;
import com.fg.ss.abhiyog.common.vo.UnitSummaryVO;

@RestController
@RequestMapping("/masters/litigation")
public class LitigationRestController {

	@Autowired
	private ILitigationService litigationService;

	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	/*@PostMapping("/addMatterBy")
	public ResponseEntity<BaseResponseVO> addMatterBy(@RequestBody LtgnMatterByVO ltgnMastersByVo) {
		LtgnRepresentativeMaster ltgnRepresentativeMaster = litigationService
				.checkExistenceRepresentativeName(ltgnMastersByVo.getMatterBy());
		if (ltgnRepresentativeMaster == null) {
			litigationService.saveLtgnRepresentativeMaster(ltgnMastersByVo);
			baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
			baseResponseVO.setResponseMessage("Matter Added Successfully");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("Unable to add MatterBy/Against");
		}
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}*/

	@GetMapping("/getAllMatterByAgainst")
	public ResponseEntity<BaseResponseVO> getAllMatterByData() {
		List<LtgnMatterByVO> allData = litigationService.findAll();
		if (allData == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(allData);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	/*@PostMapping("/addCategory")
	public BaseResponseVO addCategory(@RequestBody LtgnCategoryVO ltgnCategoryVO) {
		LtgnCaseType ltgnCaseType = litigationService.checkExistenceCaseType(ltgnCategoryVO.getCategoryName());
		if (ltgnCaseType == null) {
			litigationService.saveLtgnCaseType(ltgnCategoryVO);
			baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
			baseResponseVO.setResponseMessage("Category Name Added Sucessfully");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("Category Name Already Existed");
		}
		baseResponseVO.setData(null);
		return baseResponseVO;
	}*/

	@GetMapping("/getAllCategory")
	public ResponseEntity<BaseResponseVO> getAllCategoryData() {
		List<LtgnCategoryVO> allCategoryData = litigationService.findAllCategoryData();
		if (allCategoryData == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(allCategoryData);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	/*@PostMapping("/addUnderAct")
	public BaseResponseVO addUnderAct(@RequestBody UnderActVO underActVO) {
		UnderAct underAct = litigationService.findByUnderAct(underActVO.getUnderActName());
		if (underAct == null) {
			litigationService.saveUnderActData(underActVO);
			baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
			baseResponseVO.setResponseMessage("UnderAct Name Added Successfully.");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.FOUND.value());
			baseResponseVO.setResponseMessage("UnderActName Already Existed");
		}
		baseResponseVO.setData(null);
		return baseResponseVO;
	}*/

	@GetMapping("/getAllUnderAct")
	public ResponseEntity<BaseResponseVO> getAllUnderAct() {
		List<UnderActVO> allUnderActData = litigationService.findAllUnderActData();
		if (allUnderActData == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(allUnderActData);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	/*@PostMapping("/addCourtType")
	public BaseResponseVO addCourtType(@RequestBody CourtTypeVO courtTypeVO) {
		CourtType courtType = litigationService.checkExistenceCourtType(courtTypeVO.getCourtTypeName());
		if (courtType == null) {
			litigationService.saveCourtType(courtTypeVO);
			baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
			baseResponseVO.setResponseMessage("CourtTypeName Added Successfully");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.FOUND.value());
			baseResponseVO.setResponseMessage("CourtTypeName Already Existed");
		}
		baseResponseVO.setData(null);
		return baseResponseVO;
	}*/

	@GetMapping("/getAllCourtType")
	public ResponseEntity<BaseResponseVO> getAllCourtType() {
		List<CourtTypeVO> allCourtType = litigationService.findCourtType();
		if (allCourtType == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(allCourtType);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	@GetMapping("/getAllStates")
	public ResponseEntity<BaseResponseVO> getAllStates() {
		List<CityStateVO> allStates = litigationService.findAllStates();
		if (allStates == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(allStates);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	/*@PostMapping("/addCityDtls")
	public BaseResponseVO addCityDtls(@RequestBody CityStateVO cityStateVO) {
		City cityDtls = litigationService.checkExistenceCity(cityStateVO.getCityName());
		if (cityDtls == null) {
			litigationService.saveCityData(cityStateVO);
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("CityName Added SuccessFully");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.FOUND.value());
			baseResponseVO.setResponseMessage("City Already Existed");
		}
		return baseResponseVO;

	}*/

	@GetMapping("/getCityDtls")
	public ResponseEntity<BaseResponseVO> getCityDtls() {
		List<CityStateVO> allCities = litigationService.findAllCities();
		if (allCities == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(allCities);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	@GetMapping("/getRiskDtls")
	public ResponseEntity<BaseResponseVO> getAllRiskDtls() {
		List<RiskClaimVO> riskLevel = litigationService.findAllRiskLevel();
		if (riskLevel == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(riskLevel);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	@GetMapping("/getClaimDtls")
	public ResponseEntity<BaseResponseVO> getAllClaimDtls() {
		List<RiskClaimVO> claimPossible = litigationService.findAllClaimPossible();
		if (claimPossible == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(claimPossible);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	@GetMapping("/getCustomerTypeDtls")
	public ResponseEntity<BaseResponseVO> getCustomerTypeDtls() {
		List<CustomerTypeStatusVO> customerTypeDtls = litigationService.findAllCustomerType();
		if (customerTypeDtls == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(customerTypeDtls);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	@GetMapping("/getStatusDtls")
	public ResponseEntity<BaseResponseVO> getStatusDtls() {
		List<CustomerTypeStatusVO> statusDtls = litigationService.findAllStatus();
		if (statusDtls == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(statusDtls);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	/*@PostMapping("/addCourtForum")
	public ResponseEntity<BaseResponseVO> addCourtForum(@RequestBody CityStateVO cityStateCourtForumVO) {
		CourtCity courtCity = litigationService.findByCourtCity(cityStateCourtForumVO.getCourtForum());
		if (courtCity == null) {
			litigationService.saveCourtCityData(cityStateCourtForumVO);
			baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
			baseResponseVO.setResponseMessage("CourtForum Added Successfully");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.FOUND.value());
			baseResponseVO.setResponseMessage("CourtForum Already Existed");
		}
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}*/

	@PostMapping("/addPoliceStation")
	public BaseResponseVO addPoliceStationDtls(@RequestBody CityStateVO cityStateVO) {
		litigationService.savePoliceStationData(cityStateVO);
		baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
		baseResponseVO.setResponseMessage("PoliceStation Details Added Successfully");
		baseResponseVO.setData(null);
		return baseResponseVO;

	}

	@PostMapping("/addLitigation")
	public ResponseEntity<BaseResponseVO> addLitigation(@RequestBody AddLitigationVO addLitigationVO) {
		litigationService.saveLitigationData(addLitigationVO);
		baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
		baseResponseVO.setResponseMessage("Litigation Added Successfully");
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	@GetMapping("/getLitigationSummary")
	public ResponseEntity<BaseResponseVO> getLitigationSummary() {
		List<LitigationSummaryVO> allLitigationSummary = litigationService.getLitigationSummary();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allLitigationSummary);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	// Connected Litigation tab. add Data.
	@PostMapping("/addConnectedLitigation")
	public BaseResponseVO addConncectedLitigation(@RequestBody ConnectedLitigationVO connectedLitigationVO) {
		litigationService.addConnectedLitigation(connectedLitigationVO);
		baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
		baseResponseVO.setResponseMessage("Connected Litigation Added Successfully");
		baseResponseVO.setData(null);
		return baseResponseVO;
	}

	// witness tab add dtls.
	@PostMapping("/addWitnessDtls")
	public BaseResponseVO addWitnessDtls(@RequestBody ConnectedLitigationVO connectedLitigationVO) {
		litigationService.addWitnessDtls(connectedLitigationVO);
		baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
		baseResponseVO.setResponseMessage("Witness Details Added Successfully");
		baseResponseVO.setData(null);
		return baseResponseVO;
	}

	// Witness tab. get all info by id
	@GetMapping("/getWitnessDtls/{litigationId}")
	public ResponseEntity<BaseResponseVO> getWitnessDtls(@PathVariable("litigationId") String litigationId) {
		List<ConnectedLitigationVO> allWitnessDtls = litigationService.getWitnessDtls(litigationId);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allWitnessDtls);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	// get all Result master info
	@GetMapping("/getLitigationResultMaster")
	public ResponseEntity<BaseResponseVO> getLitigationResultMaster() {
		List<ConnectedLitigationVO> allResultMaster = litigationService.getLitigationResultMaster();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allResultMaster);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	// Completion Tab Dispose data
	@PostMapping("/litigationDisposal")
	public BaseResponseVO litigationDisposal(@RequestBody ConnectedLitigationVO connectedLitigationVO) {
		int updateLitigationdisposal = litigationService.addLitigationDisposal(connectedLitigationVO);
		if (updateLitigationdisposal > 0) {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("LitigationDisposal Updated Successfully");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("Unable to Update Litigation Disposal");
		}
		return baseResponseVO;
	}

	// based on litigation id get Status info
	@GetMapping("/getStatus/{litigationId}")
	public ResponseEntity<BaseResponseVO> getStatus(@PathVariable("litigationId") String litigationId) {
		ConnectedLitigationVO statusById = litigationService.getStatusByLitigationId(litigationId);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(statusById);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	// Document Tab. inside Upload Document Functionality
	@RequestMapping(value = "/uploadDocument", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<BaseResponseVO> uploadDocuments(@RequestPart("file") MultipartFile file,
			@RequestPart("connectedLitigationVO") ConnectedLitigationVO connectedLitigationVO) {
		litigationService.saveLitigationDocsData(file, connectedLitigationVO);
		baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
		baseResponseVO.setResponseMessage("DocumentUploadeded Successfully");
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	// Billing Tab for add Data
	/*@RequestMapping(value = "/addLawfirmBilling", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public BaseResponseVO addLawfirmBilling(@RequestPart("file") MultipartFile file,
			@RequestPart("connectedLitigationVO") ConnectedLitigationVO connectedLitigationVO) {
		litigationService.saveLawfirmBillingData(file, connectedLitigationVO);
		baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
		baseResponseVO.setResponseMessage("Lawfirm Billing Details Added Successfully");
		baseResponseVO.setData(null);
		return baseResponseVO;
	}*/

	// Billing Tab get Data functionality
	@GetMapping("/getLawfirmBilling/{litigationId}")
	public ResponseEntity<BaseResponseVO> getLawfirmBilling(@PathVariable("litigationId") String litigationId) {
		List<ConnectedLitigationVO> allLawfirmBilling = litigationService.getLawfirmBilling(litigationId);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allLawfirmBilling);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	// Details Tab
	@GetMapping("/getCaseCounselDtls/{litigationId}")
	public ResponseEntity<BaseResponseVO> getCaseCounselDetails(@PathVariable("litigationId") String litigationId) {
		List<AddLitigationVO> allCaseCounselDtls = litigationService.getCaseDtls(litigationId);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allCaseCounselDtls);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	@GetMapping("/getLitigationDetails/{litigationId}")
	public ResponseEntity<BaseResponseVO> getAllDetails(@PathVariable("litigationId") String litigationId) {
		List<AddLitigationVO> allCaseDetails = litigationService.getAllDetails(litigationId);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allCaseDetails);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	@GetMapping("/showLitigationDtls/{litigationId}")
	public ResponseEntity<BaseResponseVO> showLitigationDetails(@PathVariable("litigationId") String litigationId) {
		List<AddLitigationVO> allLitigationDtls = litigationService.showLitigationDetails(litigationId);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allLitigationDtls);
		return ResponseEntity.ok().body(baseResponseVO);

	}

	/*@PutMapping("/updateLitigation/{litigationId}")
	public ResponseEntity<BaseResponseVO> updateLitigation(@RequestBody AddLitigationVO addLitigationVO,
			@PathVariable String litigationId) {
		litigationService.updateLitigationData(addLitigationVO, litigationId);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("Litigation Details Updated Successfully");
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}*/

	// History tab next hearing date.
	@PostMapping("/addNextHearingDate")
	public ResponseEntity<BaseResponseVO> addNextHearingDate(@RequestBody ConnectedLitigationVO connectedLitigationVO) {
		litigationService.saveNextHearingDate(connectedLitigationVO);
		baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
		baseResponseVO.setResponseMessage("Hearing Date Created Successfully");
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	/*@GetMapping("/showHistoryDtls/{litigationId}")
	public ResponseEntity<BaseResponseVO> showHistoryDtls(@PathVariable String litigationId) {
		List<ConnectedLitigationVO> allHistoryDtls = litigationService.getHistoryDtls(litigationId);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allHistoryDtls);
		return ResponseEntity.ok().body(baseResponseVO);
	}*/

/*	@PutMapping("/updateHearingDetails/{litigationId}")
	public ResponseEntity<BaseResponseVO> updateHearingDetails(@RequestBody ConnectedLitigationVO connectedLitigationVO,
			@PathVariable String litigationId) {
		litigationService.updateHearingDetails(connectedLitigationVO, litigationId);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("HearingDetails Updated Successfully");
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}

	@GetMapping("/activitiLog/{litigationId}")
	public ResponseEntity<BaseResponseVO> getActivityLog(@PathVariable String litigationId) {
		List<ConnectedLitigationVO> allActivityLog = litigationService.getActivityLog(litigationId);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allActivityLog);
		return ResponseEntity.ok().body(baseResponseVO);
	}*/

	@GetMapping("/showEntityRegionUnits/{litigationId}")
	public ResponseEntity<BaseResponseVO> showEntityRegionUnits(@PathVariable String litigationId) {
		List<UnitSummaryVO> entityRegionUnits = litigationService.showEntityRegionUnits(litigationId);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(entityRegionUnits);
		return ResponseEntity.ok().body(baseResponseVO);

	}
}
