package com.fg.ss.abhiyog.common.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.fg.ss.abhiyog.common.model.BillingType;
import com.fg.ss.abhiyog.common.model.City;
import com.fg.ss.abhiyog.common.model.Claim;
import com.fg.ss.abhiyog.common.model.ConnectedLitigation;
import com.fg.ss.abhiyog.common.model.CounterPartyDtls;
import com.fg.ss.abhiyog.common.model.CourtCity;
import com.fg.ss.abhiyog.common.model.CourtType;
import com.fg.ss.abhiyog.common.model.CustomerType;
import com.fg.ss.abhiyog.common.model.Dept;
import com.fg.ss.abhiyog.common.model.Format;
import com.fg.ss.abhiyog.common.model.LawFirm;
import com.fg.ss.abhiyog.common.model.LawfirmBilling;
import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.model.LitigationDocs;
import com.fg.ss.abhiyog.common.model.LitigationHistoryTLog;
import com.fg.ss.abhiyog.common.model.LitigationMatterByAgainst;
import com.fg.ss.abhiyog.common.model.LitigationResultMaster;
import com.fg.ss.abhiyog.common.model.LitigationUnits;
import com.fg.ss.abhiyog.common.model.LtgnCaseType;
import com.fg.ss.abhiyog.common.model.LtgnLitigationLog;
import com.fg.ss.abhiyog.common.model.LtgnRepresentativeMaster;
import com.fg.ss.abhiyog.common.model.PoliceStation;
import com.fg.ss.abhiyog.common.model.Risk;
import com.fg.ss.abhiyog.common.model.StageMaster;
import com.fg.ss.abhiyog.common.model.State;
import com.fg.ss.abhiyog.common.model.Status;
import com.fg.ss.abhiyog.common.model.UnderAct;
import com.fg.ss.abhiyog.common.model.Units;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.model.Witness;
import com.fg.ss.abhiyog.common.repository.BillingTypeRepository;
import com.fg.ss.abhiyog.common.repository.CityRepository;
import com.fg.ss.abhiyog.common.repository.ClaimRepository;
import com.fg.ss.abhiyog.common.repository.ConnectedLitigationRepository;
import com.fg.ss.abhiyog.common.repository.CounterPartyRepository;
import com.fg.ss.abhiyog.common.repository.CourtCityRepository;
import com.fg.ss.abhiyog.common.repository.CourtTypeRepository;
import com.fg.ss.abhiyog.common.repository.CustomerTypeRepository;
import com.fg.ss.abhiyog.common.repository.DeptRepository;
import com.fg.ss.abhiyog.common.repository.FormatRepository;
import com.fg.ss.abhiyog.common.repository.LawfirmBillingRepository;
import com.fg.ss.abhiyog.common.repository.LitigationDocsRepository;
import com.fg.ss.abhiyog.common.repository.LitigationHistoryTLogRepository;
import com.fg.ss.abhiyog.common.repository.LitigationMatterByAgainstRepository;
import com.fg.ss.abhiyog.common.repository.LitigationRepository;
import com.fg.ss.abhiyog.common.repository.LitigationResultMasterRepository;
import com.fg.ss.abhiyog.common.repository.LitigationUnitsRepository;
import com.fg.ss.abhiyog.common.repository.LtgnCaseTypeRepository;
import com.fg.ss.abhiyog.common.repository.LtgnLitigationLogRepository;
import com.fg.ss.abhiyog.common.repository.LtgnRepresentativeMasterRepository;
import com.fg.ss.abhiyog.common.repository.OutsideCounselRepository;
import com.fg.ss.abhiyog.common.repository.PoliceStationRepository;
import com.fg.ss.abhiyog.common.repository.RiskRepository;
import com.fg.ss.abhiyog.common.repository.StageMasterRepository;
import com.fg.ss.abhiyog.common.repository.StateRepository;
import com.fg.ss.abhiyog.common.repository.StatusRepository;
import com.fg.ss.abhiyog.common.repository.UnderActRepository;
import com.fg.ss.abhiyog.common.repository.UnitsRepository;
import com.fg.ss.abhiyog.common.repository.UserRepository;
import com.fg.ss.abhiyog.common.repository.WitnessRepository;
import com.fg.ss.abhiyog.common.util.DateUtils;
import com.fg.ss.abhiyog.common.vo.AddLitigationVO;
import com.fg.ss.abhiyog.common.vo.CityStateVO;
import com.fg.ss.abhiyog.common.vo.ConnectedLitigationVO;
import com.fg.ss.abhiyog.common.vo.CourtTypeVO;
import com.fg.ss.abhiyog.common.vo.CustomerTypeStatusVO;
import com.fg.ss.abhiyog.common.vo.DashboardVO;
import com.fg.ss.abhiyog.common.vo.LitigationSummaryVO;
import com.fg.ss.abhiyog.common.vo.LtgnCategoryVO;
import com.fg.ss.abhiyog.common.vo.LtgnMatterByVO;
import com.fg.ss.abhiyog.common.vo.RiskClaimVO;
import com.fg.ss.abhiyog.common.vo.UnderActVO;
import com.fg.ss.abhiyog.common.vo.UnitSummaryVO;

