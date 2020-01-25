package com.fg.ss.abhiyog.common.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PDFGenerateService {

	public void generateHearingStatusPDFReport(Workbook workbook, HttpServletResponse response, String fileName)
			{
		ServletOutputStream outStream = null;
		String mimeType = "application/pdf";
		response.setContentType(mimeType);

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=" + fileName + ".pdf");
		response.setHeader(headerKey, headerValue);
		Document pdfDocument;
		// obtains response output stream
		try {
			outStream = response.getOutputStream();
			System.out.println("inside generateHearingStatusPDFReport");
			XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
			int noOfColumns = sheet.getRow(0).getLastCellNum();
			int noOfRows = sheet.getLastRowNum();
			
			System.out.println("noofRows: " + noOfRows);
			Iterator<Row> rowIterator = sheet.iterator();

			pdfDocument = new Document();

			PdfWriter.getInstance(pdfDocument, outStream);
			pdfDocument.open();
			PdfPTable table = new PdfPTable(noOfColumns);
			PdfPCell tableCell;
			// Loop through rows.
			
			for(Row row:sheet ) {
				for(Cell cell: row) {
					tableCell = new PdfPCell(new Phrase(cell.getStringCellValue()));
					table.addCell(tableCell);
				}
			}
			
			/*int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			
			for(int i=0; i<rowCount+1; i++) {
				Row row = sheet.getRow(i);
				for(int j=0; j<row.getLastCellNum(); j++) {
					tableCell = new PdfPCell(new Phrase(row.getCell(j).getStringCellValue()));
					System.out.println("CellValues::"+row.getCell(j).getStringCellValue());
					table.addCell(tableCell);
				}
			}*/
			
			/*while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				System.out.println("row: " + row.getRowNum());
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					tableCell = new PdfPCell(new Phrase(cell.getStringCellValue()));
					System.out.println("CellValue:: "+cell.getStringCellValue());
					table.addCell(tableCell);
					// next line
				}

			}*/
			table.completeRow();
			pdfDocument.add(table);
			pdfDocument.close();
			workbook.close();
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
