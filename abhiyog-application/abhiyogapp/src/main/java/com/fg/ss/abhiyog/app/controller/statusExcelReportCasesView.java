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
import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.vo.LitigationSummaryVO;

public class statusExcelReportCasesView extends AbstractXlsView {

	private static final Logger LOGGER = LoggerFactory.getLogger(statusExcelReportCasesView.class);

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"StatusReportCases.xls\"");
		List<Litigation> detailReportDatalst = (List) request.getSession()
				.getAttribute(CommonConstants.SESSION_REPORT_DATA);
		LOGGER.info("List Size:: " + detailReportDatalst.size());
		Sheet sheet = workbook.createSheet("Status Report Cases Detail");
		Row headerRow = sheet.createRow(0);
		Cell cell = null;
		String[] headercolumns = new String[] { "LitigationId", "Entity-Unit/Location", "Parties", "Case Number",
				"File No", "Facts of Litigation", "Under Section", "Status", "Hearing Date", "Stage", "Hearing Event" };
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
		if (detailReportDatalst != null && detailReportDatalst.size() > 0) {
			for (Litigation reportData : detailReportDatalst) {
				Row listRow = sheet.createRow(rowCount++);
				listRow.createCell(0).setCellValue(reportData.getLitigationId());
				listRow.createCell(1).setCellValue(
						reportData.getUnits().getEntitySummary().getEntityName() + reportData.getUnits().getUnitName());
				listRow.createCell(2).setCellValue(reportData.getUnits().getEntitySummary().getEntityName()
						+ reportData.getCounterPartyDtls().getCustomerName());
				listRow.createCell(3).setCellValue(reportData.getCaseNumber());
				listRow.createCell(4).setCellValue(reportData.getFileNo());
				listRow.createCell(5).setCellValue(reportData.getFactOfLitigationMatter());
				listRow.createCell(6).setCellValue(reportData.getUnderSection());
				listRow.createCell(7).setCellValue(reportData.getStatus().getStatus());
				listRow.createCell(8)
						.setCellValue(reportData.getLtgnLitigationLog().get(0).getDateOfHearing().toString());
				listRow.createCell(9).setCellValue(reportData.getLtgnLitigationLog().get(0).getStage());
				listRow.createCell(10).setCellValue(reportData.getLtgnLitigationLog().get(0).getHearingEvent());
			}
		}

	}

}
