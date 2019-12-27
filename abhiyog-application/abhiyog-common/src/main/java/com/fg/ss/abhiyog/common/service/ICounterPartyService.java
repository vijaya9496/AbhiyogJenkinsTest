package com.fg.ss.abhiyog.common.service;

import java.util.List;

import com.fg.ss.abhiyog.common.model.CounterPartyDtls;
import com.fg.ss.abhiyog.common.vo.CounterPartyVO;



public interface ICounterPartyService {

	CounterPartyDtls findCustomerByName(String counterPartyName);

	void saveCounterPartyData(CounterPartyVO counterPartyVO);

	List<CounterPartyVO> findAll();

}
