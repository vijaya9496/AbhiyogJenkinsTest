package com.fg.ss.abhiyog.common.service;

import java.util.List;

import com.fg.ss.abhiyog.common.model.LawFirm;
import com.fg.ss.abhiyog.common.vo.OutsideCounselVO;



public interface IOutsideCounselService {

	LawFirm findByLawfirm(String lawfirm);

	void saveOutsideCounselData(OutsideCounselVO outsideCounselVO);

	List<OutsideCounselVO> findAll();

	OutsideCounselVO getOutsideCounselProfile(int parseInt);

	List<OutsideCounselVO> findAllSeniorCounselDtls();

}
