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
	private String[] matterByAgainst; // using for sending request
	private List<String> matterByAgainstList = new ArrayList<String>();
	private String matterBy; // used in modal to add
	private String coZone;
	private String customerType;
	private String againstPartyClientCustomerType;
	private String counterPartyName; // this might be changed based on selection of type on ui side.
	private String coCounterParties;
	private String subject;
	private String categoryName;
	private String caseNumber;
	private String risk;
	private String claim;
	private float possibleClaim; // PossibleClaim
	private String pantaloonFileNo;
	private String underActName;
	private String underSection;
	private String otherUnderacts;
	private String courtTypeName;
	private String stateName;
	private String cityName;
	private String firNo;
	private String courtForum;
	private String judgeName;
	private String status;
	private LocalDate dateOfReceiptOfMatter; // dateOfReceiptOfMatter/Case
	private String factsOfLitigation;
	private String stage;
	private String counselAssesment;
	private String remark;
	private LocalDate nextHearingDate;
	private String seniorCounsel;
	private String lawfirm; // Lawfirm/Individual
	private String function;
	private LocalDate caseRelateFromDate;
	private LocalDate caseRelateToDate;
	private String litigationId; // to display in RestoreLitigation

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

	public String getCounterPartyName() {
		return counterPartyName;
	}

	public void setCounterPartyName(String counterPartyName) {
		this.counterPartyName = counterPartyName;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getClaim() {
		return claim;
	}

	public void setClaim(String claim) {
		this.claim = claim;
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

	public String getUnderActName() {
		return underActName;
	}

	public void setUnderActName(String underActName) {
		this.underActName = underActName;
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

	public String getCourtTypeName() {
		return courtTypeName;
	}

	public void setCourtTypeName(String courtTypeName) {
		this.courtTypeName = courtTypeName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public LocalDate getDateOfReceiptOfMatter() {
		return dateOfReceiptOfMatter;
	}

	public void setDateOfReceiptOfMatter(LocalDate dateOfReceiptOfMatter) {
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

	public LocalDate getCaseRelateFromDate() {
		return caseRelateFromDate;
	}

	public void setCaseRelateFromDate(LocalDate caseRelateFromDate) {
		this.caseRelateFromDate = caseRelateFromDate;
	}

	public LocalDate getCaseRelateToDate() {
		return caseRelateToDate;
	}

	public void setCaseRelateToDate(LocalDate caseRelateToDate) {
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

	public String getMatterBy() {
		return matterBy;
	}

	public void setMatterBy(String matterBy) {
		this.matterBy = matterBy;
	}

}
