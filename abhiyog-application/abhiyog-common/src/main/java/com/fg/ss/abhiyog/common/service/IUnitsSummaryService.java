package com.fg.ss.abhiyog.common.service;

import com.fg.ss.abhiyog.common.model.Units;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.UnitSummaryVO;


public interface IUnitsSummaryService {

	UnitSummaryVO getUnitSummary();

//	Units findUnitByName(UnitSummaryVO unitSummaryVO);

	BaseResponseVO saveFormData(UnitSummaryVO unitSummaryVO);

	Units findUnitByName(String unitName);

	BaseResponseVO updateCompanyUnitDetails(UnitSummaryVO unitSummaryVO);

}
