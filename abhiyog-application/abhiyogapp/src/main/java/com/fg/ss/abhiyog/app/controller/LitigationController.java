package com.fg.ss.abhiyog.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fg.ss.abhiyog.common.constants.CommonConstants;
import com.fg.ss.abhiyog.common.model.City;
import com.fg.ss.abhiyog.common.model.CounterPartyDtls;
import com.fg.ss.abhiyog.common.model.CourtCity;
import com.fg.ss.abhiyog.common.model.CourtType;
import com.fg.ss.abhiyog.common.model.LawFirm;
import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.model.LitigationDocs;
import com.fg.ss.abhiyog.common.model.LtgnCaseType;
import com.fg.ss.abhiyog.common.model.LtgnRepresentativeMaster;
import com.fg.ss.abhiyog.common.model.ShowCauseNoticeForms;
import com.fg.ss.abhiyog.common.model.UnderAct;
import com.fg.ss.abhiyog.common.model.Units;
import com.fg.ss.abhiyog.common.service.FileStorageService;
import com.fg.ss.abhiyog.common.service.ICounterPartyService;
import com.fg.ss.abhiyog.common.service.IEntityService;
import com.fg.ss.abhiyog.common.service.IFormatService;
import com.fg.ss.abhiyog.common.service.ILitigationService;
import com.fg.ss.abhiyog.common.service.IOutsideCounselService;
import com.fg.ss.abhiyog.common.service.IRestoreLitigationService;
import com.fg.ss.abhiyog.common.service.IUnitsSummaryService;
import com.fg.ss.abhiyog.common.service.IZoneService;
import com.fg.ss.abhiyog.common.util.DateUtils;
import com.fg.ss.abhiyog.common.vo.AddLitigationVO;
import com.fg.ss.abhiyog.common.vo.ConnectedLitigationVO;
import com.fg.ss.abhiyog.common.vo.CounterPartyVO;
import com.fg.ss.abhiyog.common.vo.DashboardVO;
import com.fg.ss.abhiyog.common.vo.LitigationSummaryVO;
import com.fg.ss.abhiyog.common.vo.OutsideCounselVO;
import com.fg.ss.abhiyog.common.vo.ShowCauseNoticeVO;
import com.fg.ss.abhiyog.common.vo.UnitSummaryVO;
import com.fg.ss.abhiyog.common.vo.UserVO;

