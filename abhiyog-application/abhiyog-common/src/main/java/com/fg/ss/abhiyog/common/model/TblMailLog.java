package com.fg.ss.abhiyog.common.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_mail_log")
public class TblMailLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "application_name")
	private String applicationName;

	@Column(name = "to_email_id")
	private String toEmailId;

	@Column(name = "cc_email_id")
	private String ccEmailId;

	@Column(name = "bcc_email_id")
	private String bccEmailId;

	@Column(name = "from_email_id")
	private String fromEmailId;

	@Column(name = "subject")
	private String subject;

	@Column(name = "message")
	private String message;

	@Column(name = "content_type")
	private String contentType;

	@Column(name = "dte_logged_date")
	private LocalDate dteLoggedDate;

	@Column(name = "dte_sent_date")
	private LocalDate dteSentDate;

	@Column(name = "status")
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getToEmailId() {
		return toEmailId;
	}

	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}

	public String getCcEmailId() {
		return ccEmailId;
	}

	public void setCcEmailId(String ccEmailId) {
		this.ccEmailId = ccEmailId;
	}

	public String getBccEmailId() {
		return bccEmailId;
	}

	public void setBccEmailId(String bccEmailId) {
		this.bccEmailId = bccEmailId;
	}

	public String getFromEmailId() {
		return fromEmailId;
	}

	public void setFromEmailId(String fromEmailId) {
		this.fromEmailId = fromEmailId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public LocalDate getDteLoggedDate() {
		return dteLoggedDate;
	}

	public void setDteLoggedDate(LocalDate dteLoggedDate) {
		this.dteLoggedDate = dteLoggedDate;
	}

	public LocalDate getDteSentDate() {
		return dteSentDate;
	}

	public void setDteSentDate(LocalDate dteSentDate) {
		this.dteSentDate = dteSentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
