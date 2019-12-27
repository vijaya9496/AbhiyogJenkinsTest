package com.fg.ss.abhiyog.common.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.Format;
import com.fg.ss.abhiyog.common.repository.FormatRepository;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.FormatVO;



@Service
public class FormatService implements IFormatService{
	@Autowired
	private FormatRepository formatRepository;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	@Override
	public List<FormatVO> getAllFormats() {
		List<Format> allFormats = formatRepository.findAll();
		if(allFormats == null) {
			return null;
		}
		return allFormats.stream().map(formats->convertToDTO(formats)).collect(Collectors.toList());
		 
	}

	private FormatVO convertToDTO(Format formats) {
		FormatVO formatVo = new FormatVO();
		formatVo.setFormat(formats.getFormat());
		formatVo.setFormatId(formats.getFormatId());
		return formatVo;
	}

	@Override
	public Format getFormatByName(String format) {
		Format formats = formatRepository.getFormatByName(format);
		return formats;
	}

	@Override
	public BaseResponseVO saveFormatData(FormatVO formatVO) {
		Format format = new Format();
		format.setFormat(formatVO.getFormat());
		formatRepository.save(format);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("FORMAT ADDED SUCCESSFULLY");
		return baseResponseVO;
	}

}
