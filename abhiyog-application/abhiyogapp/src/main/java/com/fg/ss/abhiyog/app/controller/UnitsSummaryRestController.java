package com.fg.ss.abhiyog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fg.ss.abhiyog.common.model.Units;
import com.fg.ss.abhiyog.common.service.IUnitsSummaryService;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.UnitSummaryVO;



@RestController
@RequestMapping("/masters/companyUnit")
public class UnitsSummaryRestController {

	@Autowired
	private IUnitsSummaryService unitsSummaryService;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	@GetMapping("/companyUnitsSummary")
	public ResponseEntity<BaseResponseVO> getCompanyUnitsSummary(){
		UnitSummaryVO unitSummary = unitsSummaryService.getUnitSummary();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(unitSummary);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@PostMapping("/addNewCompanyUnit")
	public ResponseEntity<BaseResponseVO> addNewCompanyUnit(@RequestBody UnitSummaryVO unitSummaryVO){
//		Units units = unitsSummaryService.findUnitByName(unitSummaryVO); 
		baseResponseVO  = unitsSummaryService.saveFormData(unitSummaryVO);
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@PutMapping("/updateCompanyUnit")
	public ResponseEntity<BaseResponseVO> updateCompanyUnit(@RequestBody UnitSummaryVO unitSummaryVO){
		Units unitsDtls = unitsSummaryService.findUnitByName(unitSummaryVO.getUnitName());
		if(unitsDtls == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("UNITNAME NOT EXISTED");
		}else {
//			System.out.println("name:: " +unitSummaryVO.getName());
			baseResponseVO = unitsSummaryService.updateCompanyUnitDetails(unitSummaryVO);
		}
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}
}
