package com.fg.ss.abhiyog.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fg.ss.abhiyog.common.constants.CommonConstants;
import com.fg.ss.abhiyog.common.model.ShowCauseNoticeForms;
import com.fg.ss.abhiyog.common.service.FileStorageService;
import com.fg.ss.abhiyog.common.service.IEntityService;
import com.fg.ss.abhiyog.common.service.IFormatService;
import com.fg.ss.abhiyog.common.service.ILitigationService;
import com.fg.ss.abhiyog.common.service.IShowCauseNoticeService;
import com.fg.ss.abhiyog.common.service.IUnitsSummaryService;
import com.fg.ss.abhiyog.common.service.IZoneService;
import com.fg.ss.abhiyog.common.util.DateUtils;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.ShowCauseNoticeVO;
import com.fg.ss.abhiyog.common.vo.UnitSummaryVO;
import com.fg.ss.abhiyog.common.vo.UserVO;

@Controller
//@RequestMapping("/masters/showCauseNotice")
public class ShowCauseNoticeRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShowCauseNoticeRestController.class);
	@Autowired
	private IShowCauseNoticeService showCauseNoticeService;

	@Autowired
	private IZoneService zoneService;

	@Autowired
	private IEntityService entityService;

	@Autowired
	private ILitigationService litigationService;

	@Autowired
	private IFormatService formatService;

	@Autowired
	private IUnitsSummaryService unitsSummaryService;

	@Autowired
	private FileStorageService fileStorageService;

	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	@RequestMapping(value = "/showNoticeSummary", method = RequestMethod.GET)
	public String showUnitLocationSummary(Model model) {
		model.addAttribute("showCauseNoticeVO", new ShowCauseNoticeVO());
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allRegions", zoneService.getAllZones());
		model.addAttribute("allFormats", formatService.getAllFormats());
		model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		return "showCauseNoticeSummary";
	}

	@RequestMapping(value = "/getShowCauseNoticeDtls", method = RequestMethod.GET)
	public void getShowCauseNoticeSummary(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		List<ShowCauseNoticeVO> allNoticeDtls = showCauseNoticeService.getAllNoticeDtls();
		fillGridDetail(allNoticeDtls, request, response, session);
	}

	private void fillGridDetail(List<ShowCauseNoticeVO> allNoticeDtls, HttpServletRequest request,
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
			if (allNoticeDtls.size() > 0) {
				totalRecord = allNoticeDtls.size();
				for (ShowCauseNoticeVO noticeSummary : allNoticeDtls) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, noticeSummary.getShowCauseNoticeId());
					cell = mapper.createArrayNode();
					cell.add(noticeSummary.getShowCauseNoticeId());
					cell.add(noticeSummary.getUnitName());
					cell.add(noticeSummary.getOwner());
					cell.add(noticeSummary.getReceivedFrom());
					cell.add(noticeSummary.getReceivedDate().toString());
					cell.add(noticeSummary.getNoticeReplyDeadline().toString());
					cell.add(noticeSummary.getComments());
					cell.add(noticeSummary.getSubject());
					cell.add(noticeSummary.getReferenceNo());
					System.out.println(noticeSummary.getDocument().size());
					StringBuilder documentNames = new StringBuilder();
					for (String documentName : noticeSummary.getDocument()) {
						documentNames.append(documentName + " ");
					}
					cell.add(documentNames.toString());
					cell.add(noticeSummary.getLoginId());
					cell.add(noticeSummary.getStatus());
					cell.add(noticeSummary.getZoneName());
					cell.add(noticeSummary.getFormatName());
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

	@RequestMapping("/getShowCauseNoticeSummaryBy")
	public void showCauseNoticeSummaryData(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		LOGGER.info("inside showCauseNoticeSummaryData");
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
			String unitName = "";
			String status = "";
			String format = "";

			if (request.getParameter("entity") != "ALL" && request.getParameter("entity") != null
					&& request.getParameter("entity") != "") {
				entity = request.getParameter("entity");
			}
			if (request.getParameter("zone") != "ALL" && request.getParameter("zone") != null
					&& request.getParameter("zone") != "") {
				zone = request.getParameter("zone");
			}
			if (request.getParameter("format") != "ALL" && request.getParameter("format") != null
					&& request.getParameter("format") != "") {
				format = request.getParameter("format");
			}
			if (request.getParameter("unit") != "ALL" && request.getParameter("unit") != null
					&& request.getParameter("unit") != "") {
				unitName = request.getParameter("unit");
			}
			if (request.getParameter("status") != "ALL" && request.getParameter("status") != null
					&& request.getParameter("status") != "") {
				status = request.getParameter("status");
			}

			List<ShowCauseNoticeVO> allNoticeDtls = showCauseNoticeService.findShowCauseSummaryData(entity, zone,
					unitName, status, format);
			if (allNoticeDtls.size() > 0) {
				totalRecord = allNoticeDtls.size();
				for (ShowCauseNoticeVO noticeSummary : allNoticeDtls) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, noticeSummary.getShowCauseNoticeId());
					cell = mapper.createArrayNode();
					cell.add(noticeSummary.getShowCauseNoticeId());
					cell.add(noticeSummary.getUnitName());
					cell.add(noticeSummary.getOwner());
					cell.add(noticeSummary.getReceivedFrom());
					cell.add(noticeSummary.getReceivedDate().toString());
					cell.add(noticeSummary.getNoticeReplyDeadline().toString());
					cell.add(noticeSummary.getComments());
					cell.add(noticeSummary.getSubject());
					cell.add(noticeSummary.getReferenceNo());
					System.out.println(noticeSummary.getDocument().size());
					StringBuilder documentNames = new StringBuilder();
					for (String documentName : noticeSummary.getDocument()) {
						documentNames.append(documentName + " ");
					}
					cell.add(documentNames.toString());
					cell.add(noticeSummary.getLoginId());
					cell.add(noticeSummary.getStatus());
					cell.add(noticeSummary.getZoneName());
					cell.add(noticeSummary.getFormatName());
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

	/*
	 * @GetMapping("/getShowCauseNoticeDtls") public ResponseEntity<BaseResponseVO>
	 * getShowCauseNoticeSummary(){ List<ShowCauseNoticeVO> allNoticeDtls =
	 * showCauseNoticeService.getAllNoticeDtls();
	 * baseResponseVO.setResponseCode(HttpStatus.OK.value());
	 * baseResponseVO.setResponseMessage("SUCCESS");
	 * baseResponseVO.setData(allNoticeDtls); return
	 * ResponseEntity.ok().body(baseResponseVO);
	 * 
	 * }
	 */

	@RequestMapping(value = "/newNotice", method = RequestMethod.GET)
	public String newNotice(Model model) {
		System.out.println("Inside newNotice");
		model.addAttribute("showCauseNoticeVO", new ShowCauseNoticeVO());
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allRegions", zoneService.getAllZones());
		model.addAttribute("allFormats", formatService.getAllFormats());
		model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());

		return "newNotice";
	}

	// @PostMapping("/addShowCauseNotice")
	@RequestMapping(value = "/addShowCauseNotice", method = RequestMethod.POST)
//	, consumes = { "multipart/form-data" }
	public String addShowCauseNoticeData(@RequestParam("uploadFile") MultipartFile multiPartFile, Model model,
			HttpSession session, HttpServletRequest request) throws ParseException, IOException {
		ShowCauseNoticeVO showCauseNoticeVO = new ShowCauseNoticeVO();
		UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
		System.out.println("inside addShowCauseNoticeData");
		System.out.println("Issue Date::  " + request.getParameter("issueDate"));

		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		showCauseNoticeVO.setLoginId(userVO.getLoginId());
		showCauseNoticeVO.setEntityName(request.getParameter("entityName"));
		showCauseNoticeVO.setFormatName(request.getParameter("formatName"));
		showCauseNoticeVO.setZoneName(request.getParameter("zoneName"));
		showCauseNoticeVO.setUnitName(request.getParameter("unitName"));
		showCauseNoticeVO.setNoticeCategoryName(request.getParameter("noticeCategoryName"));
		System.out.println(showCauseNoticeVO.getNoticeCategoryName());
		showCauseNoticeVO.setNoticeClassification(request.getParameter("noticeClassification"));
		showCauseNoticeVO.setIssueDate(LocalDate.parse(request.getParameter("issueDate"), dateTimeFormatter));

		showCauseNoticeVO.setReceivedDate(LocalDate.parse(request.getParameter("receivedDate"),dateTimeFormatter));
		showCauseNoticeVO
				.setNoticeReplyDeadline(LocalDate.parse(request.getParameter("noticeReplyDeadline"), dateTimeFormatter));
		showCauseNoticeVO.setSubject(request.getParameter("subject"));
		showCauseNoticeVO.setReferenceNo(request.getParameter("referenceNo"));
		showCauseNoticeVO.setComments(request.getParameter("comments"));
		showCauseNoticeVO.setReceivedFrom(request.getParameter("receivedFrom"));
		showCauseNoticeVO.setAdvocateName(request.getParameter("advocateName"));
		showCauseNoticeVO.setAdvocateAddress(request.getParameter("advocateAddress"));
		showCauseNoticeVO.setAllegation(request.getParameter("allegation"));
		showCauseNoticeVO.setClaims(Integer.parseInt(request.getParameter("claims")));
		showCauseNoticeVO.setDealerName(request.getParameter("dealerName"));
		showCauseNoticeVO.setDealerAddress(request.getParameter("dealerAddress"));
		showCauseNoticeVO.setOtherParties(request.getParameter("otherParties"));
		showCauseNoticeVO.setPartyNo(request.getParameter("partyNo"));
		showCauseNoticeVO.setComplaintName(request.getParameter("complaintName"));
		showCauseNoticeVO.setComplaintAddress(request.getParameter("complaintAddress"));
		showCauseNoticeVO.setVehicleModelNumber(request.getParameter("vehicleModelNumber"));
		showCauseNoticeVO.setNatureOfIpInfringement(request.getParameter("natureOfIpInfringement"));
		showCauseNoticeVO.setNoticeSentAddress(request.getParameter("noticeSentAddress"));
		showCauseNoticeVO.setNoticeSentTo(request.getParameter("noticeSentTo"));
		showCauseNoticeVO.setApplicableSection(request.getParameter("applicableSection"));
		showCauseNoticeVO.setActionTaken(request.getParameter("actionTaken"));

		System.out.println(showCauseNoticeVO.getIssueDate());
		System.out.println(showCauseNoticeVO.getNoticeReplyDeadline());
		showCauseNoticeService.saveNoticeData(showCauseNoticeVO, multiPartFile);
		model.addAttribute("showCauseNoticeVO", new ShowCauseNoticeVO());
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allRegions", zoneService.getAllZones());
		model.addAttribute("allFormats", formatService.getAllFormats());
		model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		model.addAttribute("message", "NOTICE RECORD ADDED SUCCESSFULLY");
		return "newNotice";
	}

	@RequestMapping(value = "/updateNotice", method = RequestMethod.GET)
	public String updateNotice(Model model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside updateNotice");
		System.out.println("Starting******");

		System.out.println("ID::::**** " + request.getParameter("id"));
//		System.out.println("reqId:::: ****** "+request.getParameter("reqId"));
		ShowCauseNoticeVO showCauseNoticeDtls = new ShowCauseNoticeVO();
//		if(request.getParameter("id") != null) {
		HttpSession session = request.getSession();
		int noticeId = Integer.parseInt(request.getParameter("id"));
		session.setAttribute("showCauseNoticeId", noticeId);
		System.out.println("Session Value::  " + session.getAttribute("showCauseNoticeId"));
		showCauseNoticeDtls = showCauseNoticeService.findNoticeDtls(Integer.parseInt(request.getParameter("id")));
		System.out.println("noticeId" + noticeId);
		/*
		 * }else if(request.getParameter("reqId") != null) { showCauseNoticeDtls =
		 * showCauseNoticeService
		 * .findNoticeDtls(Integer.parseInt(request.getParameter("reqId"))); }
		 */
		model.addAttribute("showCauseNoticeVO", showCauseNoticeDtls);
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allRegions", zoneService.getAllZones());
		model.addAttribute("allFormats", formatService.getAllFormats());
		model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		model.addAttribute("noticeId", noticeId);

		System.out.println("Ending******");
		return "updateNotice";

	}

	@RequestMapping(value = "/getNoticeDocsDtls", method = RequestMethod.GET)
	public void fillNoticeDocsDtls(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		System.out.println("Inside GetNoticeDocsDtls***********");
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			System.out.println("Session Value in getNoticeDocsDtls *** ::  " + session.getAttribute("showCauseNoticeId"));
			if (session.getAttribute("showCauseNoticeId") != null) {
				int showCauseNoticeId = (int) session.getAttribute("showCauseNoticeId");
				List<ShowCauseNoticeVO> showCauseNoticeDtls = showCauseNoticeService
						.findNoticeFormDtls(showCauseNoticeId);
				System.out.println(showCauseNoticeDtls.size());
				if (showCauseNoticeDtls.size() > 0) {
					if(showCauseNoticeDtls.get(0).getShowCauseNoticeFormId() != 0) {
						totalRecord = showCauseNoticeDtls.size();
						for (ShowCauseNoticeVO noticeDtls : showCauseNoticeDtls) {
							cellObj = mapper.createObjectNode();
							cellObj.put(CommonConstants.ID, noticeDtls.getShowCauseNoticeFormId());
							cell = mapper.createArrayNode();
							cell.add(noticeDtls.getShowCauseNoticeFormId());
							cell.add(noticeDtls.getDocName());
							cell.add(noticeDtls.getShowCauseNoticeId());
							cell.add(noticeDtls.getCommentsDoc());
							cell.add(noticeDtls.getFileSize());
							cell.add("Delete");
//							cellObj.put(CommonConstants.CELL, cell);
							cellObj.set(CommonConstants.CELL, cell);
							cellArray.add(cellObj);
						}
					}
					
				}
			}
			responseData.put(CommonConstants.PAGE, CommonConstants.PAGE_NO);
			responseData.put(CommonConstants.RECORDS, totalRecord);
//			responseData.put(CommonConstants.ROWS, cellArray);
			responseData.set(CommonConstants.ROWS, cellArray);
			out.println(responseData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@PutMapping("/updateShowCauseNotice")
	@RequestMapping(value="/updateShowCauseNotice", method=RequestMethod.POST)
	public String updateShowCauseNotice(Model model, HttpServletRequest request, HttpSession session) throws ParseException {
		int showCauseNoticeId = (int) session.getAttribute("showCauseNoticeId");
		LOGGER.info("ShowCauseNoticeID:: "  +showCauseNoticeId);
		UserVO uservo=  (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
		ShowCauseNoticeVO showCauseNoticeVO = new ShowCauseNoticeVO();
		showCauseNoticeVO.setEntityName(request.getParameter("entityName"));
		showCauseNoticeVO.setFormatName(request.getParameter("formatName"));
		showCauseNoticeVO.setZoneName(request.getParameter("zoneName"));
		showCauseNoticeVO.setUnitName(request.getParameter("unitName"));
		showCauseNoticeVO.setReceivedFrom(request.getParameter("receivedFrom"));
		showCauseNoticeVO.setSubject(request.getParameter("subject"));
		System.out.println("IssueDate"+request.getParameter("issueDate"));
//		showCauseNoticeVO.setIssueDate(DateUtils.getDBFormatDate(request.getParameter("issueDate")));
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		showCauseNoticeVO.setIssueDate(LocalDate.parse(request.getParameter("issueDt"), dateTimeFormatter));
		System.out.println("issue date from request:: " +showCauseNoticeVO.getIssueDate());
//		showCauseNoticeVO.setReceivedDate(DateUtils.getDBFormatDate(request.getParameter("receivedDate")));
		showCauseNoticeVO.setReceivedDate(LocalDate.parse(request.getParameter("receivedDt"), dateTimeFormatter));
//		showCauseNoticeVO.setNoticeReplyDeadline(DateUtils.getDBFormatDate(request.getParameter("noticeReplyDeadline")));
		showCauseNoticeVO.setNoticeReplyDeadline(LocalDate.parse(request.getParameter("noticeRplyDeadline"), dateTimeFormatter));
		LOGGER.info("IssueDate:: " +showCauseNoticeVO.getIssueDate());
		LOGGER.info("ReceivedDate:: " +showCauseNoticeVO.getReceivedDate());
		LOGGER.info("NoticeReplyDeadline::: "  +showCauseNoticeVO.getNoticeReplyDeadline());
		showCauseNoticeVO.setReferenceNo(request.getParameter("referenceNo"));
		showCauseNoticeVO.setComments(request.getParameter("comments"));
		showCauseNoticeVO.setActionTaken(request.getParameter("actionTaken"));
		showCauseNoticeVO.setStatus(request.getParameter("status"));
		showCauseNoticeVO.setLoginId(uservo.getLoginId());
		showCauseNoticeVO.setShowCauseNoticeId(showCauseNoticeId);
		
		boolean isUpdated = showCauseNoticeService.updateNoticeData(showCauseNoticeVO);
		if(isUpdated) {
			model.addAttribute("message", "Notice Details Updated Successfully");
			ShowCauseNoticeVO showCauseNoticeDtls = showCauseNoticeService.findNoticeDtls(showCauseNoticeId);
			model.addAttribute("showCauseNoticeVO", showCauseNoticeDtls);
			model.addAttribute("allEntities", entityService.getAllEntities());
			model.addAttribute("allRegions", zoneService.getAllZones());
			model.addAttribute("allFormats", formatService.getAllFormats());
			model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		}else {
			model.addAttribute("message", "Unable to Update NoticeDetails");
			model.addAttribute("showCauseNoticeVO", new ShowCauseNoticeVO());
			model.addAttribute("allEntities", entityService.getAllEntities());
			model.addAttribute("allRegions", zoneService.getAllZones());
			model.addAttribute("allFormats", formatService.getAllFormats());
			model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		}
		return "updateNotice";
	}

	@RequestMapping(value = "/addDocument", method = RequestMethod.POST)
	public String addDocument(@RequestParam("uploadFile") MultipartFile multiPartFile,
			HttpServletRequest request, HttpSession session, Model model) {
		int showCauseNoticeId = (int) session.getAttribute("showCauseNoticeId");
		UserVO uservo=  (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
		ShowCauseNoticeVO showCauseNoticeVO = new ShowCauseNoticeVO();
		showCauseNoticeVO.setShowCauseNoticeId(showCauseNoticeId);
		showCauseNoticeVO.setCommentsDoc(request.getParameter("commentsDoc"));
		showCauseNoticeVO.setLoginId(uservo.getLoginId());
		boolean flag = showCauseNoticeService.uploadFileandData(showCauseNoticeVO, multiPartFile);
		String message = "";
		if(flag) {
		message=	"Document Added Successfully";
		}else {
			message="Please Upload File Otherthan .exe or .war Extension";
		}
		ShowCauseNoticeVO showCauseNoticeDtls = showCauseNoticeService.findNoticeDtls(showCauseNoticeId);
		model.addAttribute("showCauseNoticeVO", showCauseNoticeDtls);
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allRegions", zoneService.getAllZones());
		model.addAttribute("allFormats", formatService.getAllFormats());
		model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		model.addAttribute("message", message);
		return "updateNotice";

	}

	@GetMapping("/downloadDocument")
	public ResponseEntity<Resource> downloadFile(HttpServletRequest request) {
		System.out.println(request.getParameter("id"));
		List<ShowCauseNoticeForms> documents = showCauseNoticeService
				.findDocumentNames(Integer.parseInt(request.getParameter("id")));
		for (ShowCauseNoticeForms allDocs : documents) {
			System.out.println(allDocs.getInputDocName());
			// Load file as Resource
			Resource resource = fileStorageService.loadFileAsResource(allDocs.getInputDocName());

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
	
	@GetMapping("/deleteDocument")
	public String deleteDocument(Model model,HttpServletRequest request, HttpSession session) {
		System.out.println(request.getParameter("id"));
		int isDeleted = showCauseNoticeService.deleteDocument(Integer.parseInt(request.getParameter("id")));
		
		if(isDeleted > 0) {
			model.addAttribute("message", "Document Deleted Successfully");
			int showCauseNoticeId = (int) session.getAttribute("showCauseNoticeId");
			System.out.println("Session ShowCauseNoticeId " +showCauseNoticeId);
			//find notice dtls by showCauseNotice Id from showCauseNotice Table only 
			ShowCauseNoticeVO showCauseNoticeDtls = showCauseNoticeService.findNoticeDtls(showCauseNoticeId);
			model.addAttribute("showCauseNoticeVO", showCauseNoticeDtls);
			model.addAttribute("allEntities", entityService.getAllEntities());
			model.addAttribute("allRegions", zoneService.getAllZones());
			model.addAttribute("allFormats", formatService.getAllFormats());
			model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		}
		return "updateNotice";
		
	}
	
	@RequestMapping(value="/getUnitLocationByZone", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String getUnitLocationByZone(@RequestParam String zoneNameVal, @RequestParam String entityNameVal){
		List<ShowCauseNoticeVO> unitList = showCauseNoticeService.getUnitDtlsByZone(zoneNameVal,entityNameVal);
		ObjectMapper mapper = new ObjectMapper();
		String newJsonData = "";
		try {
			newJsonData = mapper.writeValueAsString(unitList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newJsonData;
		
		
	}
}
