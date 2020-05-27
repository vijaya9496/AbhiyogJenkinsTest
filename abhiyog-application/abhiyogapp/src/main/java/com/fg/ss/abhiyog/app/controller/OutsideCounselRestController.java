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
import org.springframework.web.bind.annotation.GetMapping;
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
import com.fg.ss.abhiyog.common.model.LawFirm;
import com.fg.ss.abhiyog.common.service.IOutsideCounselService;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.CounterPartyVO;
import com.fg.ss.abhiyog.common.vo.OutsideCounselVO;
import com.fg.ss.abhiyog.common.vo.UnitSummaryVO;



@Controller
//@RequestMapping("/masters/outsideCounsel")
public class OutsideCounselRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OutsideCounselRestController.class);
	
	@Autowired
	private IOutsideCounselService outsideCounselService;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	@RequestMapping(value = "/showOutsideCounselSummary", method = RequestMethod.GET)
	public String showFormatSummary(Model model) {
		model.addAttribute("outsideCounselVO", new OutsideCounselVO());
		return "outsideCounselSummary";
	}
	
	@RequestMapping(value = "/getOutsideCounselSummary", method = RequestMethod.GET)
	public void getOutsideCounselSummary(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<OutsideCounselVO> allCounselSummary = outsideCounselService.findAll();
		fillGridDetail(allCounselSummary, request, response, session);
	}
	
	private void fillGridDetail(List<OutsideCounselVO> allCounselSummary, HttpServletRequest request,
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
			if (allCounselSummary.size() > 0) {
				totalRecord = allCounselSummary.size();
				for (OutsideCounselVO counselSummary : allCounselSummary) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, counselSummary.getLawfirmId());
					cell = mapper.createArrayNode();
					cell.add(counselSummary.getLawfirmId());
					cell.add(counselSummary.getLawfirm());
					cell.add(counselSummary.getLawfirmHead());
					cell.add(counselSummary.getEmailId());
					cell.add(counselSummary.getMobile());
					cell.add(counselSummary.getAddress());
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
	
	@RequestMapping(value="/viewOutsideCounsel")
	public String viewOutsideCounselProfile(Model model,HttpServletRequest request) {
		model.addAttribute("outsideCounselVO", new OutsideCounselVO());
		System.out.println("ID::" +request.getParameter("id"));
		model.addAttribute("allOutsideCounselDtls", outsideCounselService.getOutsideCounselProfile(Integer.parseInt(request.getParameter("id"))));
		return "viewOutsideCounselProfile";
		
	}

	@RequestMapping(value="/updateOutsideCounsel", method=RequestMethod.GET)
	public String updateUser(Model model, HttpServletRequest request) {
		model.addAttribute("allOutsideCounselDtls", outsideCounselService.getOutsideCounselProfile(Integer.parseInt(request.getParameter("id"))));
		HttpSession  session = request.getSession();
		session.setAttribute(CommonConstants.outsideCounselId, Integer.parseInt(request.getParameter("id")));
		return "updateOutsideCounsel";
	}
	
	
	@RequestMapping(value="/updateOutsideCounselDtls", method=RequestMethod.POST)
	public String updateOutsideCounsel(@ModelAttribute OutsideCounselVO outsideCounselVO, Model model, HttpSession session){
		LawFirm lawfirmDtls = outsideCounselService.findByLawfirm(outsideCounselVO.getLawfirm());
		int sessionOutsideCounselId = (int)session.getAttribute(CommonConstants.outsideCounselId);
		if(lawfirmDtls == null) {
			model.addAttribute("allOutsideCounselDtls", new OutsideCounselVO());
			model.addAttribute("message","LAWFIRM DATA NOT EXISTED UNABLE TO UPDATE");
		}
			outsideCounselService.saveOutsideCounselData(outsideCounselVO);
			model.addAttribute("allOutsideCounselDtls", outsideCounselService.getOutsideCounselProfile(sessionOutsideCounselId));
			model.addAttribute("message","LAWFIRM DATA UPDATED SUCCESSFULLY");
			
		return "updateOutsideCounsel";
		
	}
	
/*	@GetMapping("/outsideCounselSummary")
	public ResponseEntity<BaseResponseVO> getOutsideCounselSummary(){
		List<OutsideCounselVO> allCounselSummary = outsideCounselService.findAll();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allCounselSummary);
		return ResponseEntity.ok().body(baseResponseVO);
	}*/
	
	@RequestMapping(value="/newOutsideCounsel", method=RequestMethod.GET)
	public String newOutsideCounsel(Model model) {
		System.out.println("Inside newOutsideCounsel");
		model.addAttribute("outsideCounselVO", new OutsideCounselVO());
		return "newOutsideCounsel";
	}
	
	@RequestMapping(value="/addOutsideCounselDtls", method=RequestMethod.POST)
	public String addOutsideCounsel(@ModelAttribute OutsideCounselVO outsideCounselVO, Model model){
		LawFirm lawfirmDtls = outsideCounselService.findByLawfirm(outsideCounselVO.getLawfirm());
		if(lawfirmDtls != null) {
			model.addAttribute("outsideCounselVO", new OutsideCounselVO());
			model.addAttribute("message","LAWFIRM NAME ALREADY EXISTED");
		}
			outsideCounselService.saveOutsideCounselData(outsideCounselVO);
			model.addAttribute("outsideCounselVO", new OutsideCounselVO());
			model.addAttribute("message","LAWFIRM DATA CREATED SUCCESSFULLY");
			
		return "newOutsideCounsel";
	}
	
	
}

