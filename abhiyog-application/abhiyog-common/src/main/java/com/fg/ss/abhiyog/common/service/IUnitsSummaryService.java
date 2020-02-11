package com.fg.ss.abhiyog.common.service;

import java.util.List;

import com.fg.ss.abhiyog.common.model.Units;
import com.fg.ss.abhiyog.common.vo.UnitSummaryVO;
import com.fg.ss.abhiyog.common.vo.UserVO;


public interface IUnitsSummaryService {

	List<UnitSummaryVO> getUnitSummary();

//	Units findUnitByName(UnitSummaryVO unitSummaryVO);

	void saveFormData(String entityName, String zoneName, String unitName, String loginId);

	Units findUnitByName(String unitName);

	int updateCompanyUnitDetails(UnitSummaryVO unitSummaryVO);

	Units findExistenceUnitName(String entityName, String zoneName, String unitName);

	List<UnitSummaryVO> findUnitSummaryData(String entity, String zone);

	UnitSummaryVO getUnitLocationDtls(int id);

	List<UserVO> getUnitHeadNames();

}
