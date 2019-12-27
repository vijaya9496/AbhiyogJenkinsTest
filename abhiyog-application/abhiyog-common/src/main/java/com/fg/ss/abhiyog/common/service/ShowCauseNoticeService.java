package com.fg.ss.abhiyog.common.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fg.ss.abhiyog.common.model.Dept;
import com.fg.ss.abhiyog.common.model.Format;
import com.fg.ss.abhiyog.common.model.NoticeCategory;
import com.fg.ss.abhiyog.common.model.NoticeClassification;
import com.fg.ss.abhiyog.common.model.ShowCauseNotice;
import com.fg.ss.abhiyog.common.model.ShowCauseNoticeForms;
import com.fg.ss.abhiyog.common.model.Units;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.repository.DeptRepository;
import com.fg.ss.abhiyog.common.repository.FormatRepository;
import com.fg.ss.abhiyog.common.repository.NoticeCategoryRepository;
import com.fg.ss.abhiyog.common.repository.NoticeClassificationRepository;
import com.fg.ss.abhiyog.common.repository.ShowCauseNoticeFormsRepository;
import com.fg.ss.abhiyog.common.repository.ShowCauseNoticeRepository;
import com.fg.ss.abhiyog.common.repository.UnitsRepository;
import com.fg.ss.abhiyog.common.repository.UserRepository;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.ShowCauseNoticeVO;



@Service
public class ShowCauseNoticeService implements IShowCauseNoticeService{

	@Autowired
	private ShowCauseNoticeRepository showCauseNoticeRepository;
	
	@Autowired
	private ShowCauseNoticeFormsRepository showCauseNoticeFormsRepository;
	
	@Autowired
	private DeptRepository deptRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UnitsRepository unitsRepository;
	
	@Autowired
	private FormatRepository formatRepository;
	
	@Autowired
	private NoticeCategoryRepository noticeCategoryRepository;
	
	@Autowired
	private NoticeClassificationRepository noticeClassificationRepository;
	
	@Autowired 
	private FileStorageService fileStorageService;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	@Value("${file.upload-dir}")
	private String path;
	
