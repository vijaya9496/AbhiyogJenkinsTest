package com.fg.ss.abhiyog.common.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.Dept;
import com.fg.ss.abhiyog.common.model.LawFirm;
import com.fg.ss.abhiyog.common.repository.DeptRepository;
import com.fg.ss.abhiyog.common.repository.OutsideCounselRepository;
import com.fg.ss.abhiyog.common.vo.CounterPartyVO;
import com.fg.ss.abhiyog.common.vo.OutsideCounselVO;



@Service
public class OutsideCounselService implements IOutsideCounselService{

	@Autowired
	private OutsideCounselRepository outsideCounselRepository;
	
	@Autowired
	private DeptRepository deptRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public LawFirm findByLawfirm(String lawfirm) {
		LawFirm lawfirmDtls = outsideCounselRepository.findByLawfirm(lawfirm);
		return lawfirmDtls;
	}
	@Override
	public void saveOutsideCounselData(OutsideCounselVO outsideCounselVO) {
		LawFirm lawfirmDtls = new LawFirm();
		LawFirm lawfirm = outsideCounselRepository.findByLawfirm(outsideCounselVO.getLawfirm());
		if(lawfirm != null) {
			lawfirmDtls.setLawfirmId(lawfirm.getLawfirmId());
		}
		lawfirmDtls.setLawfirm(outsideCounselVO.getLawfirm());
		lawfirmDtls.setLawfirmHead(outsideCounselVO.getLawfirmHead());
		lawfirmDtls.setLawfirmHeadEmailId(outsideCounselVO.getEmailId());
		lawfirmDtls.setAddress(outsideCounselVO.getAddress());
		lawfirmDtls.setMobile(outsideCounselVO.getMobile());
		
		String telephone = outsideCounselVO.getTelephone() == null ? null :outsideCounselVO.getTelephone();
		lawfirmDtls.setTelephone(telephone);
		
		String fax = outsideCounselVO.getFaxNo() == null ? null :outsideCounselVO.getFaxNo();
		lawfirmDtls.setFaxNo(fax);
		
		String website = outsideCounselVO.getWebsite() == null ? null: outsideCounselVO.getWebsite();
		lawfirmDtls.setWebsite(website);
		
		Dept dept = deptRepository.findByDeptName(outsideCounselVO.getFunction());
		lawfirmDtls.setDept(dept);
		
		outsideCounselRepository.save(lawfirmDtls);
	}
	@Override
	public List<OutsideCounselVO> findAll() {
		List<LawFirm> counselSummary = outsideCounselRepository.findAll();
		Type listType = new TypeToken<List<OutsideCounselVO>>(){}.getType();
        List<OutsideCounselVO> counterPartyVoList = modelMapper.map(counselSummary, listType);
        return counterPartyVoList;
//		return counselSummary.stream().map(allCounselSummary -> convertToDTO(allCounselSummary)).collect(Collectors.toList());
	}
	private OutsideCounselVO convertToDTO(LawFirm allCounselSummary) {
		OutsideCounselVO outsideCounselDto = new OutsideCounselVO();
		outsideCounselDto.setLawfirm(allCounselSummary.getLawfirm());
		outsideCounselDto.setLawfirmHead(allCounselSummary.getLawfirmHead());
		outsideCounselDto.setEmailId(allCounselSummary.getLawfirmHeadEmailId());
		outsideCounselDto.setMobile(allCounselSummary.getMobile());
		outsideCounselDto.setAddress(allCounselSummary.getAddress());
		return outsideCounselDto;
	}

}
