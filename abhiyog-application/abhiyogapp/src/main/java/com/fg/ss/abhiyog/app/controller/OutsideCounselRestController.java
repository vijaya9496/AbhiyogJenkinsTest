package com.fg.ss.abhiyog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fg.ss.abhiyog.common.model.LawFirm;
import com.fg.ss.abhiyog.common.service.IOutsideCounselService;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.OutsideCounselVO;



@RestController
@RequestMapping("/masters/outsideCounsel")
public class OutsideCounselRestController {
	
	@Autowired
	private IOutsideCounselService outsideCounselService;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	@GetMapping("/outsideCounselSummary")
	public ResponseEntity<BaseResponseVO> getOutsideCounselSummary(){
		List<OutsideCounselVO> allCounselSummary = outsideCounselService.findAll();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allCounselSummary);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@PostMapping("/addOutsideCounsel")
	public ResponseEntity<BaseResponseVO> addOutsideCounsel(@RequestBody OutsideCounselVO outsideCounselVO){
		LawFirm lawfirmDtls = outsideCounselService.findByLawfirm(outsideCounselVO.getLawfirm());
		if(lawfirmDtls != null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("LAWFIRM NAME ALREADY EXISTED");
		}
			outsideCounselService.saveOutsideCounselData(outsideCounselVO);
			baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
			baseResponseVO.setResponseMessage("LAWFIRM DATA CREATED SUCCESSFULLY");
			baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@PutMapping("/updateOutsideCounsel")
	public ResponseEntity<BaseResponseVO> updateOutsideCounsel(@RequestBody OutsideCounselVO outsideCounselVO){
		LawFirm lawfirmDtls = outsideCounselService.findByLawfirm(outsideCounselVO.getLawfirm());
		if(lawfirmDtls == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("LAWFIRM DATA NOT EXISTED UNABLE TO UPDATE");
		}
			outsideCounselService.saveOutsideCounselData(outsideCounselVO);
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("LAWFIRM DATA UPDATED SUCCESSFULLY");
			baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
		
	}
}

