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

import com.fg.ss.abhiyog.common.model.CounterPartyDtls;
import com.fg.ss.abhiyog.common.service.ICounterPartyService;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.CounterPartyVO;



@RestController
@RequestMapping("/masters/counterParty")
public class CounterPartyRestController {
	
	@Autowired
	private ICounterPartyService counterPartyService;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	@PostMapping("/addCounterParty")
	public ResponseEntity<BaseResponseVO> addCounterPartDetails(@RequestBody CounterPartyVO counterPartyVO){
		
		CounterPartyDtls counterPartyDtls = counterPartyService.findCustomerByName(counterPartyVO.getCounterPartyName());
		if(counterPartyDtls == null) {
			counterPartyService.saveCounterPartyData(counterPartyVO);
			baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
			baseResponseVO.setResponseMessage("COUNTER PARTY DETAILS ADDED SUCCESSFULLY");
		}else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("COUNTERPARTY NAME ALREADY EXISTED");
		}
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@GetMapping("/customerSummary")
	public ResponseEntity<BaseResponseVO> getCustomerSummary(){
		List<CounterPartyVO> allCounterPartyDtls = counterPartyService.findAll();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allCounterPartyDtls);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@PutMapping("/updateCounterPartyDtls")
	public ResponseEntity<BaseResponseVO> updateCounterPartyDtls(@RequestBody CounterPartyVO counterPartyVO){
		CounterPartyDtls counterPartyDtls = counterPartyService.findCustomerByName(counterPartyVO.getCounterPartyName());
		if(counterPartyDtls == null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("COUNTER PARTY NAME NOT EXISTED. UNABLE TO UPDATE");
		}
		counterPartyService.saveCounterPartyData(counterPartyVO);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("COUNTER PARTY DETAILS UPDATED SUCCESSFULLY");
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}
}
