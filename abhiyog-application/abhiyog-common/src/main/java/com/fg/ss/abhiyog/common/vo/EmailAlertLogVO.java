package com.fg.ss.abhiyog.common.vo;

import java.time.LocalDate;

public class EmailAlertLogVO {

	private int alertId;
	private String toEmailId;
	private String subject;
	private LocalDate createdDate;
	private LocalDate mailSentDate;
	private String mailStatus;
	private String message;

	public int getAlertId() {
		return alertId;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	public String getToEmailId() {
		return toEmailId;
	}

	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getMailSentDate() {
		return mailSentDate;
	}

	public void setMailSentDate(LocalDate mailSentDate) {
		this.mailSentDate = mailSentDate;
	}

	public String getMailStatus() {
		return mailStatus;
	}

	public void setMailStatus(String mailStatus) {
		this.mailStatus = mailStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
