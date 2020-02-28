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

	@Column(name = "litigationid")
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
	private LocalDate nextDateOfHearing;

	@Column(name = "time_of_case_am_pm")
	private String timeOfCaseAmPm;

	@Column(name = "inhouse_counsel_oid")
	private int inHouseCounselId;

	@Column(name = "inhouse_counsel_other_oid")
	private int inHouseCounselOtherId;

	@Column(name = "outside_counsel_oid")
	private int outsideCounselId;

	@Column(name = "outside_counsel_other_oid")
	private int outsideCounselOtherId;

	@Column(name = "create_date")
	private LocalDate createDate;

	@Column(name = "policestation")
	private String policeStation;

	@Column(name = "otherfiles")
	private String otherFiles;

	@Column(name = "fileavailable")
	private String fileAvailable;

	@Column(name = "casefileondt")
	private Date caseFileOnDate;

	@Column(name = "provisionmade")
	private String provisionMade;

	@Column(name = "counselassesment")
	private String counselAssesment;

	@Column(name = "caserelatefrmdt")
	private Date caseRelateFromDate;

	@Column(name = "caserelatetodt")
	private Date caseRelateToDate;

	@Column(name = "comment")
	private String comment;

	@Column(name = "remark")
	private String remark;

	@Column(name = "subject")
	private String subject;

	@Column(name = "otherunderact")
	private String otherUnderAct;

	@Column(name = "businessunitid")
	private int businessUnitId;

	@Column(name = "areaofficeoid")
	private int areaOfficeId;

	@Column(name = "resultoid")
	private int resultId;

	@Column(name = "reopencomments")
	private String reOpenComments;

	@Column(name = "coentity")
	private String coEntity;

	@Column(name = "coregion")
	private String coRegion;

	@Column(name = "deletestatus")
	private int deleteStatus;

	@Column(name = "exactclaimamount")
	private float exactClaimAmount;

	@Column(name = "address")
	private String address;

	@Column(name = "exactclaimremarks")
	private String exactClaimRemarks;

	@Column(name = "storeofficepremises")
	private String storeOfficePremises;

	@Column(name = "cocounterparties")
	private String coCounterParties;

	@Column(name = "undersection")
	private String underSection;

	@Column(name = "firno")
	private String firNo;

	@Column(name = "actionplan")
	private String actionPlan;

	@Column(name = "disposeddt")
	private Date disposedDate;

	@Column(name = "unitoidtemp")
	private int unitIdTemp;

	@Column(name = "representativeoidtemp")
	private int representativeIdTemp;

	@Column(name = "executiontypeoid")
	private int executionTypeId;

	@Column(name = "liabilityoid")
	private int liabilityId;

	@Column(name = "argumentoid")
	private int argumentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deptoid")
	private Dept dept;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unitoid")
	private Units units;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_type_oid")
	private CustomerType customerType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_type_oid")
	private LtgnCaseType ltgnCaseType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_oid")
	private State state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courtcityoid")
	private CourtCity courtCity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courttypeoid")
	private CourtType courtType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "riskoid")
	private Risk risk;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "claimoid")
	private Claim claim;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lawfirmoid")
	private LawFirm lawFirm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "statusoid")
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "underactoid")
	private UnderAct underAct;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "policestationoid")
	private PoliceStation policeStationId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seniorcounselid")
	private LawFirm lawFirmSenior;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formatoid")
	private Format format;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "representativeoid")
	private LtgnRepresentativeMaster ltgnRepresentativeMaster;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_oid")
	private CounterPartyDtls counterPartyDtls;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "litigation_oid")
	private List<LitigationMatterByAgainst> litigationMatterByAgainst;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "litigationaddedbyoid")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "litigation_oid")
	private LitigationUnits litigationUnits;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "litigation_oid")
	private List<ConnectedLitigation> connectedLitigation;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "litigation_oid")
	private List<Witness> witness;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "litigation_oid")
	private LitigationDocs litigationDocs;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "litigation_oid")
	private LawfirmBilling lawfirmBilling;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "litigation_oid")
	private List<LtgnLitigationLog> ltgnLitigationLog;

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

	public LocalDate getNextDateOfHearing() {
		return nextDateOfHearing;
	}

	public void setNextDateOfHearing(LocalDate nextDateOfHearing) {
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

	public int getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
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

	public List<ConnectedLitigation> getConnectedLitigation() {
		return connectedLitigation;
	}

	public void setConnectedLitigation(List<ConnectedLitigation> connectedLitigation) {
		this.connectedLitigation = connectedLitigation;
	}

	public List<Witness> getWitness() {
		return witness;
	}

	public void setWitness(List<Witness> witness) {
		this.witness = witness;
	}

	public LitigationDocs getLitigationDocs() {
		return litigationDocs;
	}

	public void setLitigationDocs(LitigationDocs litigationDocs) {
		this.litigationDocs = litigationDocs;
	}

	public LawfirmBilling getLawfirmBilling() {
		return lawfirmBilling;
	}

	public void setLawfirmBilling(LawfirmBilling lawfirmBilling) {
		this.lawfirmBilling = lawfirmBilling;
	}

	public List<LtgnLitigationLog> getLtgnLitigationLog() {
		return ltgnLitigationLog;
	}

	public void setLtgnLitigationLog(List<LtgnLitigationLog> ltgnLitigationLog) {
		this.ltgnLitigationLog = ltgnLitigationLog;
	}

}
