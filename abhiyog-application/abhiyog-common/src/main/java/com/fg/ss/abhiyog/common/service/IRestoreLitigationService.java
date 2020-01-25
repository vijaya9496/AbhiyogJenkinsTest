package com.fg.ss.abhiyog.common.service;

import java.util.List;

import com.fg.ss.abhiyog.common.vo.AddLitigationVO;

public interface IRestoreLitigationService {

	int updateDeleteStatus(String litigationId);

	List<AddLitigationVO> getAllRestoreLitigationDtls();

	int addLitigationToRestore(String[] litigationId);

}
