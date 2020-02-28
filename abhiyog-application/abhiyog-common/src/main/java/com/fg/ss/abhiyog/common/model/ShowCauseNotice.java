package com.fg.ss.abhiyog.common.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "showcausenotice")

public class ShowCauseNotice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "showcausenoticeoid")
	private int showCauseNoticeId;

	@Column(name = "noticereceivedfrom")
	private String noticeReceivedFrom;

	@Column(name = "noticereceiveddt")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
//	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate noticeReceivedDate;

	@Column(name = "noticereplydeadline")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
//	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate noticeReplyDeadline;

	@Column(name = "comment")
	private String comment;

	@Column(name = "createdt")
	private LocalDateTime createDate;

	@Column(name = "status")
	private String status;

	@Column(name = "subject")
	private String subject;

	@Column(name = "issuedt")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
//	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate issueDate;

	@Column(name = "referenceno")
	private String referenceNo;

	@Column(name = "advocatename")
	private String advocateName;

	@Column(name = "advocateaddress")
	private String advocateAddress;

	@Column(name = "allegation")
	private String allegation;

	@Column(name = "claims")
	private int claims;

	@Column(name = "noticesent_complaint_name")
	private String noticeSentComplaintName;

	@Column(name = "noticesent_complaint_address")
	private String noticeSentComplaintAddress;

	@Column(name = "natureofipinfringement")
	private String natureOfIpInfringement;

	@Column(name = "section")
	private String section;

	@Column(name = "dealername")
	private String dealerName;

	@Column(name = "dealeraddress")
	private String dealerAddress;

	@Column(name = "otherparties")
	private String otherParties;

	@Column(name = "partyno")
	private String partyNo;

	@Column(name = "vehiclemodelnumber")
	private String vehicleModelNumber;

	@Column(name = "actiontaken")
	private String actionTaken;

	@Column(name = "owner_oid")
	private int ownerId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deptoid")
	private Dept dept;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unitoid")
	private Units units;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formatoid")
	private Format format;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "noticecategoryoid")
	private NoticeCategory noticeCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "noticeclassificationoid")
	private NoticeClassification noticeClassification;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uploaded_by_oid")
	private User user;

	@OneToMany(mappedBy = "showCauseNotice")
	private List<ShowCauseNoticeForms> showCauseNoticeForms;

	public int getShowCauseNoticeId() {
		return showCauseNoticeId;
	}

	public void setShowCauseNoticeId(int showCauseNoticeId) {
		this.showCauseNoticeId = showCauseNoticeId;
	}

	public String getNoticeReceivedFrom() {
		return noticeReceivedFrom;
	}

	public void setNoticeReceivedFrom(String noticeReceivedFrom) {
		this.noticeReceivedFrom = noticeReceivedFrom;
	}

	public LocalDate getNoticeReceivedDate() {
		return noticeReceivedDate;
	}

	public void setNoticeReceivedDate(LocalDate noticeReceivedDate) {
		this.noticeReceivedDate = noticeReceivedDate;
	}

	public LocalDate getNoticeReplyDeadline() {
		return noticeReplyDeadline;
	}

	public void setNoticeReplyDeadline(LocalDate noticeReplyDeadline) {
		this.noticeReplyDeadline = noticeReplyDeadline;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
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

	public String getNoticeSentComplaintName() {
		return noticeSentComplaintName;
	}

	public void setNoticeSentComplaintName(String noticeSentComplaintName) {
		this.noticeSentComplaintName = noticeSentComplaintName;
	}

	public String getNoticeSentComplaintAddress() {
		return noticeSentComplaintAddress;
	}

	public void setNoticeSentComplaintAddress(String noticeSentComplaintAddress) {
		this.noticeSentComplaintAddress = noticeSentComplaintAddress;
	}

	public String getNatureOfIpInfringement() {
		return natureOfIpInfringement;
	}

	public void setNatureOfIpInfringement(String natureOfIpInfringement) {
		this.natureOfIpInfringement = natureOfIpInfringement;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
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

	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Units getUnits() {
		return units;
	}

	public void setUnits(Units units) {
		this.units = units;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public NoticeCategory getNoticeCategory() {
		return noticeCategory;
	}

	public void setNoticeCategory(NoticeCategory noticeCategory) {
		this.noticeCategory = noticeCategory;
	}

	public NoticeClassification getNoticeClassification() {
		return noticeClassification;
	}

	public void setNoticeClassification(NoticeClassification noticeClassification) {
		this.noticeClassification = noticeClassification;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ShowCauseNoticeForms> getShowCauseNoticeForms() {
		return showCauseNoticeForms;
	}

	public void setShowCauseNoticeForms(List<ShowCauseNoticeForms> showCauseNoticeForms) {
		this.showCauseNoticeForms = showCauseNoticeForms;
	}

}
