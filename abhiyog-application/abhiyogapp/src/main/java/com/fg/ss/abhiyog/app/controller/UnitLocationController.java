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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fg.ss.abhiyog.common.constants.CommonConstants;
import com.fg.ss.abhiyog.common.model.Units;
import com.fg.ss.abhiyog.common.service.IEntityService;
import com.fg.ss.abhiyog.common.service.IUnitsSummaryService;
import com.fg.ss.abhiyog.common.service.IZoneService;
import com.fg.ss.abhiyog.common.vo.UnitSummaryVO;
import com.fg.ss.abhiyog.common.vo.UserVO;

@Controller
public class UnitLocationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UnitLocationController.class);
	
	@Autowired
	private IUnitsSummaryService unitsSummaryService;
	
	@Autowired
	private IZoneService zoneService;
	
	@Autowired
	private IEntityService entityService;
	
	@RequestMapping(value = "/showUnitLocationSummary", method = RequestMethod.GET)
	public String showFormatSummary(Model model) {
		model.addAttribute("unitSummaryVO", new UnitSummaryVO());
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allRegions", zoneService.getAllZones());
		return "unitLocationSummary";
	}
	
	@RequestMapping(value = "/getUnitLocationSummary", method = RequestMethod.GET)
	public void getFormatSummary(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<UnitSummaryVO> allUnitSummary = unitsSummaryService.getUnitSummary();
		fillGridDetail(allUnitSummary, request, response, session);
	}

	private void fillGridDetail(List<UnitSummaryVO> allUnitSummary, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
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
			if (allUnitSummary.size() > 0) {
				totalRecord = allUnitSummary.size();
				for (UnitSummaryVO unitSummary : allUnitSummary) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, unitSummary.getUnitId());
					cell = mapper.createArrayNode();
					cell.add(unitSummary.getUnitId());
					cell.add(unitSummary.getEntityName());
					cell.add(unitSummary.getZoneName());
					cell.add(unitSummary.getUnitName());
					System.out.println(unitSummary.getUnitHead().size());
					StringBuilder unitHeadNames = new StringBuilder();
					for(String unitHeadName: unitSummary.getUnitHead()) {
						unitHeadNames.append(unitHeadName + " ");
					}
					cell.add(unitHeadNames.toString());
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
	
	@RequestMapping("/getUnitLocationSummaryBy")
	public void userSummaryData(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		LOGGER.info("inside litigationSummaryData");
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			String entity = "";
			String zone = "";

			if (request.getParameter("entity") != "ALL" && request.getParameter("entity") != null
					&& request.getParameter("entity") != "") {
				entity = request.getParameter("entity");
			}
			if (request.getParameter("zone") != "ALL" && request.getParameter("zone") != null
					&& request.getParameter("zone") != "") {
				zone = request.getParameter("zone");
			}

			List<UnitSummaryVO> unitLocationSummary = unitsSummaryService.findUnitSummaryData(entity,zone);
			if (unitLocationSummary.size() > 0) {
				totalRecord = unitLocationSummary.size();
				for (UnitSummaryVO unitSummaryDtls : unitLocationSummary) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, unitSummaryDtls.getUnitId());
					cell = mapper.createArrayNode();
					cell.add(unitSummaryDtls.getUnitId());
					cell.add(unitSummaryDtls.getEntityName());
					cell.add(unitSummaryDtls.getZoneName());
					cell.add(unitSummaryDtls.getUnitName());
					System.out.println(unitSummaryDtls.getUnitHead().size());
					StringBuilder unitHeadNames = new StringBuilder();
					for(String unitHeadName: unitSummaryDtls.getUnitHead()) {
						unitHeadNames.append(unitHeadName + " ");
					}
					cell.add(unitHeadNames.toString());
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
			LOGGER.error("Exception generated in userSummaryData Method:: " + e.getMessage(), e);
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value="/newUnitLocation", method=RequestMethod.GET)
	public String newUnitLocation(Model model) {
		System.out.println("Inside newUnitLocation");
		model.addAttribute("unitSummaryVO", new UnitSummaryVO());
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allRegions", zoneService.getAllZones());
		return "newUnitLocation";
	}
	
	@RequestMapping(value="/addNewUnitLocation", method=RequestMethod.POST)
	public ModelAndView addNewUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Units units = unitsSummaryService.findExistenceUnitName(request.getParameter("entityName"), request.getParameter("zoneName"), request.getParameter("unitName"));
		UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);	
		System.out.println(request.getParameter("entityName")+ request.getParameter("zoneName")+ request.getParameter("unitName")+ userVO.getLoginId());
		ModelAndView modelAndView = new ModelAndView();
		if(units != null) {
			modelAndView.addObject("unitSummaryVO", new UnitSummaryVO());
			modelAndView.addObject("message", "UNIT NAME ALREADY EXISTED");
			modelAndView.addObject("allEntities", entityService.getAllEntities());
			modelAndView.addObject("allRegions", zoneService.getAllZones());
			modelAndView.setViewName("newUnitLocation");
		}else {
			unitsSummaryService.saveFormData(request.getParameter("entityName"), request.getParameter("zoneName"), request.getParameter("unitName"), userVO.getLoginId());
			modelAndView.addObject("unitSummaryVO",new UnitSummaryVO());
			modelAndView.addObject("message", "UNIT NAME ADDED SUCCESSFULLY");
			modelAndView.addObject("allEntities", entityService.getAllEntities());
			modelAndView.addObject("allRegions", zoneService.getAllZones());
			modelAndView.setViewName("newUnitLocation");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/updateUnitLocation", method=RequestMethod.GET)
	public String updateEntity(Model model, HttpServletRequest request) {
//		model.addAttribute("unitSummaryVO", new UnitSummaryVO());
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allRegions", zoneService.getAllZones());
		model.addAttribute("unitLocationDtls", unitsSummaryService.getUnitLocationDtls(Integer.parseInt(request.getParameter("id"))));
		model.addAttribute("unitHeadNames", unitsSummaryService.getUnitHeadNames());
		return "updateUnitLocation";
	}
	
	@RequestMapping(value="/updateUnitLocationDtls", method=RequestMethod.POST)
	public String updateUserDtls(@ModelAttribute UnitSummaryVO unitSummaryVO, Model model, HttpServletRequest request) {
		Units unitsDtls = unitsSummaryService.findUnitByName(unitSummaryVO.getUnitName());
		if(unitsDtls != null) {
			
//			System.out.println("name:: " +unitSummaryVO.getName());
			int isUpdated= unitsSummaryService.updateCompanyUnitDetails(unitSummaryVO);
			if(isUpdated > 0) {
				model.addAttribute("message", "SUCCESSFULLY USER MAPPED WITH UNITNAME");
				model.addAttribute("unitLocationDtls", new UnitSummaryVO());
				model.addAttribute("allEntities", entityService.getAllEntities());
				model.addAttribute("allRegions", zoneService.getAllZones());
				model.addAttribute("unitHeadNames", unitsSummaryService.getUnitHeadNames());
			}else {
				model.addAttribute("message", "UNABLE TO ADD DATA");
				model.addAttribute("unitLocationDtls", new UnitSummaryVO());
				model.addAttribute("allEntities", entityService.getAllEntities());
				model.addAttribute("allRegions", zoneService.getAllZones());
				model.addAttribute("unitHeadNames", unitsSummaryService.getUnitHeadNames());
			}
		}
		return "updateUnitLocation";
	}
	
	
}
