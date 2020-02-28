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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fg.ss.abhiyog.common.constants.CommonConstants;
import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.service.ICounterPartyService;
import com.fg.ss.abhiyog.common.service.IEntityService;
import com.fg.ss.abhiyog.common.service.IFormatService;
import com.fg.ss.abhiyog.common.service.ILitigationService;
import com.fg.ss.abhiyog.common.service.IOutsideCounselService;
import com.fg.ss.abhiyog.common.service.IZoneService;
import com.fg.ss.abhiyog.common.vo.LitigationSummaryVO;
import com.fg.ss.abhiyog.common.vo.UserVO;

@Controller
public class LitigationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LitigationController.class);
	
	@Autowired
	private ILitigationService litigationService;
	
	@Autowired
	private IZoneService zoneService;
	
	@Autowired
	private IFormatService formatService;
	
	@Autowired
	private IEntityService entityService;
	
	@Autowired
	private ICounterPartyService counterPartyService;
	
	@Autowired
	private IOutsideCounselService outsideCounselService;
	
	@RequestMapping(value="/showLitigationSummary", method=RequestMethod.GET)
	public String showLitigationSummary(Model model) {
		model.addAttribute("userVO", new UserVO());
		
		model.addAttribute("allZones", zoneService.getAllZones());
//		model.addAttribute("formatVO", new FormatVO());
		model.addAttribute("allFormats", formatService.getAllFormats());
//		model.addAttribute("entityVO", new EntityVO());
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allFunction", litigationService.getdept());
		model.addAttribute("allCounterParty", counterPartyService.findAll());
		model.addAttribute("allCategories", litigationService.findAllCategoryData());
		model.addAttribute("allPossibleClaim", litigationService.findAllClaimPossible());
		model.addAttribute("allStates", litigationService.findAllStates());
		model.addAttribute("allLawfirmDtls", outsideCounselService.findAll());
		model.addAttribute("allCourtTypeDtls", litigationService.findCourtType());
		model.addAttribute("allUnderActDtls", litigationService.findAllUnderActData());
		model.addAttribute("allRiskDtls", litigationService.findAllRiskLevel());
		model.addAttribute("allStatusDtls", litigationService.findAllStatus());
		model.addAttribute("allMatterByAgainstDtls", litigationService.findAll());
		return "litigationSummary";
	}
	
	
	@GetMapping("/getLitigationSummary")
	public void getLitigationSummary(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		List<LitigationSummaryVO> allLitigationSummary =litigationService.getLitigationSummary();
		fillGridDetail(allLitigationSummary, request, response, session);
	}


	@SuppressWarnings("deprecation")
	private void fillGridDetail(List<LitigationSummaryVO> allLitigationSummary, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		LOGGER.info("inside fillGridDetail method");
		int totalRecord=0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			if(allLitigationSummary.size() > 0) {
				totalRecord=allLitigationSummary.size();
				for(LitigationSummaryVO litigationSummary:allLitigationSummary) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, litigationSummary.getLitigationOId());
					cell = mapper.createArrayNode();
					cell.add(litigationSummary.getLitigationOId());
					cell.add(litigationSummary.getLitigationId()+ ' ' +litigationSummary.getStatus());
					cell.add(litigationSummary.getFileNo());
					cell.add(litigationSummary.getEntityName() +"("+litigationSummary.getUnitName()+")");
					cell.add(litigationSummary.getCounterParty() + "(" +litigationSummary.getCustomerType()+")");
					cell.add(litigationSummary.getCaseNumber());
					cell.add(litigationSummary.getSubject());
					cell.add(litigationSummary.getStage());
					cell.add(litigationSummary.getHearingDate().toString());
					cell.add(litigationSummary.getRiskLevel());
					cell.add(litigationSummary.getPossibleClaim());
					cell.add(litigationSummary.getRemark());
					cell.add(litigationSummary.getZoneName());
//					cellObj.put(CommonConstants.CELL, cell);
					cellObj.set(CommonConstants.CELL, cell);
					cellArray.add(cellObj);
					
				}
			}
			responseData.put(CommonConstants.PAGE,CommonConstants.PAGE_NO);
			responseData.put(CommonConstants.RECORDS, totalRecord);
