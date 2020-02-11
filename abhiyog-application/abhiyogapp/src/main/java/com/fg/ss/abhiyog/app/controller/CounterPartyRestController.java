package com.fg.ss.abhiyog.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fg.ss.abhiyog.common.constants.CommonConstants;
import com.fg.ss.abhiyog.common.model.CounterPartyDtls;
import com.fg.ss.abhiyog.common.service.ICounterPartyService;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.CounterPartyVO;



@Controller
//@RequestMapping("/masters/counterParty")
public class CounterPartyRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CounterPartyRestController.class);
	
	@Autowired
	private ICounterPartyService counterPartyService;
	
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	@RequestMapping(value="/newCounterParty", method=RequestMethod.GET)
	public String newCounterParty(Model model) {
		System.out.println("Inside newUnitLocation");
		model.addAttribute("counterPartyVO", new CounterPartyVO());
		return "addCounterParty";
	}
	
	@PostMapping("/addCounterParty")
	public String addCounterPartDetails(@ModelAttribute CounterPartyVO counterPartyVO, Model model){
		
		CounterPartyDtls counterPartyDtls = counterPartyService.findCustomerByName(counterPartyVO.getCounterPartyName());
		if(counterPartyDtls == null) {
			counterPartyService.saveCounterPartyData(counterPartyVO);
			model.addAttribute("counterPartyVO", new CounterPartyVO());
			model.addAttribute("message", "COUNTER PARTY DETAILS ADDED SUCCESSFULLY");
		}else {
			model.addAttribute("counterPartyVO", new CounterPartyVO());
			model.addAttribute("message", "COUNTERPARTY NAME ALREADY EXISTED");
		}
		return "addCounterParty";
	}
	
	@RequestMapping(value = "/showCustomerSummary", method = RequestMethod.GET)
	public String showUserSummary(Model model) {
		model.addAttribute("counterPartyVO", new CounterPartyVO());
		return "customerSummary";
	}
	
	@RequestMapping(value="/getCustomerSummary", method = RequestMethod.GET)
	public void getCustomerSummary(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<CounterPartyVO> allCounterPartyDtls = counterPartyService.findAll();
		fillGridDetail(allCounterPartyDtls, request, response, session);
	}
	
	private void fillGridDetail(List<CounterPartyVO> allCounterPartyDtls, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		LOGGER.info("inside fillGridDetail method");
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			if (allCounterPartyDtls.size() > 0) {
				totalRecord = allCounterPartyDtls.size();
				for (CounterPartyVO counterPartySummary : allCounterPartyDtls) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, counterPartySummary.getId());
					cell = mapper.createArrayNode();
					cell.add(counterPartySummary.getId());
					cell.add(counterPartySummary.getCounterPartyName());
					cell.add(counterPartySummary.getContactPerson1());
					cell.add(counterPartySummary.getMobile1());
					cell.add(counterPartySummary.getAddress());
					cell.add("Update");
					
//					cellObj.put(CommonConstants.CELL, cell);
					cellObj.set(CommonConstants.CELL, cell);
					cellArray.add(cellObj);
				}
			}
			responseData.put(CommonConstants.PAGE, CommonConstants.PAGE_NO);
			responseData.put(CommonConstants.RECORDS, totalRecord);
//			responseData.put(CommonConstants.ROWS, cellArray);
			responseData.set(CommonConstants.ROWS, cellArray);
			out.println(responseData);

		} catch (IOException e) {
			LOGGER.error("Exception generated in FillingGrid Method:: " + e.getMessage(), e);
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/viewCounterParty")
	public String viewCounterProfile(Model model,HttpServletRequest request) {
		model.addAttribute("counterPartyVO", new CounterPartyVO());
		System.out.println("ID::" +request.getParameter("id"));
		model.addAttribute("allCounterPartyDtls", counterPartyService.getCounterPartyProfile(Integer.parseInt(request.getParameter("id"))));
		return "viewCounterPartyProfile";
		
	}
	
	@RequestMapping(value="/updateCounterParty", method=RequestMethod.GET)
	public String updateUser(Model model, HttpServletRequest request) {
		model.addAttribute("allCounterPartyDtls", counterPartyService.getCounterPartyProfile(Integer.parseInt(request.getParameter("id"))));
		return "updateCounterParty";
	}
	
	@RequestMapping(value="updateCounterPartyDtls", method=RequestMethod.POST)
	public String updateUserDtls(@ModelAttribute CounterPartyVO counterPartyVO, Model model) {
		CounterPartyDtls counterPartyDtls = counterPartyService.findCustomerByName(counterPartyVO.getCounterPartyName());
		if (counterPartyDtls == null) {
			model.addAttribute("message","UNABLE TO UPDATE DETAILS");
			model.addAttribute("allCounterPartyDtls", new CounterPartyVO());
		}else {
			counterPartyService.saveCounterPartyData(counterPartyVO);
			model.addAttribute("message","COUNTER PARTY DETAILS UPDATED SUCCESSFULLY");
			model.addAttribute("allCounterPartyDtls", new CounterPartyVO());
		}
		return "updateCounterParty";
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
