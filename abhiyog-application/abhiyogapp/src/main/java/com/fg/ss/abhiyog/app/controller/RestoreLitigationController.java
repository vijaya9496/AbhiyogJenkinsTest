package com.fg.ss.abhiyog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fg.ss.abhiyog.common.service.IRestoreLitigationService;
import com.fg.ss.abhiyog.common.vo.AddLitigationVO;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;

@RestController
@RequestMapping("/masters/litigation")
public class RestoreLitigationController {
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	@Autowired
	private IRestoreLitigationService restoreLitigationService;

	// deleteLitigation means setting delete status to 1
	@PutMapping("/deleteLitigation/{litigationId}")
	public ResponseEntity<BaseResponseVO> deleteLitigation(@PathVariable("litigationId") String litigationId){
		int updatedDeleteStatus = restoreLitigationService.updateDeleteStatus(litigationId);
		if(updatedDeleteStatus > 0) {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("DeleteStatus Updated Successfully");
			baseResponseVO.setData(null);
		}
		return ResponseEntity.ok().body(baseResponseVO);
	}

	@GetMapping("/restoreLitigation")
	public ResponseEntity<BaseResponseVO> getAllRestoreLitigationDtls(){
		List<AddLitigationVO> allRestoreLitigationData = restoreLitigationService.getAllRestoreLitigationDtls();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allRestoreLitigationData);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@PutMapping("/addLitigationToRestore/{litigationId}")
	public ResponseEntity<BaseResponseVO> addLitigationToRestore(@PathVariable("litigationId") String[] litigationId){
		int restoredLitigation = restoreLitigationService.addLitigationToRestore(litigationId);
		if(restoredLitigation > 0) {
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("Litigation Restored Successfully");
			baseResponseVO.setData(null);
		}
		return ResponseEntity.ok().body(baseResponseVO);
		
	}
	
}
