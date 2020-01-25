package com.fg.ss.abhiyog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fg.ss.abhiyog.common.service.EmailService;
import com.fg.ss.abhiyog.common.service.IEmailAlertSchedularService;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.CommonEmailVO;
import com.fg.ss.abhiyog.common.vo.EmailAlertLogVO;

@RestController
@RequestMapping("/masters/utility")
public class UtilityRestController {

	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private IEmailAlertSchedularService emailAlertSchedularService;
	
	@GetMapping("/commonEmail")
//	@RequestMapping(value = "/commonEmail", method = RequestMethod.POST, consumes = {"multipart/form-data"})
//	@RequestPart("attachmentFile") MultipartFile attachmentFile
	public ResponseEntity<BaseResponseVO> commonEmail(@RequestBody CommonEmailVO commonEmailVO){
		
		baseResponseVO = emailService.sendCommonEmail(commonEmailVO);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@GetMapping("/alertLog")
	public ResponseEntity<BaseResponseVO> emailAlertLog(){
		List<EmailAlertLogVO> alertLog = emailAlertSchedularService.getAlertLogs();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("Success");
		baseResponseVO.setData(alertLog);
		return ResponseEntity.ok().body(baseResponseVO);
	}
}
