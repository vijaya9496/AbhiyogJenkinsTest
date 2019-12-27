package com.fg.ss.abhiyog.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fg.ss.abhiyog.common.service.FileStorageService;
import com.fg.ss.abhiyog.common.service.IShowCauseNoticeService;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.ShowCauseNoticeVO;



@RestController
@RequestMapping("/masters/showCauseNotice")
public class ShowCauseNoticeRestController {
	
	@Autowired
	private IShowCauseNoticeService showCauseNoticeService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	@GetMapping("/getShowCauseNoticeDtls")
	public ResponseEntity<BaseResponseVO> getShowCauseNoticeSummary(){
		List<ShowCauseNoticeVO> allNoticeDtls = showCauseNoticeService.getAllNoticeDtls();
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("SUCCESS");
		baseResponseVO.setData(allNoticeDtls);
		return ResponseEntity.ok().body(baseResponseVO);
		
	}
	
//	@PostMapping("/addShowCauseNotice")
	@RequestMapping(value = "/addShowCauseNotice", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public ResponseEntity<BaseResponseVO> addShowCauseNoticeData(@RequestPart("file") MultipartFile file, @RequestPart("showCauseNoticeVO") ShowCauseNoticeVO showCauseNoticeVO){
		showCauseNoticeService.saveNoticeData(showCauseNoticeVO,file);
		baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
		baseResponseVO.setResponseMessage("NOTICE RECORD ADDED SUCCESSFULLY");
		baseResponseVO.setData(null);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@PutMapping("/updateShowCauseNotice")
	public ResponseEntity<BaseResponseVO> updateShowCauseNotice(@RequestBody ShowCauseNoticeVO showCauseNoticeVO){
		baseResponseVO=showCauseNoticeService.updateNoticeData(showCauseNoticeVO);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	@RequestMapping(value = "/addDocument", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public ResponseEntity<BaseResponseVO> addDocument(@RequestPart("file") MultipartFile file, @RequestPart("showCauseNoticeVO") ShowCauseNoticeVO showCauseNoticeVO){
		baseResponseVO = showCauseNoticeService.uploadFileandData(showCauseNoticeVO,file);
		return ResponseEntity.ok().body(baseResponseVO);
		
	}
	
	@GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            System.out.println("contentType:: " +contentType);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

