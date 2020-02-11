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
			System.out.println("ZoneName:: " +request.getParameter("zone"));
			System.out.println("Format:: " +request.getParameter("format"));
			System.out.println("Entity::" + request.getParameter("entity"));
			System.out.println("Function:: " +request.getParameter("function"));
			System.out.println("counterParty:: " +request.getParameter("counterParty"));
			System.out.println("Category:: " +request.getParameter("category"));
			System.out.println("claim ::" +request.getParameter("possibleClaim"));
			
			if(request.getParameter("zone") != "0" && request.getParameter("zone") != null && request.getParameter("zone") != "") {
				zone = request.getParameter("zone");
			}
			if(request.getParameter("format") != "0" && request.getParameter("format") != null && request.getParameter("format") != "") {
				format = request.getParameter("format");
			}
			if(request.getParameter("entity") != "0" && request.getParameter("entity") != null && request.getParameter("entity") != "") {
				entity = request.getParameter("entity");
			}
			if(request.getParameter("function") != "0" && request.getParameter("function") != null && request.getParameter("function") != "") {
				function = request.getParameter("function");
			}
			if(request.getParameter("counterParty") != "0" && request.getParameter("counterParty") != null && request.getParameter("counterParty") != "") {
				counterParty = request.getParameter("counterParty");
			}
			if(request.getParameter("category") != "0" && request.getParameter("category") != null && request.getParameter("category") != "") {
				category = request.getParameter("category");
			}
			if(request.getParameter("possibleClaim") != "0" && request.getParameter("possibleClaim") != null && request.getParameter("possibleClaim") != "") {
				possibleClaim = request.getParameter("possibleClaim");
			}
			if(request.getParameter("state") != "0" && request.getParameter("state") != null && request.getParameter("state") != "") {
				state = request.getParameter("state");
			}
			if(request.getParameter("lawfirmIndividual") != "0" && request.getParameter("lawfirmIndividual") != null && request.getParameter("lawfirmIndividual") != "") {
				lawfirmIndividual = request.getParameter("lawfirmIndividual");
			}
			if(request.getParameter("courtType") != "0" && request.getParameter("courtType") != null && request.getParameter("courtType") != "") {
				courtType = request.getParameter("courtType");
			}
			if(request.getParameter("underActs") != "0" && request.getParameter("underActs") != null && request.getParameter("underActs") != "") {
				underActs = request.getParameter("underActs");
			}
			if(request.getParameter("risk") != "0" && request.getParameter("risk") != null && request.getParameter("risk") != "") {
				risk = request.getParameter("risk");
				System.out.println("Selected Risk:: " +risk);
			}
			if(request.getParameter("status") != "0" && request.getParameter("status") != null && request.getParameter("status") != "") {
				status = request.getParameter("status");
			}
			if(request.getParameter("matterByAgainst") != "0" && request.getParameter("matterByAgainst") != null && request.getParameter("matterByAgainst") != "") {
				matterByAgainst = request.getParameter("matterByAgainst");
			}
			if(request.getParameter("litigationByAgainst") != "0" && request.getParameter("litigationByAgainst") != null && request.getParameter("litigationByAgainst") != "") {
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
}
