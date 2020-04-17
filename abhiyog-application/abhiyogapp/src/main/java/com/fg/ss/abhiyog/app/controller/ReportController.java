package com.fg.ss.abhiyog.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fg.ss.abhiyog.common.constants.CommonConstants;
import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.service.FileDownloadService;
import com.fg.ss.abhiyog.common.service.IEntityService;
import com.fg.ss.abhiyog.common.service.IExcelGenerateService;
import com.fg.ss.abhiyog.common.service.IFormatService;
import com.fg.ss.abhiyog.common.service.ILitigationService;
import com.fg.ss.abhiyog.common.service.IUnitsSummaryService;
import com.fg.ss.abhiyog.common.service.IWordGenerateService;
import com.fg.ss.abhiyog.common.service.IZoneService;
import com.fg.ss.abhiyog.common.service.MetricsReportStatisticsService;
import com.fg.ss.abhiyog.common.service.PDFGenerateService;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.LitigationSummaryVO;
import com.fg.ss.abhiyog.common.vo.UserVO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

@Controller
//@RequestMapping("/masters/Report")
public class ReportController {
	
	@Autowired
	IExcelGenerateService excelGenerateService;
	
	@Autowired
	IWordGenerateService wordGenerateService;
	
	@Autowired
	ILitigationService litigationService;
	
	@Autowired
	private IEntityService entityService;
	
	@Autowired
	private IZoneService zoneService;

	@Autowired
	private IFormatService formatService;
	
	@Autowired
	private IUnitsSummaryService unitsSummaryService;
	
	@Autowired
	FileDownloadService downloadService;
	
	@Autowired
	PDFGenerateService pdfGenerateService;
	
