package com.fg.ss.abhiyog.common.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.CounterPartyDtls;
import com.fg.ss.abhiyog.common.model.Dept;
import com.fg.ss.abhiyog.common.repository.CounterPartyRepository;
import com.fg.ss.abhiyog.common.repository.DeptRepository;
import com.fg.ss.abhiyog.common.vo.CounterPartyVO;



@Service
public class CounterPartyService implements ICounterPartyService{

	@Autowired
	private CounterPartyRepository counterPartyRepository;
	
	@Autowired
	private DeptRepository deptRepository;
	
	
	
	@Override
	public CounterPartyDtls findCustomerByName(String counterPartyName) {
		CounterPartyDtls counterPartyDtls =  counterPartyRepository.findByCustomerName(counterPartyName);
		return counterPartyDtls;
	}
	@SuppressWarnings("null")
	@Override
	public void saveCounterPartyData(CounterPartyVO counterPartyVO) {
		CounterPartyDtls counterPartyDtls = new CounterPartyDtls();
		CounterPartyDtls counterParty = counterPartyRepository.findByCustomerName(counterPartyVO.getCounterPartyName());
		if(counterParty != null) {
			counterPartyDtls.setId(counterParty.getId());
			
		}
			counterPartyDtls.setCustomerName(counterPartyVO.getCounterPartyName());
			counterPartyDtls.setContactPerson1(counterPartyVO.getContactPerson1());
			counterPartyDtls.setContactPerson2(counterPartyVO.getContactPerson2());
			counterPartyDtls.setDesignation1(counterPartyVO.getDesignation1());
			counterPartyDtls.setDesignation2(counterPartyVO.getDesignation2());
			counterPartyDtls.setMobile1(counterPartyVO.getMobile1());
			counterPartyDtls.setMobile2(counterPartyVO.getMobile2());
			String emailId1 = counterPartyVO.getEmailId1() == null ? null : counterPartyVO.getEmailId1();
			counterPartyDtls.setEmailId1(emailId1);
			
			String emailId2 = counterPartyVO.getEmailId2() == null ? null : counterPartyVO.getEmailId2();
			counterPartyDtls.setEmailId2(emailId2);
			
			String phone1 = counterPartyVO.getTelephone1() == null ? null : counterPartyVO.getTelephone1();
			counterPartyDtls.setTelePhone1(phone1);
			String phone2 = counterPartyVO.getTelephone2() == null ? null : counterPartyVO.getTelephone2();
			counterPartyDtls.setTelePhone2(phone2);
			String fax1 =counterPartyVO.getFaxNo1() == null ? null : counterPartyVO.getFaxNo1();
			counterPartyDtls.setFax1(fax1);
			String fax2 = counterPartyVO.getFaxNo2() == null ? null :counterPartyVO.getFaxNo2();
			counterPartyDtls.setFax2(fax2);
			counterPartyDtls.setAddress(counterPartyVO.getAddress());
			counterPartyDtls.setWebsite(counterPartyVO.getWebsite());
			LocalDateTime dateTime =  LocalDateTime.now();
			counterPartyDtls.setCreateDate(dateTime);
			
			Dept dept = deptRepository.findByDeptName(counterPartyVO.getFunction());
			counterPartyDtls.setDept(dept);
			counterPartyRepository.save(counterPartyDtls);
	}
	@Override
	public List<CounterPartyVO> findAll() {
		List<CounterPartyDtls> counterPartyDtls = counterPartyRepository.findAll();
		return counterPartyDtls.stream().map(allCounterPartyDtls -> convertToDTO(allCounterPartyDtls)).collect(Collectors.toList());
	}
	private CounterPartyVO convertToDTO(CounterPartyDtls allCounterPartyDtls) {
		CounterPartyVO counterPartyVo = new CounterPartyVO();
		counterPartyVo.setCounterPartyName(allCounterPartyDtls.getCustomerName());
		counterPartyVo.setContactPerson1(allCounterPartyDtls.getContactPerson1());
		counterPartyVo.setAddress(allCounterPartyDtls.getAddress());
		counterPartyVo.setMobile1(allCounterPartyDtls.getMobile1());
		return counterPartyVo;
	}

}
