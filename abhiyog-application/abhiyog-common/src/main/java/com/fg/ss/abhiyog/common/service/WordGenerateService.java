package com.fg.ss.abhiyog.common.service;

import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.Litigation;

@Service
public class WordGenerateService implements IWordGenerateService {

	@Override
	public XWPFDocument generateHearingStatusWordReport(List<Litigation> hearingStatusDtls, String fileName) {
		// Create a new document from scratch
		String titles[] = { "LitigationId", "Entity-Unit/Location", "Parties", "Case Number", "File No",
				"Facts Of Litigation" };

		XWPFDocument doc = new XWPFDocument();
		int nRows = hearingStatusDtls.size() + 1;
		int nCols = titles.length;
		int colCt = 0;
		XWPFTable table = doc.createTable(nRows, nCols);
		XWPFTableRow hdrRow = table.getRow(0);
		List<XWPFTableCell> hdrcells = hdrRow.getTableCells();
		for (XWPFTableCell hdrCell : hdrcells) {
			CTTcPr tcpr = hdrCell.getCTTc().addNewTcPr();
			CTVerticalJc va = tcpr.addNewVAlign();
			va.setVal(STVerticalJc.CENTER);
			XWPFParagraph para = hdrCell.getParagraphs().get(0);
			XWPFRun rh = para.createRun();
			for (int i = 0; i < titles.length; i++) {
				if (colCt == i) {
					rh.setText(titles[i]);
					rh.setBold(true);
					para.setAlignment(ParagraphAlignment.CENTER);
				}
			}
			colCt++;
		}

		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTString styleStr = tblPr.addNewTblStyle();
		styleStr.setVal("StyledTable");
		List<XWPFTableRow> rows = table.getRows();
		System.out.println(rows.size());
		int rowCt = 1;
		colCt = 0;
		for (Litigation statusDtls : hearingStatusDtls) {
			if (rowCt == nRows) {
				break;
			} else {
				CTTrPr trPr = rows.get(rowCt).getCtRow().addNewTrPr();
				CTHeight ht = trPr.addNewTrHeight();
				ht.setVal(BigInteger.valueOf(360));
				List<XWPFTableCell> cells = rows.get(rowCt).getTableCells();
				System.out.println("Cells length:: " + cells.size());
				for (XWPFTableCell cell : cells) {
					CTTcPr tcpr = cell.getCTTc().addNewTcPr();
					CTVerticalJc va = tcpr.addNewVAlign();
					va.setVal(STVerticalJc.CENTER);
					CTShd ctshd = tcpr.addNewShd();
					ctshd.setColor("auto");
					ctshd.setVal(STShd.CLEAR);
					if (rowCt % 2 == 0) {
						ctshd.setFill("D3DFEE");
					} else {
						ctshd.setFill("EDF2F8");
					}
					XWPFParagraph para = cell.getParagraphs().get(0);
					XWPFRun rh = para.createRun();
					if (colCt == 0) {
						rh.setText(statusDtls.getLitigationId());
						para.setAlignment(ParagraphAlignment.LEFT);
					} else if (colCt == 1) {
						rh.setText(statusDtls.getUnits().getEntitySummary().getEntityName() + "and"
								+ statusDtls.getUnits().getUnitName());
						para.setAlignment(ParagraphAlignment.LEFT);
					} else if (colCt == 2) {
						rh.setText(statusDtls.getCounterPartyDtls().getCustomerName() + " and "
								+ statusDtls.getUnits().getUnitName());
						para.setAlignment(ParagraphAlignment.LEFT);
					} else if (colCt == 3) {
						rh.setText(statusDtls.getCaseNumber());
						para.setAlignment(ParagraphAlignment.LEFT);
					} else if (colCt == 4) {
						rh.setText(statusDtls.getFileNo());
						para.setAlignment(ParagraphAlignment.LEFT);
					} else if (colCt == 5) {
						rh.setText(statusDtls.getFactOfLitigationMatter());
						para.setAlignment(ParagraphAlignment.LEFT);
					}
					colCt++;
				} // for cell
			}
			colCt = 0;
			rowCt++;

		} // List
		return doc;
	}
}
