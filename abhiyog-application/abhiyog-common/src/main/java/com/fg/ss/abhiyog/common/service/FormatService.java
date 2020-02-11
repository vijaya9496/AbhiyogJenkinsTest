package com.fg.ss.abhiyog.common.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.Format;
import com.fg.ss.abhiyog.common.repository.FormatRepository;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.FormatVO;
import com.fg.ss.abhiyog.common.vo.UserVO;



@Service
public class FormatService implements IFormatService{
	@Autowired
	private FormatRepository formatRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	@Override
	public List<FormatVO> getAllFormats() {
		List<Format> allFormats = formatRepository.findAll();
		if(allFormats == null) {
			return null;
		}
		Type listType = new TypeToken<List<FormatVO>>(){}.getType();
        List<FormatVO> formatVoList = modelMapper.map(allFormats, listType);
        return formatVoList;
//		return allFormats.stream().map(formats->convertToDTO(formats)).collect(Collectors.toList());
		 
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
	public void saveFormatData(FormatVO formatVO) {
		Format format = new Format();
		format.setFormat(formatVO.getFormat());
		formatRepository.save(format);
		/*baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("FORMAT ADDED SUCCESSFULLY");
		return baseResponseVO;*/
	}

	@Override
	public String findById(int id) {
		Optional<Format> formatDtls = formatRepository.findById(id);
		String formatName = formatDtls.get().getFormat();
		return formatName;
	}

	@Override
	public int updateFormatByName(String format, String updateFormatName) {
		int isUpdated = formatRepository.updateFormat(format, updateFormatName);
		return isUpdated;
	}

}
