package com.fg.ss.abhiyog.common.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.Zone;
import com.fg.ss.abhiyog.common.repository.ZonesRespository;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.EntityVO;
import com.fg.ss.abhiyog.common.vo.ZoneVO;



@Service
public class ZoneService implements IZoneService{

	@Autowired
	private ZonesRespository zonesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	@Override
	public List<ZoneVO> getAllZones() {
		List<Zone> allZones = zonesRepository.findAll();
		if(allZones == null) {
			return null;
		}
		Type listType = new TypeToken<List<ZoneVO>>(){}.getType();
        List<ZoneVO> zoneVoList = modelMapper.map(allZones, listType);
        return zoneVoList;
//		return allZones.stream().map(zones->convertToDto(zones)).collect(Collectors.toList());
		
	}

	private ZoneVO convertToDto(Zone zones) {
		ZoneVO zoneDto = new ZoneVO();
		zoneDto.setZoneId(zones.getZoneId());
		zoneDto.setZoneName(zones.getZoneName());
		
		return zoneDto;
	}

	@Override
	public Zone checkZoneExistence(String zoneName) {
		Zone isZoneExisted  = zonesRepository.findByZoneName(zoneName);
		return isZoneExisted;
	}

	@Override
	public void saveZoneData(ZoneVO zoneVO) {
		Zone zone = new Zone();
		zone.setZoneName(zoneVO.getZoneName());
		zonesRepository.save(zone);
	}

	

}
