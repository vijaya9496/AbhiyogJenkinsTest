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

public class metricsReportView extends AbstractXlsView{

	private static final Logger LOGGER = LoggerFactory.getLogger(causeListReportView.class);
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"metricsReportView.xls\"");
		List<Litigation> detailReportDatalst = (List) request.getSession()
				.getAttribute(CommonConstants.SESSION_REPORT_DATA);
		LOGGER.info("List Size:: " + detailReportDatalst.size());
		Sheet sheet = workbook.createSheet("Metrics Report Detail");
		Row headerRow = sheet.createRow(0);
		Cell cell = null;
		String[] headercolumns = new String[] { "LitigationId", "Zone", "Entity", "Unit/Location",
				"Format", "Matter By/Against", "Name of Plaintiff/Complainant/Petitioner/Appelant/Appeal By Company", "Name of Defendant/Respondent/Other/Appeal Against Company", "UnderAct", "UnderSection", "Other UnderAct", "Parties", "Category", "Case Number", "File No", "Court/Forum", "Date of Receipt of Matter/Case", "City", "State", "Facts", "Last Hearing Dt", "Stage", "Possible Claim(Range)", "Possible Claim", "Lawfirm/Individual", "Remark", "Status" };
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
				listRow.createCell(1).setCellValue(reportData.getUnits().getRegions().getZoneName());
				listRow.createCell(2).setCellValue(reportData.getUnits().getEntitySummary().getEntityName());
				listRow.createCell(3).setCellValue(reportData.getUnits().getUnitName());
				listRow.createCell(4).setCellValue(reportData.getFormat().getFormat());
				listRow.createCell(5).setCellValue(reportData.getLitigationMatterByAgainst().get(0).getLtgnRepresentativeMaster().getRepresentativeName());
				listRow.createCell(6).setCellValue(reportData.getCounterPartyDtls().getCustomerName() + " Future Retail Limited");
				listRow.createCell(7).setCellValue("Future Retail Limited");
				listRow.createCell(8)
						.setCellValue(reportData.getUnderAct().getUnderAct());
				listRow.createCell(9).setCellValue(reportData.getUnderSection());
				listRow.createCell(10).setCellValue(reportData.getOtherUnderAct());
				listRow.createCell(11).setCellValue(reportData.getCounterPartyDtls().getCustomerName());
				listRow.createCell(12).setCellValue(reportData.getLtgnCaseType().getCaseType());
				listRow.createCell(13).setCellValue(reportData.getCaseNumber());
				listRow.createCell(14).setCellValue(reportData.getFileNo());
				listRow.createCell(15).setCellValue(reportData.getCourtCity().getCourtCity());
				listRow.createCell(16).setCellValue(reportData.getCaseFileOnDate().toString());
				listRow.createCell(17).setCellValue(reportData.getCourtCity().getCity().getCityName());
				listRow.createCell(18).setCellValue(reportData.getCourtCity().getCity().getState().getStateName());
				listRow.createCell(19).setCellValue(reportData.getFactOfLitigationMatter());
				listRow.createCell(20).setCellValue(reportData.getLtgnLitigationLog().get(0).getDateOfHearing().toString());
				listRow.createCell(21).setCellValue(reportData.getLtgnLitigationLog().get(0).getStageMaster().getStage());
				listRow.createCell(22).setCellValue(reportData.getClaim().getClaim());
				listRow.createCell(23).setCellValue(reportData.getExactClaimAmount());
				listRow.createCell(24).setCellValue(reportData.getLawFirm().getLawfirm());
				listRow.createCell(25).setCellValue(reportData.getRemark());
				listRow.createCell(26).setCellValue(reportData.getStatus().getStatus());
			}
		}
		
	}

}