	@Override
	public void saveNoticeData(ShowCauseNoticeVO showCauseNoticeVO, MultipartFile file) {
		ShowCauseNotice showCauseNotice = new ShowCauseNotice();
		ShowCauseNoticeForms showCauseNoticeForms = new ShowCauseNoticeForms();
		
		showCauseNotice.setNoticeReceivedFrom(showCauseNoticeVO.getReceivedFrom());
		showCauseNotice.setNoticeReceivedDate(showCauseNoticeVO.getReceivedDate());
		showCauseNotice.setNoticeReplyDeadline(showCauseNoticeVO.getNoticeReplyDeadline());
		showCauseNotice.setComment(showCauseNoticeVO.getComments());
		LocalDateTime dateTime = LocalDateTime.now();
		showCauseNotice.setCreateDate(dateTime);
		showCauseNotice.setStatus("open");
		showCauseNotice.setOwnerId(0);
		showCauseNotice.setSubject(showCauseNoticeVO.getSubject());
		showCauseNotice.setIssueDate(showCauseNoticeVO.getIssueDate());
		showCauseNotice.setReferenceNo(showCauseNoticeVO.getReferenceNo());
		
		String advocateName=showCauseNoticeVO.getAdvocateName() == null ? null :showCauseNoticeVO.getAdvocateName();
		showCauseNotice.setAdvocateName(advocateName);
		
		String advocateAddress = showCauseNoticeVO.getAdvocateAddress() == null ? null : showCauseNoticeVO.getAdvocateAddress();
		showCauseNotice.setAdvocateAddress(advocateAddress);
		
		String allegation = showCauseNoticeVO.getAllegation() == null ? null : showCauseNoticeVO.getAllegation();
		showCauseNotice.setAllegation(allegation);
		
		int claims = showCauseNoticeVO.getClaims() == 0 ? 0 : showCauseNoticeVO.getClaims();
		showCauseNotice.setClaims(claims);
		
		String complaintName = showCauseNoticeVO.getComplaintName() == null ? null : showCauseNoticeVO.getComplaintName();
		showCauseNotice.setNoticeSentComplaintName(complaintName);
		
		String complaintAddress = showCauseNoticeVO.getComplaintAddress() == null ? null : showCauseNoticeVO.getComplaintAddress();
		showCauseNotice.setNoticeSentComplaintAddress(complaintAddress);
		
		String natureInfringement = showCauseNoticeVO.getNatureOfIpInfringement() == null ? null :showCauseNoticeVO.getNatureOfIpInfringement();
		showCauseNotice.setNatureOfIpInfringement(natureInfringement);
		
		String section = showCauseNoticeVO.getSection() == null ? null : showCauseNoticeVO.getSection();
		showCauseNotice.setSection(section);
		
		String dealerName = showCauseNoticeVO.getDealerName() == null ? null : showCauseNoticeVO.getDealerName();
		showCauseNotice.setDealerName(dealerName);
		
		String dealerAddress = showCauseNoticeVO.getDealerAddress() == null ? null: showCauseNoticeVO.getDealerAddress();
		showCauseNotice.setDealerAddress(dealerAddress);
		
		String otherParties = showCauseNoticeVO.getOtherParties() == null ? null : showCauseNoticeVO.getOtherParties();
		showCauseNotice.setOtherParties(otherParties);
		
		String partyNo = showCauseNoticeVO.getPartyNo() == null ? null : showCauseNoticeVO.getPartyNo();
		showCauseNotice.setPartyNo(partyNo);
		
		String modelnumber = showCauseNoticeVO.getVehicleModelNumber() == null ? null : showCauseNoticeVO.getVehicleModelNumber();
		showCauseNotice.setVehicleModelNumber(modelnumber);
		
		showCauseNotice.setActionTaken(showCauseNoticeVO.getActionTaken());
		
		Dept dept = deptRepository.findByDeptName(showCauseNoticeVO.getFunction());
		showCauseNotice.setDept(dept);
		
		User user = userRepository.findByLoginId(showCauseNoticeVO.getLoginId());
		showCauseNotice.setUser(user);
		
		Units units = unitsRepository.getUnitDtls(showCauseNoticeVO.getUnitName());
		showCauseNotice.setUnits(units);
		
		Format format = formatRepository.getFormatByName(showCauseNoticeVO.getFormatName());
		showCauseNotice.setFormat(format);
		
		NoticeCategory noticeCategory = noticeCategoryRepository.findByCategoryName(showCauseNoticeVO.getNoticeCategoryName());
		showCauseNotice.setNoticeCategory(noticeCategory);
		
		NoticeClassification noticeClassification = noticeClassificationRepository.findByClassificationName(showCauseNoticeVO.getNoticeClassification());
		showCauseNotice.setNoticeClassification(noticeClassification);
		
		
		//uploading File
		String fileName= fileStorageService.storeFile(file);
		
		showCauseNoticeForms.setInputDocName(fileName);
		showCauseNoticeForms.setInputDocPath(path+"/"+fileName);
		showCauseNoticeForms.setUploadedDate(dateTime);
		showCauseNoticeForms.setDocSize(file.getSize());
		showCauseNoticeForms.setFileExtension(file.getContentType());
		showCauseNoticeForms.setComments(showCauseNoticeVO.getComments());
		showCauseNoticeForms.setShowCauseNotice(showCauseNotice);
		showCauseNoticeForms.setUserDtls(user);
		try {
			showCauseNoticeForms.setFileData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		showCauseNoticeRepository.save(showCauseNotice);
		showCauseNoticeFormsRepository.save(showCauseNoticeForms);
		
	}

	@Override
	public List<ShowCauseNoticeVO> getAllNoticeDtls() {
		List<ShowCauseNotice> allNoticeDtls = showCauseNoticeRepository.getAllNoticeDtls();
		if(allNoticeDtls == null) {
			return null;
		}
		return allNoticeDtls.stream().map(allDtls -> convertToDto(allDtls)).collect(Collectors.toList());
	}

	private ShowCauseNoticeVO convertToDto(ShowCauseNotice showCauseNotice) {
		ShowCauseNoticeVO showCauseNoticeVO = new ShowCauseNoticeVO();
		showCauseNoticeVO.setUnitName(showCauseNotice.getUnits().getUnitName());
		showCauseNoticeVO.setEntityName(showCauseNotice.getUnits().getEntitySummary().getEntityName());
		showCauseNoticeVO.setOwner("NA");
		showCauseNoticeVO.setReceivedFrom(showCauseNotice.getNoticeReceivedFrom());
		showCauseNoticeVO.setReceivedDate(showCauseNotice.getNoticeReceivedDate());
		showCauseNoticeVO.setNoticeReplyDeadline(showCauseNotice.getNoticeReplyDeadline());
		showCauseNoticeVO.setComments(showCauseNotice.getComment());
		showCauseNoticeVO.setSubject(showCauseNotice.getSubject());
		showCauseNoticeVO.setReferenceNo(showCauseNotice.getReferenceNo());
		showCauseNoticeVO.setLoginId(showCauseNotice.getUser().getLoginId());
		showCauseNoticeVO.setStatus(showCauseNotice.getStatus());
		System.out.println("Status: "+showCauseNoticeVO.getStatus());
		showCauseNoticeVO.setZoneName(showCauseNotice.getUnits().getRegions().getZoneName());
		showCauseNoticeVO.setFormatName(showCauseNotice.getFormat().getFormat());
		showCauseNoticeVO.setShowCauseNoticeId(showCauseNotice.getShowCauseNoticeId());
		
		for(ShowCauseNoticeForms forms: showCauseNotice.getShowCauseNoticeForms()) {
			showCauseNoticeVO.getDocument().add((forms.getInputDocName()));
		}
		return showCauseNoticeVO;
	}
	
	@Override
	public BaseResponseVO updateNoticeData(ShowCauseNoticeVO showCauseNoticeVO) {
		Optional<ShowCauseNotice> noticeIdExistence = showCauseNoticeRepository
				.findById(showCauseNoticeVO.getShowCauseNoticeId());
		ShowCauseNotice showCauseNoticeDtls = convertToEntity(showCauseNoticeVO);
		if (noticeIdExistence.isPresent()) {
			showCauseNoticeRepository.save(showCauseNoticeDtls);
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("Notice Updated Successfully");
		} else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("UNABLE TO UPDATE");
		}
		baseResponseVO.setData(null);
		return baseResponseVO;

	}

