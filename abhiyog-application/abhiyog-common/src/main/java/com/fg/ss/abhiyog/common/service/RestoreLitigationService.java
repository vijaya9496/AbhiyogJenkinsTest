package com.fg.ss.abhiyog.common.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.repository.LitigationRepository;
import com.fg.ss.abhiyog.common.vo.AddLitigationVO;

@Service
public class RestoreLitigationService implements IRestoreLitigationService{

	@Autowired
	private LitigationRepository litigationRepository;
	
	@Override
	public int updateDeleteStatus(String litigationId) {
		int updatedDeleteStatus = litigationRepository.updateDeleteStatus(litigationId);
		return updatedDeleteStatus;
	}

	@Override
	public List<AddLitigationVO> getAllRestoreLitigationDtls() {
		List<Litigation> allRestoreLitigationDtls = litigationRepository.getAllRestoreLitigationDtls();
		return allRestoreLitigationDtls.stream().map(restoreLitigationDtl -> convertToRestoreLitigationDtlsDto(restoreLitigationDtl)).collect(Collectors.toList());
	}

	private AddLitigationVO convertToRestoreLitigationDtlsDto(Litigation restoreLitigationDtl) {
		AddLitigationVO addRestoreLitigationData = new AddLitigationVO();
		addRestoreLitigationData.setLitigationId(restoreLitigationDtl.getLitigationId());
		addRestoreLitigationData.setEntityName(restoreLitigationDtl.getUnits().getEntitySummary().getEntityName());
		addRestoreLitigationData.setCounterPartyName(restoreLitigationDtl.getCounterPartyDtls().getCustomerName());
		addRestoreLitigationData.setCaseNumber(restoreLitigationDtl.getCaseNumber());
		addRestoreLitigationData.setSubject(restoreLitigationDtl.getSubject());
		addRestoreLitigationData.setUnderActName(restoreLitigationDtl.getUnderAct().getUnderAct());
		addRestoreLitigationData.setRisk(restoreLitigationDtl.getRisk().getRisk());
		return addRestoreLitigationData;
	}

	@SuppressWarnings({ "unused"})
	@Override
	public int addLitigationToRestore(String[] litigationId) {
		int litigationRestored = 0;
		for(String lId : litigationId) {
			 litigationRestored = litigationRepository.addLitigationToRestore(lId);
		}
		return litigationRestored;
	}

}
