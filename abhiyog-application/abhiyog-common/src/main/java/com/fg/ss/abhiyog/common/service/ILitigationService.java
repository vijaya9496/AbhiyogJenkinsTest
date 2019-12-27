package com.fg.ss.abhiyog.common.service;

import java.util.List;

import com.fg.ss.abhiyog.common.model.City;
import com.fg.ss.abhiyog.common.model.CourtCity;
import com.fg.ss.abhiyog.common.model.CourtType;
import com.fg.ss.abhiyog.common.model.LtgnCaseType;
import com.fg.ss.abhiyog.common.model.LtgnRepresentativeMaster;
import com.fg.ss.abhiyog.common.model.UnderAct;
import com.fg.ss.abhiyog.common.vo.AddLitigationVO;
import com.fg.ss.abhiyog.common.vo.CityStateVO;
import com.fg.ss.abhiyog.common.vo.CourtTypeVO;
import com.fg.ss.abhiyog.common.vo.CustomerTypeStatusVO;
import com.fg.ss.abhiyog.common.vo.LtgnCategoryVO;
import com.fg.ss.abhiyog.common.vo.LtgnMatterByVO;
import com.fg.ss.abhiyog.common.vo.RiskClaimVO;
import com.fg.ss.abhiyog.common.vo.UnderActVO;

public interface ILitigationService {

	LtgnRepresentativeMaster checkExistenceRepresentativeName(String matterBy);

	void saveLtgnRepresentativeMaster(LtgnMatterByVO ltgnMastersByVo);

	List<LtgnMatterByVO> findAll();

	LtgnCaseType checkExistenceCaseType(String categoryName);

	void saveLtgnCaseType(LtgnCategoryVO ltgnCategoryVO);

	List<LtgnCategoryVO> findAllCategoryData();

	UnderAct findByUnderAct(String underActName);

	void saveUnderActData(UnderActVO underActVO);

	List<UnderActVO> findAllUnderActData();

	CourtType checkExistenceCourtType(String courtTypeName);

	void saveCourtType(CourtTypeVO courtTypeVO);

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

	void saveLitigationData(AddLitigationVO addLitigationVO);

	void savePoliceStationData(CityStateVO cityStateVO);

	

}
