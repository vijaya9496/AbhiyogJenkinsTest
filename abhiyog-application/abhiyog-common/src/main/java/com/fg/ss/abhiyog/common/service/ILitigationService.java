package com.fg.ss.abhiyog.common.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fg.ss.abhiyog.common.model.City;
import com.fg.ss.abhiyog.common.model.CourtCity;
import com.fg.ss.abhiyog.common.model.CourtType;
import com.fg.ss.abhiyog.common.model.Dept;
import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.model.LtgnCaseType;
import com.fg.ss.abhiyog.common.model.LtgnRepresentativeMaster;
import com.fg.ss.abhiyog.common.model.UnderAct;
import com.fg.ss.abhiyog.common.vo.AddLitigationVO;
import com.fg.ss.abhiyog.common.vo.CityStateVO;
import com.fg.ss.abhiyog.common.vo.ConnectedLitigationVO;
import com.fg.ss.abhiyog.common.vo.CourtTypeVO;
import com.fg.ss.abhiyog.common.vo.CustomerTypeStatusVO;
import com.fg.ss.abhiyog.common.vo.LitigationSummaryVO;
import com.fg.ss.abhiyog.common.vo.LtgnCategoryVO;
import com.fg.ss.abhiyog.common.vo.LtgnMatterByVO;
import com.fg.ss.abhiyog.common.vo.RiskClaimVO;
import com.fg.ss.abhiyog.common.vo.UnderActVO;
import com.fg.ss.abhiyog.common.vo.UnitSummaryVO;

public interface ILitigationService {

	LtgnRepresentativeMaster checkExistenceRepresentativeName(String matterBy);

	void saveLtgnRepresentativeMaster(String matterBy);

	List<LtgnMatterByVO> findAll();

	LtgnCaseType checkExistenceCaseType(String categoryName);

	void saveLtgnCaseType(String categoryName);

	List<LtgnCategoryVO> findAllCategoryData();

	UnderAct findByUnderAct(String underActName);

	void saveUnderActData(String underActName);

	List<UnderActVO> findAllUnderActData();

	CourtType checkExistenceCourtType(String courtTypeName);

	void saveCourtType(String courtTypeName);

	List<CourtTypeVO> findCourtType();

	List<CityStateVO> findAllStates();

	City checkExistenceCity(String cityName);

	void saveCityData(CityStateVO cityStateVO);

	List<CityStateVO> findAllCities();

	List<RiskClaimVO> findAllRiskLevel();

	List<RiskClaimVO> findAllClaimPossible();

	List<CustomerTypeStatusVO> findAllCustomerType();

	List<CustomerTypeStatusVO> findAllStatus();

	CourtCity findByCourtCity(String courtForum);

	void saveCourtCityData(CityStateVO cityStateCourtForumVO);

	boolean saveLitigationData(AddLitigationVO addLitigationVO);

	void savePoliceStationData(CityStateVO cityStateVO);

	List<LitigationSummaryVO> getLitigationSummary();

	void addConnectedLitigation(ConnectedLitigationVO connectedLitigationVO);

	void addWitnessDtls(ConnectedLitigationVO connectedLitigationVO);

	List<ConnectedLitigationVO> getWitnessDtls(String litigationId);

	List<ConnectedLitigationVO> getLitigationResultMaster();

	int addLitigationDisposal(ConnectedLitigationVO connectedLitigationVO);

	ConnectedLitigationVO getStatusByLitigationId(String litigationId);

	void saveLitigationDocsData(MultipartFile file, ConnectedLitigationVO connectedLitigationVO);

	void saveLawfirmBillingData(MultipartFile file, ConnectedLitigationVO connectedLitigationVO);

	List<ConnectedLitigationVO> getLawfirmBilling(String litigationId);

	List<AddLitigationVO> getCaseDtls(String litigationId);

	List<AddLitigationVO> getAllDetails(String litigationId);

	List<AddLitigationVO> showLitigationDetails(String litigationId);

	void updateLitigationDetails(AddLitigationVO addLitigationVO);

	void saveNextHearingDate(ConnectedLitigationVO connectedLitigationVO);

	List<ConnectedLitigationVO> getHistoryDtls(String litigationId);

	void updateHearingDetails(ConnectedLitigationVO connectedLitigationVO, String litigationId);

	List<ConnectedLitigationVO> getActivityLog(String litigationId);

	void updateLitigationData(AddLitigationVO addLitigationVO, String litigationId);

	List<UnitSummaryVO> showEntityRegionUnits(String litigationId);

	List<Litigation> getHearingStatusReportDtls(LocalDate fromDate, LocalDate toDate);

	List<Dept> getdept();

	List<LitigationSummaryVO> findLitigationSummaryFieldSelection(String zone, String format, String entity, String function,
			String counterParty, String category, String possibleClaim, String state, String lawfirmIndividual,
			String courtType, String underActs, String risk, String status, String matterByAgainst,
			String litigationByAgainst);

	LitigationSummaryVO getLitigationDetails(int id);
	
	List<CityStateVO> getAllCourtForum();

	

	

}