	private ShowCauseNotice convertToEntity(ShowCauseNoticeVO showCauseNoticeVO) {
		ShowCauseNotice showCauseNotice = new ShowCauseNotice();
		showCauseNotice.setShowCauseNoticeId(showCauseNoticeVO.getShowCauseNoticeId());
		showCauseNotice.setNoticeReceivedFrom(showCauseNoticeVO.getReceivedFrom());
		showCauseNotice.setIssueDate(showCauseNoticeVO.getIssueDate());
		showCauseNotice.setNoticeReceivedDate(showCauseNoticeVO.getReceivedDate());
		showCauseNotice.setNoticeReplyDeadline(showCauseNoticeVO.getNoticeReplyDeadline());
		showCauseNotice.setSubject(showCauseNoticeVO.getSubject());
		showCauseNotice.setReferenceNo(showCauseNoticeVO.getReferenceNo());
		showCauseNotice.setComment(showCauseNoticeVO.getComments());
		showCauseNotice.setActionTaken(showCauseNoticeVO.getActionTaken());
		showCauseNotice.setStatus(showCauseNoticeVO.getStatus());
		LocalDateTime dateTime = LocalDateTime.now();
		showCauseNotice.setCreateDate(dateTime);
		Format format = formatRepository.getFormatByName(showCauseNoticeVO.getFormatName());
		showCauseNotice.setFormat(format);
		Units units = unitsRepository.getUnitDtls(showCauseNoticeVO.getUnitName());
		showCauseNotice.setUnits(units);
		Dept dept = deptRepository.findByDeptName(showCauseNoticeVO.getFunction());
		showCauseNotice.setDept(dept);
		User user = userRepository.findByLoginId(showCauseNoticeVO.getLoginId());
		showCauseNotice.setUser(user);
		return showCauseNotice;
	}
	
	@Override
	public BaseResponseVO uploadFileandData(ShowCauseNoticeVO showCauseNoticeVO, MultipartFile file) {
		// uploading File
		String fileName = fileStorageService.storeFile(file);
		ShowCauseNoticeForms showCauseNoticeForms = new ShowCauseNoticeForms();
		showCauseNoticeForms.setInputDocName(fileName);
		showCauseNoticeForms.setInputDocPath(path + "/" + fileName);
		LocalDateTime dateTime = LocalDateTime.now();
		showCauseNoticeForms.setUploadedDate(dateTime);
		showCauseNoticeForms.setDocSize(file.getSize());
		showCauseNoticeForms.setFileExtension(file.getContentType());
		showCauseNoticeForms.setComments(showCauseNoticeVO.getComments());
		ShowCauseNotice showCauseNotice = showCauseNoticeRepository.findByID(showCauseNoticeVO.getShowCauseNoticeId());
		showCauseNoticeForms.setShowCauseNotice(showCauseNotice);
//		showCauseNoticeForms.getShowCauseNotice().setShowCauseNoticeId(showCauseNoticeVO.getShowCauseNoticeId());
		User user = userRepository.findByLoginId(showCauseNoticeVO.getLoginId());
		showCauseNoticeForms.setUserDtls(user);
		try {
			showCauseNoticeForms.setFileData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		showCauseNoticeFormsRepository.save(showCauseNoticeForms);
		baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("UPLOADED SUCCESSFULLY");
		baseResponseVO.setData(null);
		return baseResponseVO;
		
	}

}
