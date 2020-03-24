package com.fg.ss.abhiyog.common.vo;

import java.time.LocalDate;
import java.util.Date;

public class LitigationSummaryVO {

	private String litigationId;
	private String status;
	private String fileNo;
	private String entityName;
	private String unitName; // LocationName
	private String counterParty;
	private String customerType;
	private String caseNumber;
	private String subject;
	private String stage; // currentStage
	private LocalDate hearingDate;
	private String riskLevel;
	private String possibleClaim;
	private String remark;
	private String zoneName;
	private String coCounterParties;
	private String coParties;
	private String format;
	private String coFormat;
	private String coZone;
	private String matterByAgainst; // matterBy/Against
	private String function; // deptName
	private String address;
	private String againstPartyClientType;
	private String underActName;

	private int litigationOId;

	public String getLitigationId() {
		return litigationId;
	}

	public void setLitigationId(String litigationId) {
		this.litigationId = litigationId;
	}

	public int getLitigationOId() {
		return litigationOId;
	}

	public void setLitigationOId(int litigationOId) {
		this.litigationOId = litigationOId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(String counterParty) {
		this.counterParty = counterParty;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public LocalDate getHearingDate() {
		return hearingDate;
	}

	public void setHearingDate(LocalDate hearingDate) {
		this.hearingDate = hearingDate;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getPossibleClaim() {
		return possibleClaim;
	}

	public void setPossibleClaim(String possibleClaim) {
		this.possibleClaim = possibleClaim;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCoCounterParties() {
		return coCounterParties;
	}

	public void setCoCounterParties(String coCounterParties) {
		this.coCounterParties = coCounterParties;
	}

	public String getCoParties() {
		return coParties;
	}

	public void setCoParties(String coParties) {
		this.coParties = coParties;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCoFormat() {
		return coFormat;
	}

	public void setCoFormat(String coFormat) {
		this.coFormat = coFormat;
	}

	public String getCoZone() {
		return coZone;
	}

	public void setCoZone(String coZone) {
		this.coZone = coZone;
	}

	public String getMatterByAgainst() {
		return matterByAgainst;
	}

	public void setMatterByAgainst(String matterByAgainst) {
		this.matterByAgainst = matterByAgainst;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAgainstPartyClientType() {
		return againstPartyClientType;
	}

	public void setAgainstPartyClientType(String againstPartyClientType) {
		this.againstPartyClientType = againstPartyClientType;
	}

	public String getUnderActName() {
		return underActName;
	}

	public void setUnderActName(String underActName) {
		this.underActName = underActName;
	}
	
	

}
