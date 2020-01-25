package com.fg.ss.abhiyog.common.vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddLitigationVO {

	private String loginId;
	private String entityName;
	private String zoneName;
	private String unitName;
	private String format;
	private String address;
	private String storeOfficePremises; // operational or non-operational
	private String coFormat;
	private String[] matterByAgainst;	//using for sending request
	private List<String> matterByAgainstList = new ArrayList<String>();
	private String coZone;
	private String customerType;
	private String againstPartyClientCustomerType;
	private String counterParty; // this might be changed based on selection of type on ui side.
	private String coCounterParties;
	private String subject;
	private String category;
	private String caseNumber;
	private String riskAssesment;
	private String possibleClaimRange;
	private float possibleClaim;
	private String pantaloonFileNo;
	private String underActs;
	private String underSection;
	private String otherUnderacts;
	private String courtType;
	private String state;
	private String city;
	private String firNo;
	private String courtForum;
	private String judgeName;
	private String status;
	private Date dateOfReceiptOfMatter; // dateOfReceiptOfMatter/Case
	private String factsOfLitigation;
	private String stage;
	private String counselAssesment;
	private String remark;
	private LocalDate nextHearingDate;
	private String seniorCounsel;
	private String lawfirm; // Lawfirm/Individual
	private String function;
	private Date caseRelateFromDate;
	private Date caseRelateToDate;
	private String litigationId; //to display in RestoreLitigation
 

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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStoreOfficePremises() {
		return storeOfficePremises;
	}

	public void setStoreOfficePremises(String storeOfficePremises) {
		this.storeOfficePremises = storeOfficePremises;
	}

	public String getCoFormat() {
		return coFormat;
	}

	public void setCoFormat(String coFormat) {
		this.coFormat = coFormat;
	}

	public String[] getMatterByAgainst() {
		return matterByAgainst;
	}

	public void setMatterByAgainst(String[] matterByAgainst) {
		this.matterByAgainst = matterByAgainst;
	}

	public String getCoZone() {
		return coZone;
	}

	public void setCoZone(String coZone) {
		this.coZone = coZone;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	

	public String getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(String counterParty) {
		this.counterParty = counterParty;
	}

	public String getCoCounterParties() {
		return coCounterParties;
	}

	public void setCoCounterParties(String coCounterParties) {
		this.coCounterParties = coCounterParties;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getRiskAssesment() {
		return riskAssesment;
	}

	public void setRiskAssesment(String riskAssesment) {
		this.riskAssesment = riskAssesment;
	}

	public String getPossibleClaimRange() {
		return possibleClaimRange;
	}

	public void setPossibleClaimRange(String possibleClaimRange) {
		this.possibleClaimRange = possibleClaimRange;
	}

	public float getPossibleClaim() {
		return possibleClaim;
	}

	public void setPossibleClaim(float possibleClaim) {
		this.possibleClaim = possibleClaim;
	}

	public String getPantaloonFileNo() {
		return pantaloonFileNo;
	}

	public void setPantaloonFileNo(String pantaloonFileNo) {
		this.pantaloonFileNo = pantaloonFileNo;
	}

	public String getUnderActs() {
		return underActs;
	}

	public void setUnderActs(String underActs) {
		this.underActs = underActs;
	}

	public String getUnderSection() {
		return underSection;
	}

	public void setUnderSection(String underSection) {
		this.underSection = underSection;
	}

	public String getOtherUnderacts() {
		return otherUnderacts;
	}

	public void setOtherUnderacts(String otherUnderacts) {
		this.otherUnderacts = otherUnderacts;
	}

	public String getCourtType() {
		return courtType;
	}

	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirNo() {
		return firNo;
	}

	public void setFirNo(String firNo) {
		this.firNo = firNo;
	}

	public String getCourtForum() {
		return courtForum;
	}

	public void setCourtForum(String courtForum) {
		this.courtForum = courtForum;
	}

	public String getJudgeName() {
		return judgeName;
	}

	public void setJudgeName(String judgeName) {
		this.judgeName = judgeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateOfReceiptOfMatter() {
		return dateOfReceiptOfMatter;
	}

	public void setDateOfReceiptOfMatter(Date dateOfReceiptOfMatter) {
		this.dateOfReceiptOfMatter = dateOfReceiptOfMatter;
	}

	public String getFactsOfLitigation() {
		return factsOfLitigation;
	}

	public void setFactsOfLitigation(String factsOfLitigation) {
		this.factsOfLitigation = factsOfLitigation;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getCounselAssesment() {
		return counselAssesment;
	}

	public void setCounselAssesment(String counselAssesment) {
		this.counselAssesment = counselAssesment;
	}

	public LocalDate getNextHearingDate() {
		return nextHearingDate;
	}

	public void setNextHearingDate(LocalDate nextHearingDate) {
		this.nextHearingDate = nextHearingDate;
	}

	public String getSeniorCounsel() {
		return seniorCounsel;
	}

	public void setSeniorCounsel(String seniorCounsel) {
		this.seniorCounsel = seniorCounsel;
	}

	public String getLawfirm() {
		return lawfirm;
	}

	public void setLawfirm(String lawfirm) {
		this.lawfirm = lawfirm;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Date getCaseRelateFromDate() {
		return caseRelateFromDate;
	}

	public void setCaseRelateFromDate(Date caseRelateFromDate) {
		this.caseRelateFromDate = caseRelateFromDate;
	}

	public Date getCaseRelateToDate() {
		return caseRelateToDate;
	}

	public void setCaseRelateToDate(Date caseRelateToDate) {
		this.caseRelateToDate = caseRelateToDate;
	}

	public List<String> getMatterByAgainstList() {
		return matterByAgainstList;
	}

	public void setMatterByAgainstList(List<String> matterByAgainstList) {
		this.matterByAgainstList = matterByAgainstList;
	}

	public String getAgainstPartyClientCustomerType() {
		return againstPartyClientCustomerType;
	}

	public void setAgainstPartyClientCustomerType(String againstPartyClientCustomerType) {
		this.againstPartyClientCustomerType = againstPartyClientCustomerType;
	}

	public String getLitigationId() {
		return litigationId;
	}

	public void setLitigationId(String litigationId) {
		this.litigationId = litigationId;
	}
	
}
