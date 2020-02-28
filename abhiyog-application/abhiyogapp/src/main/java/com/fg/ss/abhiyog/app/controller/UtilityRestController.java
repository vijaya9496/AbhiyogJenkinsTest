package com.fg.ss.abhiyog.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fg.ss.abhiyog.common.constants.CommonConstants;
import com.fg.ss.abhiyog.common.service.EmailService;
import com.fg.ss.abhiyog.common.service.IEmailAlertSchedularService;
import com.fg.ss.abhiyog.common.util.DateUtils;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.CommonEmailVO;
import com.fg.ss.abhiyog.common.vo.EmailAlertLogVO;
import com.fg.ss.abhiyog.common.vo.ShowCauseNoticeVO;
import com.fg.ss.abhiyog.common.vo.UserVO;

@Controller
//@RequestMapping("/masters/utility")
public class UtilityRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UtilityRestController.class);
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	@Autowired
	private EmailService emailService;

	@Autowired
	private IEmailAlertSchedularService emailAlertSchedularService;

	@Value("${abhiyogapp.email.fromEmail}")
	private String fromEmail;

	@RequestMapping(value = "/commonEmail", method = RequestMethod.GET)
	public String commonEmail(Model model) {
		model.addAttribute("commonEmailVO", new CommonEmailVO());
		model.addAttribute("fromEmail", fromEmail);
		return "commonEmail";
	}

	@RequestMapping(value = "/sendCommonEmail", method = RequestMethod.POST)
	public String commonEmail(@RequestParam("attachmentFile") MultipartFile multiPartFile, HttpServletRequest request,
			Model model) {
		CommonEmailVO commonEmailVO = new CommonEmailVO();

		commonEmailVO.setToMailId(request.getParameter("toMailId"));
		String[] bccStr = request.getParameterValues("bccMails");
		System.out.println("bccStr length*** " +bccStr.length);
		
		if(bccStr.length > 0) {
			for (int i = 0; i < bccStr.length; i++) {
				if(bccStr[i].isEmpty()) {
					System.out.println("BCCSTR contains Empty spaces");
				}else {
					List<String> bccMailId = Arrays.asList(bccStr[i].split(","));
					for(String mails:bccMailId) {
						commonEmailVO.getBccMails().add(mails);
					}
				}
			}
		}
		
		
		String[] ccStr = request.getParameterValues("ccMails");
		System.out.println("ccStr length**** " +ccStr.length);
		if (ccStr.length > 0) {
			for (int i = 0; i < ccStr.length; i++) {
				if (ccStr[i].isEmpty()) {
					System.out.println("CCSTR contains Empty spaces");
				} else {
					List<String> ccMailId = Arrays.asList(ccStr[i].split(","));
					for(String mails:ccMailId) {
						commonEmailVO.getCcMails().add(mails);
					}
				}
			}
		}
		commonEmailVO.setSubject(request.getParameter("subject"));
		commonEmailVO.setMessage(request.getParameter("message"));

		boolean flag = emailService.sendCommonEmail(commonEmailVO, multiPartFile);
		if (flag) {
			model.addAttribute("message", "Email Sent Successfully");
			model.addAttribute("fromEmail", fromEmail);
			model.addAttribute("commonEmailVO", new CommonEmailVO());
		} else {
			model.addAttribute("message", "Unable To Send Mail");
			model.addAttribute("fromEmail", fromEmail);
			model.addAttribute("commonEmailVO", new CommonEmailVO());
		}

		return "commonEmail";
	}
	
	
	@RequestMapping(value = "/showAlertLogSummary", method = RequestMethod.GET)
	public String showAlertLogSummary(Model model) {
		model.addAttribute("commonEmailVO", new CommonEmailVO());
		return "alertLog";
	}

	@RequestMapping(value = "/getAlertLogSummary", method = RequestMethod.GET)
	public void getUserSummary(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<EmailAlertLogVO> alertLog = emailAlertSchedularService.getAlertLogs();
		fillGridDetail(alertLog, request, response, session);
	}

	private void fillGridDetail(List<EmailAlertLogVO> alertLog, HttpServletRequest request, HttpServletResponse response,
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
			if (alertLog.size() > 0) {
				totalRecord = alertLog.size();
				for (EmailAlertLogVO alertLogSummary : alertLog) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, alertLogSummary.getAlertId());
					cell = mapper.createArrayNode();
					cell.add(alertLogSummary.getAlertId());
					cell.add(alertLogSummary.getToEmailId());
					cell.add(alertLogSummary.getSubject());
					cell.add(alertLogSummary.getCreatedDate().toString());
					cell.add(alertLogSummary.getMailSentDate().toString());
					cell.add(alertLogSummary.getMailStatus());
//					cell.add(alertLogSummary.getMessage());
					cell.add("View");
					
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
	
	@RequestMapping(value="/getMailDesc", method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String getMailDesc(HttpServletRequest request) {
		System.out.println(Integer.parseInt(request.getParameter("id")));
		EmailAlertLogVO mailDesc = emailAlertSchedularService.getMailDesc(Integer.parseInt(request.getParameter("id")));
		return mailDesc.getMessage();
	}
	
	@RequestMapping("/getAlertLogBy")
	public void showAlertLogBy(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws ParseException {
		LOGGER.info("inside showAlertLogBy");
		int totalRecord = 0;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseData = mapper.createObjectNode();
		ArrayNode cellArray = null;
		ArrayNode cell = null;
		ObjectNode cellObj = null;
		try {
			PrintWriter out = response.getWriter();
			cellArray = mapper.createArrayNode();
			LocalDate fromDate;
			LocalDate toDate;
			String mailStatus = "";

			if (request.getParameter("mailStatus") != "ALL") {
				mailStatus = request.getParameter("mailStatus");
			}
			
			fromDate =DateUtils.getDBFormatedDte(request.getParameter("fromDate"));
			toDate  = DateUtils.getDBFormatedDte(request.getParameter("toDate"));
			
			System.out.println(fromDate);
			System.out.println(toDate);
			System.out.println("FromDate:: " +request.getParameter("fromDate"));
			System.out.println("ToDate:: " +request.getParameter("toDate"));

			List<EmailAlertLogVO> allAlertLogDtls = emailAlertSchedularService.getAlertLogDtlsBy(fromDate,toDate,mailStatus);
			if (allAlertLogDtls.size() > 0) {
				totalRecord = allAlertLogDtls.size();
				for (EmailAlertLogVO alertLogSummary : allAlertLogDtls) {
					cellObj = mapper.createObjectNode();
					cellObj.put(CommonConstants.ID, alertLogSummary.getAlertId());
					cell = mapper.createArrayNode();
					cell.add(alertLogSummary.getAlertId());
					cell.add(alertLogSummary.getToEmailId());
					cell.add(alertLogSummary.getSubject());
					cell.add(alertLogSummary.getCreatedDate().toString());
					cell.add(alertLogSummary.getMailSentDate().toString());
					cell.add(alertLogSummary.getMailStatus());
//					cell.add(alertLogSummary.getMessage());
					cell.add("View");
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
	
	

	/*@GetMapping("/alertLog")
	public ResponseEntity<BaseResponseVO> emailAlertLog() {
		List<EmailAlertLogVO> alertLog = emailAlertSchedularService.getAlertLogs();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("Success");
		baseResponseVO.setData(alertLog);
		return ResponseEntity.ok().body(baseResponseVO);
	}*/
}
