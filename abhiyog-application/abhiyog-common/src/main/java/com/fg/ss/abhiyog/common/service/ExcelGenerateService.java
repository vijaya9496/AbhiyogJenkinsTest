package com.fg.ss.abhiyog.common.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.model.LtgnLitigationLog;

@Service
public class ExcelGenerateService implements IExcelGenerateService{

	@Override
	public Workbook generateHearingStatusExcelReport(List<Litigation> hearingStatusDtls) {
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("HearingStatus");
			String titles[] = {"LitigationId","Entity-Unit/Location" ,"Parties", "Case Number", "File No", "Facts Of Litigation", "Under Section", 
					"Status", "Hearing Date", "Stage", "Hearing Event"};
			
			//Set Font
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			
			//Set Style
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			headerCellStyle.setWrapText(true);
			
			//Create Header
			Row headerRow = sheet.createRow(0);
			
			//set Header Rows
			for(int i=0; i<titles.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(titles[i]);
				cell.setCellStyle(headerCellStyle);
			}
			
			//Add Other Data
			int rowNum =1;
			for(Litigation statusDtls :hearingStatusDtls) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(statusDtls.getLitigationId());
				row.createCell(1).setCellValue(statusDtls.getUnits().getEntitySummary().getEntityName() + " Vs " + statusDtls.getUnits().getUnitName());
				row.createCell(2).setCellValue(statusDtls.getUnits().getEntitySummary().getEntityName() + " Vs " + statusDtls.getCounterPartyDtls().getCustomerName());
				row.createCell(3).setCellValue(statusDtls.getCaseNumber());
				row.createCell(4).setCellValue(statusDtls.getFileNo());
				row.createCell(5).setCellValue(statusDtls.getFactOfLitigationMatter());
				row.createCell(6).setCellValue(statusDtls.getUnderSection());
				row.createCell(7).setCellValue(statusDtls.getStatus().getStatus());
				
/*				SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			    SimpleDateFormat out = new SimpleDateFormat("dd-MM-yyyy");

			    Date date = in.parse(statusDtls.getNextDateOfHearing().toString());*/
				LocalDate date= statusDtls.getNextDateOfHearing();
			    String result = date.toString();
				row.createCell(8).setCellValue(result);

				row.createCell(9).setCellValue(statusDtls.getStage());
				for(LtgnLitigationLog hearingEventDtls:statusDtls.getLtgnLitigationLog()) {
					System.out.println("HearingEvent:: " +hearingEventDtls.getHearingEvent());
					row.createCell(10).setCellValue(hearingEventDtls.getHearingEvent());
				}
			}
			
			
		}catch(Exception e) {
			System.out.println("Exception occured in generateHearingStatusExcelReport {} " +e);
			e.printStackTrace();
		}
		return workbook;
	}

}
