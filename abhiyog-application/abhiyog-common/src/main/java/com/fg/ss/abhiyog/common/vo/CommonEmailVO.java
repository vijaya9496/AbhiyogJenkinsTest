package com.fg.ss.abhiyog.common.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommonEmailVO {

	private String subject;
	private String message;
	private List<String> ccMails = new ArrayList<String>();
	private List<String> bccMails = new ArrayList<String>();
	private String toMailId;
	private String attachmentFile;
	private String fromEmail;
	private Date fromDate;
	private Date toDate;
	private String mailStatus;

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

	public List<String> getCcMails() {
		return ccMails;
	}

	public void setCcMails(List<String> ccMails) {
		this.ccMails = ccMails;
	}

	public List<String> getBccMails() {
		return bccMails;
	}

	public void setBccMails(List<String> bccMails) {
		this.bccMails = bccMails;
	}

	public String getToMailId() {
		return toMailId;
	}

	public void setToMailId(String toMailId) {
		this.toMailId = toMailId;
	}

	public String getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(String attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getMailStatus() {
		return mailStatus;
	}

	public void setMailStatus(String mailStatus) {
		this.mailStatus = mailStatus;
	}

}
