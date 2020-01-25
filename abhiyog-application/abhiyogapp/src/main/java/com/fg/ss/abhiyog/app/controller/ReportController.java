package com.fg.ss.abhiyog.app.controller;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.service.FileDownloadService;
import com.fg.ss.abhiyog.common.service.IExcelGenerateService;
import com.fg.ss.abhiyog.common.service.ILitigationService;
import com.fg.ss.abhiyog.common.service.IWordGenerateService;
import com.fg.ss.abhiyog.common.service.PDFGenerateService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/masters/Report")
public class ReportController {
	
	@Autowired
	IExcelGenerateService excelGenerateService;
	
	@Autowired
	IWordGenerateService wordGenerateService;
	
	@Autowired
	ILitigationService litigationService;
	
	@Autowired
	FileDownloadService downloadService;
	
	@Autowired
	PDFGenerateService pdfGenerateService;

	//for ExportToExcel
	@GetMapping("/HearingStatusReportCriteria")
	public void exportToExcelHearingStatusReport(HttpServletResponse response,@RequestParam("fromDate") @DateTimeFormat(iso=ISO.DATE)LocalDate fromDate, @RequestParam("toDate") @DateTimeFormat(iso=ISO.DATE) LocalDate toDate,@RequestParam("fileType") String fileType) throws FileNotFoundException, DocumentException {
		List<Litigation> hearingStatusDtls = litigationService.getHearingStatusReportDtls(fromDate, toDate);
		System.out.println("hearingStatusDtls size::: " +hearingStatusDtls.size());
		Workbook workbook = excelGenerateService.generateHearingStatusExcelReport(hearingStatusDtls);
		if(fileType.equals("Excel")) {
			downloadService.downloadFile(workbook,response,"HearingStatusReport");
		}else if(fileType.equals("PDF")) {
			pdfGenerateService.generateHearingStatusPDFReport(workbook,response,"HearingStatusReport");
		}
		
		
	}
	
	/*@GetMapping("/HearingStatusReportCriteriaWord")
	public void exportToWordHearingStatusReport(HttpServletResponse response,@RequestParam("fromDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @RequestParam("toDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate) {
		List<Litigation> hearingStatusDtls = litigationService.getHearingStatusReportDtls(fromDate, toDate);
		System.out.println("hearingStatusDtls size::: " +hearingStatusDtls.size());
		XWPFDocument document = wordGenerateService.generateHearingStatusWordReport(hearingStatusDtls,"HearingStatusReport");
		downloadService.downloadFile(document,response,"HearingStatusReport");
	}*/
}
