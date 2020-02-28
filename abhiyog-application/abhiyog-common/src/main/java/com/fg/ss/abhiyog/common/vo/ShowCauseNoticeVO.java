package com.fg.ss.abhiyog.common.vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowCauseNoticeVO {

	private String loginId;
	private String entityName;
	private String formatName;
	private String zoneName;
	private String unitName;
	private String noticeCategoryName;
	private String receivedFrom;
	private LocalDate issueDate;
	private LocalDate receivedDate;
	private LocalDate noticeReplyDeadline;
	private String subject;
	private String referenceNo;
	private String comments;
	private String actionTaken;
	private String uploadFile;
	private String natureOfIpInfringement;
	private int showCauseNoticeId;
	private int showCauseNoticeFormId;
	private String section;
	private String advocateName; // notice sent to
	private String advocateAddress; // notice sent address
	private String allegation;
	private int claims;
	private String noticeClassification;
	private String complaintName;
	private String complaintAddress;
	private String dealerName;
	private String dealerAddress;
	private String otherParties;
	private String partyNo;
	private String vehicleModelNumber;
	private String function;
	private String owner;
	private List<String> document = new ArrayList<String>(); // used in showCauseNoticeSummary
	private String docName;
	private String status;
	private String applicableSection;
	private String noticeSentTo;
	private String noticeSentAddress;
	private List<Long> fileSize;
	private String commentsDoc; // ShowCausenoticeForms Comments Field.
	private int reqId; // using in updateNotice

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getNoticeCategoryName() {
		return noticeCategoryName;
	}

	public void setNoticeCategoryName(String noticeCategoryName) {
		this.noticeCategoryName = noticeCategoryName;
	}

	public String getReceivedFrom() {
		return receivedFrom;
	}

	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(LocalDate receivedDate) {
		this.receivedDate = receivedDate;
	}

	public LocalDate getNoticeReplyDeadline() {
		return noticeReplyDeadline;
	}

	public void setNoticeReplyDeadline(LocalDate noticeReplyDeadline) {
		this.noticeReplyDeadline = noticeReplyDeadline;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public String getNatureOfIpInfringement() {
		return natureOfIpInfringement;
	}

	public void setNatureOfIpInfringement(String natureOfIpInfringement) {
		this.natureOfIpInfringement = natureOfIpInfringement;
	}

	public int getShowCauseNoticeId() {
		return showCauseNoticeId;
	}

	public void setShowCauseNoticeId(int showCauseNoticeId) {
		this.showCauseNoticeId = showCauseNoticeId;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getAdvocateName() {
		return advocateName;
	}

	public void setAdvocateName(String advocateName) {
		this.advocateName = advocateName;
	}

	public String getAdvocateAddress() {
		return advocateAddress;
	}

	public void setAdvocateAddress(String advocateAddress) {
		this.advocateAddress = advocateAddress;
	}

	public String getAllegation() {
		return allegation;
	}

	public void setAllegation(String allegation) {
		this.allegation = allegation;
	}

	public int getClaims() {
		return claims;
	}

	public void setClaims(int claims) {
		this.claims = claims;
	}

	public String getNoticeClassification() {
		return noticeClassification;
	}

	public void setNoticeClassification(String noticeClassification) {
		this.noticeClassification = noticeClassification;
	}

	public String getComplaintName() {
		return complaintName;
	}

	public void setComplaintName(String complaintName) {
		this.complaintName = complaintName;
	}

	public String getComplaintAddress() {
		return complaintAddress;
	}

	public void setComplaintAddress(String complaintAddress) {
		this.complaintAddress = complaintAddress;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getDealerAddress() {
		return dealerAddress;
	}

	public void setDealerAddress(String dealerAddress) {
		this.dealerAddress = dealerAddress;
	}

	public String getOtherParties() {
		return otherParties;
	}

	public void setOtherParties(String otherParties) {
		this.otherParties = otherParties;
	}

	public String getPartyNo() {
		return partyNo;
	}

	public void setPartyNo(String partyNo) {
		this.partyNo = partyNo;
	}

	public String getVehicleModelNumber() {
		return vehicleModelNumber;
	}

	public void setVehicleModelNumber(String vehicleModelNumber) {
		this.vehicleModelNumber = vehicleModelNumber;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<String> getDocument() {
		return document;
	}

	public void setDocument(List<String> document) {
		this.document = document;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplicableSection() {
		return applicableSection;
	}

	public void setApplicableSection(String applicableSection) {
		this.applicableSection = applicableSection;
	}

	public String getNoticeSentTo() {
		return noticeSentTo;
	}

	public void setNoticeSentTo(String noticeSentTo) {
		this.noticeSentTo = noticeSentTo;
	}

	public String getNoticeSentAddress() {
		return noticeSentAddress;
	}

	public void setNoticeSentAddress(String noticeSentAddress) {
		this.noticeSentAddress = noticeSentAddress;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public List<Long> getFileSize() {
		return fileSize;
	}

	public void setFileSize(List<Long> fileSize) {
		this.fileSize = fileSize;
	}

	public String getCommentsDoc() {
		return commentsDoc;
	}

	public void setCommentsDoc(String commentsDoc) {
		this.commentsDoc = commentsDoc;
	}

	public int getShowCauseNoticeFormId() {
		return showCauseNoticeFormId;
	}

	public void setShowCauseNoticeFormId(int showCauseNoticeFormId) {
		this.showCauseNoticeFormId = showCauseNoticeFormId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	/*
	 * @Override public String toString() { StringBuilder builder = new
	 * StringBuilder(); builder.append("ShowCauseNoticeVO [loginId=");
	 * builder.append(loginId); builder.append(", entityName=");
	 * builder.append(entityName); builder.append(", formatName=");
	 * builder.append(formatName); builder.append(", zoneName=");
	 * builder.append(zoneName); builder.append(", unitName=");
	 * builder.append(unitName); builder.append(", noticeCategoryName=");
	 * builder.append(noticeCategoryName); builder.append(", receivedFrom=");
	 * builder.append(receivedFrom); builder.append(", issueDate=");
	 * builder.append(issueDate); builder.append(", receivedDate=");
	 * builder.append(receivedDate); builder.append(", noticeReplyDeadline=");
	 * builder.append(noticeReplyDeadline); builder.append(", subject=");
	 * builder.append(subject); builder.append(", referenceNo=");
	 * builder.append(referenceNo); builder.append(", comments=");
	 * builder.append(comments); builder.append(", actionTaken=");
	 * builder.append(actionTaken); builder.append(", natureOfIpInfringement=");
	 * builder.append(natureOfIpInfringement);
	 * builder.append(", showCauseNoticeId="); builder.append(showCauseNoticeId);
	 * builder.append(", section="); builder.append(section);
	 * builder.append(", advocateName="); builder.append(advocateName);
	 * builder.append(", advocateAddress="); builder.append(advocateAddress);
	 * builder.append(", allegation="); builder.append(allegation);
	 * builder.append(", claims="); builder.append(claims);
	 * builder.append(", noticeClassification=");
	 * builder.append(noticeClassification); builder.append(", complaintName=");
	 * builder.append(complaintName); builder.append(", complaintAddress=");
	 * builder.append(complaintAddress); builder.append(", dealerName=");
	 * builder.append(dealerName); builder.append(", dealerAddress=");
	 * builder.append(dealerAddress); builder.append(", otherParties=");
	 * builder.append(otherParties); builder.append(", partyNo=");
	 * builder.append(partyNo); builder.append(", vehicleModelNumber=");
	 * builder.append(vehicleModelNumber); builder.append(", function=");
	 * builder.append(function); builder.append(", owner="); builder.append(owner);
	 * builder.append(", document="); builder.append(document);
	 * builder.append(", status="); builder.append(status); builder.append("]");
	 * return builder.toString(); }
	 */

}
