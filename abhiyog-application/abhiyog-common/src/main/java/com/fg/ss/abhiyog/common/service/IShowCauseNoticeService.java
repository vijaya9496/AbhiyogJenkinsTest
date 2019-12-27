package com.fg.ss.abhiyog.common.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.ShowCauseNoticeVO;

public interface IShowCauseNoticeService {

	void saveNoticeData(ShowCauseNoticeVO showCauseNoticeVO, MultipartFile file);

	List<ShowCauseNoticeVO> getAllNoticeDtls();

	BaseResponseVO updateNoticeData(ShowCauseNoticeVO showCauseNoticeVO);
	
	BaseResponseVO uploadFileandData(ShowCauseNoticeVO showCauseNoticeVO, MultipartFile file);

}
