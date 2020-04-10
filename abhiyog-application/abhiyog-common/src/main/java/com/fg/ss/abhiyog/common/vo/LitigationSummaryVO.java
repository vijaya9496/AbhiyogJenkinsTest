package com.fg.ss.abhiyog.common.vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LitigationSummaryVO {

	private String litigationId;
	private String loginId;
	private String status;
	private String fileNo;
	private String entityName;
	private String unitName; // LocationName
	private int unitOId;
	private String counterPartyName;
	private String customerType;
	private String caseNumber;
	private String subject;
	private String stage; // currentStage
	private LocalDate hearingDate;
	private String risk;
	private String claim; //range
	private float possibleClaim;
	private String remark;
	private String zoneName;
	private String coCounterParties;
	private String coParties;
	private String format;
	private String coFormat;
	private String coZone;
	private String matterBy; // matterBy/Against
	private List<String> matterByAgainst = new ArrayList<>();
	private String function; // deptName
	private String address;
	private String againstPartyClientType;
	private String underActName;
	private String categoryName;
	private String underSection;
	private String otherUnderAct;
	private String stateName;
	private String cityName;
	private String courtCity;
	private String policeStation;
	private String courtTypeName;
	private String courtForum;
	private String judgeName;
	private String factsOfLitigation;
	private String storeOfficePremises;
	private String lawfirm; //Lawfirm/Individual
	private String seniorCounsel;
	private LocalDate caseRelateFromDate;
	private LocalDate caseRelateToDate;
	private String caseRelateFrmDte;
	private String caseRelateToDte;
	private LocalDate dateOfReceiptOfMatterCase; //CaseFileOnDate
	private LocalDate nextHearingDate;
	private String counselAssesment;
	private String firNo;
	private int updated;
	private int notUpdated;
	private int total;

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

	

	public int getUnitOId() {
		return unitOId;
	}

	public void setUnitOId(int unitOId) {
		this.unitOId = unitOId;
	}

	public String getCounterPartyName() {
		return counterPartyName;
	}

	public void setCounterPartyName(String counterPartyName) {
		this.counterPartyName = counterPartyName;
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

	public String getMatterBy() {
		return matterBy;
	}

	public void setMatterBy(String matterBy) {
		this.matterBy = matterBy;
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

	

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUnderSection() {
		return underSection;
	}

	public void setUnderSection(String underSection) {
		this.underSection = underSection;
	}

	public String getOtherUnderAct() {
		return otherUnderAct;
	}

	public void setOtherUnderAct(String otherUnderAct) {
		this.otherUnderAct = otherUnderAct;
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

	public String getCourtCity() {
		return courtCity;
	}

	public void setCourtCity(String courtCity) {
		this.courtCity = courtCity;
	}

	public String getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}

	public String getCourtTypeName() {
		return courtTypeName;
	}

	public void setCourtTypeName(String courtTypeName) {
		this.courtTypeName = courtTypeName;
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

	public String getFactsOfLitigation() {
		return factsOfLitigation;
	}

	public void setFactsOfLitigation(String factsOfLitigation) {
		this.factsOfLitigation = factsOfLitigation;
	}

	public String getStoreOfficePremises() {
		return storeOfficePremises;
	}

	public void setStoreOfficePremises(String storeOfficePremises) {
		this.storeOfficePremises = storeOfficePremises;
	}

	public String getLawfirm() {
		return lawfirm;
	}

	public void setLawfirm(String lawfirm) {
		this.lawfirm = lawfirm;
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

	public LocalDate getDateOfReceiptOfMatterCase() {
		return dateOfReceiptOfMatterCase;
	}

	public void setDateOfReceiptOfMatterCase(LocalDate dateOfReceiptOfMatterCase) {
		this.dateOfReceiptOfMatterCase = dateOfReceiptOfMatterCase;
	}

	public String getCounselAssesment() {
		return counselAssesment;
	}

	public void setCounselAssesment(String counselAssesment) {
		this.counselAssesment = counselAssesment;
	}

	public String getFirNo() {
		return firNo;
	}

	public void setFirNo(String firNo) {
		this.firNo = firNo;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	public int getNotUpdated() {
		return notUpdated;
	}

	public void setNotUpdated(int notUpdated) {
		this.notUpdated = notUpdated;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public float getPossibleClaim() {
		return possibleClaim;
	}

	public void setPossibleClaim(float possibleClaim) {
		this.possibleClaim = possibleClaim;
	}

	public LocalDate getNextHearingDate() {
		return nextHearingDate;
	}

	public void setNextHearingDate(LocalDate nextHearingDate) {
		this.nextHearingDate = nextHearingDate;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public List<String> getMatterByAgainst() {
		return matterByAgainst;
	}

	public void setMatterByAgainst(List<String> matterByAgainst) {
		this.matterByAgainst = matterByAgainst;
	}

	public String getSeniorCounsel() {
		return seniorCounsel;
	}

	public void setSeniorCounsel(String seniorCounsel) {
		this.seniorCounsel = seniorCounsel;
	}

	public String getCaseRelateFrmDte() {
		return caseRelateFrmDte;
	}

	public void setCaseRelateFrmDte(String caseRelateFrmDte) {
		this.caseRelateFrmDte = caseRelateFrmDte;
	}

	public String getCaseRelateToDte() {
		return caseRelateToDte;
	}

	public void setCaseRelateToDte(String caseRelateToDte) {
		this.caseRelateToDte = caseRelateToDte;
	}
	
	

}
