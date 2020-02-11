package com.fg.ss.abhiyog.common.service;

import java.util.List;

import com.fg.ss.abhiyog.common.model.Zone;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.ZoneVO;



public interface IZoneService {

	List<ZoneVO> getAllZones();

	Zone checkZoneExistence(String zoneName);

	void saveZoneData(ZoneVO zoneVO);

	

}