@Controller
public class LitigationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LitigationController.class);

	@Autowired
	private ILitigationService litigationService;

	@Autowired
	private IRestoreLitigationService restoreLitigationService;

	@Autowired
	private IZoneService zoneService;

	@Autowired
	private IFormatService formatService;

	@Autowired
	private IEntityService entityService;

	@Autowired
	private IUnitsSummaryService unitsSummaryService;

	@Autowired
	private ICounterPartyService counterPartyService;

	@Autowired
	private IOutsideCounselService outsideCounselService;
	
	@Autowired
	private FileStorageService fileStorageService;

	@RequestMapping(value = "/showLitigationSummary", method = RequestMethod.GET)
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
	public void getLitigationSummary(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<LitigationSummaryVO> allLitigationSummary = litigationService.getLitigationSummary();
		fillGridDetail(allLitigationSummary, request, response, session);
	}

	@SuppressWarnings("deprecation")
	private void fillGridDetail(List<LitigationSummaryVO> allLitigationSummary, HttpServletRequest request,
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
			if (allLitigationSummary.size() > 0) {
				totalRecord = allLitigationSummary.size();
				for (LitigationSummaryVO litigationSummary : allLitigationSummary) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, litigationSummary.getLitigationOId());
					cell = mapper.createArrayNode();
					cell.add(litigationSummary.getLitigationOId());
					cell.add(litigationSummary.getLitigationId() + ' ' + litigationSummary.getStatus());
					cell.add(litigationSummary.getFileNo());
					cell.add(litigationSummary.getEntityName() + "(" + litigationSummary.getUnitName() + ")");
					cell.add(litigationSummary.getCounterPartyName() + "(" + litigationSummary.getCustomerType() + ")");
					cell.add(litigationSummary.getCaseNumber());
					cell.add(litigationSummary.getSubject());
					cell.add(litigationSummary.getStage());
					cell.add(litigationSummary.getHearingDate().toString());
					cell.add(litigationSummary.getRisk());
					cell.add(litigationSummary.getClaim());
					cell.add(litigationSummary.getRemark());
					cell.add(litigationSummary.getZoneName());
					cell.add("Delete");
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

	@RequestMapping(value = "/getLitigationSummaryData")
	public void litigationSummaryData(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
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
			String zone = "";
			String format = "";
			String entity = "";
			String function = "";
			String counterParty = "";
			String category = "";
			String possibleClaim = "";
			String state = "";
			String lawfirmIndividual = "";
			String courtType = "";
			String underActs = "";
			String risk = "";
			String status = "";
			String matterByAgainst = "";
			String litigationByAgainst = "";
//			UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
			System.out.println("ZoneName:: " + request.getParameter("zoneName"));
			System.out.println("Format:: " + request.getParameter("format"));
			System.out.println("Entity::" + request.getParameter("entityName"));
			System.out.println("Function:: " + request.getParameter("deptName"));
			System.out.println("counterParty:: " + request.getParameter("counterPartyName"));
			System.out.println("Category:: " + request.getParameter("categoryName"));
			System.out.println("claim ::" + request.getParameter("claim"));

			if (request.getParameter("zoneName") != "ALL") {
				zone = request.getParameter("zoneName");
			}
			if (request.getParameter("format") != "ALL") {
				format = request.getParameter("format");
			}
			if (request.getParameter("entityName") != "ALL" && request.getParameter("entityName") != "null"
					&& request.getParameter("entityName") != "") {
				entity = request.getParameter("entityName");
			}
			if (request.getParameter("deptName") != "ALL" && request.getParameter("deptName") != "null"
					&& request.getParameter("deptName") != "") {
				function = request.getParameter("deptName");
			}
			if (request.getParameter("counterPartyName") != "ALL" && request.getParameter("counterPartyName") != "null"
					&& request.getParameter("counterPartyName") != "") {
				counterParty = request.getParameter("counterPartyName");
			}
			if (request.getParameter("categoryName") != "ALL" && request.getParameter("categoryName") != "null"
					&& request.getParameter("categoryName") != "") {
				category = request.getParameter("categoryName");
			}
			if (request.getParameter("claim") != "ALL" && request.getParameter("claim") != "null"
					&& request.getParameter("claim") != "") {
				possibleClaim = request.getParameter("claim");
			}
			if (request.getParameter("stateName") != "ALL" && request.getParameter("stateName") != "null"
					&& request.getParameter("stateName") != "") {
				state = request.getParameter("stateName");
			}
			if (request.getParameter("lawfirm") != "ALL" && request.getParameter("lawfirm") != "null"
					&& request.getParameter("lawfirm") != "") {
				lawfirmIndividual = request.getParameter("lawfirm");
			}
			if (request.getParameter("courtTypeName") != "ALL" && request.getParameter("courtTypeName") != "null"
					&& request.getParameter("courtTypeName") != "") {
				courtType = request.getParameter("courtTypeName");
			}
			if (request.getParameter("underActName") != "ALL" && request.getParameter("underActName") != "null"
					&& request.getParameter("underActName") != "") {
				underActs = request.getParameter("underActName");
			}
			if (request.getParameter("risk") != "ALL" && request.getParameter("risk") != "null"
					&& request.getParameter("risk") != "") {
				risk = request.getParameter("risk");
				System.out.println("Selected Risk:: " + risk);
			}
			if (request.getParameter("status") != "ALL" && request.getParameter("status") != "null"
					&& request.getParameter("status") != "") {
				status = request.getParameter("status");
			}
			if (request.getParameter("matterBy") != "ALL" && request.getParameter("matterBy") != "null"
					&& request.getParameter("matterBy") != "") {
				matterByAgainst = request.getParameter("matterBy");
			}
			if (request.getParameter("litigationByAgainst") != "ALL"
					&& request.getParameter("litigationByAgainst") != "null"
					&& request.getParameter("litigationByAgainst") != "") {
				litigationByAgainst = request.getParameter("litigationByAgainst");
			}

			List<LitigationSummaryVO> litigationSummaryData = litigationService.findLitigationSummaryFieldSelection(
					zone, format, entity, function, counterParty, category, possibleClaim, state, lawfirmIndividual,
					courtType, underActs, risk, status, matterByAgainst, litigationByAgainst);
			LOGGER.info("litigationSummaryData size:: " + litigationSummaryData.size());
			if (litigationSummaryData.size() > 0) {
				totalRecord = litigationSummaryData.size();
				for (LitigationSummaryVO litigationData : litigationSummaryData) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, litigationData.getLitigationOId());
					cell = mapper.createArrayNode();
					cell.add(litigationData.getLitigationOId());
					cell.add(litigationData.getLitigationId() + ' ' + litigationData.getStatus());
					cell.add(litigationData.getFileNo());
					cell.add(litigationData.getEntityName() + "(" + litigationData.getUnitName() + ")");
					cell.add(litigationData.getCounterPartyName() + "(" + litigationData.getCustomerType() + ")");
					cell.add(litigationData.getCaseNumber());
					cell.add(litigationData.getSubject());
					cell.add(litigationData.getStage());
					cell.add(litigationData.getHearingDate().toString());
					cell.add(litigationData.getRisk());
					cell.add(litigationData.getClaim());
					cell.add(litigationData.getRemark());
					cell.add(litigationData.getZoneName());
					cell.add("Delete");
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
			LOGGER.info("outside litigationSummaryData method");
		} catch (IOException e) {
			LOGGER.error("Exception generated in litigationSummaryData Method:: " + e.getMessage(), e);
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/viewLitigationDetails")
	public String viewLitigationDetails(Model model, HttpServletRequest request) {
		model.addAttribute("litigationSummaryVO", new LitigationSummaryVO());
		model.addAttribute("connectedLitigationVO", new ConnectedLitigationVO());
		System.out.println("ID::" + request.getParameter("id"));
		HttpSession session = request.getSession();
		session.setAttribute("litigationSessionId", Integer.parseInt(request.getParameter("id")));
		session.setAttribute("ltgnId", request.getParameter("litigationId"));
		System.out.println("LtgnID**" + session.getAttribute("ltgnId"));
		model.addAttribute("allLitiagtionDtls",
				litigationService.getLitigationDetails(Integer.parseInt(request.getParameter("id"))));
		model.addAttribute("allLitigationIDs", litigationService.getLitigationIds());
		model.addAttribute("allResults", litigationService.getLitigationResultMaster());
		model.addAttribute("allBillingTypes", litigationService.getAllBillingTypes());
		model.addAttribute("allStageDetails", litigationService.getAllStageDetails());

		return "test";
	}

	@RequestMapping(value = "/updateDeleteStatus")
	public String updateDeleteStatus(Model model, HttpServletRequest request) {
		int isUpdatedDeleteStatus = restoreLitigationService
				.updateDeleteStatus(Integer.parseInt(request.getParameter("id")));
		if (isUpdatedDeleteStatus > 0) {
			model.addAttribute("message", "Litigation Deleted Successfully");
		} else {
			model.addAttribute("message", "Unable to Delete Litigation");
		}
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

	@RequestMapping(value = "/addMatterBy", method = RequestMethod.POST)
	@ResponseBody
	public String addMatterByAgainst(@RequestParam String matterByVal) {
		System.out.println("MatterBY Against*** " + matterByVal);
		LtgnRepresentativeMaster ltgnRepresentativeMaster = litigationService
				.checkExistenceRepresentativeName(matterByVal);
		if (ltgnRepresentativeMaster == null) {
			litigationService.saveLtgnRepresentativeMaster(matterByVal);
//			model.addAttribute("message", "Matter By/Against Added Successfully");
			System.out.println("Matter By/Against Added Successfully");
		} else {
//			model.addAttribute("message", "Unable to Add Matter By/Against");
			System.out.println("Unable to Add Matter By/Against");
		}
		return matterByVal;
	}

	@RequestMapping(value = "/addLawfirm", method = RequestMethod.POST)
	@ResponseBody
	public String addLawfirmIndividual(@RequestParam String lawfirmVal) {
		System.out.println("inside addLawfirmIndividual");
		System.out.println("Lawfirm ***  " + lawfirmVal);
		OutsideCounselVO outsideCounselVO = new OutsideCounselVO();
		outsideCounselVO.setLawfirm(lawfirmVal);
		LawFirm lawfirmDtls = outsideCounselService.findByLawfirm(lawfirmVal);
		if (lawfirmDtls != null) {
//			model.addAttribute("outsideCounselVO", new OutsideCounselVO());
//			model.addAttribute("addLitigationVO", new AddLitigationVO());
//			model.addAttribute("message", "LAWFIRM NAME ALREADY EXISTED");
			System.out.println("LAWFIRM NAME ALREADY EXISTED");
		} else {
			outsideCounselService.saveOutsideCounselData(outsideCounselVO);
		}
//		model.addAttribute("allLawfirmDtls", outsideCounselService.findAll());
		return lawfirmVal;
	}

	@RequestMapping(value = "/addCityDtls", method = RequestMethod.POST)
	@ResponseBody
	public String addCourtType(@RequestParam String cityNameVal, @RequestParam String stateVal) {
		System.out.println("CityName*** " + cityNameVal);
		City cityDtls = litigationService.checkExistenceCity(cityNameVal);
		if (cityDtls == null) {
			litigationService.saveCityData(cityNameVal, stateVal);
		} else {
			System.out.println("City Name Already Existed");
		}
		return cityNameVal;
	}

	@RequestMapping(value = "/addCourtType", method = RequestMethod.POST)
	@ResponseBody
	public String addCityDtls(@RequestParam String courtTypeVal) {
		System.out.println("CourtTypeName*** " + courtTypeVal);
		CourtType courtType = litigationService.checkExistenceCourtType(courtTypeVal);
		if (courtType == null) {
			litigationService.saveCourtType(courtTypeVal);

		} else {
			System.out.println("Court Type Name Already Existed");
		}

		return courtTypeVal;
	}

	@RequestMapping(value = "/addCourtForumDtls", method = RequestMethod.POST)
	@ResponseBody
	public String addCityDtls(@RequestParam String courtForumVal, @RequestParam String cityNameVal) {
		System.out.println("CourtForumVal*** " + courtForumVal);
		CourtCity courtCity = litigationService.findByCourtCity(courtForumVal);
		if (courtCity == null) {
			litigationService.saveCourtCityData(courtForumVal, cityNameVal);
		} else {
			System.out.println("CourtForum Already Existed");
		}
		return courtForumVal;
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	@ResponseBody
	public String addCategory(@RequestParam String categoryVal) {
		System.out.println("CategoryName*** " + categoryVal);
		LtgnCaseType ltgnCaseType = litigationService.checkExistenceCaseType(categoryVal);
		if (ltgnCaseType == null) {
			litigationService.saveLtgnCaseType(categoryVal);
//			model.addAttribute("addLitigationVO", new AddLitigationVO());
//			model.addAttribute("message", "Category Name Added Sucessfully");
			System.out.println("Category Name Added Sucessfully");
		} else {
//			model.addAttribute("addLitigationVO", new AddLitigationVO());
//			model.addAttribute("message", "Category Name Already Existed");
			System.out.println("Category Name Already Existed");
		}
		return categoryVal;
	}

	@RequestMapping(value = "/addUnderAct", method = RequestMethod.POST)
	@ResponseBody
	public String addUnderAct(@RequestParam String underActVal) {
		System.out.println("UnderAct Name ** " + underActVal);
		UnderAct underAct = litigationService.findByUnderAct(underActVal);
		if (underAct == null) {
			litigationService.saveUnderActData(underActVal);
//			model.addAttribute("message", "UnderAct Name Added Successfully.");
			System.out.println("UnderAct Name Added Successfully.");
		} else {
//			model.addAttribute("message", "UnderActName Already Existed");
			System.out.println("UnderActName Already Existed");
		}
		return underActVal;
	}

	@RequestMapping(value = "/addCounterPartyDtls")
	@ResponseBody
	public String addCounterParty(@RequestParam String counterPartyNameVal) {
		System.out.println("CounterPartyVal**" + counterPartyNameVal);
		CounterPartyDtls counterPartyDtls = counterPartyService.findCustomerByName(counterPartyNameVal);
		CounterPartyVO counterPartyVO = new CounterPartyVO();
		counterPartyVO.setCounterPartyName(counterPartyNameVal);
		if (counterPartyDtls == null) {
			counterPartyService.saveCounterPartyData(counterPartyVO);
//			model.addAttribute("counterPartyVO", new CounterPartyVO());
//			model.addAttribute("message", "COUNTER PARTY DETAILS ADDED SUCCESSFULLY");
			System.out.println("COUNTER PARTY DETAILS ADDED SUCCESSFULLY");
		} else {
//			model.addAttribute("counterPartyVO", new CounterPartyVO());
//			model.addAttribute("message", "COUNTERPARTY NAME ALREADY EXISTED");
			System.out.println("COUNTERPARTY NAME ALREADY EXISTED");
		}
		return counterPartyNameVal;
	}

	@RequestMapping(value = "/addUnitLocationDtls", method = RequestMethod.POST)
	@ResponseBody
	public String addNewUser(@RequestParam String entityVal, @RequestParam String zoneVal, @RequestParam String unitVal,
			HttpServletResponse response, HttpSession session) {
		Units units = unitsSummaryService.findExistenceUnitName(entityVal, zoneVal, unitVal);
		UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
		System.out.println(entityVal + zoneVal + unitVal);
//		ModelAndView modelAndView = new ModelAndView();
		if (units != null) {
			System.out.println("UNIT NAME ALREADY EXISTED");
//			modelAndView.addObject("unitSummaryVO", new UnitSummaryVO());
//			modelAndView.addObject("message", "UNIT NAME ALREADY EXISTED");
//			modelAndView.addObject("allEntities", entityService.getAllEntities());
//			modelAndView.addObject("allRegions", zoneService.getAllZones());
//			modelAndView.setViewName("addLitigation");
		} else {
			unitsSummaryService.saveFormData(entityVal, zoneVal, unitVal, userVO.getLoginId());
//			modelAndView.addObject("unitSummaryVO", new UnitSummaryVO());
			System.out.println("UNIT NAME ADDED SUCCESSFULLY");
//			modelAndView.addObject("message", "UNIT NAME ADDED SUCCESSFULLY");
//			modelAndView.addObject("allEntities", entityService.getAllEntities());
//			modelAndView.addObject("allRegions", zoneService.getAllZones());
//			modelAndView.setViewName("addLitigation");
		}
		return unitVal;
	}

	@RequestMapping(value = "/newLitigation", method = RequestMethod.GET)
	public String newLitigation(Model model) {
		model.addAttribute("addLitigationVO", new AddLitigationVO());
//		model.addAttribute("unitSummaryVO", new UnitSummaryVO());
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allRegions", zoneService.getAllZones());
		model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		model.addAttribute("allFormats", formatService.getAllFormats());
		model.addAttribute("allCustomerType", litigationService.findAllCustomerType());
		model.addAttribute("allCounterPartyDtls", counterPartyService.findAll());
		model.addAttribute("allCategory", litigationService.findAllCategoryData());
		model.addAttribute("allRiskDtls", litigationService.findAllRiskLevel());
		model.addAttribute("allPossibleClaim", litigationService.findAllClaimPossible());
		model.addAttribute("allUnderActDtls", litigationService.findAllUnderActData());
		model.addAttribute("allCourtTypeDtls", litigationService.findCourtType());
		model.addAttribute("allStatusDtls", litigationService.findAllStatus());
		model.addAttribute("allseniorCounselDtls", outsideCounselService.findAllSeniorCounselDtls());
		model.addAttribute("allLawfirmDtls", outsideCounselService.findAll());
		model.addAttribute("allStateDtls", litigationService.findAllStates());
		model.addAttribute("allCityDtls", litigationService.findAllCities());
		model.addAttribute("allCourtForumDtls", litigationService.getAllCourtForum());
		model.addAttribute("allMatterByAgainstDtls", litigationService.findAll());
		return "addLitigation";
	}

	@RequestMapping(value = "/saveLitigation", method = RequestMethod.POST)
	public String saveLitigation(HttpServletRequest request, Model model, HttpSession session) throws ParseException {
		UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
		AddLitigationVO addLitigationVO = new AddLitigationVO();
		System.out.println("EntityName**" + request.getParameter("entityName"));
		addLitigationVO.setEntityName(request.getParameter("entityName"));
		System.out.println("ZoneName** " + request.getParameter("zoneName"));
		addLitigationVO.setZoneName(request.getParameter("zoneName"));
		System.out.println("UnitName" + request.getParameter("unitName"));
		addLitigationVO.setUnitName(request.getParameter("unitName"));
		addLitigationVO.setFormat(request.getParameter("format"));
		addLitigationVO.setCoFormat(request.getParameter("coFormat"));
		addLitigationVO.setAddress(request.getParameter("address"));
		addLitigationVO.setStoreOfficePremises(request.getParameter("storeOfficePremises"));
		addLitigationVO.setCoFormat(request.getParameter("coFormat"));
		addLitigationVO.setMatterByAgainst(request.getParameterValues("matterByAgainst"));
		addLitigationVO.setCustomerType(request.getParameter("customerType"));
		addLitigationVO.setCoZone(request.getParameter("coZone"));
		addLitigationVO.setCounterPartyName(request.getParameter("counterPartyName"));
		addLitigationVO.setCoCounterParties(request.getParameter("coCounterParties"));
		addLitigationVO.setSubject(request.getParameter("subject"));
		addLitigationVO.setCategoryName(request.getParameter("categoryName"));
		addLitigationVO.setCaseNumber(request.getParameter("caseNumber"));
		addLitigationVO.setRisk(request.getParameter("risk"));
		addLitigationVO.setClaim(request.getParameter("claim"));
		addLitigationVO.setPossibleClaim(Float.valueOf(request.getParameter("possibleClaim")));
		addLitigationVO.setPantaloonFileNo(request.getParameter("pantaloonFileNo"));
		addLitigationVO.setUnderSection(request.getParameter("underSection"));
		addLitigationVO.setOtherUnderacts(request.getParameter("otherUnderacts"));
		addLitigationVO.setUnderActName(request.getParameter("underActName"));
		addLitigationVO.setJudgeName(request.getParameter("judgeName"));
		addLitigationVO.setCourtTypeName(request.getParameter("courtTypeName"));
		addLitigationVO.setStateName(request.getParameter("stateName"));
		addLitigationVO.setCityName(request.getParameter("cityName"));
		addLitigationVO.setStatus(request.getParameter("status"));
		addLitigationVO.setFirNo(request.getParameter("firNo"));
		addLitigationVO.setCourtForum(request.getParameter("courtForum"));
		System.out.println("DateofReceiptOfMatter *** " + request.getParameter("dateOfReceiptOfMatter"));

		addLitigationVO
				.setDateOfReceiptOfMatter(DateUtils.getDBFormatedDte(request.getParameter("dateOfReceiptOfMatter")));
		addLitigationVO.setFactsOfLitigation(request.getParameter("factsOfLitigation"));
		addLitigationVO.setStage(request.getParameter("stage"));
		addLitigationVO.setCounselAssesment(request.getParameter("counselAssesment"));
		addLitigationVO.setRemark(request.getParameter("remark"));
		addLitigationVO.setNextHearingDate(DateUtils.getDBFormatedDte(request.getParameter("nextHearingDate")));
		addLitigationVO.setSeniorCounsel(request.getParameter("seniorCounsel"));
		addLitigationVO.setLawfirm(request.getParameter("lawfirm"));
		addLitigationVO.setFunction("Legal");
		addLitigationVO.setLoginId(userVO.getLoginId());

		boolean isInserted = litigationService.saveLitigationData(addLitigationVO);
		if (isInserted) {
			model.addAttribute("message", "Litigation Details Added Successfully");
		} else {
			model.addAttribute("message", "Unable To Add Litigation Details");
		}
		model.addAttribute("addLitigationVO", new AddLitigationVO());
//		model.addAttribute("unitSummaryVO", new UnitSummaryVO());
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allRegions", zoneService.getAllZones());
		model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		model.addAttribute("allFormats", formatService.getAllFormats());
		model.addAttribute("allCustomerType", litigationService.findAllCustomerType());
		model.addAttribute("allCounterPartyDtls", counterPartyService.findAll());
		model.addAttribute("allCategory", litigationService.findAllCategoryData());
		model.addAttribute("allRiskDtls", litigationService.findAllRiskLevel());
		model.addAttribute("allPossibleClaim", litigationService.findAllClaimPossible());
		model.addAttribute("allUnderActDtls", litigationService.findAllUnderActData());
		model.addAttribute("allCourtTypeDtls", litigationService.findCourtType());
		model.addAttribute("allStatusDtls", litigationService.findAllStatus());
		model.addAttribute("allseniorCounselDtls", outsideCounselService.findAllSeniorCounselDtls());
		model.addAttribute("allLawfirmDtls", outsideCounselService.findAll());
		model.addAttribute("allStateDtls", litigationService.findAllStates());
		model.addAttribute("allCityDtls", litigationService.findAllCities());
		model.addAttribute("allCourtForumDtls", litigationService.getAllCourtForum());
		model.addAttribute("allMatterByAgainstDtls", litigationService.findAll());

		return "addLitigation";
	}

	@RequestMapping(value = "/showRestoreLitigationSummary", method = RequestMethod.GET)
	public String showRestoreLitigationSummary(Model model) {
		model.addAttribute("addLitigationVO", new AddLitigationVO());
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allCounterParty", counterPartyService.findAll());
		model.addAttribute("allCourtTypeDtls", litigationService.findCourtType());
		model.addAttribute("allUnderActDtls", litigationService.findAllUnderActData());
		model.addAttribute("allRiskDtls", litigationService.findAllRiskLevel());
		model.addAttribute("allCategories", litigationService.findAllCategoryData());
		return "restoreLitigation";
	}

	@RequestMapping(value = "/getRestoreLitigationSummary", method = RequestMethod.GET)
	public void getRestoreLitigationSummary(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		List<LitigationSummaryVO> allRestoreLitigationSummary = litigationService.getRestoreLitigationSummary();
		fillRestoreGridDetail(allRestoreLitigationSummary, response, session);
	}

	private void fillRestoreGridDetail(List<LitigationSummaryVO> allRestoreLitigationSummary,
			HttpServletResponse response, HttpSession session) {
		LOGGER.info("inside fillRestoreGridDetail method");
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			if (allRestoreLitigationSummary.size() > 0) {
				totalRecord = allRestoreLitigationSummary.size();
				for (LitigationSummaryVO restoreLitigationSummary : allRestoreLitigationSummary) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, restoreLitigationSummary.getLitigationOId());
					cell = mapper.createArrayNode();
					cell.add(restoreLitigationSummary.getLitigationOId());
					cell.add(restoreLitigationSummary.getLitigationId());
					cell.add(restoreLitigationSummary.getEntityName());
					cell.add(restoreLitigationSummary.getCounterPartyName());
					cell.add(restoreLitigationSummary.getCaseNumber());
					cell.add(restoreLitigationSummary.getSubject());
					cell.add(restoreLitigationSummary.getUnderActName());
					cell.add(restoreLitigationSummary.getRisk());

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
			LOGGER.error("Exception generated in fillRestoreGridDetail Method:: " + e.getMessage(), e);
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/getRestoreLitigationSummaryData")
	public void restoreLitigationSummaryData(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		LOGGER.info("inside restoreLitigationSummaryData");
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
			String counterParty = "";
			String category = "";
			String courtType = "";
			String underActs = "";
			String risk = "";

//			UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
			System.out.println("Entity::" + request.getParameter("entityName"));
			System.out.println("counterParty:: " + request.getParameter("counterPartyName"));
			System.out.println("Category:: " + request.getParameter("categoryName"));

			if (request.getParameter("entityName") != "ALL" && request.getParameter("entityName") != "null"
					&& request.getParameter("entityName") != "") {
				entity = request.getParameter("entityName");
			}

			if (request.getParameter("counterPartyName") != "ALL" && request.getParameter("counterPartyName") != "null"
					&& request.getParameter("counterPartyName") != "") {
				counterParty = request.getParameter("counterPartyName");
			}
			if (request.getParameter("categoryName") != "ALL" && request.getParameter("categoryName") != "null"
					&& request.getParameter("categoryName") != "") {
				category = request.getParameter("categoryName");
			}

			if (request.getParameter("courtTypeName") != "ALL" && request.getParameter("courtTypeName") != "null"
					&& request.getParameter("courtTypeName") != "") {
				courtType = request.getParameter("courtTypeName");
			}
			if (request.getParameter("underActName") != "ALL" && request.getParameter("underActName") != "null"
					&& request.getParameter("underActName") != "") {
				underActs = request.getParameter("underActName");
			}
			if (request.getParameter("risk") != "ALL" && request.getParameter("risk") != "null"
					&& request.getParameter("risk") != "") {
				risk = request.getParameter("risk");
				System.out.println("Selected Risk:: " + risk);
			}

			List<LitigationSummaryVO> restoreLitigationSummaryData = litigationService
					.findRestoreLitigationSummaryFieldSelection(entity, counterParty, category, courtType, underActs,
							risk);
			LOGGER.info("litigationSummaryData size:: " + restoreLitigationSummaryData.size());
			if (restoreLitigationSummaryData.size() > 0) {
				totalRecord = restoreLitigationSummaryData.size();
				for (LitigationSummaryVO restoreLitigationData : restoreLitigationSummaryData) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, restoreLitigationData.getLitigationOId());
					cell = mapper.createArrayNode();
					cell.add(restoreLitigationData.getLitigationOId());
					cell.add(restoreLitigationData.getLitigationId());
					cell.add(restoreLitigationData.getEntityName());
					cell.add(restoreLitigationData.getCounterPartyName());
					cell.add(restoreLitigationData.getCaseNumber());
					cell.add(restoreLitigationData.getSubject());
					cell.add(restoreLitigationData.getUnderActName());
					cell.add(restoreLitigationData.getRisk());
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
			LOGGER.error("Exception generated in restoreLitigationSummaryData Method:: " + e.getMessage(), e);
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/updateRestoreLitigationData", method = RequestMethod.POST)
	@ResponseBody
	public void updateRestoreLitigationData(@RequestBody String selectedGridRows, Model model) {
		LOGGER.info("Selected Grid Row Id" + selectedGridRows);
		selectedGridRows = selectedGridRows.substring(selectedGridRows.indexOf(":") + 2);
		selectedGridRows = selectedGridRows.substring(0, selectedGridRows.length() - 2);
		LOGGER.info("After Remove" + selectedGridRows);
		List<String> separatedString = Arrays.asList(selectedGridRows.split(","));
		for (int i = 0; i < separatedString.size(); i++) {
			System.out.println(separatedString.get(i));
			int isRestoreLitigationUpdated = litigationService
					.updateRestoreLitigationData(Integer.parseInt(separatedString.get(i)));
			System.out.println(isRestoreLitigationUpdated);
			if (isRestoreLitigationUpdated > 0) {
				LOGGER.info("Litigation Restored Successfully");
			} else {
				LOGGER.info("Unable to Restored Litigation");
			}
		}
	}

	@RequestMapping(value = "/getWitnessDetails", method = RequestMethod.GET)
//	@ResponseBody
	public void getWitnessDetails(HttpServletRequest request, HttpServletResponse response, Model model,
			HttpSession session) {
		int id = (int) session.getAttribute("litigationSessionId");
		System.out.println("LitigationID** " + id);
		List<ConnectedLitigationVO> allWitnessDtls = litigationService.getWitnessDetails(id);
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
			if (allWitnessDtls.size() > 0) {
				totalRecord = allWitnessDtls.size();
				for (ConnectedLitigationVO witnessDtls : allWitnessDtls) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, witnessDtls.getWitnessId());
					cell = mapper.createArrayNode();
					cell.add(witnessDtls.getLitigationId());
					cell.add(witnessDtls.getWitnessName());

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

	@RequestMapping(value = "/addWitnessDetails", method = RequestMethod.POST)
	@ResponseBody
	public String addWitnessDetails(@RequestParam String witnessVal, HttpServletRequest request, HttpSession session,
			Model model) {
		System.out.println("Witness Name*** " + witnessVal);
		System.out.println("LitigationID** " + request.getParameter("id"));
		ConnectedLitigationVO connectedLitigationVO = new ConnectedLitigationVO();
		connectedLitigationVO.setWitnessName(witnessVal);
		int id = (int) session.getAttribute("litigationSessionId");
		System.out.println("ID:: " + id);
		connectedLitigationVO.setLitigationOId(id);
		litigationService.addWitnessDtls(connectedLitigationVO);
//		model.addAttribute("message", "Witness Details Added Successfully");
		return "Success";

	}

	@RequestMapping(value = "/addConnectedLitigationDetails", method = RequestMethod.POST)
	@ResponseBody
	public String addConnectedLitigation(@RequestParam String commentsVal, @RequestParam String litigationIdVal,
			HttpServletRequest request, HttpSession session) {
		ConnectedLitigationVO connectedLitigationVO = new ConnectedLitigationVO();
		int id = (int) session.getAttribute("litigationSessionId");
		System.out.println("ID:: " + id);
		connectedLitigationVO.setConnectedLitigationId(litigationIdVal);
		connectedLitigationVO.setComments(commentsVal);
		connectedLitigationVO.setLitigationOId(id);
		litigationService.addConnectedLitigation(connectedLitigationVO);

		return "Success";

	}

	@RequestMapping(value = "/historyDetails", method = RequestMethod.POST)
	@ResponseBody
	public String historyDetails(@RequestParam String hearingDateVal, @RequestParam String stageVal,
			@RequestParam String stageDetailsVal, HttpServletRequest request, HttpSession session)
			throws ParseException {
		ConnectedLitigationVO connectedLitigationVO = new ConnectedLitigationVO();
		int id = (int) session.getAttribute("litigationSessionId");
		System.out.println("ID:: " + id);
		connectedLitigationVO.setHearingDt(DateUtils.getDBFormatedDte(hearingDateVal));
		connectedLitigationVO.setStage(stageVal);
		connectedLitigationVO.setStageDetails(stageDetailsVal);
		connectedLitigationVO.setLitigationOId(id);
		UserVO uservo = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
		connectedLitigationVO.setLoginId(uservo.getLoginId());
		litigationService.saveNextHearingDate(connectedLitigationVO);

		return "Success";

	}

	@RequestMapping(value = "/getHistoryDetails", method = RequestMethod.GET)

	public void getHistoryDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		LOGGER.info("inside getHistoryDetails method");
		int id = (int) session.getAttribute("litigationSessionId");
		System.out.println("ID:: " + id);
		List<ConnectedLitigationVO> allHistoryDetails = litigationService.getHistoryDtls(id);

		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			if (allHistoryDetails.size() > 0) {
				totalRecord = allHistoryDetails.size();
				for (ConnectedLitigationVO historyDtls : allHistoryDetails) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, historyDtls.getHearingId());
					cell = mapper.createArrayNode();
					cell.add(historyDtls.getHearingId());
					cell.add(historyDtls.getLitigationId());
					cell.add(historyDtls.getHearingDt().toString());
					cell.add(historyDtls.getStage());
					cell.add(historyDtls.getStageDetails());
					cell.add(historyDtls.getEvent());
					cell.add(historyDtls.getAddedBy());
					cell.add(historyDtls.getAttendedBy());
					cell.add("NA");
					cell.add("Update");
					cell.add("Delete");

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
		session.setAttribute(CommonConstants.SESSION_REPORT_DATA, allHistoryDetails);

	}

	@RequestMapping(value = "/updateHistoryDetails", method = RequestMethod.GET)
	public String updateHistoryDetails(Model model, HttpServletRequest request) {

		model.addAttribute("updateHistoryDetails",
				litigationService.findHistoryDetails(Integer.parseInt(request.getParameter("id"))));
		return "updateHistoryDetails";
	}

	@RequestMapping(value = "/updateHistory", method = RequestMethod.GET)
	public String updateHistory(@ModelAttribute ConnectedLitigationVO connectedLitigationVO, Model model) {

		litigationService.updateHearingDetails(connectedLitigationVO);
		model.addAttribute("updateHistoryDetails", new ConnectedLitigationVO());
		model.addAttribute("message", "Hearing Details Updated Successfully");
		return "updateHistoryDetails";
	}

	@RequestMapping(value = "/getActivityLog", method = RequestMethod.GET)
	public void getActivityLog(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int id = (int) session.getAttribute("litigationSessionId");
		List<ConnectedLitigationVO> allActivityLog = litigationService.getActivityLog(id);
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			if (allActivityLog.size() > 0) {
				totalRecord = allActivityLog.size();
				for (ConnectedLitigationVO logDtls : allActivityLog) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, logDtls.getLogId());
					cell = mapper.createArrayNode();
					cell.add(logDtls.getLogId());
					cell.add(logDtls.getlHistoryId());
					cell.add(logDtls.getUpdatedBy());
					cell.add(logDtls.getActivityType());
					cell.add(logDtls.getActivityDescription().toString());
					cell.add(logDtls.getModifiedDate().toString());

//				cellObj.put(CommonConstants.CELL, cell);
					cellObj.set(CommonConstants.CELL, cell);
					cellArray.add(cellObj);

				}
			}
			responseData.put(CommonConstants.PAGE, CommonConstants.PAGE_NO);
			responseData.put(CommonConstants.RECORDS, totalRecord);
//		responseData.put(CommonConstants.ROWS, cellArray);
			responseData.set(CommonConstants.ROWS, cellArray);
			out.println(responseData);

		} catch (IOException e) {
			LOGGER.error("Exception generated in FillingGrid Method:: " + e.getMessage(), e);
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/updateHistoryDetails/{hearingId}", method = RequestMethod.GET)
	public String updateHistoryDetails(@PathVariable("hearingId") int hearingId, Model model) {
		System.out.println("hearingID" + hearingId);
		model.addAttribute("updateHistoryDetails", litigationService.findHistoryDetails(hearingId));
		return "updateHistoryDetails";
	}

	@RequestMapping(value = "/addDisposedDate", method = RequestMethod.POST)
	@ResponseBody
	public String addDisposedDate(@RequestParam String resultVal, @RequestParam String disposedDateVal,
			@RequestParam String commentsVal, HttpServletRequest request, HttpSession session) throws ParseException {
		System.out.println(resultVal);
		System.out.println(disposedDateVal);
		System.out.println(commentsVal);
		int id = (int) session.getAttribute("litigationSessionId");
		System.out.println("ID:: " + id);
		ConnectedLitigationVO connectedLitigationVO = new ConnectedLitigationVO();
		connectedLitigationVO.setResultName(resultVal);
		connectedLitigationVO.setDisposedDate(DateUtils.getDBFormatedDte(disposedDateVal));
		connectedLitigationVO.setComments(commentsVal);
		connectedLitigationVO.setLitigationOId(id);

		litigationService.addLitigationDisposal(connectedLitigationVO);

		return "Success";

	}

	@RequestMapping(value = "/addLawfirmBilling", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public String addLawfirmBilling(HttpServletRequest request, HttpSession session,
			MultipartHttpServletRequest multiPartRequest) throws ParseException {
		int id = (int) session.getAttribute("litigationSessionId");
		System.out.println("ID:: " + id);
		System.out.println("billing Type:: " + request.getParameter("billingType"));
		System.out.println("billing Amount:: " + request.getParameter("billingAmount"));
		System.out.println("billingDate::" + request.getParameter("billingDate"));
		System.out.println("remark:: " + request.getParameter("remark"));

		ConnectedLitigationVO connectedLitigationVO = new ConnectedLitigationVO();
		connectedLitigationVO.setBillingType(request.getParameter("billingType"));
		connectedLitigationVO.setBillingAmount(Float.valueOf(request.getParameter("billingAmount")));
		connectedLitigationVO.setBillingDate(DateUtils.getDBFormatedDte(request.getParameter("billingDate")));
		connectedLitigationVO.setRemark(request.getParameter("remark"));

		MultipartFile multiPartFile = multiPartRequest.getFile("uploadFile");

		connectedLitigationVO.setLitigationOId(id);

		litigationService.saveLawfirmBillingData(multiPartFile, connectedLitigationVO);
		return "Successfully Uploaded";

	}

	@RequestMapping(value = "/uploadDocument", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public String uploadDocument(HttpServletRequest request, HttpSession session,
			MultipartHttpServletRequest multiPartrequest) {

		ConnectedLitigationVO connectedLitigationVO = new ConnectedLitigationVO();
		int id = (int) session.getAttribute("litigationSessionId");
		System.out.println("ID:: " + id);
		System.out.println("Comments::" + request.getParameter("uploadComments"));
		System.out.println("DocumentTitle:: " + request.getParameter("documentTitle"));
		UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
		connectedLitigationVO.setLitigationOId(id);
		connectedLitigationVO.setLoginId(userVO.getLoginId());
		connectedLitigationVO.setUploadComments(request.getParameter("uploadComments"));
		connectedLitigationVO.setDocumentTitle(request.getParameter("documentTitle"));
		MultipartFile multiPartFile = multiPartrequest.getFile("uploadFile");

		litigationService.saveLitigationDocsData(multiPartFile, connectedLitigationVO);

		return "SUCCESSFULLY UPLOADED";
	}

	@RequestMapping(value = "/getDashboardSummary", method = RequestMethod.GET)
	public String getDashboardSummary() {
		return "dashboard";
	}

	@RequestMapping(value = "/getOutsideCounselDtls/{lawfirmVal}", method = RequestMethod.GET)
	public ModelAndView getOutsideCounselDtls(@PathVariable String lawfirmVal) {
		System.out.println("lawfirm" + lawfirmVal);
		ModelAndView modelAndView = new ModelAndView();
		OutsideCounselVO outsideCounselVO = new OutsideCounselVO();
		LawFirm lawfirmDtls = outsideCounselService.findByLawfirm(lawfirmVal);
		outsideCounselVO.setLawfirm(lawfirmDtls.getLawfirm());
		System.out.println(outsideCounselVO.getLawfirm());
		outsideCounselVO.setLawfirmHead(lawfirmDtls.getLawfirmHead()); // partner/counsel
		outsideCounselVO.setAddress(lawfirmDtls.getAddress());
		outsideCounselVO.setEmailId(lawfirmDtls.getLawfirmHeadEmailId());
		outsideCounselVO.setFaxNo(lawfirmDtls.getFaxNo());
		outsideCounselVO.setMobile(lawfirmDtls.getMobile());
		outsideCounselVO.setWebsite(lawfirmDtls.getWebsite());
		outsideCounselVO.setTelephone(lawfirmDtls.getTelephone());
//		model.addAttribute("outsideCounselVO", outsideCounselVO);
		modelAndView.addObject("outsideCounselVO", outsideCounselVO);
		modelAndView.setViewName("outsideCounselDetails");
		return modelAndView;

	}

	@RequestMapping(value = "/dashboardSummary", method = RequestMethod.GET)
	public void dashboardSummary(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<LitigationSummaryVO> dashboardSummary = litigationService.getDashboardSummary();
		LOGGER.info("inside dashboardSummary method");
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			if (dashboardSummary.size() > 0) {
				totalRecord = dashboardSummary.size();
				for (LitigationSummaryVO litigationSummary : dashboardSummary) {
					cellObj = mapper.createObjectNode();
//					cellObj.put(CommonConstants.ID, litigationSummary.getLitigationOId());
					cell = mapper.createArrayNode();
					cell.add(litigationSummary.getUnitOId());
					cell.add(litigationSummary.getZoneName());
					cell.add(litigationSummary.getUnitName());
					cell.add(litigationSummary.getUpdated());
					cell.add(litigationSummary.getNotUpdated());
					cell.add(litigationSummary.getTotal());

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
	
	@RequestMapping(value="/getDashboardDetails", method=RequestMethod.GET)
	public String getDashboardDetails() {
		return "dashboardDetails";
	}
	
	@RequestMapping(value="/getDashboardDtlByUnitoId", method=RequestMethod.GET)
	public void getDashboardDtlByUnitoId(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		int unitoId=Integer.parseInt(request.getParameter("unitoid"));
		LOGGER.info("unitoId: "+unitoId);
		List<LitigationSummaryVO> dashboardDetails = litigationService.getDashboardDetails(unitoId);
		LOGGER.info("inside getDashboardDtlByUnitoId method");
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			if (dashboardDetails.size() > 0) {
				totalRecord = dashboardDetails.size();
				for (LitigationSummaryVO litigationSummary : dashboardDetails) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, litigationSummary.getLitigationOId());
					cell = mapper.createArrayNode();
					cell.add(litigationSummary.getLitigationOId());
					cell.add(litigationSummary.getLitigationId()+"\r\n"+litigationSummary.getStatus());
					cell.add("");
					cell.add(litigationSummary.getEntityName()+"\r\n"+litigationSummary.getUnitName());
					cell.add(litigationSummary.getCounterPartyName()+"\r\n"+litigationSummary.getAgainstPartyClientType());
					cell.add(litigationSummary.getCaseNumber());
					cell.add(litigationSummary.getStage());
					cell.add(litigationSummary.getCourtCity()+"\r\n"+litigationSummary.getHearingDate());
					cell.add(litigationSummary.getRisk());
					cell.add(litigationSummary.getClaim());
					cell.add(litigationSummary.getRemark());
								
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

	@RequestMapping(value = "/updateLtgn", method = RequestMethod.GET)
	public String updateLtgn(Model model, HttpServletRequest request, HttpSession session) {
//		model.addAttribute("updateLitigatinVO", new LitigationSummaryVO());
		System.out.println("LitigationId" + request.getParameter("litigationId"));
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allRegions", zoneService.getAllZones());
		model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		model.addAttribute("allFormats", formatService.getAllFormats());
		model.addAttribute("allCustomerType", litigationService.findAllCustomerType());
		model.addAttribute("allCounterPartyDtls", counterPartyService.findAll());
		model.addAttribute("allCategory", litigationService.findAllCategoryData());
		model.addAttribute("allRiskDtls", litigationService.findAllRiskLevel());
		model.addAttribute("allPossibleClaim", litigationService.findAllClaimPossible());
		model.addAttribute("allUnderActDtls", litigationService.findAllUnderActData());
		model.addAttribute("allCourtTypeDtls", litigationService.findCourtType());
		model.addAttribute("allStatusDtls", litigationService.findAllStatus());
		model.addAttribute("allseniorCounselDtls", outsideCounselService.findAllSeniorCounselDtls());
		model.addAttribute("allLawfirmDtls", outsideCounselService.findAll());
		model.addAttribute("allStateDtls", litigationService.findAllStates());
		model.addAttribute("allCityDtls", litigationService.findAllCities());
		model.addAttribute("allCourtForumDtls", litigationService.getAllCourtForum());
		model.addAttribute("allMatterByAgainstDtls", litigationService.findAll());
		int id = (int) session.getAttribute("litigationSessionId");
		model.addAttribute("ltgnDtls", litigationService.getLitigationDetails(id));
		return "updateLitigation";
	}

	@RequestMapping(value = "/updateLitigationDtls", method = RequestMethod.POST)
	public String updateLitigationDtls(Model model, @ModelAttribute LitigationSummaryVO litigationSummaryVO,
			HttpServletRequest request, HttpSession session) throws ParseException {
		int id = (int) session.getAttribute("litigationSessionId");
		UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
		litigationSummaryVO.setLitigationOId(id);
		litigationSummaryVO.setLoginId(userVO.getLoginId());
		System.out.println(request.getParameter("caseRelateFromDate"));
		System.out.println(request.getParameter("caseRelateToDate"));

		litigationService.updateLitigationData(litigationSummaryVO);
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allRegions", zoneService.getAllZones());
		model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		model.addAttribute("allFormats", formatService.getAllFormats());
		model.addAttribute("allCustomerType", litigationService.findAllCustomerType());
		model.addAttribute("allCounterPartyDtls", counterPartyService.findAll());
		model.addAttribute("allCategory", litigationService.findAllCategoryData());
		model.addAttribute("allRiskDtls", litigationService.findAllRiskLevel());
		model.addAttribute("allPossibleClaim", litigationService.findAllClaimPossible());
		model.addAttribute("allUnderActDtls", litigationService.findAllUnderActData());
		model.addAttribute("allCourtTypeDtls", litigationService.findCourtType());
		model.addAttribute("allStatusDtls", litigationService.findAllStatus());
		model.addAttribute("allseniorCounselDtls", outsideCounselService.findAllSeniorCounselDtls());
		model.addAttribute("allLawfirmDtls", outsideCounselService.findAll());
		model.addAttribute("allStateDtls", litigationService.findAllStates());
		model.addAttribute("allCityDtls", litigationService.findAllCities());
		model.addAttribute("allCourtForumDtls", litigationService.getAllCourtForum());
		model.addAttribute("allMatterByAgainstDtls", litigationService.findAll());
		model.addAttribute("message", "Litigation Details Updated Successfully");
		model.addAttribute("ltgnDtls", litigationService.getLitigationDetails(id));
		return "updateLitigation";

	}
	
	@RequestMapping(value="/getWitnessDtls", method=RequestMethod.GET)
	public void getWitnessDtls(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int id = (int) session.getAttribute("litigationSessionId");
		List<ConnectedLitigationVO> witnessDtls = litigationService.getWitnessDtls(id);
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			if (witnessDtls.size() > 0) {
				totalRecord = witnessDtls.size();
				for (ConnectedLitigationVO witnessDtl : witnessDtls) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, witnessDtl.getWitnessId());
					cell = mapper.createArrayNode();
					cell.add(witnessDtl.getWitnessId());
					cell.add(witnessDtl.getWitnessName());
					cell.add(witnessDtl.getLitigationId());
					
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
	
	@RequestMapping(value="/getLawfirmBillingDtls", method=RequestMethod.GET)
	public void getLawfirmBillingDtls(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int id = (int) session.getAttribute("litigationSessionId");
		List<ConnectedLitigationVO> lawfirmDtls = litigationService.getLawfirmBillingDtls(id);
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			if (lawfirmDtls.size() > 0) {
				totalRecord = lawfirmDtls.size();
				for (ConnectedLitigationVO lawfirmBillingDtl : lawfirmDtls) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, lawfirmBillingDtl.getLawfirmBillingId());
					cell = mapper.createArrayNode();
					cell.add(lawfirmBillingDtl.getLawfirmBillingId());
					cell.add(lawfirmBillingDtl.getLitigationId());
					cell.add(lawfirmBillingDtl.getBillingType());
					cell.add(lawfirmBillingDtl.getBillingAmount());
					cell.add(lawfirmBillingDtl.getBillingDate().toString());
					cell.add(lawfirmBillingDtl.getDocumentTitle());
					cell.add(lawfirmBillingDtl.getRemark());
					
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

	@RequestMapping(value = "/getConnectedLitigationDtls", method = RequestMethod.GET)
	public void getConnectedLitigation(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int id = (int) session.getAttribute("litigationSessionId");
		List<ConnectedLitigationVO> connectedLitigationDtls = litigationService.getConnectedLitigationDtls(id);
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			if (connectedLitigationDtls.size() > 0) {
				totalRecord = connectedLitigationDtls.size();
				for (ConnectedLitigationVO connectedLitigationDtl : connectedLitigationDtls) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, connectedLitigationDtl.getLitigationId());
					cell = mapper.createArrayNode();
					cell.add(connectedLitigationDtl.getLitigationId());
					cell.add(connectedLitigationDtl.getComments());

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
	
	
	@RequestMapping(value = "/getDocumentSummary", method = RequestMethod.GET)
	public void getDocumentSummary(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int id = (int) session.getAttribute("litigationSessionId");
		List<ConnectedLitigationVO> documentSummaryDtls = litigationService.getDocumentSummaryDtls(id);
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			if (documentSummaryDtls.size() > 0) {
				totalRecord = documentSummaryDtls.size();
				for (ConnectedLitigationVO docDtls : documentSummaryDtls) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, docDtls.getLitigationOId());
					cell = mapper.createArrayNode();
					cell.add(docDtls.getLitigationOId());
					cell.add(docDtls.getHearingDate());
					cell.add(docDtls.getDocumentTitle());
					cell.add(docDtls.getComments());
					cell.add(docDtls.getFileSize());
					cell.add("Delete");

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
	
	@RequestMapping(value = "/historyReportExportToExcel",method = RequestMethod.GET)
	public ModelAndView exportToStatusReportCasesExcel(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new historyReportView());
		return new ModelAndView(new historyReportView());
	}
	
	
	@GetMapping("/downloadUploadedDoc")
	public ResponseEntity<Resource> downloadFile(HttpServletRequest request) {
		System.out.println(request.getParameter("id"));
		Optional<LitigationDocs> documents = litigationService
				.findDocumentNames(Integer.parseInt(request.getParameter("id")));

		if (documents.isPresent()) {
			System.out.println(documents.get().getDocName());
			// Load file as Resource
			Resource resource = fileStorageService.loadFileAsResource(documents.get().getDocName());

			// Try to determine file's content type
			String contentType = null;
			try {
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
				System.out.println("contentType:: " + contentType);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			// Fallback to the default content type if type could not be determined
			if (contentType == null) {
				contentType = "application/octet-stream";
			}

			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		}
		return null;

	}
	
	
	@GetMapping("/deleteUploadedDocument")
	@ResponseBody
	public String deleteDocument(Model model,HttpServletRequest request, HttpSession session) {
		System.out.println(request.getParameter("id"));
		int isDeleted = litigationService.deleteDocument(Integer.parseInt(request.getParameter("id")));
		
		if(isDeleted > 0) {
			model.addAttribute("message", "Document Deleted Successfully");
			
		}
		return "test";
		
	}
	

	@RequestMapping(value = "/litigationCalendar", method = RequestMethod.GET)
	public String litigationCalendar(Model model) {
		model.addAttribute("litigationSummaryVO", new LitigationSummaryVO());

		model.addAttribute("allZones", zoneService.getAllZones());
		model.addAttribute("allFormats", formatService.getAllFormats());
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

		return "litigationCalendar";
	}
}
