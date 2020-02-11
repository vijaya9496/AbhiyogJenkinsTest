package com.fg.ss.abhiyog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fg.ss.abhiyog.common.model.EntitySummary;
import com.fg.ss.abhiyog.common.service.IEntityService;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.EntityVO;



@RestController
@RequestMapping("/masters/entity")
public class EntityRestController {

	@Autowired
	private IEntityService entityService;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	@GetMapping("/entitySummary")
	public ResponseEntity<BaseResponseVO> getAllEntity(){
		List<EntityVO> allEntities = entityService.getAllEntities();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allEntities);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
/*	@GetMapping("/addNewEntity")
	public ResponseEntity<BaseResponseVO> addNewEntity(@RequestParam("entityName") String entityName){
		EntitySummary entity = entityService.getEntityByName(entityName);
		if(entity != null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("ENTITY NAME ALREADY EXISTED");
		}else {
			baseResponseVO = entityService.saveEntityData(entityName);
		}
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}*/
	
	@PutMapping("/updateEntity")
	public ResponseEntity<BaseResponseVO> updateEntity(@RequestBody EntityVO entityVO){
		EntitySummary entity = entityService.getEntityByName(entityVO.getEntityName());
		if(entity != null) {
			int isUpdated = entityService.updateEntityByName(entityVO.getEntityName(), entityVO.getUpdatedEntityName());
			if(isUpdated > 0) {
				baseResponseVO.setResponseCode(HttpStatus.OK.value());
				baseResponseVO.setResponseMessage("ENTITY NAME UPDATED SUCCESSFULLY");
			}else {
				baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
				baseResponseVO.setResponseMessage("GIVEN ENTITY NAME ALREADY EXISTED");
			}
		}else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("ENTITY NAME NOT EXISTED");
		}
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}
}
