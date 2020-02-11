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

import com.fg.ss.abhiyog.common.model.Format;
import com.fg.ss.abhiyog.common.service.IFormatService;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.FormatVO;



@RestController
@RequestMapping("/masters/format")
public class FormatRestController {

	@Autowired
	private IFormatService formatService;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	@GetMapping("/getAllFormats")
	public ResponseEntity<BaseResponseVO> getAllFormats(){
		List<FormatVO> allformats = formatService.getAllFormats();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allformats);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	/*@PostMapping("/addNewFormat")
	public ResponseEntity<BaseResponseVO> addFormat(@RequestBody FormatVO formatVO){
		Format format = formatService.getFormatByName(formatVO.getFormat());
		if(format != null) {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("UNABLE TO ADD FORMAT");
		}else {
			baseResponseVO=formatService.saveFormatData(formatVO);
		}
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
		
	}*/
}
