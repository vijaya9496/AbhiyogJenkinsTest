package com.fg.ss.abhiyog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fg.ss.abhiyog.common.model.City;
import com.fg.ss.abhiyog.common.model.CourtCity;
import com.fg.ss.abhiyog.common.model.CourtType;
import com.fg.ss.abhiyog.common.model.LtgnCaseType;
import com.fg.ss.abhiyog.common.model.LtgnRepresentativeMaster;
import com.fg.ss.abhiyog.common.model.UnderAct;
import com.fg.ss.abhiyog.common.service.ILitigationService;
import com.fg.ss.abhiyog.common.vo.AddLitigationVO;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.CityStateVO;
import com.fg.ss.abhiyog.common.vo.CourtTypeVO;
import com.fg.ss.abhiyog.common.vo.CustomerTypeStatusVO;
import com.fg.ss.abhiyog.common.vo.LtgnCategoryVO;
import com.fg.ss.abhiyog.common.vo.LtgnMatterByVO;
import com.fg.ss.abhiyog.common.vo.RiskClaimVO;
import com.fg.ss.abhiyog.common.vo.UnderActVO;

@RestController
@RequestMapping("/masters/litigation")
public class LitigationRestController {

	@Autowired
	private ILitigationService litigationService;

	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	@PostMapping("/addMatterBy")
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
	}

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

	@PostMapping("/addCategory")
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
	}

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

	@PostMapping("/addUnderAct")
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
	}

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

	@PostMapping("/addCourtType")
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
	}

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

	@PostMapping("/addCityDtls")
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

	}

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
	public ResponseEntity<BaseResponseVO> getAllRiskDtls(){
		List<RiskClaimVO> riskLevel = litigationService.findAllRiskLevel();
		if(riskLevel == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		}else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(riskLevel);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@GetMapping("/getClaimDtls")
	public ResponseEntity<BaseResponseVO> getAllClaimDtls(){
		List<RiskClaimVO> claimPossible = litigationService.findAllClaimPossible();
		if(claimPossible == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		}else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(claimPossible);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@GetMapping("/getCustomerTypeDtls")
	public ResponseEntity<BaseResponseVO> getCustomerTypeDtls(){
		List<CustomerTypeStatusVO> customerTypeDtls = litigationService.findAllCustomerType();
		if(customerTypeDtls == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		}else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(customerTypeDtls);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@GetMapping("/getStatusDtls")
	public ResponseEntity<BaseResponseVO> getStatusDtls(){
		List<CustomerTypeStatusVO> statusDtls = litigationService.findAllStatus();
		if(statusDtls == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("NO DATA");
		}else {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
		}
		baseResponseVO.setData(statusDtls);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	
	@PostMapping("/addCourtForum")
	public ResponseEntity<BaseResponseVO> addCourtForum(@RequestBody CityStateVO cityStateCourtForumVO){
		CourtCity courtCity = litigationService.findByCourtCity(cityStateCourtForumVO.getCourtForum());
		if(courtCity == null) {
			litigationService.saveCourtCityData(cityStateCourtForumVO);
			baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
			baseResponseVO.setResponseMessage("CourtForum Added Successfully");
		}else {
			baseResponseVO.setResponseCode(HttpStatus.FOUND.value());
			baseResponseVO.setResponseMessage("CourtForum Already Existed");
		}
		baseResponseVO.setData(null);
		return  ResponseEntity.ok().body(baseResponseVO);
	}
	
	@PostMapping("/addPoliceStation")
	public BaseResponseVO addPoliceStationDtls(@RequestBody CityStateVO cityStateVO){
		 litigationService.savePoliceStationData(cityStateVO);
		 baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
		 baseResponseVO.setResponseMessage("PoliceStation Details Added Successfully");
		 baseResponseVO.setData(null);
		 return baseResponseVO;
		
	}
	
	@PostMapping("/addLitigation")
	public ResponseEntity<BaseResponseVO> addLitigation(@RequestBody AddLitigationVO addLitigationVO){
		litigationService.saveLitigationData(addLitigationVO);
		baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
		baseResponseVO.setResponseMessage("Litigation Added Successfully");
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
		
	
}