//			responseData.put(CommonConstants.ROWS, cellArray);
			responseData.set(CommonConstants.ROWS, cellArray);
			out.println(responseData);
			
		} catch (IOException e) {
			LOGGER.error("Exception generated in FillingGrid Method:: " + e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getLitigationSummaryData")
	public void litigationSummaryData(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		LOGGER.info("inside litigationSummaryData");
		int totalRecord=0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			String zone ="";
			String format ="";
			String entity = "";
			String function ="";
			String counterParty = "";
			String category = "";
			String possibleClaim = "";
			String state = "";
			String lawfirmIndividual = "";
			String courtType = "";
			String underActs ="";
			String risk="";
			String status = "";
			String matterByAgainst ="";
			String litigationByAgainst = "";
//			UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
			System.out.println("ZoneName:: " +request.getParameter("zoneName"));
			System.out.println("Format:: " +request.getParameter("format"));
			System.out.println("Entity::" + request.getParameter("entityName"));
			System.out.println("Function:: " +request.getParameter("deptName"));
			System.out.println("counterParty:: " +request.getParameter("counterPartyName"));
			System.out.println("Category:: " +request.getParameter("categoryName"));
			System.out.println("claim ::" +request.getParameter("claim"));
			
			if(request.getParameter("zoneName") != "ALL") {
				zone = request.getParameter("zoneName");
			}
			if(request.getParameter("format") != "ALL") {
				format = request.getParameter("format");
			}
			if(request.getParameter("entityName") != "ALL" && request.getParameter("entityName") != "null" && request.getParameter("entityName") != "") {
				entity = request.getParameter("entityName");
			}
			if(request.getParameter("deptName") != "ALL" && request.getParameter("deptName") != "null" && request.getParameter("deptName") != "") {
				function = request.getParameter("deptName");
			}
			if(request.getParameter("counterPartyName") != "ALL" && request.getParameter("counterPartyName") != "null" && request.getParameter("counterPartyName") != "") {
				counterParty = request.getParameter("counterPartyName");
			}
			if(request.getParameter("categoryName") != "ALL" && request.getParameter("categoryName") != "null" && request.getParameter("categoryName") != "") {
				category = request.getParameter("categoryName");
			}
			if(request.getParameter("claim") != "ALL" && request.getParameter("claim") != "null" && request.getParameter("claim") != "") {
				possibleClaim = request.getParameter("claim");
			}
			if(request.getParameter("stateName") != "ALL" && request.getParameter("stateName") != "null" && request.getParameter("stateName") != "") {
				state = request.getParameter("stateName");
			}
			if(request.getParameter("lawfirm") != "ALL" && request.getParameter("lawfirm") != "null" && request.getParameter("lawfirm") != "") {
				lawfirmIndividual = request.getParameter("lawfirm");
			}
			if(request.getParameter("courtTypeName") != "ALL" && request.getParameter("courtTypeName") != "null" && request.getParameter("courtTypeName") != "") {
				courtType = request.getParameter("courtTypeName");
			}
			if(request.getParameter("underActName") != "ALL" && request.getParameter("underActName") != "null" && request.getParameter("underActName") != "") {
				underActs = request.getParameter("underActName");
			}
			if(request.getParameter("risk") != "ALL" && request.getParameter("risk") != "null" && request.getParameter("risk") != "") {
				risk = request.getParameter("risk");
				System.out.println("Selected Risk:: " +risk);
			}
			if(request.getParameter("status") != "ALL" && request.getParameter("status") != "null" && request.getParameter("status") != "") {
				status = request.getParameter("status");
			}
			if(request.getParameter("matterBy") != "ALL" && request.getParameter("matterBy") != "null" && request.getParameter("matterBy") != "") {
				matterByAgainst = request.getParameter("matterBy");
			}
			if(request.getParameter("litigationByAgainst") != "ALL" && request.getParameter("litigationByAgainst") != "null" && request.getParameter("litigationByAgainst") != "") {
				litigationByAgainst = request.getParameter("litigationByAgainst");
			}
			
			List<LitigationSummaryVO> litigationSummaryData = litigationService.findLitigationSummaryFieldSelection(zone,format, entity, function, counterParty, category, possibleClaim, state, lawfirmIndividual, courtType, underActs, risk, status, matterByAgainst, litigationByAgainst);
			LOGGER.info("litigationSummaryData size:: " +litigationSummaryData.size());
			if(litigationSummaryData.size() > 0) {
				totalRecord=litigationSummaryData.size();
				for(LitigationSummaryVO litigationData:litigationSummaryData) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, litigationData.getLitigationOId());
					cell = mapper.createArrayNode();
					cell.add(litigationData.getLitigationOId());
					cell.add(litigationData.getLitigationId()+ ' ' +litigationData.getStatus());
					cell.add(litigationData.getFileNo());
					cell.add(litigationData.getEntityName() +"("+litigationData.getUnitName()+")");
					cell.add(litigationData.getCounterParty() + "(" +litigationData.getCustomerType()+")");
					cell.add(litigationData.getCaseNumber());
					cell.add(litigationData.getSubject());
					cell.add(litigationData.getStage());
					cell.add(litigationData.getHearingDate().toString());
					cell.add(litigationData.getRiskLevel());
					cell.add(litigationData.getPossibleClaim());
					cell.add(litigationData.getRemark());
					cell.add(litigationData.getZoneName());
//					cellObj.put(CommonConstants.CELL, cell);
					cellObj.set(CommonConstants.CELL, cell);
					cellArray.add(cellObj);
				}
			}
			responseData.put(CommonConstants.PAGE,CommonConstants.PAGE_NO);
			responseData.put(CommonConstants.RECORDS, totalRecord);
//			responseData.put(CommonConstants.ROWS, cellArray);
			responseData.set(CommonConstants.ROWS, cellArray);
			out.println(responseData);
			LOGGER.info("outside litigationSummaryData method");
		} catch (IOException e) {
			LOGGER.error("Exception generated in litigationSummaryData Method:: " + e.getMessage(), e);
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/viewLitigationDetails")
	public String viewLitigationDetails(Model model,HttpServletRequest request) {
		model.addAttribute("litigationSummaryVO", new LitigationSummaryVO());
		System.out.println("ID::" +request.getParameter("id"));
		model.addAttribute("allLitiagtionDtls", litigationService.getLitigationDetails(Integer.parseInt(request.getParameter("id"))));
		return "litigationDetails";
		
	}
}
