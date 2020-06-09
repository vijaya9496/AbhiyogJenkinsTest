package com.fg.ss.abhiyog.common.service;

//import static org.hamcrest.CoreMatchers.startsWith;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.repository.UserRepository;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.CommonEmailVO;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@Value("${abhiyogapp.email.subject}")
	private String emailSubject;

	@Value("${abhiyogapp.email.fromEmail}")
	private String fromEmail;

	@Value("${abhiyogapp.email.mailContent}")
	private String mailContent;

	@Value("${abhiyogapp.resetPassword}")
	private String sampleResetPassword;
	
	@Autowired
	private IEmailAlertSchedularService emailAlertSchedularService;

	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	public boolean sendMail(String recepientEmailId, String loginId) {
		int updatePassword = userRepository.changePassword(loginId, bcryptPasswordEncoder.encode(sampleResetPassword));
		boolean isMailSent = false;
		if (updatePassword > 0) {
			isMailSent = send(sampleResetPassword, fromEmail, recepientEmailId, mailContent, emailSubject);
		}
		return isMailSent;
	}

	private boolean send(String sampleResetPassword, String fromEmail, String recepientEmailId,
			String mailContent, String emailSubject) {
		boolean isMailSent = false;
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(fromEmail);
			helper.setTo(recepientEmailId);
			helper.setSubject(emailSubject);
			helper.setText(mailContent);
			helper.setText("Password::" + " " + sampleResetPassword);
			javaMailSender.send(message);
			isMailSent=true;
			/*baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("Password Sent To Company Email ID");*/
		} catch (MessagingException e) {
			/*baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("Unable to Send Password to Mail");*/
			isMailSent=false;
			e.printStackTrace();
		}
