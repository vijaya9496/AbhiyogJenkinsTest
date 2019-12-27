package com.fg.ss.abhiyog.common.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.repository.UserRepository;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;




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
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	public BaseResponseVO sendMail(String recepientEmailId, String loginId) {
		int updatePassword = userRepository.changePassword(loginId,bcryptPasswordEncoder.encode(sampleResetPassword));
		if(updatePassword > 0) {
			baseResponseVO=	send(sampleResetPassword, fromEmail, recepientEmailId, mailContent, emailSubject);
		}
		return baseResponseVO;
	}

	private BaseResponseVO send(String sampleResetPassword, String fromEmail, String recepientEmailId, String mailContent,
			String emailSubject) {
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(fromEmail);
			helper.setTo(recepientEmailId);
			helper.setSubject(emailSubject);
			helper.setText(mailContent);
			helper.setText("Password::"+ " " +sampleResetPassword);
			javaMailSender.send(message);
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("Password Sent To Company Email ID");
		} catch (MessagingException e) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("Unable to Send Password to Mail");
			e.printStackTrace();
		}
		baseResponseVO.setData(null);
		return baseResponseVO;
		
	}

}
