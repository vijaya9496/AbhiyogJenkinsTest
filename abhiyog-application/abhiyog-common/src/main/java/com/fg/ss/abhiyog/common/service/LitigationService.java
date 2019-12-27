package com.fg.ss.abhiyog.common.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.City;
import com.fg.ss.abhiyog.common.model.Claim;
import com.fg.ss.abhiyog.common.model.CounterPartyDtls;
import com.fg.ss.abhiyog.common.model.CourtCity;
import com.fg.ss.abhiyog.common.model.CourtType;
import com.fg.ss.abhiyog.common.model.CustomerType;
import com.fg.ss.abhiyog.common.model.Dept;
import com.fg.ss.abhiyog.common.model.Format;
import com.fg.ss.abhiyog.common.model.LawFirm;
import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.model.LitigationMatterByAgainst;
import com.fg.ss.abhiyog.common.model.LitigationUnits;
import com.fg.ss.abhiyog.common.model.LtgnCaseType;
import com.fg.ss.abhiyog.common.model.LtgnRepresentativeMaster;
import com.fg.ss.abhiyog.common.model.PoliceStation;
import com.fg.ss.abhiyog.common.model.Risk;
import com.fg.ss.abhiyog.common.model.State;
import com.fg.ss.abhiyog.common.model.Status;
import com.fg.ss.abhiyog.common.model.UnderAct;
import com.fg.ss.abhiyog.common.model.Units;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.repository.CityRepository;
import com.fg.ss.abhiyog.common.repository.ClaimRepository;
import com.fg.ss.abhiyog.common.repository.CounterPartyRepository;
import com.fg.ss.abhiyog.common.repository.CourtCityRepository;
import com.fg.ss.abhiyog.common.repository.CourtTypeRepository;
import com.fg.ss.abhiyog.common.repository.CustomerTypeRepository;
import com.fg.ss.abhiyog.common.repository.DeptRepository;
import com.fg.ss.abhiyog.common.repository.FormatRepository;
import com.fg.ss.abhiyog.common.repository.LitigationMatterByAgainstRepository;
import com.fg.ss.abhiyog.common.repository.LitigationRepository;
import com.fg.ss.abhiyog.common.repository.LitigationUnitsRepository;
import com.fg.ss.abhiyog.common.repository.LtgnCaseTypeRepository;
import com.fg.ss.abhiyog.common.repository.LtgnRepresentativeMasterRepository;
import com.fg.ss.abhiyog.common.repository.OutsideCounselRepository;
import com.fg.ss.abhiyog.common.repository.PoliceStationRepository;
import com.fg.ss.abhiyog.common.repository.RiskRepository;
import com.fg.ss.abhiyog.common.repository.StateRepository;
import com.fg.ss.abhiyog.common.repository.StatusRepository;
import com.fg.ss.abhiyog.common.repository.UnderActRepository;
import com.fg.ss.abhiyog.common.repository.UnitsRepository;
import com.fg.ss.abhiyog.common.repository.UserRepository;
import com.fg.ss.abhiyog.common.vo.AddLitigationVO;
import com.fg.ss.abhiyog.common.vo.CityStateVO;
import com.fg.ss.abhiyog.common.vo.CourtTypeVO;
import com.fg.ss.abhiyog.common.vo.CustomerTypeStatusVO;
import com.fg.ss.abhiyog.common.vo.LtgnCategoryVO;
import com.fg.ss.abhiyog.common.vo.LtgnMatterByVO;
import com.fg.ss.abhiyog.common.vo.RiskClaimVO;
import com.fg.ss.abhiyog.common.vo.UnderActVO;

@Service
public class LitigationService implements ILitigationService {

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

	static int i = 0001;

	@Override
	public LtgnRepresentativeMaster checkExistenceRepresentativeName(String matterBy) {
		LtgnRepresentativeMaster ltgnRepresentativeMaster = ltgnRepresentativeMasterRepository
				.findByRepresentativeName(matterBy);
		return ltgnRepresentativeMaster;
	}

