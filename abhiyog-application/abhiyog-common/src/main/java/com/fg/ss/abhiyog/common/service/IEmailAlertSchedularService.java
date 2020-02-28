package com.fg.ss.abhiyog.common.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.vo.EmailAlertLogVO;

public interface IEmailAlertSchedularService {

	List<Litigation> findNextSevenDaysHearingDateDtls();

	List<User> getToEmailIds();

	void saveMailLogData(String fromEmail, String emailId, String result, String message, LocalDate localDate);

	List<EmailAlertLogVO> getAlertLogs();

	EmailAlertLogVO getMailDesc(int parseInt);

	List<EmailAlertLogVO> getAlertLogDtlsBy(LocalDate fromDate, LocalDate toDate, String mailStatus);
}