@Service
public class LitigationService implements ILitigationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LitigationService.class);
	@Autowired
	private LtgnRepresentativeMasterRepository ltgnRepresentativeMasterRepository;

	@Autowired
	private LtgnCaseTypeRepository ltgnCaseTypeRepository;

	@Autowired
	private UnderActRepository underActRepository;

	@Autowired
	private CourtTypeRepository courtTypeRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private RiskRepository riskRepository;

	@Autowired
	private ClaimRepository claimRepository;

	@Autowired
	private CustomerTypeRepository customerTypeRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private CourtCityRepository courtCityRepository;

	@Autowired
	private UnitsRepository unitsRepository;

	@Autowired
	private FormatRepository formatRepository;

	@Autowired
	private CounterPartyRepository counterPartyRepository;

	@Autowired
	private OutsideCounselRepository outsideCounselRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LitigationRepository litigationRepository;

	@Autowired
	private LitigationMatterByAgainstRepository litigationMatterByAgainstRepository;

	@Autowired
	private DeptRepository deptRepository;

	@Autowired
	private PoliceStationRepository policeStationRepository;

	@Autowired
	private LitigationUnitsRepository litigationUnitsRepository;

	@Autowired
	private ConnectedLitigationRepository connectedLitigationRepository;

	@Autowired
	private WitnessRepository witnessRepository;

	@Autowired
	private LitigationResultMasterRepository litigationResultMasterRepository;

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private LitigationDocsRepository litigationDocsRepository;

	@Autowired
	private BillingTypeRepository billingTypeRepository;

	@Autowired
	private LawfirmBillingRepository lawfirmBillingRepository;

	@Autowired
	private StageMasterRepository stageMasterRepository;

	@Autowired
	private LtgnLitigationLogRepository ltgnLitigationLogRepository;

	@Autowired
	private LitigationHistoryTLogRepository litigationHistoryTLogRepository;

	@Autowired
	private EntityManager entityManager;

	@Value("${file.upload-dir}")
	private String path;

	static int i = 0001;

	@Override
	public LtgnRepresentativeMaster checkExistenceRepresentativeName(String matterBy) {
		LtgnRepresentativeMaster ltgnRepresentativeMaster = ltgnRepresentativeMasterRepository
				.findByRepresentativeName(matterBy);
		return ltgnRepresentativeMaster;
	}

	@Override
	public void saveLtgnRepresentativeMaster(String matterBy) {
		LtgnRepresentativeMaster ltgnRepresentativeMasterData = new LtgnRepresentativeMaster();
		ltgnRepresentativeMasterData.setRepresentativeName(matterBy);
		ltgnRepresentativeMasterData.setStatus("Active");
		ltgnRepresentativeMasterRepository.save(ltgnRepresentativeMasterData);
	}

	@Override
	public List<LtgnMatterByVO> findAll() {
		List<LtgnRepresentativeMaster> allRepresentativeData = ltgnRepresentativeMasterRepository.findAll();
		return allRepresentativeData.stream().map(allData -> convertToDto(allData)).collect(Collectors.toList());
	}

	private LtgnMatterByVO convertToDto(LtgnRepresentativeMaster allData) {
		LtgnMatterByVO ltgnMatterByVo = new LtgnMatterByVO();
		ltgnMatterByVo.setRepresentativeId(allData.getRepresentativeId());
		ltgnMatterByVo.setMatterBy(allData.getRepresentativeName());
		ltgnMatterByVo.setStatus(allData.getStatus());
		return ltgnMatterByVo;
	}

	@Override
	public LtgnCaseType checkExistenceCaseType(String categoryName) {
		LtgnCaseType ltgnCaseType = ltgnCaseTypeRepository.findByCaseType(categoryName);
		return ltgnCaseType;
	}

	@Override
	public void saveLtgnCaseType(String categoryName) {
		LtgnCaseType ltgnCaseType = new LtgnCaseType();
		ltgnCaseType.setCaseType(categoryName);
		ltgnCaseType.setStatus("Active");
		ltgnCaseTypeRepository.save(ltgnCaseType);
	}

	@Override
	public List<LtgnCategoryVO> findAllCategoryData() {
		List<LtgnCaseType> ltgnCaseType = ltgnCaseTypeRepository.findAll();
		return ltgnCaseType.stream().map(allLtgnCaseType -> convertToCaseTypeDto(allLtgnCaseType))
				.collect(Collectors.toList());
	}

	private LtgnCategoryVO convertToCaseTypeDto(LtgnCaseType allLtgnCaseType) {
		LtgnCategoryVO ltgnCategoryVO = new LtgnCategoryVO();
		ltgnCategoryVO.setCategoryId(allLtgnCaseType.getCaseTypeId());
		ltgnCategoryVO.setCategoryName(allLtgnCaseType.getCaseType());
		ltgnCategoryVO.setStatus(allLtgnCaseType.getStatus());
		return ltgnCategoryVO;
	}

	@Override
	public UnderAct findByUnderAct(String underActName) {
		UnderAct underAct = underActRepository.findByUnderAct(underActName);
		return underAct;
	}

	@Override
	public void saveUnderActData(String underActName) {
		UnderAct underAct = new UnderAct();
		underAct.setUnderAct(underActName);
		underActRepository.save(underAct);
	}

	@Override
	public List<UnderActVO> findAllUnderActData() {
		List<UnderAct> allUnderAct = underActRepository.findAll();
		return allUnderAct.stream().map(allUnderActData -> convertToUnderActDto(allUnderActData))
				.collect(Collectors.toList());
	}

	private UnderActVO convertToUnderActDto(UnderAct allUnderActData) {
		UnderActVO underActVO = new UnderActVO();
		underActVO.setUnderActId(allUnderActData.getUnderActId());
		underActVO.setUnderActName(allUnderActData.getUnderAct());
		return underActVO;
	}

	@Override
	public CourtType checkExistenceCourtType(String courtTypeName) {
		CourtType courtTypeExistence = courtTypeRepository.findByCourtType(courtTypeName);
		return courtTypeExistence;
	}

	@Override
	public void saveCourtType(String courtTypeName) {
		CourtType courtType = new CourtType();
		courtType.setCourtType(courtTypeName);
		courtTypeRepository.save(courtType);
	}

	@Override
	public List<CourtTypeVO> findCourtType() {
		List<CourtType> allCourtType = courtTypeRepository.findAll();
		return allCourtType.stream().map(allCourtTypeData -> convertToCourtTypeDto(allCourtTypeData))
				.collect(Collectors.toList());
	}

	private CourtTypeVO convertToCourtTypeDto(CourtType allCourtTypeData) {
		CourtTypeVO courtTypeVO = new CourtTypeVO();
		courtTypeVO.setCourtTypeId(allCourtTypeData.getCourtTypeId());
		courtTypeVO.setCourtTypeName(allCourtTypeData.getCourtType());
		return courtTypeVO;
	}

	@Override
	public List<CityStateVO> findAllStates() {
		List<State> allStates = stateRepository.findAll();
		return allStates.stream().map(allStatesData -> convertToStatesDto(allStatesData)).collect(Collectors.toList());
	}

	private CityStateVO convertToStatesDto(State allStatesData) {
		CityStateVO cityStatesVO = new CityStateVO();
		cityStatesVO.setStateId(allStatesData.getStateId());
		cityStatesVO.setStateName(allStatesData.getStateName());
		return cityStatesVO;
	}

	@Override
	public City checkExistenceCity(String cityName) {
		City cityExistence = cityRepository.findByCityName(cityName);
		return cityExistence;
	}

	@Override
	public void saveCityData(String cityName, String stateName) {
		City cityDtls = new City();
		cityDtls.setCityName(cityName);
		State stateDtls = stateRepository.findByStateName(stateName);
		cityDtls.setState(stateDtls);
		cityRepository.save(cityDtls);
	}

	@Override
	public List<CityStateVO> findAllCities() {
		List<City> allCityDtls = cityRepository.findAll();
		return allCityDtls.stream().map(allCityData -> convertToCityDto(allCityData)).collect(Collectors.toList());
	}

	private CityStateVO convertToCityDto(City allCityData) {
		CityStateVO cityStateVO = new CityStateVO();
		cityStateVO.setCityId(allCityData.getCityId());
		cityStateVO.setCityName(allCityData.getCityName());
		cityStateVO.setStateId(allCityData.getState().getStateId());
		return cityStateVO;
	}

	@Override
	public List<RiskClaimVO> findAllRiskLevel() {
		List<Risk> allRiskLevel = riskRepository.findAll();
		return allRiskLevel.stream().map(allRiskLevels -> convertToRiskDto(allRiskLevels)).collect(Collectors.toList());
	}

	private RiskClaimVO convertToRiskDto(Risk allRiskLevels) {
		RiskClaimVO riskClaimVO = new RiskClaimVO();
		riskClaimVO.setRiskId(allRiskLevels.getRiskId());
		riskClaimVO.setRisk(allRiskLevels.getRisk());
		return riskClaimVO;
	}

	@Override
	public List<RiskClaimVO> findAllClaimPossible() {
		List<Claim> allClaimPossible = claimRepository.findAll();
		return allClaimPossible.stream().map(allClaimDtls -> convertToClaimDto(allClaimDtls))
				.collect(Collectors.toList());
	}

	private RiskClaimVO convertToClaimDto(Claim allClaimDtls) {
		RiskClaimVO allClaimPossibleDtls = new RiskClaimVO();
		allClaimPossibleDtls.setClaimId(allClaimDtls.getClaimId());
		allClaimPossibleDtls.setClaim(allClaimDtls.getClaim());
		return allClaimPossibleDtls;
	}

	@Override
	public List<CustomerTypeStatusVO> findAllCustomerType() {
		List<CustomerType> allCustomerType = customerTypeRepository.findAll();
		return allCustomerType.stream().map(allCustomerTypeDtls -> convertToCustomerTypeDto(allCustomerTypeDtls))
				.collect(Collectors.toList());
	}

	private CustomerTypeStatusVO convertToCustomerTypeDto(CustomerType allCustomerTypeDtls) {
		CustomerTypeStatusVO customerTypeStatusVo = new CustomerTypeStatusVO();
		customerTypeStatusVo.setCustomerTypeId(allCustomerTypeDtls.getCustomerTypeId());
		customerTypeStatusVo.setCustomerType(allCustomerTypeDtls.getCustomerType());
		return customerTypeStatusVo;
	}

	@Override
	public List<CustomerTypeStatusVO> findAllStatus() {
		List<Status> allStatusDtls = statusRepository.findAll();
		return allStatusDtls.stream().map(statusDtls -> convertToStatusDtls(statusDtls)).collect(Collectors.toList());
	}

	private CustomerTypeStatusVO convertToStatusDtls(Status statusDtls) {
		CustomerTypeStatusVO allStatusDtls = new CustomerTypeStatusVO();
		allStatusDtls.setStatusId(statusDtls.getStatusId());
		allStatusDtls.setStatus(statusDtls.getStatus());
		return allStatusDtls;
	}

	@Override
	public CourtCity findByCourtCity(String courtForum) {
		CourtCity courtCity = courtCityRepository.findByCourtCity(courtForum);
		return courtCity;
	}

	@Override
	public void saveCourtCityData(String courtForumVal, String cityNameVal) {
		CourtCity courtCity = new CourtCity();
		courtCity.setCourtCity(courtForumVal);
		City city = cityRepository.findByCityName(cityNameVal);
		courtCity.setCity(city);
		courtCityRepository.save(courtCity);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean saveLitigationData(AddLitigationVO addLitigationVO) {
		boolean isInserted = false;
		final String prefixLtgnId = "L";
		Litigation litigation = new Litigation();
//		Litigation checkLtgnExistence = new Litigation();
		LitigationMatterByAgainst litigationMatterByAgainst = new LitigationMatterByAgainst();
		LitigationUnits litigationUnits = new LitigationUnits();
		HashMap<String, String> allCustomerTypes = new HashMap<String, String>();
		System.out.println(allCustomerTypes);
		allCustomerTypes.put("Plaintiff", "Defendant");
		allCustomerTypes.put("Defendant", "Plaintiff");
		allCustomerTypes.put("Complainant", "Accused");
		allCustomerTypes.put("Accused", "Complainant");
		allCustomerTypes.put("Applicant", "Opponent");
		allCustomerTypes.put("Opponent", "Applicant");
		allCustomerTypes.put("Petitioner", "Respondant");
		allCustomerTypes.put("Respondant", "Petitioner");
		allCustomerTypes.put("Appelant", "Respondant");
		allCustomerTypes.put("Appeal By Company", "Petitioner");
		allCustomerTypes.put("Appeal Against Company", "Petitioner");

		System.out.println(addLitigationVO.getEntityName() + addLitigationVO.getZoneName() + addLitigationVO.getUnitName());
		Units unitsDtls = unitsRepository.getUnitDtlsByEntityName(addLitigationVO.getEntityName(),
				addLitigationVO.getZoneName(), addLitigationVO.getUnitName());
		if(unitsDtls.equals(null)) {
			return false;
		}else {
			litigation.setUnits(unitsDtls);
			litigationUnits.setUnits(unitsDtls);
		}

		Format formatDtls = formatRepository.getFormatByName(addLitigationVO.getFormat());
		litigation.setFormat(formatDtls);

		litigation.setAddress(addLitigationVO.getAddress());
		litigation.setStoreOfficePremises(addLitigationVO.getStoreOfficePremises());
		litigation.setCoRegion(addLitigationVO.getCoZone());

		CustomerType customerType = customerTypeRepository.findByCustomerType(addLitigationVO.getCustomerType());
		litigation.setCustomerType(customerType);

		for (Entry<String, String> entry : allCustomerTypes.entrySet()) {
			if (entry.getKey().equals(litigation.getCustomerType().getCustomerType())) {
//				System.out.println("AgainstPArtyType:: "+entry.getValue());
				litigation.setAgainstPartyClientType(entry.getValue());
			}
		}

		CounterPartyDtls counterPartyDtls = counterPartyRepository
				.findByCustomerName(addLitigationVO.getCounterPartyName());
		litigation.setCounterPartyDtls(counterPartyDtls);
		litigation.setCoCounterParties(addLitigationVO.getCoCounterParties());
		litigation.setSubject(addLitigationVO.getSubject());

		LtgnCaseType ltgnCaseType = ltgnCaseTypeRepository.findByCaseType(addLitigationVO.getCategoryName());
		litigation.setLtgnCaseType(ltgnCaseType);
		litigation.setCaseNumber(addLitigationVO.getCaseNumber());

		Risk riskAsses = riskRepository.findByRisk(addLitigationVO.getRisk());
		litigation.setRisk(riskAsses);

		Claim claimRange = claimRepository.findByClaim(addLitigationVO.getClaim());
		litigation.setClaim(claimRange);
		litigation.setExactClaimAmount(addLitigationVO.getPossibleClaim());
		litigation.setFileNo(addLitigationVO.getPantaloonFileNo());

		UnderAct underActs = underActRepository.findByUnderAct(addLitigationVO.getUnderActName());
		litigation.setUnderAct(underActs);
		litigation.setUnderSection(addLitigationVO.getUnderSection());
		litigation.setOtherUnderAct(addLitigationVO.getOtherUnderacts());

		CourtType courtType = courtTypeRepository.findByCourtType(addLitigationVO.getCourtTypeName());
		litigation.setCourtType(courtType);

		State state = stateRepository.findByStateName(addLitigationVO.getStateName());
		litigation.setState(state);

		litigation.setCity(addLitigationVO.getCityName());
		litigation.setFirNo(addLitigationVO.getFirNo());

		PoliceStation policeStation = policeStationRepository.getPoliceDtlsByCityName(addLitigationVO.getCityName());
		litigation.setPoliceStationId(policeStation);

		CourtCity courtCity = courtCityRepository.findByCourtCity(addLitigationVO.getCourtForum());
		litigation.setCourtCity(courtCity);

		Status status = statusRepository.findByStatusName(addLitigationVO.getStatus());
		litigation.setStatus(status);
		litigation.setCaseFileOnDate(addLitigationVO.getDateOfReceiptOfMatter());
		litigation.setFactOfLitigationMatter(addLitigationVO.getFactsOfLitigation());
		litigation.setStage(addLitigationVO.getStage());
		litigation.setCounselAssesment(addLitigationVO.getCounselAssesment());
		litigation.setRemark(addLitigationVO.getRemark());
		litigation.setNextDateOfHearing(addLitigationVO.getNextHearingDate());
		

		LawFirm seniorLawfirm = outsideCounselRepository.findByLawfirm(addLitigationVO.getSeniorCounsel());
		litigation.setLawFirmSenior(seniorLawfirm);

		LawFirm lawfirm = outsideCounselRepository.findByLawfirm(addLitigationVO.getLawfirm());
		litigation.setLawFirm(lawfirm);

		User user = userRepository.findByLoginId(addLitigationVO.getLoginId());
		litigation.setUser(user);

		Dept dept = deptRepository.findByDeptName(addLitigationVO.getFunction());
		litigation.setDept(dept);

		// Setting LitigationId
		Litigation litgationId = litigationRepository.getLitigationId();
		if (litgationId == null) {
			litigation.setLitigationId(prefixLtgnId + String.format("%04d", i));
		} else {
			String ltgnId = litgationId.getLitigationId();
			int lastIdValue = Integer.parseInt(ltgnId.substring(ltgnId.length() - 4));
			litigation.setLitigationId(prefixLtgnId + String.format("%04d", ++lastIdValue));
		}

		System.out.println("MatterByAgainst:: " + addLitigationVO.getMatterByAgainst().length);
		for (String matterByAgainst : addLitigationVO.getMatterByAgainst()) {
			System.out.println("RepresentativeNAme:: " + matterByAgainst);
			System.out.println("LitigationOID:: " + litigation.getLitigationOid());
			LtgnRepresentativeMaster ltgnRepresentativeMaster = ltgnRepresentativeMasterRepository
					.findByRepresentativeName(matterByAgainst);
			litigationMatterByAgainst.setLtgnRepresentativeMaster(ltgnRepresentativeMaster);
			litigation.setLtgnRepresentativeMaster(ltgnRepresentativeMaster);
			litigationMatterByAgainst.setLitigation(litigation);
			litigationMatterByAgainstRepository.save(litigationMatterByAgainst);

		}

		litigationUnits.setLitigation(litigation);
		LocalDate dateTime = LocalDate.now();
		litigation.setCreateDate(dateTime);
		litigationUnitsRepository.save(litigationUnits);
		litigationRepository.save(litigation);
		return true;

	}

	@Override
	public void savePoliceStationData(CityStateVO cityStateVO) {
		// based on city name adding policeStation details.
		PoliceStation policeStation = new PoliceStation();
		City city = cityRepository.findByCityStateName(cityStateVO.getCityName(), cityStateVO.getStateName());
		policeStation.setPoliceStation(cityStateVO.getPoliceStationName());
		policeStation.setCity(city);
		policeStationRepository.save(policeStation);
	}

	@Override
	public List<LitigationSummaryVO> getLitigationSummary() {
		List<Litigation> allLitigationSummary = litigationRepository.findAllLitigationSummary();
		return allLitigationSummary.stream().map(litigationSummary -> convertToLitigationSummary(litigationSummary))
				.collect(Collectors.toList());
	}

	private LitigationSummaryVO convertToLitigationSummary(Litigation litigationSummary) {
		LitigationSummaryVO litigationSummaryVO = new LitigationSummaryVO();
		litigationSummaryVO.setLitigationOId(litigationSummary.getLitigationOid());
		litigationSummaryVO.setLitigationId(litigationSummary.getLitigationId());
		litigationSummaryVO.setStatus(litigationSummary.getStatus().getStatus());
		litigationSummaryVO.setFileNo(litigationSummary.getFileNo());
		litigationSummaryVO.setEntityName(litigationSummary.getUnits().getEntitySummary().getEntityName());
		litigationSummaryVO.setUnitName(litigationSummary.getUnits().getUnitName());
		litigationSummaryVO.setCounterPartyName(litigationSummary.getCounterPartyDtls().getCustomerName());
		litigationSummaryVO.setCustomerType(litigationSummary.getCustomerType().getCustomerType());
		litigationSummaryVO.setCaseNumber(litigationSummary.getCaseNumber());
		litigationSummaryVO.setSubject(litigationSummary.getSubject());
		litigationSummaryVO.setStage(litigationSummary.getStage());
		litigationSummaryVO.setHearingDate(litigationSummary.getNextDateOfHearing());
		litigationSummaryVO.setRisk(litigationSummary.getRisk().getRisk());
		litigationSummaryVO.setClaim(litigationSummary.getClaim().getClaim());
		litigationSummaryVO.setRemark(litigationSummary.getRemark());
		litigationSummaryVO.setZoneName(litigationSummary.getUnits().getRegions().getZoneName());
		litigationSummaryVO.setUnderActName(litigationSummary.getUnderAct().getUnderAct());
		return litigationSummaryVO;
	}

	@Override
	public void addConnectedLitigation(ConnectedLitigationVO connectedLitigationVO) {
		ConnectedLitigation connectedLitigation = new ConnectedLitigation();
		connectedLitigation.setComment(connectedLitigationVO.getComments());
		Optional<Litigation> litigationId = litigationRepository.findById(connectedLitigationVO.getLitigationOId());
		if(litigationId.isPresent()) {
			connectedLitigation.setLitigation(litigationId.get());
		}
		
		Litigation connectedLitigationId = litigationRepository
				.findByLitigationId(connectedLitigationVO.getConnectedLitigationId());
		connectedLitigation.setConnectedLitigation(connectedLitigationId);
		LocalDateTime dateTime = LocalDateTime.now();
		connectedLitigation.setCreateDate(dateTime);
		connectedLitigationRepository.save(connectedLitigation);
	}

	@Override
	public void addWitnessDtls(ConnectedLitigationVO connectedLitigationVO) {
		Witness witness = new Witness();
		witness.setWitness(connectedLitigationVO.getWitnessName());
//		Litigation litigationId = litigationRepository.findByLitigationId(connectedLitigationVO.getLitigationId());
		System.out.println(connectedLitigationVO.getLitigationOId());
		
		Optional<Litigation> litigationDtls = litigationRepository.findById(connectedLitigationVO.getLitigationOId());
		if(litigationDtls.isPresent()) {
			witness.setLitigation(litigationDtls.get());
		}
		
		witnessRepository.save(witness);
	}

	@Override
	public List<ConnectedLitigationVO> getWitnessDtls(String litigationId) {
		List<Witness> allWitnessDtls = witnessRepository.findAllByLitigationId(litigationId);
		return allWitnessDtls.stream().map(witnessDtls -> convertToWitnessDtls(witnessDtls))
				.collect(Collectors.toList());
	}

	private ConnectedLitigationVO convertToWitnessDtls(Witness witnessDtls) {
		ConnectedLitigationVO connectedLitigationVO = new ConnectedLitigationVO();
		connectedLitigationVO.setWitnessName(witnessDtls.getWitness());
		return connectedLitigationVO;
	}

	@Override
	public List<ConnectedLitigationVO> getLitigationResultMaster() {
		List<LitigationResultMaster> allResultMaster = litigationResultMasterRepository.findAll();
		return allResultMaster.stream().map(ResultMaster -> convertToResultDto(ResultMaster))
				.collect(Collectors.toList());
	}

	private ConnectedLitigationVO convertToResultDto(LitigationResultMaster resultMaster) {
		ConnectedLitigationVO allResultMasterDtls = new ConnectedLitigationVO();
		allResultMasterDtls.setResultName(resultMaster.getResult());
		return allResultMasterDtls;
	}

	@Override
	public int addLitigationDisposal(ConnectedLitigationVO connectedLitigationVO) {
		LitigationResultMaster resultMaster = litigationResultMasterRepository
				.findByResultName(connectedLitigationVO.getResultName());
		System.out.println("ResultID::: " + resultMaster.getResultId());
		int updateLitigationDisposal = litigationRepository.updateLitigation(resultMaster.getResultId(),
				connectedLitigationVO.getComments(), connectedLitigationVO.getDisposedDate(),
				connectedLitigationVO.getLitigationOId());
		return updateLitigationDisposal;
	}

	@Override
	public ConnectedLitigationVO getStatusByLitigationId(String litigationId) {
		ConnectedLitigationVO connectedLitigationVO = new ConnectedLitigationVO();
		Litigation status = litigationRepository.getStatusById(litigationId);
		connectedLitigationVO.setStatusName(status.getStatus().getStatus());
		return connectedLitigationVO;
	}

	@Override
	public void saveLitigationDocsData(MultipartFile file, ConnectedLitigationVO connectedLitigationVO) {
		LitigationDocs litigationDocs = new LitigationDocs();
		boolean flag = false;
		if(!file.isEmpty()) {
			String fileName = fileStorageService.storeFile(file);
			litigationDocs.setDocName(fileName);
			litigationDocs.setDocPath(path + "/" + fileName);
			litigationDocs.setDocSize(file.getSize());
			flag = true;
		}
		
		LocalDateTime dateTime = LocalDateTime.now();
		litigationDocs.setUploadedDate(dateTime);
		litigationDocs.setDocComment(connectedLitigationVO.getUploadComments());
		System.out.println("Comments:: " + connectedLitigationVO.getComments());
		litigationDocs.setDocTitle(connectedLitigationVO.getDocumentTitle());
		System.out.println("Doc Title:: " + connectedLitigationVO.getDocumentTitle());
		System.out.println("Doc Title:: " + litigationDocs.getDocTitle());
		Optional<Litigation> litigation = litigationRepository.findById(connectedLitigationVO.getLitigationOId());
		litigationDocs.setLitigation(litigation.get());
		User user = userRepository.findByLoginId(connectedLitigationVO.getLoginId());
		litigationDocs.setUser(user);
		litigationDocsRepository.save(litigationDocs);
		if(flag) {
			String fileAvailable = "Yes";
			int fileUpdatedStatus = litigationRepository
					.updateFileStatusByLitigationId(connectedLitigationVO.getLitigationId(), fileAvailable);
		}
		
	}

	@Override
	public void saveLawfirmBillingData(MultipartFile multiPartFile, ConnectedLitigationVO connectedLitigationVO) {
		LawfirmBilling lawfirmBilling = new LawfirmBilling();
		lawfirmBilling.setBillingAmount(connectedLitigationVO.getBillingAmount());
		lawfirmBilling.setBillingDate(connectedLitigationVO.getBillingDate());
		LocalDateTime dateTime = LocalDateTime.now();
		lawfirmBilling.setCreateDate(dateTime);
		lawfirmBilling.setRemark(connectedLitigationVO.getRemark());
		BillingType billingType = billingTypeRepository.findByBillingType(connectedLitigationVO.getBillingType());
		lawfirmBilling.setBillingType(billingType);
		Optional<Litigation> litigationId = litigationRepository.findById(connectedLitigationVO.getLitigationOId());
		lawfirmBilling.setLitigation(litigationId.get());
		
		if(!multiPartFile.isEmpty()) {
			String fileName = fileStorageService.storeFile(multiPartFile);
			lawfirmBilling.setDocName(fileName);
			lawfirmBilling.setDocSize(multiPartFile.getSize());
			lawfirmBilling.setFileExtension(multiPartFile.getContentType());
			lawfirmBilling.setDocPath(path + "/" + fileName);
			try {
				lawfirmBilling.setFileData(multiPartFile.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		lawfirmBillingRepository.save(lawfirmBilling);
	}

	@Override
	public List<ConnectedLitigationVO> getLawfirmBilling(String litigationId) {
		List<LawfirmBilling> allLawfirmBilling = lawfirmBillingRepository.findByLitigationId(litigationId);
		return allLawfirmBilling.stream().map(lawfirmBilling -> convertToLawfirmBillingDto(lawfirmBilling))
				.collect(Collectors.toList());
	}

	private ConnectedLitigationVO convertToLawfirmBillingDto(LawfirmBilling lawfirmBilling) {
		ConnectedLitigationVO connectedLitigationVO = new ConnectedLitigationVO();
		connectedLitigationVO.setBillingType(lawfirmBilling.getBillingType().getBillingType());
		connectedLitigationVO.setBillingAmount(lawfirmBilling.getBillingAmount());
		connectedLitigationVO.setBillingDate(lawfirmBilling.getBillingDate());
		connectedLitigationVO.setRemark(lawfirmBilling.getRemark());
		connectedLitigationVO.setDocumentTitle(lawfirmBilling.getDocPath());
		connectedLitigationVO.setHearingDt(lawfirmBilling.getLitigation().getNextDateOfHearing());
		return connectedLitigationVO;
	}

	@Override
	public List<AddLitigationVO> getCaseDtls(String litigationId) {
		List<Litigation> allCaseDtls = litigationRepository.getCaseDtls(litigationId);
		return allCaseDtls.stream().map(CaseDtls -> convertToCasedtlsDto(CaseDtls)).collect(Collectors.toList());
	}

	private AddLitigationVO convertToCasedtlsDto(Litigation caseDtls) {
		AddLitigationVO addCaseDtls = new AddLitigationVO();
		addCaseDtls.setRisk(caseDtls.getRisk().getRisk());
		addCaseDtls.setUnderActName(caseDtls.getUnderAct().getUnderAct());
		addCaseDtls.setClaim(caseDtls.getClaim().getClaim());
		addCaseDtls.setUnderSection(caseDtls.getUnderSection());
		addCaseDtls.setOtherUnderacts(caseDtls.getOtherUnderAct());
		addCaseDtls.setPossibleClaim(caseDtls.getExactClaimAmount());
		addCaseDtls.setPantaloonFileNo(caseDtls.getFileNo());
		addCaseDtls.setDateOfReceiptOfMatter(caseDtls.getCaseFileOnDate());
		addCaseDtls.setStateName(caseDtls.getState().getStateName());
		addCaseDtls.setCityName(caseDtls.getCity());
		addCaseDtls.setCourtTypeName(caseDtls.getCourtType().getCourtType());
		addCaseDtls.setFirNo(caseDtls.getFirNo());
		addCaseDtls.setCourtForum(caseDtls.getCourtCity().getCourtCity());
		addCaseDtls.setStoreOfficePremises(caseDtls.getStoreOfficePremises());
		addCaseDtls.setFactsOfLitigation(caseDtls.getFactOfLitigationMatter());
		addCaseDtls.setCounselAssesment(caseDtls.getCounselAssesment());
		addCaseDtls.setLawfirm(caseDtls.getLawFirm().getLawfirm());
		addCaseDtls.setSeniorCounsel(caseDtls.getLawFirm().getLawfirm());
		addCaseDtls.setCaseRelateFromDate(caseDtls.getCaseRelateFromDate());
		addCaseDtls.setCaseRelateToDate(caseDtls.getCaseRelateToDate());
		addCaseDtls.setCategoryName(caseDtls.getLtgnCaseType().getCaseType());
		return addCaseDtls;
	}

	@Override
	public List<AddLitigationVO> getAllDetails(String litigationId) {
		List<Litigation> allCaseDetails = litigationRepository.getAllDetails(litigationId);
		return allCaseDetails.stream().map(caseDetails -> convertToAllCaseDetailsDto(caseDetails))
				.collect(Collectors.toList());
	}

	private AddLitigationVO convertToAllCaseDetailsDto(Litigation caseDetails) {
		AddLitigationVO addCaseDetails = new AddLitigationVO();
		addCaseDetails.setEntityName(caseDetails.getUnits().getEntitySummary().getEntityName());
		addCaseDetails.setCounterPartyName(caseDetails.getCounterPartyDtls().getCustomerName());
		addCaseDetails.setFormat(caseDetails.getFormat().getFormat());
		addCaseDetails.setZoneName(caseDetails.getUnits().getRegions().getZoneName());
		for (LitigationMatterByAgainst ltgnMatterByAgainst : caseDetails.getLitigationMatterByAgainst()) {
			addCaseDetails.getMatterByAgainstList()
					.add(ltgnMatterByAgainst.getLtgnRepresentativeMaster().getRepresentativeName());
		}
		addCaseDetails.setUnitName(caseDetails.getUnits().getUnitName());
		addCaseDetails.setFunction(caseDetails.getDept().getDeptName());
		addCaseDetails.setAddress(caseDetails.getAddress());
		addCaseDetails.setStatus(caseDetails.getStatus().getStatus());
		addCaseDetails.setSubject(caseDetails.getSubject());
		addCaseDetails.setStage(caseDetails.getStage());
		addCaseDetails.setNextHearingDate(caseDetails.getNextDateOfHearing());
		addCaseDetails.setRemark(caseDetails.getRemark());
		addCaseDetails.setCaseNumber(caseDetails.getCaseNumber());
		addCaseDetails.setCustomerType(caseDetails.getCustomerType().getCustomerType());// againstcustomer type
		addCaseDetails.setAgainstPartyClientCustomerType(caseDetails.getAgainstPartyClientType());
		return addCaseDetails;
	}

	@Override
	public List<AddLitigationVO> showLitigationDetails(String litigationId) {
		List<Litigation> allLitigationDetails = litigationRepository.showLitigationDetails(litigationId);
		return allLitigationDetails.stream().map(litigationDetails -> convertToLitigationDetailsDto(litigationDetails))
				.collect(Collectors.toList());
	}

	private AddLitigationVO convertToLitigationDetailsDto(Litigation litigationDetails) {
		AddLitigationVO showLitigationDetails = new AddLitigationVO();
		showLitigationDetails.setEntityName(litigationDetails.getUnits().getEntitySummary().getEntityName());
		showLitigationDetails.setZoneName(litigationDetails.getUnits().getRegions().getZoneName());
		showLitigationDetails.setUnitName(litigationDetails.getUnits().getUnitName());
		showLitigationDetails.setFormat(litigationDetails.getFormat().getFormat());
		showLitigationDetails.setAddress(litigationDetails.getAddress());
		showLitigationDetails.setStoreOfficePremises(litigationDetails.getStoreOfficePremises());
		for (LitigationMatterByAgainst ltgnMatterByAgainst : litigationDetails.getLitigationMatterByAgainst()) {
			showLitigationDetails.getMatterByAgainstList()
					.add(ltgnMatterByAgainst.getLtgnRepresentativeMaster().getRepresentativeName());
		}
		showLitigationDetails.setCoZone(litigationDetails.getCoRegion());
		showLitigationDetails.setFunction(litigationDetails.getDept().getDeptName());
		showLitigationDetails.setCustomerType(litigationDetails.getCustomerType().getCustomerType());
		showLitigationDetails.setCounterPartyName(litigationDetails.getCounterPartyDtls().getCustomerName());
		showLitigationDetails.setAgainstPartyClientCustomerType(litigationDetails.getAgainstPartyClientType());
		showLitigationDetails.setCoCounterParties(litigationDetails.getCoCounterParties());
		showLitigationDetails.setSubject(litigationDetails.getSubject());
		showLitigationDetails.setCategoryName(litigationDetails.getLtgnCaseType().getCaseType());
		showLitigationDetails.setCaseNumber(litigationDetails.getCaseNumber());
		showLitigationDetails.setRisk(litigationDetails.getRisk().getRisk());
		showLitigationDetails.setClaim(litigationDetails.getClaim().getClaim());
		showLitigationDetails.setPossibleClaim(litigationDetails.getExactClaimAmount());
		showLitigationDetails.setPantaloonFileNo(litigationDetails.getFileNo());
		showLitigationDetails.setUnderActName(litigationDetails.getUnderAct().getUnderAct());
		showLitigationDetails.setUnderSection(litigationDetails.getUnderSection());
		showLitigationDetails.setOtherUnderacts(litigationDetails.getOtherUnderAct());
		showLitigationDetails.setCourtTypeName(litigationDetails.getCourtType().getCourtType());
		showLitigationDetails.setStateName(litigationDetails.getState().getStateName());
		showLitigationDetails.setCityName(litigationDetails.getCity());
		showLitigationDetails.setFirNo(litigationDetails.getFirNo());
		showLitigationDetails.setCourtForum(litigationDetails.getCourtCity().getCourtCity());
		showLitigationDetails.setDateOfReceiptOfMatter(litigationDetails.getCaseFileOnDate());
		showLitigationDetails.setFactsOfLitigation(litigationDetails.getFactOfLitigationMatter());
		showLitigationDetails.setCounselAssesment(litigationDetails.getCounselAssesment());
		showLitigationDetails.setRemark(litigationDetails.getRemark());
		showLitigationDetails.setLawfirm(litigationDetails.getLawFirm().getLawfirm());
		showLitigationDetails.setSeniorCounsel(litigationDetails.getLawFirm().getLawfirm());
		return showLitigationDetails;
	}

	
	@Override
	public void saveNextHearingDate(ConnectedLitigationVO connectedLitigationVO) {
		LtgnLitigationLog ltgnLitigationLog = new LtgnLitigationLog();
		ltgnLitigationLog.setStage(connectedLitigationVO.getStageDetails());
		StageMaster stageMaster = stageMasterRepository.findByStageName(connectedLitigationVO.getStage());
		ltgnLitigationLog.setStageMaster(stageMaster);
//		ltgnLitigationLog.setNextDateOfHearing(connectedLitigationVO.getHearingDt());
		ltgnLitigationLog.setDateOfHearing(connectedLitigationVO.getHearingDt());
		Optional<Litigation> litigationDtls = litigationRepository.findById(connectedLitigationVO.getLitigationOId());
		ltgnLitigationLog.setLitigation(litigationDtls.get());
		User userDtls = userRepository.findByLoginId(connectedLitigationVO.getLoginId());
		ltgnLitigationLog.setUser(userDtls);
		ltgnLitigationLogRepository.save(ltgnLitigationLog);

	}

	@Override
	public List<ConnectedLitigationVO> getHistoryDtls(String litigationId) {
		List<LtgnLitigationLog> allHistoryDtls = ltgnLitigationLogRepository.getHistoryDtls(litigationId);
		return allHistoryDtls.stream().map(historyDtls -> convertToHistoryDtlsDto(historyDtls))
				.collect(Collectors.toList());
	}

	private ConnectedLitigationVO convertToHistoryDtlsDto(LtgnLitigationLog historyDtls) {
		ConnectedLitigationVO allHistoryDtls = new ConnectedLitigationVO();
		allHistoryDtls.setHearingId(historyDtls.getLitigationLogId());
		allHistoryDtls.setHearingDt(historyDtls.getNextDateOfHearing());
		allHistoryDtls.setStage(historyDtls.getStageMaster().getStage());
		allHistoryDtls.setStageDetails(historyDtls.getStage());
		allHistoryDtls.setEvent(historyDtls.getHearingEvent());
		allHistoryDtls.setAddedBy(historyDtls.getUser().getLoginId());
		allHistoryDtls.setAttendedBy(historyDtls.getHearingAttendedBy());
		return allHistoryDtls;
	}

	@Override
	public void updateHearingDetails(ConnectedLitigationVO connectedLitigationVO, String litigationId) {
		LtgnLitigationLog updateLtgnLitigationLog = new LtgnLitigationLog();
		LitigationHistoryTLog litigationHistoryTLog = new LitigationHistoryTLog();
		LtgnLitigationLog ltgnLitigationLog = ltgnLitigationLogRepository.findByLitigationId(litigationId);
		if (ltgnLitigationLog != null) {
			updateLtgnLitigationLog.setLitigationLogId(ltgnLitigationLog.getLitigationLogId());
			updateLtgnLitigationLog.setLitigation(ltgnLitigationLog.getLitigation());
			updateLtgnLitigationLog.setStageMaster(ltgnLitigationLog.getStageMaster());
			System.out.println("UserID:: " + ltgnLitigationLog.getUser());
			updateLtgnLitigationLog.setUser(ltgnLitigationLog.getUser());
			litigationHistoryTLog.setUser(ltgnLitigationLog.getUser());
			litigationHistoryTLog.setLtgnLitigationLog(ltgnLitigationLog);
		}
		updateLtgnLitigationLog.setNextDateOfHearing(connectedLitigationVO.getHearingDt());
		updateLtgnLitigationLog.setStage(connectedLitigationVO.getStageDetails());
		updateLtgnLitigationLog.setHearingEvent(connectedLitigationVO.getEvent());
		updateLtgnLitigationLog.setHearingAttendedBy(connectedLitigationVO.getAttendedBy());

		litigationHistoryTLog.setActivityType("History Added");
		LocalDateTime dateTime = LocalDateTime.now();
		litigationHistoryTLog.setCreateDate(dateTime);
		litigationHistoryTLog.setActivityDescription("Hearing Dt:" + connectedLitigationVO.getHearingDt() + "  Stage: "
				+ connectedLitigationVO.getStageDetails() + "  Event: " + connectedLitigationVO.getEvent()
				+ "  Attended By: " + connectedLitigationVO.getAttendedBy());

		ltgnLitigationLogRepository.save(updateLtgnLitigationLog);
		litigationHistoryTLogRepository.save(litigationHistoryTLog);

	}

	@Override
	public List<ConnectedLitigationVO> getActivityLog(String litigationId) {
		List<LitigationHistoryTLog> allActivityLog = litigationHistoryTLogRepository
				.findActivityLogByLitigationId(litigationId);
		return allActivityLog.stream().map(activityLog -> convertToActivityLogDto(activityLog))
				.collect(Collectors.toList());
	}

	private ConnectedLitigationVO convertToActivityLogDto(LitigationHistoryTLog activityLog) {
		StringBuilder activityDescription = new StringBuilder();
		ConnectedLitigationVO allActivityLog = new ConnectedLitigationVO();
		allActivityLog.setUpdatedBy(activityLog.getUser().getLoginId());
		allActivityLog.setLogId(activityLog.getLitigationHistoryTLogId()); // litigationhistory log id
		allActivityLog.setlHistoryId(activityLog.getLtgnLitigationLog().getLitigationLogId()); // ltgnLitigationOgid as
																								// hearing id
		allActivityLog.setActivityType(activityLog.getActivityType());
		activityDescription.append("Hearing Dt: " + activityLog.getLtgnLitigationLog().getNextDateOfHearing());
		activityDescription.append("Stage: " + activityLog.getLtgnLitigationLog().getStage());
		activityDescription.append("Event: " + activityLog.getLtgnLitigationLog().getHearingEvent());
		activityDescription.append("Attended By: " + activityLog.getLtgnLitigationLog().getHearingAttendedBy());
		allActivityLog.setActivityDescription(activityDescription);
		allActivityLog.setModifiedDate(activityLog.getCreateDate());
		return allActivityLog;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void updateLitigationData(LitigationSummaryVO addLitigationVO) {
//		final String prefixLtgnId = "L";
		Litigation litigation = new Litigation();
		LitigationUnits litigationUnits = new LitigationUnits();
		HashMap<String, String> allCustomerTypes = new HashMap<String, String>();
		System.out.println(allCustomerTypes);
		allCustomerTypes.put("Plaintiff", "Defendant");
		allCustomerTypes.put("Defendant", "Plaintiff");
		allCustomerTypes.put("Complainant", "Accused");
		allCustomerTypes.put("Accused", "Complainant");
		allCustomerTypes.put("Applicant", "Opponent");
		allCustomerTypes.put("Opponent", "Applicant");
		allCustomerTypes.put("Petitioner", "Respondant");
		allCustomerTypes.put("Respondant", "Petitioner");
		allCustomerTypes.put("Appelant", "Respondant");
		allCustomerTypes.put("Appeal By Company", "Petitioner");
		allCustomerTypes.put("Appeal Against Company", "Petitioner");

		Format formatDtls = formatRepository.getFormatByName(addLitigationVO.getFormat());
		litigation.setFormat(formatDtls);

		litigation.setAddress(addLitigationVO.getAddress());
		litigation.setStoreOfficePremises(addLitigationVO.getStoreOfficePremises());
		litigation.setCoRegion(addLitigationVO.getCoZone());

		CustomerType customerType = customerTypeRepository.findByCustomerType(addLitigationVO.getCustomerType());
		litigation.setCustomerType(customerType);

		for (Entry<String, String> entry : allCustomerTypes.entrySet()) {
			if (entry.getKey().equals(litigation.getCustomerType().getCustomerType())) {
				System.out.println(entry.getValue());
				litigation.setAgainstPartyClientType(entry.getValue());
			}
		}

		CounterPartyDtls counterPartyDtls = counterPartyRepository
				.findByCustomerName(addLitigationVO.getCounterPartyName());
		litigation.setCounterPartyDtls(counterPartyDtls);
		litigation.setCoCounterParties(addLitigationVO.getCoCounterParties());
		litigation.setSubject(addLitigationVO.getSubject());

		LtgnCaseType ltgnCaseType = ltgnCaseTypeRepository.findByCaseType(addLitigationVO.getCategoryName());
		litigation.setLtgnCaseType(ltgnCaseType);
		litigation.setCaseNumber(addLitigationVO.getCaseNumber());

		String function = "Legal";
		Dept dept = deptRepository.findByDeptName(function);
		litigation.setDept(dept);
		
		Risk riskAsses = riskRepository.findByRisk(addLitigationVO.getRisk());
		litigation.setRisk(riskAsses);

		Claim claimRange = claimRepository.findByClaim(addLitigationVO.getClaim());
		litigation.setClaim(claimRange);
		litigation.setExactClaimAmount(addLitigationVO.getPossibleClaim());
		litigation.setFileNo(addLitigationVO.getFileNo());

		UnderAct underActs = underActRepository.findByUnderAct(addLitigationVO.getUnderActName());
		litigation.setUnderAct(underActs);
		litigation.setUnderSection(addLitigationVO.getUnderSection());
		litigation.setOtherUnderAct(addLitigationVO.getOtherUnderAct());

		CourtType courtType = courtTypeRepository.findByCourtType(addLitigationVO.getCourtTypeName());
		litigation.setCourtType(courtType);

		State state = stateRepository.findByStateName(addLitigationVO.getStateName());
		litigation.setState(state);

		litigation.setCity(addLitigationVO.getCityName());
		litigation.setFirNo(addLitigationVO.getFirNo());

		PoliceStation policeStation = policeStationRepository.getPoliceDtlsByCityName(addLitigationVO.getCityName());
		litigation.setPoliceStationId(policeStation);

		CourtCity courtCity = courtCityRepository.findByCourtCity(addLitigationVO.getCourtForum());
		litigation.setCourtCity(courtCity);

		Status status = statusRepository.findByStatusName(addLitigationVO.getStatus());
		litigation.setStatus(status);
		litigation.setCaseFileOnDate(addLitigationVO.getDateOfReceiptOfMatterCase());
		litigation.setFactOfLitigationMatter(addLitigationVO.getFactsOfLitigation());
		litigation.setStage(addLitigationVO.getStage());
		litigation.setCounselAssesment(addLitigationVO.getCounselAssesment());
		litigation.setRemark(addLitigationVO.getRemark());
		litigation.setNextDateOfHearing(addLitigationVO.getNextHearingDate());

		LawFirm seniorLawfirm = outsideCounselRepository.findByLawfirm(addLitigationVO.getSeniorCounsel());
		litigation.setLawFirmSenior(seniorLawfirm);

		LawFirm lawfirm = outsideCounselRepository.findByLawfirm(addLitigationVO.getLawfirm());
		litigation.setLawFirm(lawfirm);

		User user = userRepository.findByLoginId(addLitigationVO.getLoginId());
		litigation.setUser(user);

		// process for matterByAgainst
		List<String> representativeNameList = new ArrayList<String>();
		System.out.println("Size:: " + addLitigationVO.getMatterByAgainst().size());
		if (addLitigationVO.getMatterByAgainst() != null) {
			// fetch litigationMatterByAgainst values based on litigationId
			List<LitigationMatterByAgainst> checkAllMatterByAgainst = litigationMatterByAgainstRepository
					.findMatterByAgainstByLitigationId(addLitigationVO.getLitigationOId());
			for (LitigationMatterByAgainst matterByAgainst : checkAllMatterByAgainst) {
				System.out.println("represntativeName**" +matterByAgainst.getLtgnRepresentativeMaster().getRepresentativeName());
				System.out.println("matterByAgainst"+addLitigationVO.getMatterByAgainst());
				
				if(addLitigationVO.getMatterByAgainst().contains(matterByAgainst.getLtgnRepresentativeMaster().getRepresentativeName())) {
					System.out.println("contained"+matterByAgainst.getLtgnRepresentativeMaster().getRepresentativeName());
				}else {
					System.out.println("not containes"+matterByAgainst.getLtgnRepresentativeMaster().getRepresentativeName());
					representativeNameList.add(matterByAgainst.getLtgnRepresentativeMaster().getRepresentativeName());
				}
				/*if (!(addLitigationVO.getMatterByAgainst())
						.contains(matterByAgainst.getLtgnRepresentativeMaster().getRepresentativeName())) {
					representativeNameList.add(matterByAgainst.getLtgnRepresentativeMaster().getRepresentativeName());
				}*/
			}

			if(representativeNameList != null) {
				for (String representName : representativeNameList) {
					int isDeleted = litigationMatterByAgainstRepository.deleteMappedRepresentativeName(representName);
					if (isDeleted > 0) {
						System.out.println("Mapped Representative Name Deleted Successfully");
					}
				}
			}
			

			for (String matterByAgainst : addLitigationVO.getMatterByAgainst()) {
				System.out.println("representativeName:: " + matterByAgainst);
				LitigationMatterByAgainst checkMatterByExists = litigationMatterByAgainstRepository
						.findMatterByAgainst(matterByAgainst, addLitigationVO.getLitigationOId());
				if (checkMatterByExists == null) {
					// insert new records

					/*Litigation saveMatterByAgainstData = litigationRepository
							.getDtlsByLitigationRepresentativeName(matterByAgainst, addLitigationVO.getLitigationOId());

					LitigationMatterByAgainst litigationMatterByAgainst = new LitigationMatterByAgainst();

					litigationMatterByAgainst.setLitigation(saveMatterByAgainstData);
					litigationMatterByAgainst.getLitigation()
							.setLitigationOId(saveMatterByAgainstData.getLitigationOId());

					System.out
							.println("LitigationOID:: " + litigationMatterByAgainst.getLitigation().getLitigationOId());

					litigationMatterByAgainst
							.setLtgnRepresentativeMaster(saveMatterByAgainstData.getLtgnRepresentativeMaster());
					System.out.println("Representative Matter:: "
							+ litigationMatterByAgainst.getLtgnRepresentativeMaster().getRepresentativeId());*/
					LitigationMatterByAgainst litigationMatterByAgainst = new LitigationMatterByAgainst();
					LtgnRepresentativeMaster representativeMaster = ltgnRepresentativeMasterRepository.findByRepresentativeName(matterByAgainst);
					litigationMatterByAgainst.setLtgnRepresentativeMaster(representativeMaster);
					
					Optional<Litigation> ltgnDtls = litigationRepository.findById(addLitigationVO.getLitigationOId());
					litigationMatterByAgainst.setLitigation(ltgnDtls.get());
					
					System.out.println(litigationMatterByAgainst.getLitigationMatterByAgainstId());
					System.out.println(litigationMatterByAgainst.getLitigation().getLitigationOid());
					
					litigationMatterByAgainstRepository.save(litigationMatterByAgainst);
				} else {
					// fetch record and update
					System.out.println("Representative Name Already Mapped");
					Litigation updateMatterByAgainstData = litigationRepository
							.getDtlsByLitigationRepresentativeName(matterByAgainst, addLitigationVO.getLitigationOId());
					litigation.setLtgnRepresentativeMaster(updateMatterByAgainstData.getLtgnRepresentativeMaster());
					litigation.setLitigationMatterByAgainst(updateMatterByAgainstData.getLitigationMatterByAgainst());
//					litigation.setLtgnLitigationLog(updateMatterByAgainstData.getLtgnLitigationLog());
				}

			}
		}

		// Process for adding unitDetails
		/*Units unitsDtls = new Units();
		System.out.println("EntityName:: " + addLitigationVO.getEntityName());
		if (addLitigationVO.getEntityName() != null || addLitigationVO.getZoneName() != null
				|| addLitigationVO.getUnitName() != null) {
			if (addLitigationVO.getEntityName().isEmpty() || addLitigationVO.getZoneName().isEmpty()
					|| addLitigationVO.getUnitName().isEmpty()) {
				System.out.println("Update is not required for litigationUnitDetails");
				LitigationUnits litigationUnitsDtls = litigationUnitsRepository.findUnitDetails(addLitigationVO.getLitigationOId());
				litigation.setUnits(litigationUnitsDtls.getUnits());
				System.out.println("UnitDtls:: " + litigationUnitsDtls.getUnits().getUnitId());
			} else {
				unitsDtls = unitsRepository.getUnitDtlsByEntityName(addLitigationVO.getEntityName(),
						addLitigationVO.getZoneName(), addLitigationVO.getUnitName());
				if (unitsDtls == null) {
					litigationUnits.setUnits(unitsDtls);
					litigationUnits.setLitigation(litigation);
					litigationUnitsRepository.save(litigationUnits);
				} else {
					System.out.println("UnitDetails Already Mapped with litigationUnits");
				}
			}
		}*/

		LitigationUnits litigationUnitsDtls = litigationUnitsRepository.findUnitDetails(addLitigationVO.getLitigationOId());
		litigation.setUnits(litigationUnitsDtls.getUnits());
		
		// update details by litigationOID
		Optional<Litigation> updateById = litigationRepository.findById(addLitigationVO.getLitigationOId());
		System.out.println("Update By OID " + updateById.get().getLitigationOid());
		litigation.setLitigationOid(addLitigationVO.getLitigationOId());
		litigation.setLitigationId(updateById.get().getLitigationId());
		System.out.println(litigation.getLitigationId());
		
		

		try {
			litigation.setCaseRelateFromDate(DateUtils.getDBFormatedDte(addLitigationVO.getCaseRelateFrmDte()));
			litigation.setCaseRelateToDate(DateUtils.getDBFormatedDte(addLitigationVO.getCaseRelateToDte()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

//		litigation.setUnits(unitsDtls);
		LocalDate dateTime = LocalDate.now();
		litigation.setCreateDate(dateTime);
//		litigationUnitsRepository.save(litigationUnits);

		litigationRepository.save(litigation);
		

	}

	@Override
	public List<UnitSummaryVO> showEntityRegionUnits(String litigationId) {
		List<Units> entityRegionUnitsDtls = unitsRepository.findDtlsByLitigationId(litigationId);
		return entityRegionUnitsDtls.stream()
				.map(allEntityRegionUnitsDtls -> convertToEntityRegionUnitsDto(allEntityRegionUnitsDtls))
				.collect(Collectors.toList());
	}

	private UnitSummaryVO convertToEntityRegionUnitsDto(Units allEntityRegionUnitsDtls) {
		UnitSummaryVO entityRegionUnitsDtls = new UnitSummaryVO();
		entityRegionUnitsDtls.setEntityName(allEntityRegionUnitsDtls.getEntitySummary().getEntityName());
		entityRegionUnitsDtls.setUnitName(allEntityRegionUnitsDtls.getUnitName());
		entityRegionUnitsDtls.setZoneName(allEntityRegionUnitsDtls.getRegions().getZoneName());
		return entityRegionUnitsDtls;
	}

	@Override
	public List<Litigation> getHearingStatusReportDtls(LocalDate fromDate, LocalDate toDate) {
		List<Litigation> hearingStatusReportDtls = litigationRepository.findHearingStatusDtlsByFromandToDate(fromDate,
				toDate);
		return hearingStatusReportDtls;
	}

	@Override
	public List<Dept> getdept() {
		List<Dept> allDept = deptRepository.findAll();
		return allDept;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LitigationSummaryVO> findLitigationSummaryFieldSelection(String zone, String format, String entity,
			String function, String counterParty, String category, String possibleClaim, String state,
			String lawfirmIndividual, String courtType, String underActs, String risk, String status,
			String matterByAgainst, String litigationByAgainst) {

		List<Litigation> litigationSummary = new ArrayList<>();
		Map<String, Object> parameterMap = new HashMap<>();
		List<String> whereClause = new ArrayList<>();
		StringBuilder reportQuery = new StringBuilder();

		reportQuery.append(
				"select lt from Litigation as lt join Units as u on u.unitId = lt.units.unitId join EntitySummary as e on e.entityId = u.entitySummary.entityId join Zone as r on r.zoneId = u.regions.zoneId join Risk as rs on rs.riskId = lt.risk.riskId join Claim as c on c.claimId = lt.claim.claimId join Status as s on s.statusId = lt.status.statusId join CounterPartyDtls cpd on cpd.id = lt.counterPartyDtls.id join CustomerType as ct on ct.customerTypeId = lt.customerType.customerTypeId ");

		if (!zone.equals("ALL")) {
			System.out.println(zone);
			whereClause.add(" r.zoneName=:zone ");
			parameterMap.put("zone", zone);
		}
		if (!format.equals("ALL")) {
			whereClause.add(" lt.format.format:format ");
			parameterMap.put("format", format);
		}
		if (!entity.equals("ALL")) {
			whereClause.add(" e.entityName=:entity ");
			parameterMap.put("entity", entity);
		}
		if (!counterParty.equals("ALL")) {
			whereClause.add(" cpd.customerName=:counterParty ");
			parameterMap.put("counterParty", counterParty);
		}
		if (!category.endsWith("ALL")) {
			whereClause.add(" lt.ltgnCaseType.caseType=:category ");
			parameterMap.put("category", category);
		}
		if (!possibleClaim.equals("ALL")) {
			whereClause.add(" c.claim=:possibleClaim ");
			parameterMap.put("possibleClaim", possibleClaim);
		}
		if (!state.equals("ALL")) {
			whereClause.add(" lt.state.stateName=:state ");
			parameterMap.put("state", state);
		}
		if (!lawfirmIndividual.equals("ALL")) {
			whereClause.add(" lt.lawFirm.lawfirm=:lawfirmIndividual ");
			parameterMap.put("lawfirmIndividual", lawfirmIndividual);
		}
		if (!courtType.equals("ALL")) {
			whereClause.add(" lt.courtType.courtType=:courtType ");
			parameterMap.put("courtType", courtType);
		}
		if (!underActs.equals("ALL")) {
			whereClause.add(" lt.underAct.underAct=:underActs ");
			parameterMap.put("underActs", underActs);
		}
		if (!risk.equals("ALL")) {
			System.out.println(risk);
			whereClause.add(" rs.risk=:risk ");
			parameterMap.put("risk", risk);
		}
		if (!status.equals("ALL")) {
			whereClause.add(" s.status=:status ");
			parameterMap.put("status", status);
		}
		if (!matterByAgainst.equals("ALL")) {
			whereClause.add(" lt.ltgnRepresentativeMaster.representativeName=:matterByAgainst ");
			parameterMap.put("matterByAgainst", matterByAgainst);
		}
		if (!litigationByAgainst.equals("ALL")) {
			whereClause.add(" lt.ltgnRepresentativeMaster.representativeName=:litigationByAgainst ");
			parameterMap.put("litigationByAgainst", litigationByAgainst);
		}

		reportQuery.append(" where deleteStatus != 1 ");
		
		if (!zone.equals("ALL") || !format.equals("ALL") || !entity.equals("ALL") || !counterParty.equals("ALL")
				|| !category.endsWith("ALL") || !possibleClaim.equals("ALL") || !state.equals("ALL")
				|| !lawfirmIndividual.equals("ALL") || !courtType.equals("ALL") || !underActs.equals("ALL")
				|| !risk.equals("ALL") || !status.equals("ALL") || !matterByAgainst.equals("ALL")
				|| !litigationByAgainst.equals("ALL")) {
			reportQuery.append(" and " + StringUtils.join(whereClause, " and "));
		}

		reportQuery.append(" order by lt.litigationId desc");
		
		Query jpaQuery = entityManager.createQuery(reportQuery.toString());
		LOGGER.info("Created Query::  " + reportQuery.toString());
		for (String key : parameterMap.keySet()) {
			jpaQuery.setParameter(key, parameterMap.get(key));
		}
		litigationSummary = jpaQuery.getResultList();
		LOGGER.info("List Size:: " + litigationSummary.size());
		return litigationSummary.stream().map(allLitigationSummary -> convertToLitigationSummary(allLitigationSummary))
				.collect(Collectors.toList());
	}

	@Override
	public LitigationSummaryVO getLitigationDetails(int id) {
		Optional<Litigation> litigationDtls = litigationRepository.findById(id);
		LitigationSummaryVO litigationSummaryVO = new LitigationSummaryVO();
		System.out.println("Litigation ID In service"+litigationDtls.get().getLitigationId());
		litigationSummaryVO.setLitigationId(litigationDtls.get().getLitigationId());
		litigationSummaryVO.setStatus(litigationDtls.get().getStatus().getStatus());
		litigationSummaryVO.setFileNo(litigationDtls.get().getFileNo());
		litigationSummaryVO.setEntityName(litigationDtls.get().getUnits().getEntitySummary().getEntityName());
		litigationSummaryVO.setUnitName(litigationDtls.get().getUnits().getUnitName());
		litigationSummaryVO.setCounterPartyName(litigationDtls.get().getCounterPartyDtls().getCustomerName());
		litigationSummaryVO.setCustomerType(litigationDtls.get().getCustomerType().getCustomerType());
		litigationSummaryVO.setCaseNumber(litigationDtls.get().getCaseNumber());
		litigationSummaryVO.setSubject(litigationDtls.get().getSubject());
		litigationSummaryVO.setStage(litigationDtls.get().getStage());
		litigationSummaryVO.setHearingDate(litigationDtls.get().getNextDateOfHearing());
		litigationSummaryVO.setRisk(litigationDtls.get().getRisk().getRisk());
		litigationSummaryVO.setClaim(litigationDtls.get().getClaim().getClaim());
		litigationSummaryVO.setPossibleClaim(litigationDtls.get().getExactClaimAmount());
		litigationSummaryVO.setRemark(litigationDtls.get().getRemark());
		litigationSummaryVO.setZoneName(litigationDtls.get().getUnits().getRegions().getZoneName());
//		litigationSummaryVO.setMatterBy(litigationDtls.get().getUnits().getEntitySummary().getEntityName());
		litigationSummaryVO.setDateOfReceiptOfMatterCase(litigationDtls.get().getCaseFileOnDate());
		litigationSummaryVO.setNextHearingDate(litigationDtls.get().getNextDateOfHearing());
		for(LitigationMatterByAgainst matterByAgainst: litigationDtls.get().getLitigationMatterByAgainst()) {
			litigationSummaryVO.setMatterBy(matterByAgainst.getLtgnRepresentativeMaster().getRepresentativeName());
			litigationSummaryVO.getMatterByAgainst().add(matterByAgainst.getLtgnRepresentativeMaster().getRepresentativeName());
		}
		
		litigationSummaryVO.setAgainstPartyClientType(litigationDtls.get().getAgainstPartyClientType());
		litigationSummaryVO.setCoCounterParties(litigationDtls.get().getCoCounterParties());
		litigationSummaryVO.setCoZone(litigationDtls.get().getCoRegion());
		litigationSummaryVO.setAddress(litigationDtls.get().getAddress());
		litigationSummaryVO.setFunction(litigationDtls.get().getDept().getDeptName());
		litigationSummaryVO.setFormat(litigationDtls.get().getFormat().getFormat());
		litigationSummaryVO.setUnderActName(litigationDtls.get().getUnderAct().getUnderAct());
		litigationSummaryVO.setUnderSection(litigationDtls.get().getUnderSection());
		litigationSummaryVO.setOtherUnderAct(litigationDtls.get().getOtherUnderAct());
		litigationSummaryVO.setStateName(litigationDtls.get().getState().getStateName());
		litigationSummaryVO.setCityName(litigationDtls.get().getCity());
		System.out.println(litigationDtls.get().getCaseRelateFromDate());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		if(litigationDtls.get().getCaseRelateFromDate() != null) {
			litigationSummaryVO.setCaseRelateFrmDte(litigationDtls.get().getCaseRelateFromDate().toString());
		}
		if(litigationDtls.get().getCaseRelateToDate() != null) {
			litigationSummaryVO.setCaseRelateToDte(litigationDtls.get().getCaseRelateToDate().toString());
		}
		
//		litigationSummaryVO.setJudgeName(litigationDtls.get().get);
		litigationSummaryVO.setFactsOfLitigation(litigationDtls.get().getFactOfLitigationMatter());
		litigationSummaryVO.setCounselAssesment(litigationDtls.get().getCounselAssesment());
		String lawfirm = litigationDtls.get().getLawFirm().getLawfirm() == null ? null : litigationDtls.get().getLawFirm().getLawfirm();
		litigationSummaryVO.setLawfirm(lawfirm);
		litigationSummaryVO.setSeniorCounsel(litigationDtls.get().getLawFirmSenior().getLawfirm());
		litigationSummaryVO.setFirNo(litigationDtls.get().getFirNo());
		litigationSummaryVO.setCategoryName(litigationDtls.get().getLtgnCaseType().getCaseType());
		if(litigationDtls.get().getPoliceStationId().getPoliceStation() != null) {
			litigationSummaryVO.setPoliceStation(litigationDtls.get().getPoliceStationId().getPoliceStation());
		}
		
		litigationSummaryVO.setCourtTypeName(litigationDtls.get().getCourtType().getCourtType());
		litigationSummaryVO.setCourtForum(litigationDtls.get().getCourtCity().getCourtCity());
		litigationSummaryVO.setStoreOfficePremises(litigationDtls.get().getStoreOfficePremises());
		
		return litigationSummaryVO;
	}

	@Override
	public List<CityStateVO> getAllCourtForum() {
		List<CourtCity> allCourtForum = courtCityRepository.findAll();
		return allCourtForum.stream().map(allCourtForumDtls -> convertToCourtForumVO(allCourtForumDtls))
				.collect(Collectors.toList());
	}

	private CityStateVO convertToCourtForumVO(CourtCity allCourtForumDtls) {
		CityStateVO courtForum = new CityStateVO();
		courtForum.setCourtForum(allCourtForumDtls.getCourtCity());
		return courtForum;
	}

	@Override
	public List<LitigationSummaryVO> getRestoreLitigationSummary() {
		List<Litigation> allRestoreLitigationSummary = litigationRepository.findRestoreLitigationSummary();
		return allRestoreLitigationSummary.stream().map(restoreLitigationSummary -> convertToLitigationSummary(restoreLitigationSummary))
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LitigationSummaryVO> findRestoreLitigationSummaryFieldSelection(String entity, String counterParty,
			String category, String courtType, String underActs, String risk) {
		
		List<Litigation> litigationSummary = new ArrayList<>();
		Map<String, Object> parameterMap = new HashMap<>();
		List<String> whereClause = new ArrayList<>();
		StringBuilder reportQuery = new StringBuilder();

		reportQuery.append(
				"select lt from Litigation as lt join Units as u on u.unitId = lt.units.unitId join EntitySummary as e on e.entityId = u.entitySummary.entityId join Zone as r on r.zoneId = u.regions.zoneId join Risk as rs on rs.riskId = lt.risk.riskId join Claim as c on c.claimId = lt.claim.claimId join Status as s on s.statusId = lt.status.statusId join CounterPartyDtls cpd on cpd.id = lt.counterPartyDtls.id join CustomerType as ct on ct.customerTypeId = lt.customerType.customerTypeId");
		
		if (!entity.equals("ALL")) {
			whereClause.add(" e.entityName=:entity ");
			parameterMap.put("entity", entity);
		}
		if (!counterParty.equals("ALL")) {
			whereClause.add(" cpd.customerName=:counterParty ");
			parameterMap.put("counterParty", counterParty);
		}
		if (!category.equals("ALL")) {
			whereClause.add(" lt.ltgnCaseType.caseType=:category ");
			parameterMap.put("category", category);
		}
		
		if (!courtType.equals("ALL")) {
			whereClause.add(" lt.courtType.courtType=:courtType ");
			parameterMap.put("courtType", courtType);
		}
		if (!underActs.equals("ALL")) {
			whereClause.add(" lt.underAct.underAct=:underActs ");
			parameterMap.put("underActs", underActs);
		}
		if (!risk.equals("ALL")) {
			System.out.println(risk);
			whereClause.add(" rs.risk=:risk ");
			parameterMap.put("risk", risk);
		}
		
		reportQuery.append(" where deleteStatus = 1 ");

		if (!entity.equals("ALL") || !counterParty.equals("ALL") || !category.endsWith("ALL") || 
				!courtType.equals("ALL") || !underActs.equals("ALL") || !risk.equals("ALL") ) {
			reportQuery.append(" and " +StringUtils.join(whereClause, " and "));
		}

		LOGGER.info("Created Query::  " + reportQuery.toString());
		Query jpaQuery = entityManager.createQuery(reportQuery.toString());
		
		for (String key : parameterMap.keySet()) {
			jpaQuery.setParameter(key, parameterMap.get(key));
		}
		litigationSummary = jpaQuery.getResultList();
		LOGGER.info("List Size:: " + litigationSummary.size());
		return litigationSummary.stream().map(allLitigationSummary -> convertToLitigationSummary(allLitigationSummary))
				.collect(Collectors.toList());
	}

	@Override
	public int updateRestoreLitigationData(int loId) {
		int isRestoreLitigationUpdated = litigationRepository.updateRestoreLitigationData(loId);
		return isRestoreLitigationUpdated;
	}

	@Override
	public List<ConnectedLitigationVO> getWitnessDetails(int id) {
		List<Litigation> allWitnessDtls = litigationRepository.getWitnessDtls(id);
		ConnectedLitigationVO witnessDtls = new ConnectedLitigationVO();
		List<ConnectedLitigationVO> witnessDtlsList = new ArrayList<ConnectedLitigationVO>();
		for(Litigation wDtls : allWitnessDtls) {
//			
/*			for(Witness witnessData : wDtls.getWitness()) {
				witnessDtls.setWitnessName(witnessData.getWitness());
				witnessDtls.setWitnessId(witnessData.getWitnessId());
			}*/
			witnessDtls.setLitigationId(wDtls.getLitigationId());
			
		}
		witnessDtlsList.add(witnessDtls);
		return witnessDtlsList;
	}

	@Override
	public List<ConnectedLitigationVO> getLitigationIds() {
		List<Litigation> allLtgnId = litigationRepository.findAll();
		List<ConnectedLitigationVO> listLtgnId = new ArrayList<>();
		
		for(Litigation litigationID : allLtgnId) {
			ConnectedLitigationVO ltgnId = new ConnectedLitigationVO();
			ltgnId.setLitigationId(litigationID.getLitigationId());
			listLtgnId.add(ltgnId);
		}
		return listLtgnId;
	}

	@Override
	public List<ConnectedLitigationVO> getAllBillingTypes() {
		List<BillingType> allBillingTypes = billingTypeRepository.findAll();
		List<ConnectedLitigationVO> listBillingTypes = new ArrayList<>();
		for(BillingType billingType : allBillingTypes) {
			ConnectedLitigationVO billingDtls = new ConnectedLitigationVO();
			billingDtls.setBillingType(billingType.getBillingType());
			listBillingTypes.add(billingDtls);
		}
		return listBillingTypes;
	}

	@Override
	public List<ConnectedLitigationVO> getAllStageDetails() {
		List<StageMaster> allStageDtls = stageMasterRepository.findAll();
		
		List<ConnectedLitigationVO> listConnectedList = new ArrayList<>();
		for(StageMaster stageDtls : allStageDtls) {
			ConnectedLitigationVO connectedLitigationVO = new ConnectedLitigationVO();
			connectedLitigationVO.setStage(stageDtls.getStage());
			listConnectedList.add(connectedLitigationVO);
		}
		return listConnectedList;
	}

	@Override
	public List<ConnectedLitigationVO> getHistorySummary(int id) {
		List<Litigation> allHistoryDtls = litigationRepository.getHistorySummary(id);
		List<ConnectedLitigationVO> listHistoryDtls = new ArrayList<>();
		for(Litigation historyDtls: allHistoryDtls) {
			ConnectedLitigationVO historyDetail = new ConnectedLitigationVO();
			historyDetail.setStage(historyDtls.getLtgnLitigationLog().get(0).getStage());
			historyDetail.setStageDetails(historyDtls.getLtgnLitigationLog().get(0).getStageMaster().getStage());
			historyDetail.setEvent(historyDtls.getLtgnLitigationLog().get(0).getHearingEvent());
			historyDetail.setAddedBy(historyDtls.getLtgnLitigationLog().get(0).getUser().getLoginId());
			historyDetail.setAttendedBy(historyDtls.getLtgnLitigationLog().get(0).getHearingAttendedBy());
			historyDetail.setHearingId(historyDtls.getLtgnLitigationLog().get(0).getLitigationLogId());
			historyDetail.setHearingDt(historyDtls.getLtgnLitigationLog().get(0).getNextDateOfHearing());
			historyDetail.setLitigationId(historyDtls.getLitigationId());
			listHistoryDtls.add(historyDetail);
		}
		return listHistoryDtls;
	}

	@Override
	public ConnectedLitigationVO findHistoryDetails(int hearingId) {
		Optional<LtgnLitigationLog> historyDtls = ltgnLitigationLogRepository.findById(hearingId);
		ConnectedLitigationVO connectedLitigationVO = new ConnectedLitigationVO();
		connectedLitigationVO.setLitigationId(historyDtls.get().getLitigation().getLitigationId());
		connectedLitigationVO.setHearingId(historyDtls.get().getLitigationLogId());
		connectedLitigationVO.setHearingDt(historyDtls.get().getNextDateOfHearing());
		connectedLitigationVO.setStageDetails(historyDtls.get().getStage());
		connectedLitigationVO.setEvent(historyDtls.get().getHearingEvent());
		connectedLitigationVO.setAttendedBy(historyDtls.get().getHearingAttendedBy());
		return connectedLitigationVO;
	}

	@Override
	public List<LitigationSummaryVO> getDashboardSummary() {
		List<DashboardVO> dashboardSummary = litigationRepository.getDashboardSummary();
		List<LitigationSummaryVO> listDashboardSummary = new ArrayList<>();
		
		System.out.println("Size**" +dashboardSummary.size());
		for(DashboardVO summaryDtls : dashboardSummary) {
			LitigationSummaryVO litigationSummaryVO = new LitigationSummaryVO();
			System.out.println("UnitName::" +summaryDtls.getunitName());
			System.out.println("regionName::" +summaryDtls.getregionName());
			System.out.println("upcoming::" +summaryDtls.getupcoming());
			System.out.println("notUpdated::" +summaryDtls.getnotUpdated());
			System.out.println("total::" +summaryDtls.gettotal());
			litigationSummaryVO.setUnitName(summaryDtls.getunitName());
			litigationSummaryVO.setZoneName(summaryDtls.getregionName());
			litigationSummaryVO.setUpdated(summaryDtls.getupcoming());
			litigationSummaryVO.setNotUpdated(summaryDtls.getnotUpdated());
			litigationSummaryVO.setTotal(summaryDtls.gettotal());
			
			listDashboardSummary.add(litigationSummaryVO);
		}
		return listDashboardSummary;
	}

	

}
