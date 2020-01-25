package com.fg.ss.abhiyog.common.vo;

public class CommonEmailVO {

	private String subject;
	private String message;
	private String[] ccMails;
	private String[] bccMails;
	private String toMailId;
	private String attachmentFile;
	
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
	public String[] getCcMails() {
		return ccMails;
	}
	public void setCcMails(String[] ccMails) {
		this.ccMails = ccMails;
	}
	public String[] getBccMails() {
		return bccMails;
	}
	public void setBccMails(String[] bccMails) {
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
	
	
}
