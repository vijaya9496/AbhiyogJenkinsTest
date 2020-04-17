package com.fg.ss.abhiyog.app.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.fg.ss.abhiyog.common.constants.CommonConstants;
import com.fg.ss.abhiyog.common.vo.ConnectedLitigationVO;
import com.fg.ss.abhiyog.common.vo.LitigationSummaryVO;

public class historyReportView extends AbstractXlsView{

	private static final Logger LOGGER = LoggerFactory.getLogger(causeListReportView.class);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"HistoryReportView.xls\"");
		List<ConnectedLitigationVO> allHistoryDtls = (List) request.getSession()
				.getAttribute(CommonConstants.SESSION_REPORT_DATA);
		LOGGER.info("List Size:: " + allHistoryDtls.size());
		Sheet sheet = workbook.createSheet("History Report Detail");
		Row headerRow = sheet.createRow(0);
		Cell cell = null;
		String[] headercolumns = new String[] { "Hearing Id", "LitigationId", "Hearing Date", "Stage Name",
				"Stage Details", "Event", "Added By", "Attended By", "Document" };
		HSSFFont font = (HSSFFont) workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = (HSSFCellStyle) workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFont(font);

		for (int j = 0; j < headercolumns.length; j++) {
			cell = headerRow.createCell(j);
			cell.setCellValue(headercolumns[j]);
			cell.setCellStyle(style);
		}
		int rowCount = 1;
		if (allHistoryDtls != null && allHistoryDtls.size() > 0) {
			for (ConnectedLitigationVO reportData : allHistoryDtls) {
				Row listRow = sheet.createRow(rowCount++);
				listRow.createCell(0).setCellValue(reportData.getHearingId());
				listRow.createCell(1).setCellValue(reportData.getLitigationId());
				listRow.createCell(2).setCellValue(reportData.getHearingDt().toString());
				listRow.createCell(3).setCellValue(reportData.getStage());
				listRow.createCell(4).setCellValue(reportData.getStageDetails());
				listRow.createCell(5).setCellValue(reportData.getEvent());
				listRow.createCell(6).setCellValue(reportData.getAddedBy());
				listRow.createCell(7).setCellValue(reportData.getAttendedBy());
				
			}
		}

	
		
	}

}
