package com.fg.ss.abhiyog.common.service;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class FileDownloadService {

	public void downloadFile(Workbook workbook, HttpServletResponse response, String fileName) {
		ServletOutputStream outStream = null;
		try {
			String mimeType = "application/vnd.ms-excel";
			response.setContentType(mimeType);
			
			String  headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=" + fileName + ".xlsx");
			response.setHeader(headerKey, headerValue);
			
			//obtains response output stream
			outStream = response.getOutputStream();
			workbook.write(outStream);
			
			//Close workbook
			workbook.close();
		}catch(Exception e) {
			System.out.println("Exception occured in dowloadFile {} " +e);
		}finally {
			try {
				if(outStream!=null) {
					outStream.close();
				}
			} catch (IOException e2) {
				System.out.println("Exception occured while closing outStream in method downloadFile : {}" +e2);
			}
		}
		
	}

	public void downloadFile(XWPFDocument document, HttpServletResponse response, String fileName) {
		ServletOutputStream outStream = null;
		try {
			String mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
			response.setContentType(mimeType);
			
			String  headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=" + fileName + ".docx");
			response.setHeader(headerKey, headerValue);
			
			//obtains response output stream
			outStream = response.getOutputStream();
			document.write(outStream);
			
			//Close workbook
			document.close();
		}catch(Exception e) {
			System.out.println("Exception occured in dowloadFile {} " +e);
		}finally {
			try {
				if(outStream!=null) {
					outStream.close();
				}
			} catch (IOException e2) {
				System.out.println("Exception occured while closing outStream in method downloadFile : {}" +e2);
			}
		}
		
	}

	public void downloadFile(Document pdfDocument, HttpServletResponse response, String fileName) {
		ServletOutputStream outStream = null;
		try {
			String mimeType = "application/pdf";
			response.setContentType(mimeType);
			
			String  headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=" + fileName + ".pdf");
			response.setHeader(headerKey, headerValue);
			
			//obtains response output stream
			outStream = response.getOutputStream();
						PdfWriter.getInstance(pdfDocument, outStream);
			
			
			//Close document
			pdfDocument.close();
		}catch(Exception e) {
			System.out.println("Exception occured in dowloadFile {} " +e);
			e.printStackTrace();
		}finally {
			try {
				if(outStream!=null) {
					outStream.close();
				}
			} catch (IOException e2) {
				System.out.println("Exception occured while closing outStream in method downloadFile : {}" +e2);
			}
		}
		
	}
	
}
