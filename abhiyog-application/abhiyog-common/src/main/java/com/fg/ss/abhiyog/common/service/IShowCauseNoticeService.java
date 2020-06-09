package com.fg.ss.abhiyog.common.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fg.ss.abhiyog.common.model.ShowCauseNotice;
import com.fg.ss.abhiyog.common.model.ShowCauseNoticeForms;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.ShowCauseNoticeVO;

public interface IShowCauseNoticeService {

	void saveNoticeData(ShowCauseNoticeVO showCauseNoticeVO, MultipartFile multiPartFile) throws IOException;

	List<ShowCauseNoticeVO> getAllNoticeDtls();

	boolean updateNoticeData(ShowCauseNoticeVO showCauseNoticeVO);
	
	boolean uploadFileandData(ShowCauseNoticeVO showCauseNoticeVO, MultipartFile file);

	List<ShowCauseNoticeVO> findShowCauseSummaryData(String entity, String zone, String unitName, String status,
			String format);

	List<ShowCauseNoticeForms> findDocumentNames(int id);

	ShowCauseNoticeVO findNoticeDtls(int parseInt);

	List<ShowCauseNoticeVO> findNoticeFormDtls(int showCauseNoticeId);

	int deleteDocument(int parseInt);

	List<ShowCauseNoticeVO> getUnitDtlsByZone(String zoneNameVal,String entityNameVal);

	

	

}
