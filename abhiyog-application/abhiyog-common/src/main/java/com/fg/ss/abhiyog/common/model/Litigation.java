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
@Table(name = "ltgn_litigation")
public class Litigation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "litigation_oid")
	private int litigationOId;

	@Column(name = "litigation_id")
	private String litigationId;

	@Column(name = "against_party_name")
	private String againstPartyName;

	@Column(name = "against_party_client_type")
	private String againstPartyClientType;

	@Column(name = "against_party_advocate_name")
	private String againstPartyAdvocateName;

	@Column(name = "against_party_advocate_mobile")
	private String againstPartyAdvocateMobile;

	@Column(name = "case_number")
	private String caseNumber;

	@Column(name = "fir_number")
	private String firNumber;

	@Column(name = "starting_year")
	private String startingYear;

	@Column(name = "under_act")
	private String underActs;

	@Column(name = "court")
	private String court;

	@Column(name = "court_number")
	private String courtNumber;

	@Column(name = "city")
	private String city;

	@Column(name = "stage")
	private String stage;

	@Column(name = "fact_of_litigation_matter")
	private String factOfLitigationMatter;

	@Column(name = "file_no")
	private String fileNo;

	@Column(name = "fileno")
	private int fileNumber;

	@Column(name = "next_date_of_hearing")
	private Date nextDateOfHearing;

	@Column(name = "time_of_case_am_pm")
	private String timeOfCaseAmPm;

	@Column(name = "inhouse_counsel_id")
	private int inHouseCounselId;

	@Column(name = "inhouse_counsel_other_id")
	private int inHouseCounselOtherId;

	@Column(name = "outside_counsel_id")
	private int outsideCounselId;

	@Column(name = "outside_counsel_other_id")
	private int outsideCounselOtherId;

	@Column(name = "create_date")
	private LocalDate createDate;

	@Column(name = "police_station")
	private String policeStation;

	@Column(name = "otherFiles")
	private String otherFiles;

	@Column(name = "file_available")
	private String fileAvailable;

	@Column(name = "case_file_on_dt")
	private Date caseFileOnDate;

	@Column(name = "provision_made")
	private String provisionMade;

	@Column(name = "counsel_assesment")
	private String counselAssesment;

	@Column(name = "case_relate_from_dt")
	private Date caseRelateFromDate;

	@Column(name = "case_relate_to_dt")
	private Date caseRelateToDate;

	@Column(name = "comment")
	private String comment;

	@Column(name = "remark")
	private String remark;

	@Column(name = "subject")
	private String subject;

	@Column(name = "other_under_act")
	private String otherUnderAct;

	@Column(name = "business_unit_id")
	private int businessUnitId;

	@Column(name = "area_office_id")
	private int areaOfficeId;

	@Column(name = "re_open_comments")
	private String reOpenComments;

	@Column(name = "result_id")
	private int resultId;

	@Column(name = "co_entity")
	private String coEntity;

	@Column(name = "co_region")
	private String coRegion;

	@Column(name = "exact_claim_amount")
	private float exactClaimAmount;

	@Column(name = "address")
	private String address;

	@Column(name = "exact_claim_remarks")
	private String exactClaimRemarks;

	@Column(name = "store_office_premises")
	private String storeOfficePremises;

	@Column(name = "co_counter_parties")
	private String coCounterParties;

	@Column(name = "under_section")
	private String underSection;

	@Column(name = "fir_no")
	private String firNo;

	@Column(name = "action_plan")
	private String actionPlan;

	@Column(name = "disposed_dt")
	private Date disposedDate;

	@Column(name = "unit_id_temp")
	private int unitIdTemp;

	@Column(name = "representative_id_temp")
	private int representativeIdTemp;

	@Column(name = "execution_type_id")
	private int executionTypeId;

	@Column(name = "liability_id")
	private int liabilityId;

	@Column(name = "argument_id")
	private int argumentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	private Dept dept;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	private Units units;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_type_id")
	private CustomerType customerType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_type_id")
	private LtgnCaseType ltgnCaseType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	private State state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "court_city_id")
	private CourtCity courtCity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "court_type_id")
	private CourtType courtType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "risk_id")
	private Risk risk;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "claim_id")
	private Claim claim;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lawfirm_id")
	private LawFirm lawFirm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "under_act_id")
	private UnderAct underAct;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "police_station_id")
	private PoliceStation policeStationId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "senior_counsel_id")
	private LawFirm lawFirmSenior;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "format_id")
	private Format format;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "representative_id")
	private LtgnRepresentativeMaster ltgnRepresentativeMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private CounterPartyDtls counterPartyDtls;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "litigation_oid")
	private List<LitigationMatterByAgainst> litigationMatterByAgainst;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "litigation_added_by_id")
	private User user;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="litigation_oid")
	private LitigationUnits litigationUnits;

	public int getLitigationOId() {
		return litigationOId;
	}

	public void setLitigationOId(int litigationOId) {
		this.litigationOId = litigationOId;
	}

	public String getLitigationId() {
		return litigationId;
	}

	public void setLitigationId(String litigationId) {
		this.litigationId = litigationId;
	}

	public String getAgainstPartyName() {
		return againstPartyName;
	}

	public void setAgainstPartyName(String againstPartyName) {
		this.againstPartyName = againstPartyName;
	}

	public String getAgainstPartyClientType() {
		return againstPartyClientType;
	}

	public void setAgainstPartyClientType(String againstPartyClientType) {
		this.againstPartyClientType = againstPartyClientType;
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

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getFirNumber() {
		return firNumber;
	}

	public void setFirNumber(String firNumber) {
		this.firNumber = firNumber;
	}

	public String getStartingYear() {
		return startingYear;
	}

	public void setStartingYear(String startingYear) {
		this.startingYear = startingYear;
	}

	public String getUnderActs() {
		return underActs;
	}

	public void setUnderActs(String underActs) {
		this.underActs = underActs;
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

	public int getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}

	public Date getNextDateOfHearing() {
		return nextDateOfHearing;
	}

	public void setNextDateOfHearing(Date nextDateOfHearing) {
		this.nextDateOfHearing = nextDateOfHearing;
	}

	public String getTimeOfCaseAmPm() {
		return timeOfCaseAmPm;
	}

	public void setTimeOfCaseAmPm(String timeOfCaseAmPm) {
		this.timeOfCaseAmPm = timeOfCaseAmPm;
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

	public int getOutsideCounselId() {
		return outsideCounselId;
	}

	public void setOutsideCounselId(int outsideCounselId) {
		this.outsideCounselId = outsideCounselId;
	}

	public int getOutsideCounselOtherId() {
		return outsideCounselOtherId;
	}

	public void setOutsideCounselOtherId(int outsideCounselOtherId) {
		this.outsideCounselOtherId = outsideCounselOtherId;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public String getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}

	public String getOtherFiles() {
		return otherFiles;
	}

	public void setOtherFiles(String otherFiles) {
		this.otherFiles = otherFiles;
	}

	public String getFileAvailable() {
		return fileAvailable;
	}

	public void setFileAvailable(String fileAvailable) {
		this.fileAvailable = fileAvailable;
	}

	public Date getCaseFileOnDate() {
		return caseFileOnDate;
	}

	public void setCaseFileOnDate(Date caseFileOnDate) {
		this.caseFileOnDate = caseFileOnDate;
	}

	public String getProvisionMade() {
		return provisionMade;
	}

	public void setProvisionMade(String provisionMade) {
		this.provisionMade = provisionMade;
	}

	public String getCounselAssesment() {
		return counselAssesment;
	}

	public void setCounselAssesment(String counselAssesment) {
		this.counselAssesment = counselAssesment;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOtherUnderAct() {
		return otherUnderAct;
	}

	public void setOtherUnderAct(String otherUnderAct) {
		this.otherUnderAct = otherUnderAct;
	}

	public int getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(int businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public int getAreaOfficeId() {
		return areaOfficeId;
	}

	public void setAreaOfficeId(int areaOfficeId) {
		this.areaOfficeId = areaOfficeId;
	}

	public String getReOpenComments() {
		return reOpenComments;
	}

	public void setReOpenComments(String reOpenComments) {
		this.reOpenComments = reOpenComments;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public String getCoEntity() {
		return coEntity;
	}

	public void setCoEntity(String coEntity) {
		this.coEntity = coEntity;
	}

	public String getCoRegion() {
		return coRegion;
	}

	public void setCoRegion(String coRegion) {
		this.coRegion = coRegion;
	}

	public float getExactClaimAmount() {
		return exactClaimAmount;
	}

	public void setExactClaimAmount(float exactClaimAmount) {
		this.exactClaimAmount = exactClaimAmount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getExactClaimRemarks() {
		return exactClaimRemarks;
	}

	public void setExactClaimRemarks(String exactClaimRemarks) {
		this.exactClaimRemarks = exactClaimRemarks;
	}

	public String getStoreOfficePremises() {
		return storeOfficePremises;
	}

	public void setStoreOfficePremises(String storeOfficePremises) {
		this.storeOfficePremises = storeOfficePremises;
	}

	public String getCoCounterParties() {
		return coCounterParties;
	}

	public void setCoCounterParties(String coCounterParties) {
		this.coCounterParties = coCounterParties;
	}

	public String getUnderSection() {
		return underSection;
	}

	public void setUnderSection(String underSection) {
		this.underSection = underSection;
	}

	public String getFirNo() {
		return firNo;
	}

	public void setFirNo(String firNo) {
		this.firNo = firNo;
	}

	public String getActionPlan() {
		return actionPlan;
	}

	public void setActionPlan(String actionPlan) {
		this.actionPlan = actionPlan;
	}

	public Date getDisposedDate() {
		return disposedDate;
	}

	public void setDisposedDate(Date disposedDate) {
		this.disposedDate = disposedDate;
	}

	public int getUnitIdTemp() {
		return unitIdTemp;
	}

	public void setUnitIdTemp(int unitIdTemp) {
		this.unitIdTemp = unitIdTemp;
	}

	public int getRepresentativeIdTemp() {
		return representativeIdTemp;
	}

	public void setRepresentativeIdTemp(int representativeIdTemp) {
		this.representativeIdTemp = representativeIdTemp;
	}

	public int getExecutionTypeId() {
		return executionTypeId;
	}

	public void setExecutionTypeId(int executionTypeId) {
		this.executionTypeId = executionTypeId;
	}

	public int getLiabilityId() {
		return liabilityId;
	}

	public void setLiabilityId(int liabilityId) {
		this.liabilityId = liabilityId;
	}

	public int getArgumentId() {
		return argumentId;
	}

	public void setArgumentId(int argumentId) {
		this.argumentId = argumentId;
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

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public LtgnCaseType getLtgnCaseType() {
		return ltgnCaseType;
	}

	public void setLtgnCaseType(LtgnCaseType ltgnCaseType) {
		this.ltgnCaseType = ltgnCaseType;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public CourtCity getCourtCity() {
		return courtCity;
	}

	public void setCourtCity(CourtCity courtCity) {
		this.courtCity = courtCity;
	}

	public CourtType getCourtType() {
		return courtType;
	}

	public void setCourtType(CourtType courtType) {
		this.courtType = courtType;
	}

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public LawFirm getLawFirm() {
		return lawFirm;
	}

	public void setLawFirm(LawFirm lawFirm) {
		this.lawFirm = lawFirm;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UnderAct getUnderAct() {
		return underAct;
	}

	public void setUnderAct(UnderAct underAct) {
		this.underAct = underAct;
	}

	public PoliceStation getPoliceStationId() {
		return policeStationId;
	}

	public void setPoliceStationId(PoliceStation policeStationId) {
		this.policeStationId = policeStationId;
	}

	public LawFirm getLawFirmSenior() {
		return lawFirmSenior;
	}

	public void setLawFirmSenior(LawFirm lawFirmSenior) {
		this.lawFirmSenior = lawFirmSenior;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public LtgnRepresentativeMaster getLtgnRepresentativeMaster() {
		return ltgnRepresentativeMaster;
	}

	public void setLtgnRepresentativeMaster(LtgnRepresentativeMaster ltgnRepresentativeMaster) {
		this.ltgnRepresentativeMaster = ltgnRepresentativeMaster;
	}

	public CounterPartyDtls getCounterPartyDtls() {
		return counterPartyDtls;
	}

	public void setCounterPartyDtls(CounterPartyDtls counterPartyDtls) {
		this.counterPartyDtls = counterPartyDtls;
	}

	public List<LitigationMatterByAgainst> getLitigationMatterByAgainst() {
		return litigationMatterByAgainst;
	}

	public void setLitigationMatterByAgainst(List<LitigationMatterByAgainst> litigationMatterByAgainst) {
		this.litigationMatterByAgainst = litigationMatterByAgainst;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LitigationUnits getLitigationUnits() {
		return litigationUnits;
	}

	public void setLitigationUnits(LitigationUnits litigationUnits) {
		this.litigationUnits = litigationUnits;
	}
	
	

}
