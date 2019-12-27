package com.fg.ss.abhiyog.common.service;

import java.util.List;

import com.fg.ss.abhiyog.common.model.Format;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.FormatVO;



public interface IFormatService{

	List<FormatVO> getAllFormats();

	Format getFormatByName(String format);

	BaseResponseVO saveFormatData(FormatVO formatVO);

}
