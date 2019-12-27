package com.fg.ss.abhiyog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fg.ss.abhiyog.common.model.Zone;
import com.fg.ss.abhiyog.common.service.IZoneService;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.ZoneVO;



@RestController
@RequestMapping("/masters/zone")
public class ZoneRestController {

	@Autowired
	private IZoneService zoneService;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	@GetMapping("/getAllZones")
	public ResponseEntity<BaseResponseVO> getAllZones(){
		List<ZoneVO> zones = zoneService.getAllZones();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(zones);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@PostMapping("/addNewZone")
	public ResponseEntity<BaseResponseVO> addNewZone(@RequestBody ZoneVO zoneVO){
		Zone isZoneExisted = zoneService.checkZoneExistence(zoneVO.getZoneName());
		if(isZoneExisted == null) {
			baseResponseVO = zoneService.saveZoneData(zoneVO);
		}else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("ZONE ALREADY EXISTED");
		}
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
		
	}
}