//		baseResponseVO.setData(null);
		return isMailSent;

	}

	public boolean sendCommonEmail(CommonEmailVO commonEmailVO, MultipartFile multiPartFile) {
		boolean flag = false;
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(fromEmail);
			try {
				if (commonEmailVO.getToMailId() != null) {
					helper.setTo(commonEmailVO.getToMailId());
				}
			} catch (Exception e) {
				e.printStackTrace();
//				baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
//				baseResponseVO.setResponseMessage("Provide Valid TO Email address");
			}
			
			System.out.println("bccMails Size*** " +commonEmailVO.getBccMails().size());
			
			if (commonEmailVO.getBccMails().size() > 0) {
				String[] bccMails = null;
				for(String mails : commonEmailVO.getBccMails()) {
					System.out.println("Mails:: ** " +mails);
					 bccMails = new String[commonEmailVO.getBccMails().size()];
					bccMails = commonEmailVO.getBccMails().toArray(bccMails);
				}
				System.out.println("BCCMAILS ** " +bccMails );
				helper.setBcc(bccMails);
			}
			
			System.out.println("ccMails Size**** "  +commonEmailVO.getCcMails().size());
			
			if (commonEmailVO.getCcMails().size() > 0) {
				String[] ccMails = null;
				for(String mails : commonEmailVO.getCcMails()) {
					System.out.println("Mails:: ** " +mails);
					ccMails = new String[commonEmailVO.getCcMails().size()];
					ccMails = commonEmailVO.getCcMails().toArray(ccMails);
				}
				System.out.println("CCMAILS ** " +ccMails );
				helper.setCc(ccMails);
			}

			helper.setSubject(commonEmailVO.getSubject());
			helper.setText(commonEmailVO.getMessage());
			String extension = "";
			if (!multiPartFile.isEmpty()) {
				String attachName = multiPartFile.getOriginalFilename();
				

				int i = attachName.lastIndexOf('.');
				if (i >= 0) {
				    extension = attachName.substring(i+1);
				}
				System.out.println("extension:: " +extension);
				if(!extension.equals("war") && !extension.equals("exe")) {
					helper.addAttachment(attachName, new InputStreamSource() {
						@Override
						public InputStream getInputStream() throws IOException {
							
							return multiPartFile.getInputStream();
						}
					});
				}else {
					flag = false;
					return flag;
				}
				
			} else {
				System.out.println("No file selected");
			}

			javaMailSender.send(message);
			flag = true;
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public void sendEmailAlert(List<Litigation> hearingDateDtls, List<User> toEmailIdList) {
		String result = new String();
		try {
			for(User toEmailId : toEmailIdList) {
				result = "fail";
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh.mm.ss aa");
				String dateString = dateFormat.format(new Date()).toString();

				LocalDateTime localDateTime = LocalDateTime.now();
				LocalDate localDate = localDateTime.toLocalDate();
				
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
				helper.setFrom(fromEmail);
				helper.setSubject("Abhiyog Alert");
				String htmlMsg = "<html>"
						+ 		 "<head>"  
						+		 "<style>"  
						+	     "table, th, td {"  
						+            "border: 1px solid black;" 
						+            "border-collapse: collapse;"  
						+        "}"
						+		 "</style>" 
						+		 "</head> "
						+ 		  "<body>"
						+ 		  "<b>From:</b>"+fromEmail+ "<br>"
						+ 		  "<b>Sent:</b>" +dateString+"<br>"
						+ "<b>To:</b>" +toEmailId.getEmailId()+"<br>"
						+ "<b>Subject:</b>Abhiyog Alert <br>"
						+ "<br>"
						+ "<br>"
						+ "<br>"
						+ "Dear <b>" +toEmailId.getFirstName()+"</b>, <br> "
						+ "\n This is an auto generated email from software application <b>Abhiyog</b>. <br>" 
						+ "As per our records, following cases are likely to come up for hearing on the date mentioned below."
						+ "<br><br><br><br>"
						+ "<table>"
						+ "<tr>"
						+ "<th>Litigation ID</th>"
						+ "<th>Hearing Date</th>"
						+ "<th>Unit/Location</th>"
						+ "<th>Parties</th>"
						+ "<th>Case Number</th>"
						+ "<th>Stage</th>"
						+ "<th>Court</th>"
						+ "<th>Court/Forum</th>"
						+ "</tr>";
				
				for(Litigation hearingDtls: hearingDateDtls) {
					String bgCol = new String();
					System.out.println("nextDateOfHearing:: " +hearingDtls.getNextDateOfHearing());
					System.out.println("LocalDate:: " + localDate);
					//when nexthearingdate same as currentdate set tr as orange
					if(hearingDtls.getNextDateOfHearing().compareTo(localDate) == 0) {
						System.out.println("nexthearingdate same as currentdate");
						 bgCol = "#FFA500";
					}
					//when nexthearingDate >=1 and < 3 set tr as blue
					else if(hearingDtls.getNextDateOfHearing().compareTo(localDate) >= 1 &&  hearingDtls.getNextDateOfHearing().compareTo(localDate) <= 3) {
						System.out.println("nexthearingDate >=1 and <= 3");
						bgCol = "#87ceeb";
					}
					//nextHearingDate > 3 and <=5 set tr as white
					else if(hearingDtls.getNextDateOfHearing().compareTo(localDate) > 3 && hearingDtls.getNextDateOfHearing().compareTo(localDate) <= 5) {
						System.out.println("nextHearingDate > 3 and <=5");
						bgCol = "#ffffff";
					}
					//nextHearingDate > 5 set tr as yellow
					else if(hearingDtls.getNextDateOfHearing().compareTo(localDate) > 5) {
						System.out.println("nextHearingDate > 5");
						bgCol = "#FFFF00";
					}else {
						System.out.println("nexthearingDate not matched");
					}
					if(bgCol != null) {
						htmlMsg += "<tr bgcolor=" +bgCol+">"
								+ "<td>"+"<a href="+ "http:localhost:8080>" +hearingDtls.getLitigationId()+ "</a>"+ "</td>"
								+"<td>" +hearingDtls.getNextDateOfHearing() +"</td>"
								+ "<td>"+hearingDtls.getUnits().getEntitySummary().getEntityName()+ "(" +hearingDtls.getUnits().getUnitName()+")"+"</td>"
								+ "<td>"+hearingDtls.getCounterPartyDtls().getCustomerName()+"Vs"+hearingDtls.getUnits().getEntitySummary().getEntityName()+"</td>"
								+ "<td>"+hearingDtls.getCaseNumber()+"</td>"
								+ "<td>"+hearingDtls.getStage()+"</td>"
								+ "<td>"+hearingDtls.getCourtType().getCourtType()+"</td>"
								+ "<td>"+hearingDtls.getCourtCity().getCourtCity()+"</td>"
								+ "</tr>";
					}
				}
				htmlMsg +="</table>"
						+ "<br><br><br><br><br><br>"
						+ "<ul><li>Please Do not reply to this mail. This is sent from an unattended mail box</li>"
						+ "<li>Please mark your all queries to <b>support@legasis.in</b></li></ul>"
						+ "Yours sincerely, <br>"
						+ "Team Abhiyog"
						+ "</body>"
						+ "</html>";
				
				message.setContent(htmlMsg,"text/html");
				helper.setTo(toEmailId.getEmailId());
				javaMailSender.send(message);
				result = "Success";
				System.out.println("HtmlMsg:: " +htmlMsg);
				System.out.println("mimemesage:: " +message.toString());
				System.out.println("Email Sent Successfully ToEmailID:: " +toEmailId.getEmailId());
				emailAlertSchedularService.saveMailLogData(fromEmail, toEmailId.getEmailId(),result, htmlMsg, localDate);
				System.out.println("Date Inserted Successfully in tblMailLog table");
			}
		}catch(Exception e) {
			System.out.println("Exception occured in sendEmailAlert {} " + e);
			e.printStackTrace();
		}
		
	}

	/*
	 * private File convertMultiPartToFile(MultipartFile attachmentFile) throws
	 * IOException { File convFile = new File( attachmentFile.getOriginalFilename()
	 * ); FileOutputStream fos = new FileOutputStream( convFile ); fos.write(
	 * attachmentFile.getBytes() ); fos.close(); return convFile;
	 * 
	 * }
	 */

}
