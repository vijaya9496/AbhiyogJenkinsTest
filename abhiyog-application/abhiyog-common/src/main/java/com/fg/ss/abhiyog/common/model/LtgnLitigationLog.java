package com.fg.ss.abhiyog.common.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ltgn_litigation_log")
public class LtgnLitigationLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "litigation_log_oid")
	private int litigationLogId;

	@Column(name = "customer_oid")
	private int customerId;

	@Column(name = "customer_type")
	private String customerType;

	@Column(name = "against_party_name")
	private String againstPartyName;

	@Column(name = "against_party_client_type")
	private String againstPartyClient;

	@Column(name = "against_party_advocate_name")
	private String againstPartyAdvocateName;

	@Column(name = "against_party_advocate_mobile")
	private String againstPartyAdvocateMobile;

	@Column(name = "case_Type_oid")
	private String caseTypeId;

	@Column(name = "case_number")
	private String caseNumber;

	@Column(name = "starting_year")
	private String startingYear;

	@Column(name = "under_act")
	private String underAct;

	@Column(name = "court")
	private String court;

	@Column(name = "court_number")
	private String courtNumber;

	@Column(name = "city")
	private String city;

	@Column(name = "stage")
	private String stage;

	@Column(name = "hearing_event")
	private String hearingEvent;

	@Column(name = "fact_of_litigation_matter")
	private String factOfLitigationMatter;

	@Column(name = "file_no")
	private String fileNo;

	@Column(name = "date_of_hearing")
	private LocalDate dateOfHearing;

	@Column(name = "next_date_of_hearing")
	private LocalDate nextDateOfHearing;

	@Column(name = "time_of_case_am_pm")
	private String timeOfCaseAMPM;

	@Column(name = "litigation_handled_by_partner_oid")
	private int litigationHandledByPartnerid;

	@Column(name = "inhouse_counsel_oid")
	private int inHouseCounselId;

	@Column(name = "inhouse_counsel_other_oid")
	private int inHouseCounselOtherId;

	@Column(name = "outside_counsel_oid")
	private int ousideCounselId;

	@Column(name = "outside_counsel_other_oid")
	private int outsideCounselOtherId;

	@Column(name = "last_modified_date")
	private Date lastModifiedDate;

	@Column(name = "last_modified_by_counsel_oid")
	private int lastModifiedByCounselId;

	@Column(name = "hearing_attended_by")
	private String hearingAttendedBy;

	@Column(name = "hearing_status")
	private String hearingStatus;

	@Column(name = "action_taken")
	private String actionTaken;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stage_details_oid")
	private StageMaster stageMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "litigation_oid")
	private Litigation litigation;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hearing_added_by")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "litigation_log_oid")
	private LitigationHistoryTLog litigationHistoryTLog;

	public int getLitigationLogId() {
		return litigationLogId;
	}

	public void setLitigationLogId(int litigationLogId) {
		this.litigationLogId = litigationLogId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getAgainstPartyName() {
		return againstPartyName;
	}

	public void setAgainstPartyName(String againstPartyName) {
		this.againstPartyName = againstPartyName;
	}

	public String getAgainstPartyClient() {
		return againstPartyClient;
	}

	public void setAgainstPartyClient(String againstPartyClient) {
		this.againstPartyClient = againstPartyClient;
	}

	public String getAgainstPartyAdvocateName() {
		return againstPartyAdvocateName;
	}

	public void setAgainstPartyAdvocateName(String againstPartyAdvocateName) {
		this.againstPartyAdvocateName = againstPartyAdvocateName;
	}

	public String getAgainstPartyAdvocateMobile() {
		return againstPartyAdvocateMobile;
	}

	public void setAgainstPartyAdvocateMobile(String againstPartyAdvocateMobile) {
		this.againstPartyAdvocateMobile = againstPartyAdvocateMobile;
	}

	public String getCaseTypeId() {
		return caseTypeId;
	}

	public void setCaseTypeId(String caseTypeId) {
		this.caseTypeId = caseTypeId;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getStartingYear() {
		return startingYear;
	}

	public void setStartingYear(String startingYear) {
		this.startingYear = startingYear;
	}

	public String getUnderAct() {
		return underAct;
	}

	public void setUnderAct(String underAct) {
		this.underAct = underAct;
	}

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	public String getCourtNumber() {
		return courtNumber;
	}

	public void setCourtNumber(String courtNumber) {
		this.courtNumber = courtNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getHearingEvent() {
		return hearingEvent;
	}

	public void setHearingEvent(String hearingEvent) {
		this.hearingEvent = hearingEvent;
	}

	public String getFactOfLitigationMatter() {
		return factOfLitigationMatter;
	}

	public void setFactOfLitigationMatter(String factOfLitigationMatter) {
		this.factOfLitigationMatter = factOfLitigationMatter;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public LocalDate getDateOfHearing() {
		return dateOfHearing;
	}

	public void setDateOfHearing(LocalDate dateOfHearing) {
		this.dateOfHearing = dateOfHearing;
	}

	public LocalDate getNextDateOfHearing() {
		return nextDateOfHearing;
	}

	public void setNextDateOfHearing(LocalDate nextDateOfHearing) {
		this.nextDateOfHearing = nextDateOfHearing;
	}

	public String getTimeOfCaseAMPM() {
		return timeOfCaseAMPM;
	}

	public void setTimeOfCaseAMPM(String timeOfCaseAMPM) {
		this.timeOfCaseAMPM = timeOfCaseAMPM;
	}

	public int getLitigationHandledByPartnerid() {
		return litigationHandledByPartnerid;
	}

	public void setLitigationHandledByPartnerid(int litigationHandledByPartnerid) {
		this.litigationHandledByPartnerid = litigationHandledByPartnerid;
	}

	public int getInHouseCounselId() {
		return inHouseCounselId;
	}

	public void setInHouseCounselId(int inHouseCounselId) {
		this.inHouseCounselId = inHouseCounselId;
	}

	public int getInHouseCounselOtherId() {
		return inHouseCounselOtherId;
	}

	public void setInHouseCounselOtherId(int inHouseCounselOtherId) {
		this.inHouseCounselOtherId = inHouseCounselOtherId;
	}

	public int getOusideCounselId() {
		return ousideCounselId;
	}

	public void setOusideCounselId(int ousideCounselId) {
		this.ousideCounselId = ousideCounselId;
	}

	public int getOutsideCounselOtherId() {
		return outsideCounselOtherId;
	}

	public void setOutsideCounselOtherId(int outsideCounselOtherId) {
		this.outsideCounselOtherId = outsideCounselOtherId;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public int getLastModifiedByCounselId() {
		return lastModifiedByCounselId;
	}

	public void setLastModifiedByCounselId(int lastModifiedByCounselId) {
		this.lastModifiedByCounselId = lastModifiedByCounselId;
	}

	public String getHearingAttendedBy() {
		return hearingAttendedBy;
	}

	public void setHearingAttendedBy(String hearingAttendedBy) {
		this.hearingAttendedBy = hearingAttendedBy;
	}

	public String getHearingStatus() {
		return hearingStatus;
	}

	public void setHearingStatus(String hearingStatus) {
		this.hearingStatus = hearingStatus;
	}

	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public StageMaster getStageMaster() {
		return stageMaster;
	}

	public void setStageMaster(StageMaster stageMaster) {
		this.stageMaster = stageMaster;
	}

	public Litigation getLitigation() {
		return litigation;
	}

	public void setLitigation(Litigation litigation) {
		this.litigation = litigation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LitigationHistoryTLog getLitigationHistoryTLog() {
		return litigationHistoryTLog;
	}

	public void setLitigationHistoryTLog(LitigationHistoryTLog litigationHistoryTLog) {
		this.litigationHistoryTLog = litigationHistoryTLog;
	}

}
