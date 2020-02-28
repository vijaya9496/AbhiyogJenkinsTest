package com.fg.ss.abhiyog.common.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.model.ShowCauseNotice;
import com.fg.ss.abhiyog.common.model.TblMailLog;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.repository.LitigationRepository;
import com.fg.ss.abhiyog.common.repository.TblMailLogRepository;
import com.fg.ss.abhiyog.common.repository.UserRepository;
import com.fg.ss.abhiyog.common.vo.EmailAlertLogVO;
import com.fg.ss.abhiyog.common.vo.ShowCauseNoticeVO;

@Service
public class EmailAlertSchedularService implements IEmailAlertSchedularService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailAlertSchedularService.class);

	@Autowired
	private LitigationRepository litigationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TblMailLogRepository tblMailLogRepository;
	
	@Autowired
	private EntityManager entityManager;
	
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

	@Override
	public EmailAlertLogVO getMailDesc(int id) {
		TblMailLog mailDesc = tblMailLogRepository.findMailDescBy(id);
		EmailAlertLogVO emailAlertLogoVO = new EmailAlertLogVO();
		emailAlertLogoVO.setMessage(mailDesc.getMessage());
		return emailAlertLogoVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmailAlertLogVO> getAlertLogDtlsBy(LocalDate fromDate, LocalDate toDate, String mailStatus) {
		List<TblMailLog> alertLogSummary = new ArrayList<>();
		Map<String, Object> parameterMap = new HashMap<>();
		List<String> whereClause = new ArrayList<>();
		StringBuilder reportQuery = new StringBuilder();
		
//		System.out.println("FromDate**** " +fromDate);
//		System.out.println("ToDate**** " +toDate);

		reportQuery.append(
				"select tl from TblMailLog tl ");

		if (!mailStatus.equals("ALL")) {
			System.out.println(mailStatus);
			whereClause.add(" tl.status=:mailStatus ");
			parameterMap.put("mailStatus", mailStatus);
		}
		
		if(fromDate != null && toDate != null) {
			whereClause.add(" tl.dteSentDate between :fromDate and :toDate ");
			parameterMap.put("toDate", toDate);
			parameterMap.put("fromDate", fromDate);
		}
		

		if (!mailStatus.equals("ALL") || fromDate != null || toDate != null) {
			reportQuery.append(" where " + StringUtils.join(whereClause, " and "));
			reportQuery.append(" order by tl.id desc");
		}

		Query jpaQuery = entityManager.createQuery(reportQuery.toString());
		LOGGER.info("Created Query::  " + reportQuery.toString());
		for (String key : parameterMap.keySet()) {
			jpaQuery.setParameter(key, parameterMap.get(key));
		}
		alertLogSummary = jpaQuery.getResultList();
		LOGGER.info("alertLogSummaryBy  Size:: " + alertLogSummary.size());
//		return convertToDto(showCauseNoticeSummary);
		return alertLogSummary.stream().map(allAlertLogs ->convertToAlertLogsDto(allAlertLogs)).collect(Collectors.toList());
	}
	
	

}
