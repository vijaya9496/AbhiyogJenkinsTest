package com.fg.ss.abhiyog.common.vo;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ConnectedLitigationVO {

	private String loginId;
	private String litigationId;
	private int litigationOId;
	private String comments;
	private String connectedLitigationId;
	private String witnessName;
	private int witnessId;
	private String resultName;
	private LocalDate disposedDate;
	private String statusName;
	private String hearingDate;
	private String documentTitle;
	private int lawfirmBillingId;
	private String billingType;
	private float billingAmount;
	private LocalDate billingDate;
	private String remark;
	private MultipartFile uploadFile;
	private String uploadComments;
	private LocalDate hearingDt;
	private String stage;
	private String stageDetails;
	private int hearingId;
	private String event;
	private String addedBy;
	private String attendedBy;
	private StringBuilder activityDescription;
	private String activityType;
	private String updatedBy;
	private int lHistoryId; // hearingId in history tab(ltgn_litigation_log id)
	private int logId; // litigationhistorytLogId
	private LocalDateTime modifiedDate;
	private Long fileSize;

	public String getLitigationId() {
		return litigationId;
	}

	public void setLitigationId(String litigationId) {
		this.litigationId = litigationId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getConnectedLitigationId() {
		return connectedLitigationId;
	}

	public void setConnectedLitigationId(String connectedLitigationId) {
		this.connectedLitigationId = connectedLitigationId;
	}

	public String getWitnessName() {
		return witnessName;
	}

	public void setWitnessName(String witnessName) {
		this.witnessName = witnessName;
	}

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

	public LocalDate getDisposedDate() {
		return disposedDate;
	}

	public void setDisposedDate(LocalDate disposedDate) {
		this.disposedDate = disposedDate;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getHearingDate() {
		return hearingDate;
	}

	public void setHearingDate(String hearingDate) {
		this.hearingDate = hearingDate;
	}

	public String getDocumentTitle() {
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public float getBillingAmount() {
		return billingAmount;
	}

	public void setBillingAmount(float billingAmount) {
		this.billingAmount = billingAmount;
	}

	public LocalDate getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDate getHearingDt() {
		return hearingDt;
	}

	public void setHearingDt(LocalDate hearingDt) {
		this.hearingDt = hearingDt;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getStageDetails() {
		return stageDetails;
	}

	public void setStageDetails(String stageDetails) {
		this.stageDetails = stageDetails;
	}

	public int getHearingId() {
		return hearingId;
	}

	public void setHearingId(int hearingId) {
		this.hearingId = hearingId;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getAttendedBy() {
		return attendedBy;
	}

	public void setAttendedBy(String attendedBy) {
		this.attendedBy = attendedBy;
	}

	public StringBuilder getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(StringBuilder activityDescription) {
		this.activityDescription = activityDescription;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getlHistoryId() {
		return lHistoryId;
	}

	public void setlHistoryId(int lHistoryId) {
		this.lHistoryId = lHistoryId;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getLitigationOId() {
		return litigationOId;
	}

	public void setLitigationOId(int litigationOId) {
		this.litigationOId = litigationOId;
	}

	public int getWitnessId() {
		return witnessId;
	}

	public void setWitnessId(int witnessId) {
		this.witnessId = witnessId;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadComments() {
		return uploadComments;
	}

	public void setUploadComments(String uploadComments) {
		this.uploadComments = uploadComments;
	}

	public int getLawfirmBillingId() {
		return lawfirmBillingId;
	}

	public void setLawfirmBillingId(int lawfirmBillingId) {
		this.lawfirmBillingId = lawfirmBillingId;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	
	
	

}
