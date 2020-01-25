package com.fg.ss.abhiyog.common.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.model.TblMailLog;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.repository.LitigationRepository;
import com.fg.ss.abhiyog.common.repository.TblMailLogRepository;
import com.fg.ss.abhiyog.common.repository.UserRepository;
import com.fg.ss.abhiyog.common.vo.EmailAlertLogVO;

@Service
public class EmailAlertSchedularService implements IEmailAlertSchedularService{

	@Autowired
	private LitigationRepository litigationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TblMailLogRepository tblMailLogRepository;
	
	@Override
	public List<Litigation> findNextSevenDaysHearingDateDtls() {
		List<Litigation> listHearingDateDtls = litigationRepository.findNextSevenDaysHearingDateDtls();
		return listHearingDateDtls;
	}

	@Override
	public List<User> getToEmailIds() {
		List<User> listToEmailIds = userRepository.findToEmailIds();
		return listToEmailIds;
	}

	@Override
	public void saveMailLogData(String fromEmail, String toEmailId, String result, String message, LocalDate inDate) {
		TblMailLog tblMailLog = new TblMailLog();
		tblMailLog.setApplicationName("Abhiyog");
		tblMailLog.setContentType("HTML");
		tblMailLog.setDteLoggedDate(inDate);
		tblMailLog.setDteSentDate(inDate);
		tblMailLog.setFromEmailId(fromEmail);
		tblMailLog.setToEmailId(toEmailId);
		tblMailLog.setBccEmailId(null);
		tblMailLog.setCcEmailId(null);
		if(result.equals("Success")) {
			tblMailLog.setStatus("Y");
		}else {
			tblMailLog.setStatus("N");
		}
		tblMailLog.setSubject("Abhiyog Alert");
		tblMailLog.setMessage(message);
		System.out.println("Message:: " +message);
		
		tblMailLogRepository.save(tblMailLog);
	}

	@Override
	public List<EmailAlertLogVO> getAlertLogs() {
		List<TblMailLog> alertLogs = tblMailLogRepository.findAllDesc();
		return alertLogs.stream().map(allAlertLogs ->convertToAlertLogsDto(allAlertLogs)).collect(Collectors.toList());
	}

	private EmailAlertLogVO convertToAlertLogsDto(TblMailLog allAlertLogs) {
		EmailAlertLogVO alertLogVo = new EmailAlertLogVO();
		alertLogVo.setAlertId(allAlertLogs.getId());
		alertLogVo.setToEmailId(allAlertLogs.getToEmailId());
		alertLogVo.setSubject(allAlertLogs.getSubject());
		alertLogVo.setCreatedDate(allAlertLogs.getDteLoggedDate());
		alertLogVo.setMailSentDate(allAlertLogs.getDteSentDate());
		String status = allAlertLogs.getStatus().equals("Y")? "Sent": "Not Sent"; 
		alertLogVo.setMailStatus(status);
		alertLogVo.setMessage(allAlertLogs.getMessage());
		return alertLogVo;
	}

}