	@Override
	public void saveLtgnRepresentativeMaster(LtgnMatterByVO ltgnMastersByVo) {
		LtgnRepresentativeMaster ltgnRepresentativeMasterData = new LtgnRepresentativeMaster();
		ltgnRepresentativeMasterData.setRepresentativeName(ltgnMastersByVo.getMatterBy());
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
	public void saveLtgnCaseType(LtgnCategoryVO ltgnCategoryVO) {
		LtgnCaseType ltgnCaseType = new LtgnCaseType();
		ltgnCaseType.setCaseType(ltgnCategoryVO.getCategoryName());
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
	public void saveUnderActData(UnderActVO underActVO) {
		UnderAct underAct = new UnderAct();
		underAct.setUnderAct(underActVO.getUnderActName());
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
	public void saveCourtType(CourtTypeVO courtTypeVO) {
		CourtType courtType = new CourtType();
		courtType.setCourtType(courtTypeVO.getCourtTypeName());
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
	public void saveCityData(CityStateVO cityStateVO) {
		City cityDtls = new City();
		cityDtls.setCityName(cityStateVO.getCityName());
		State stateDtls = stateRepository.findByStateName(cityStateVO.getStateName());
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
	public void saveCourtCityData(CityStateVO cityStateCourtForumVO) {
		CourtCity courtCity = new CourtCity();
		courtCity.setCourtCity(cityStateCourtForumVO.getCourtForum());
		City city = cityRepository.findByCityName(cityStateCourtForumVO.getCityName());
		courtCity.setCity(city);
		courtCityRepository.save(courtCity);
	}

	@SuppressWarnings("unused")
	@Override
	public void saveLitigationData(AddLitigationVO addLitigationVO) {
		final String prefixLtgnId = "L";
		Litigation litigation = new Litigation();
		LitigationMatterByAgainst litigationMatterByAgainst = new LitigationMatterByAgainst();
		LitigationUnits litigationUnits = new LitigationUnits();

		Units unitsDtls = unitsRepository.getUnitDtlsByEntityName(addLitigationVO.getEntityName(),
				addLitigationVO.getZoneName(), addLitigationVO.getUnitName());
		litigation.setUnits(unitsDtls);
		litigationUnits.setUnits(unitsDtls);

		Format formatDtls = formatRepository.getFormatByName(addLitigationVO.getFormat());
		litigation.setFormat(formatDtls);

		litigation.setAddress(addLitigationVO.getAddress());
		litigation.setStoreOfficePremises(addLitigationVO.getStoreOfficePremises());
		litigation.setCoRegion(addLitigationVO.getCoZone());

		CustomerType customerType = customerTypeRepository.findByCustomerType(addLitigationVO.getCustomerType());
		litigation.setCustomerType(customerType);

		CounterPartyDtls counterPartyDtls = counterPartyRepository.findByCustomerName(addLitigationVO.getDefendant());
		litigation.setCounterPartyDtls(counterPartyDtls);
		litigation.setCoCounterParties(addLitigationVO.getCoCounterParties());
		litigation.setSubject(addLitigationVO.getSubject());

		LtgnCaseType ltgnCaseType = ltgnCaseTypeRepository.findByCaseType(addLitigationVO.getCategory());
		litigation.setLtgnCaseType(ltgnCaseType);
		litigation.setCaseNumber(addLitigationVO.getCaseNumber());

		Risk riskAsses = riskRepository.findByRisk(addLitigationVO.getRiskAssesment());
		litigation.setRisk(riskAsses);

		Claim claimRange = claimRepository.findByClaim(addLitigationVO.getPossibleClaimRange());
		litigation.setClaim(claimRange);
		litigation.setExactClaimAmount(addLitigationVO.getPossibleClaim());
		litigation.setFileNo(addLitigationVO.getPantaloonFileNo());

		UnderAct underActs = underActRepository.findByUnderAct(addLitigationVO.getUnderActs());
		litigation.setUnderAct(underActs);
		litigation.setUnderSection(addLitigationVO.getUnderSection());
		litigation.setOtherUnderAct(addLitigationVO.getOtherUnderacts());

		CourtType courtType = courtTypeRepository.findByCourtType(addLitigationVO.getCourtType());
		litigation.setCourtType(courtType);

		State state = stateRepository.findByStateName(addLitigationVO.getState());
		litigation.setState(state);

		litigation.setCity(addLitigationVO.getCity());
		litigation.setFirNo(addLitigationVO.getFirNo());

		PoliceStation policeStation = policeStationRepository.getPoliceDtlsByCityName(addLitigationVO.getCity());
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

		Litigation litgationId = litigationRepository.getLitigationId();
//		Page<Litigation> litgationId = litigationRepository.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC,"litigationId")));

		if (litgationId == null) {
			litigation.setLitigationId(prefixLtgnId + String.format("%04d", i));
		} else {
			String totalId = litgationId.getLitigationId();
			int lastIdValue = Integer.parseInt(totalId.substring(totalId.length() - 4));
			litigation.setLitigationId(prefixLtgnId + String.format("%04d", ++lastIdValue));
		}

		Dept dept = deptRepository.findByDeptName(addLitigationVO.getFunction());
		litigation.setDept(dept);

		for (String matterByAgainst : addLitigationVO.getMatterByAgainst()) {
			LtgnRepresentativeMaster ltgnRepresentativeMaster = ltgnRepresentativeMasterRepository
					.findByRepresentativeName(matterByAgainst);
			litigation.setLtgnRepresentativeMaster(ltgnRepresentativeMaster);
			litigationMatterByAgainst.setLtgnRepresentativeMaster(ltgnRepresentativeMaster);
			litigationMatterByAgainst.setLitigation(litigation);
		}
//		i++;
		litigationUnits.setLitigation(litigation);
		LocalDate dateTime = LocalDate.now();
		litigation.setCreateDate(dateTime);
		litigationUnitsRepository.save(litigationUnits);
		litigationRepository.save(litigation);
		litigationMatterByAgainstRepository.save(litigationMatterByAgainst);

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

}