	@Autowired
	MetricsReportStatisticsService metricsReportStatisticsService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	
	@RequestMapping(value = "/causeListReport", method = RequestMethod.GET)
	public ModelAndView causeListReport(Model model,HttpSession session) {
//		UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("causeListReport");
		model.addAttribute("litigationSummaryVO", new LitigationSummaryVO());
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		model.addAttribute("allMatterByAgainstDtls", litigationService.findAll());
		model.addAttribute("allRiskDtls", litigationService.findAllRiskLevel());
		model.addAttribute("allStatusDtls", litigationService.findAllStatus());
//		modelAndView.addObject("userVO", userVO);
		return modelAndView;
	}
	
	
	@RequestMapping("/causeListReportData")
	private void causeListReportData( HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		LOGGER.info("inside causeListReportData method");
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			String frmDate="";
			String toDate="";
			String status = "";
			String litigationByAgainst = "";
			String risk = "";
			String entityName = "";
			String unitLocation ="";
			String matterByAgainst = "";
//			UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
			System.out.println(request.getParameter("fromDate"));
			System.out.println(request.getParameter("toDate"));
			System.out.println(request.getParameter("entityName"));
			System.out.println(request.getParameter("unitName"));
			System.out.println(request.getParameter("matterBy"));
			System.out.println(request.getParameter("litigationBy"));
			System.out.println(request.getParameter("risk"));
			if(request.getParameter("fromDate")!=null && request.getParameter("fromDate")!="")
				frmDate=request.getParameter("fromDate");
			if(request.getParameter("toDate")!=null && request.getParameter("toDate")!="")
				toDate = request.getParameter("toDate");
			if (request.getParameter("entityName") != "ALL" && request.getParameter("entityName") != null) {
				entityName = request.getParameter("entityName");
			}
			System.out.println("FromDAte"+request.getParameter("fromDate"));
			if(request.getParameter("unitName") != "ALL" && request.getParameter("unitName") != null) {
				unitLocation = request.getParameter("unitName");
			}
			if(request.getParameter("matterBy") != "ALL" && request.getParameter("matterBy") != null) {
				matterByAgainst = request.getParameter("matterBy");
			}
			if(request.getParameter("litigationBy") != "ALL" && request.getParameter("litigationBy") != null) {
				litigationByAgainst = request.getParameter("litigationBy");
			}
			if(request.getParameter("risk") != "ALL" && request.getParameter("risk") != null) {
				risk = request.getParameter("risk");
			}
			if(request.getParameter("status") != "ALL" && request.getParameter("status") != null) {
				status = request.getParameter("status");
			}
			System.out.println("FromDate::"+frmDate);
			System.out.println("ToDate::"+toDate);
			System.out.println("EntityName::"+entityName);
			
			List<LitigationSummaryVO> causeListReportData = null;
			if(frmDate != null && toDate != null && frmDate != "" && toDate != "") {
				causeListReportData = litigationService.getCauseListReportData(frmDate,toDate, entityName, unitLocation, matterByAgainst, litigationByAgainst, risk, status);
				cellArray = mapper.createArrayNode();
				if (causeListReportData.size() > 0) {
					totalRecord = causeListReportData.size();
					for (LitigationSummaryVO litigationSummary : causeListReportData) {
						cellObj = mapper.createObjectNode();
						cellObj.put(CommonConstants.ID, litigationSummary.getLitigationOId());
						cell = mapper.createArrayNode();
						cell.add(litigationSummary.getLitigationOId());
						cell.add(litigationSummary.getLitigationId());
						cell.add(litigationSummary.getEntityName()  + "-" + litigationSummary.getUnitName() +"-"+ litigationSummary.getFunction() );
						cell.add(litigationSummary.getEntityName()  +  litigationSummary.getCounterPartyName() );
						cell.add(litigationSummary.getCaseNumber());
						cell.add(litigationSummary.getFileNo());
						cell.add(litigationSummary.getSubject());
						cell.add(litigationSummary.getUnderSection());
						cell.add(litigationSummary.getCourtForum());
						cell.add(litigationSummary.getHearingDate().toString());
						cell.add(litigationSummary.getStage());
						cell.add(litigationSummary.getStatus());
//						cellObj.put(CommonConstants.CELL, cell);
						cellObj.set(CommonConstants.CELL, cell);
						cellArray.add(cellObj);

					}
				}
			}else {
				System.out.println("FromDate and ToDate null");
			}
			
			
			responseData.put(CommonConstants.PAGE, CommonConstants.PAGE_NO);
			responseData.put(CommonConstants.RECORDS, totalRecord);
//			responseData.put(CommonConstants.ROWS, cellArray);
			responseData.set(CommonConstants.ROWS, cellArray);
			out.println(responseData);
			session.setAttribute(CommonConstants.SESSION_REPORT_DATA, causeListReportData);
		} catch (IOException e) {
			LOGGER.error("Exception generated in FillingGrid Method:: " + e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/statusReportCasesData")
	private void statusReportCasesData( HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		LOGGER.info("inside causeListReportData method");
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			String frmDate="";
			String toDate="";
			String status = "";
			String litigationByAgainst = "";
			String risk = "";
			String entityName = "";
			String unitLocation ="";
			String matterByAgainst = "";
//			UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
			System.out.println(request.getParameter("fromDate"));
			System.out.println(request.getParameter("toDate"));
			System.out.println(request.getParameter("entityName"));
			System.out.println(request.getParameter("unitName"));
			System.out.println(request.getParameter("matterBy"));
			System.out.println(request.getParameter("litigationBy"));
			System.out.println(request.getParameter("risk"));
			if(request.getParameter("fromDate")!=null && request.getParameter("fromDate")!="")
				frmDate=request.getParameter("fromDate");
			if(request.getParameter("toDate")!=null && request.getParameter("toDate")!="")
				toDate = request.getParameter("toDate");
			if (request.getParameter("entityName") != "ALL" && request.getParameter("entityName") != null) {
				entityName = request.getParameter("entityName");
			}
			System.out.println("FromDAte"+request.getParameter("fromDate"));
			if(request.getParameter("unitName") != "ALL" && request.getParameter("unitName") != null) {
				unitLocation = request.getParameter("unitName");
			}
			if(request.getParameter("matterBy") != "ALL" && request.getParameter("matterBy") != null) {
				matterByAgainst = request.getParameter("matterBy");
			}
			if(request.getParameter("litigationBy") != "ALL" && request.getParameter("litigationBy") != null) {
				litigationByAgainst = request.getParameter("litigationBy");
			}
			if(request.getParameter("risk") != "ALL" && request.getParameter("risk") != null) {
				risk = request.getParameter("risk");
			}
			if(request.getParameter("status") != "ALL" && request.getParameter("status") != null) {
				status = request.getParameter("status");
			}
			System.out.println("FromDate::"+frmDate);
			System.out.println("ToDate::"+toDate);
			System.out.println("EntityName::"+entityName);
			
			List<Litigation> causeListReportData = null;
			if(frmDate != null && toDate != null && frmDate != "" && toDate != "") {
				causeListReportData = litigationService.getStatusReportCasesData(frmDate,toDate, entityName, unitLocation, matterByAgainst, litigationByAgainst, risk, status);
				cellArray = mapper.createArrayNode();
				if (causeListReportData.size() > 0) {
					totalRecord = causeListReportData.size();
					for (Litigation statusReportCases : causeListReportData) {
						cellObj = mapper.createObjectNode();
						cellObj.put(CommonConstants.ID, statusReportCases.getLitigationOid());
						cell = mapper.createArrayNode();
						cell.add(statusReportCases.getLitigationOid());
						cell.add(statusReportCases.getLitigationId());
						cell.add(statusReportCases.getUnits().getEntitySummary().getEntityName()  +  statusReportCases.getUnits().getUnitName() );
						cell.add(statusReportCases.getUnits().getEntitySummary().getEntityName()  +  statusReportCases.getCounterPartyDtls().getCustomerName() );
						cell.add(statusReportCases.getCaseNumber());
						cell.add(statusReportCases.getFileNo());
						cell.add(statusReportCases.getFactOfLitigationMatter());
						cell.add(statusReportCases.getUnderSection());
						cell.add(statusReportCases.getStatus().getStatus());
						cell.add(statusReportCases.getLtgnLitigationLog().get(0).getDateOfHearing().toString());
						cell.add(statusReportCases.getLtgnLitigationLog().get(0).getStage());
						cell.add(statusReportCases.getLtgnLitigationLog().get(0).getHearingEvent());
//						cellObj.put(CommonConstants.CELL, cell);
						cellObj.set(CommonConstants.CELL, cell);
						cellArray.add(cellObj);

					}
				}
			}else {
				System.out.println("FromDate and ToDate null");
			}
			
			
			responseData.put(CommonConstants.PAGE, CommonConstants.PAGE_NO);
			responseData.put(CommonConstants.RECORDS, totalRecord);
//			responseData.put(CommonConstants.ROWS, cellArray);
			responseData.set(CommonConstants.ROWS, cellArray);
			out.println(responseData);
			session.setAttribute(CommonConstants.SESSION_REPORT_DATA, causeListReportData);
		} catch (IOException e) {
			LOGGER.error("Exception generated in FillingGrid Method:: " + e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping(value = "/exportToStatusReportCasesExcel",method = RequestMethod.GET)
	public ModelAndView exportToStatusReportCasesExcel(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new statusExcelReportCasesView());
		return new ModelAndView(new statusExcelReportCasesView());
	}
	
	
	@RequestMapping(value = "/exportToCauseListReportExcel",method = RequestMethod.GET)
	public ModelAndView exportToCauseListReportExcel(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new causeListReportView());
		return new ModelAndView(new causeListReportView());
	}
	
	
	@RequestMapping(value = "/statusReportCases", method = RequestMethod.GET)
	public ModelAndView statusReportCases(Model model,HttpSession session) {
//		UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("statusReportCases");
		model.addAttribute("litigationSummaryVO", new LitigationSummaryVO());
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		model.addAttribute("allMatterByAgainstDtls", litigationService.findAll());
		model.addAttribute("allRiskDtls", litigationService.findAllRiskLevel());
		model.addAttribute("allStatusDtls", litigationService.findAllStatus());
//		modelAndView.addObject("userVO", userVO);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/metricsReportCases", method = RequestMethod.GET)
	public ModelAndView metricsReportCases(Model model,HttpSession session) {
//		UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("metricsReport");
		model.addAttribute("litigationSummaryVO", new LitigationSummaryVO());
		model.addAttribute("allEntities", entityService.getAllEntities());
		model.addAttribute("allUnitLocationDtls", unitsSummaryService.getUnitSummary());
		model.addAttribute("allMatterByAgainstDtls", litigationService.findAll());
		model.addAttribute("allRiskDtls", litigationService.findAllRiskLevel());
		model.addAttribute("allStatusDtls", litigationService.findAllStatus());
		model.addAttribute("allZones", zoneService.getAllZones());
		model.addAttribute("allFormats", formatService.getAllFormats());
		model.addAttribute("zoneWiseStatistics", metricsReportStatisticsService.getZoneWiseStatistics());
		model.addAttribute("entityWiseStatistics", metricsReportStatisticsService.getEntityWiseStatistics());
		model.addAttribute("caseTypeWiseStatistics", metricsReportStatisticsService.getCaseTypeWiseStatistics());
		model.addAttribute("litigationByAgainst", metricsReportStatisticsService.getLitigationByStatistics());
//		modelAndView.addObject("userVO", userVO);
		return modelAndView;
	}
	
	@RequestMapping("/metricsReportData")
	private void metricsReportData( HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		LOGGER.info("inside metricsReportData method");
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			String frmDate="";
			String toDate="";
			String status = "";
			String litigationByAgainst = "";
			String risk = "";
			String entityName = "";
			String unitLocation ="";
			String matterByAgainst = "";
			String format = "";
			String zone = "";
//			UserVO userVO = (UserVO) session.getAttribute(CommonConstants.SESSION_USER_VO);
			System.out.println(request.getParameter("fromDate"));
			System.out.println(request.getParameter("toDate"));
			System.out.println(request.getParameter("entityName"));
			System.out.println(request.getParameter("unitName"));
			System.out.println(request.getParameter("matterBy"));
			System.out.println(request.getParameter("litigationBy"));
			System.out.println(request.getParameter("risk"));
			if(request.getParameter("fromDate")!=null && request.getParameter("fromDate")!="")
				frmDate=request.getParameter("fromDate");
			if(request.getParameter("toDate")!=null && request.getParameter("toDate")!="")
				toDate = request.getParameter("toDate");
			if (request.getParameter("entityName") != "ALL" && request.getParameter("entityName") != null) {
				entityName = request.getParameter("entityName");
			}
			System.out.println("FromDAte"+request.getParameter("fromDate"));
			if(request.getParameter("unitName") != "ALL" && request.getParameter("unitName") != null) {
				unitLocation = request.getParameter("unitName");
			}
			if(request.getParameter("matterBy") != "ALL" && request.getParameter("matterBy") != null) {
				matterByAgainst = request.getParameter("matterBy");
			}
			if(request.getParameter("litigationBy") != "ALL" && request.getParameter("litigationBy") != null) {
				litigationByAgainst = request.getParameter("litigationBy");
			}
			if(request.getParameter("risk") != "ALL" && request.getParameter("risk") != null) {
				risk = request.getParameter("risk");
			}
			if(request.getParameter("status") != "ALL" && request.getParameter("status") != null) {
				status = request.getParameter("status");
			}
			if(request.getParameter("format") != "ALL" && request.getParameter("format") != null) {
				format = request.getParameter("format");
			}
			if(request.getParameter("zoneName") != "ALL" && request.getParameter("zoneName") != null) {
				zone = request.getParameter("zoneName");
			}
			/*System.out.println("FromDate::"+frmDate);
			System.out.println("ToDate::"+toDate);
			System.out.println("EntityName::"+entityName);*/
			
			List<Litigation> metricsReportData = null;
			if(frmDate != null && toDate != null && frmDate != "" && toDate != "") {
				metricsReportData = litigationService.getMetricsReportData(frmDate,toDate, entityName, unitLocation, matterByAgainst, litigationByAgainst, risk, status, format, zone);
				cellArray = mapper.createArrayNode();
				if (metricsReportData.size() > 0) {
					totalRecord = metricsReportData.size();
					for (Litigation metricsReportCases : metricsReportData) {
						cellObj = mapper.createObjectNode();
						cellObj.put(CommonConstants.ID, metricsReportCases.getLitigationOid());
						cell = mapper.createArrayNode();
						cell.add(metricsReportCases.getLitigationOid());
						cell.add(metricsReportCases.getLitigationId());
						cell.add(metricsReportCases.getUnits().getRegions().getZoneName());
						cell.add(metricsReportCases.getUnits().getEntitySummary().getEntityName());
						cell.add(metricsReportCases.getUnits().getUnitName());
						cell.add(metricsReportCases.getFormat().getFormat());
						cell.add(metricsReportCases.getLitigationMatterByAgainst().get(0).getLtgnRepresentativeMaster().getRepresentativeName());
						cell.add(metricsReportCases.getCounterPartyDtls().getCustomerName());
						cell.add("Future Retail Limited");
						cell.add(metricsReportCases.getUnderAct().getUnderAct());
						cell.add(metricsReportCases.getUnderSection());
						cell.add(metricsReportCases.getOtherUnderAct());
						cell.add(metricsReportCases.getCounterPartyDtls().getCustomerName() +" Future Retail Limited");
						cell.add(metricsReportCases.getLtgnCaseType().getCaseType());
						cell.add(metricsReportCases.getCaseNumber());
						cell.add(metricsReportCases.getFileNo());
						cell.add(metricsReportCases.getCourtCity().getCourtCity());
						cell.add(metricsReportCases.getCaseFileOnDate().toString());
						cell.add(metricsReportCases.getCourtCity().getCity().getCityName());
						cell.add(metricsReportCases.getCourtCity().getCity().getState().getStateName());
						cell.add(metricsReportCases.getFactOfLitigationMatter());
						cell.add(metricsReportCases.getLtgnLitigationLog().get(0).getDateOfHearing().toString());
						if(metricsReportCases.getLtgnLitigationLog().get(0).getStageMaster().getStage() == null || metricsReportCases.getLtgnLitigationLog().get(0).getStageMaster().getStage() == "") {
							cell.add("NA");
						}else {
							cell.add(metricsReportCases.getLtgnLitigationLog().get(0).getStageMaster().getStage());
						}
						
						cell.add(metricsReportCases.getClaim().getClaim());
						cell.add(metricsReportCases.getExactClaimAmount());
						cell.add(metricsReportCases.getLawFirm().getLawfirm());
						cell.add(metricsReportCases.getRemark());
						cell.add(metricsReportCases.getStatus().getStatus());
//						cellObj.put(CommonConstants.CELL, cell);
						cellObj.set(CommonConstants.CELL, cell);
						cellArray.add(cellObj);

					}
				}
			}else {
				System.out.println("FromDate and ToDate null");
			}
			
			
			responseData.put(CommonConstants.PAGE, CommonConstants.PAGE_NO);
			responseData.put(CommonConstants.RECORDS, totalRecord);
//			responseData.put(CommonConstants.ROWS, cellArray);
			responseData.set(CommonConstants.ROWS, cellArray);
			out.println(responseData);
			session.setAttribute(CommonConstants.SESSION_REPORT_DATA, metricsReportData);
		} catch (IOException e) {
			LOGGER.error("Exception generated in FillingGrid Method:: " + e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/exportToMetricsReportExcel",method = RequestMethod.GET)
	public ModelAndView exportToMetricsReportExcel(HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new metricsReportView());
		return new ModelAndView(new metricsReportView());
	}
	

	//for ExportToExcel
	/*@GetMapping("/HearingStatusReportCriteria")
	public void exportToExcelHearingStatusReport(HttpServletResponse response,@RequestParam("fromDate") @DateTimeFormat(iso=ISO.DATE)LocalDate fromDate, @RequestParam("toDate") @DateTimeFormat(iso=ISO.DATE) LocalDate toDate,@RequestParam("fileType") String fileType) throws FileNotFoundException, DocumentException {
		List<Litigation> hearingStatusDtls = litigationService.getHearingStatusReportDtls(fromDate, toDate);
		System.out.println("hearingStatusDtls size::: " +hearingStatusDtls.size());
		Workbook workbook = excelGenerateService.generateHearingStatusExcelReport(hearingStatusDtls);
		if(fileType.equals("Excel")) {
			downloadService.downloadFile(workbook,response,"HearingStatusReport");
		}else if(fileType.equals("PDF")) {
			pdfGenerateService.generateHearingStatusPDFReport(workbook,response,"HearingStatusReport");
		}
		
		
	}*/
	
/*	@GetMapping("/zoneWiseStatistics")
	public ResponseEntity<BaseResponseVO> zoneWiseStatistics(){
		List<Map<String,Integer>> zoneWiseStatisticsList = metricsReportStatisticsService.getZoneWiseStatistics();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("Success");
		baseResponseVO.setData(zoneWiseStatisticsList);
		return ResponseEntity.ok().body(baseResponseVO);
	}*/
	
/*	@GetMapping("/entityWiseStatistics")
	public ResponseEntity<BaseResponseVO> entityWiseStatistics(){
		List<Map<String,Integer>> entityWiseStatisticsList = metricsReportStatisticsService.getEntityWiseStatistics();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("Success");
		baseResponseVO.setData(entityWiseStatisticsList);
		return ResponseEntity.ok().body(baseResponseVO);
		
	}
	
	@GetMapping("/caseTypeWiseStatistics")
	public ResponseEntity<BaseResponseVO> caseTypeWiseStatistics(){
		List<Map<String,Integer>> caseTypeWiseStatisticsList = metricsReportStatisticsService.getCaseTypeWiseStatistics();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("Success");
		baseResponseVO.setData(caseTypeWiseStatisticsList);
		return ResponseEntity.ok().body(baseResponseVO);
		
	}
	
	@GetMapping("/litigationByStatistics")
	public ResponseEntity<BaseResponseVO> getLitigationByAndAgainstStatistics(){
		List<Map<String, Integer>> litigationStatisticCount = metricsReportStatisticsService.getLitigationByStatistics();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("Success");
		baseResponseVO.setData(litigationStatisticCount);
		return ResponseEntity.ok().body(baseResponseVO);
		
	}
	
	/*@GetMapping("/HearingStatusReportCriteriaWord")
	public void exportToWordHearingStatusReport(HttpServletResponse response,@RequestParam("fromDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @RequestParam("toDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate) {
		List<Litigation> hearingStatusDtls = litigationService.getHearingStatusReportDtls(fromDate, toDate);
		System.out.println("hearingStatusDtls size::: " +hearingStatusDtls.size());
		XWPFDocument document = wordGenerateService.generateHearingStatusWordReport(hearingStatusDtls,"HearingStatusReport");
		downloadService.downloadFile(document,response,"HearingStatusReport");
	}*/
}
