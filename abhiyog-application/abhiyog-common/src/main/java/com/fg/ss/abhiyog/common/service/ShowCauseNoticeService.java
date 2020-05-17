package com.fg.ss.abhiyog.common.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

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
public class ShowCauseNoticeService implements IShowCauseNoticeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShowCauseNoticeService.class);

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
	private EntityManager entityManager;

	@Autowired
	private FileStorageService fileStorageService;

	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	@Value("${file.upload-dir}")
	private String path;

	@Override
	public void saveNoticeData(ShowCauseNoticeVO showCauseNoticeVO, MultipartFile multiPartFile) throws IOException {
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

		String advocateName = showCauseNoticeVO.getAdvocateName() == null ? null : showCauseNoticeVO.getAdvocateName();
		showCauseNotice.setAdvocateName(advocateName);

		String advocateAddress = showCauseNoticeVO.getAdvocateAddress() == null ? null
				: showCauseNoticeVO.getAdvocateAddress();
		showCauseNotice.setAdvocateAddress(advocateAddress);

		String allegation = showCauseNoticeVO.getAllegation() == null ? null : showCauseNoticeVO.getAllegation();
		showCauseNotice.setAllegation(allegation);

		int claims = showCauseNoticeVO.getClaims() == 0 ? 0 : showCauseNoticeVO.getClaims();
		showCauseNotice.setClaims(claims);

		String complaintName = showCauseNoticeVO.getComplaintName() == null ? null
				: showCauseNoticeVO.getComplaintName();
		showCauseNotice.setNoticeSentComplaintName(complaintName);

		String complaintAddress = showCauseNoticeVO.getComplaintAddress() == null ? null
				: showCauseNoticeVO.getComplaintAddress();
		showCauseNotice.setNoticeSentComplaintAddress(complaintAddress);

		String natureInfringement = showCauseNoticeVO.getNatureOfIpInfringement() == null ? null
				: showCauseNoticeVO.getNatureOfIpInfringement();
		showCauseNotice.setNatureOfIpInfringement(natureInfringement);

		String section = showCauseNoticeVO.getSection() == null ? null : showCauseNoticeVO.getSection();
		showCauseNotice.setSection(section);

		String dealerName = showCauseNoticeVO.getDealerName() == null ? null : showCauseNoticeVO.getDealerName();
		showCauseNotice.setDealerName(dealerName);

		String dealerAddress = showCauseNoticeVO.getDealerAddress() == null ? null
				: showCauseNoticeVO.getDealerAddress();
		showCauseNotice.setDealerAddress(dealerAddress);

		String otherParties = showCauseNoticeVO.getOtherParties() == null ? null : showCauseNoticeVO.getOtherParties();
		showCauseNotice.setOtherParties(otherParties);

		String partyNo = showCauseNoticeVO.getPartyNo() == null ? null : showCauseNoticeVO.getPartyNo();
		showCauseNotice.setPartyNo(partyNo);

		String modelnumber = showCauseNoticeVO.getVehicleModelNumber() == null ? null
				: showCauseNoticeVO.getVehicleModelNumber();
		showCauseNotice.setVehicleModelNumber(modelnumber);

		showCauseNotice.setActionTaken(showCauseNoticeVO.getActionTaken());

		Dept dept = deptRepository.findByDeptName("Legal");
		showCauseNotice.setDept(dept);

		User user = userRepository.findByLoginId(showCauseNoticeVO.getLoginId());
		showCauseNotice.setUser(user);

//		Units units = unitsRepository.getUnitDtls(showCauseNoticeVO.getUnitName());
		Units units = unitsRepository.getUnitDtlsByEntityName(showCauseNoticeVO.getEntityName(), showCauseNoticeVO.getZoneName(), showCauseNoticeVO.getUnitName());
		showCauseNotice.setUnits(units);

		Format format = formatRepository.getFormatByName(showCauseNoticeVO.getFormatName());
		showCauseNotice.setFormat(format);

		NoticeCategory noticeCategory = noticeCategoryRepository
				.findByCategoryName(showCauseNoticeVO.getNoticeCategoryName());
		showCauseNotice.setNoticeCategory(noticeCategory);

		NoticeClassification noticeClassification = noticeClassificationRepository
				.findByClassificationName(showCauseNoticeVO.getNoticeClassification());
		showCauseNotice.setNoticeClassification(noticeClassification);

		showCauseNoticeRepository.save(showCauseNotice);
		// uploading File

		if (!multiPartFile.isEmpty()) {
			String fileName = fileStorageService.storeFile(multiPartFile);

			showCauseNoticeForms.setInputDocName(fileName);
			showCauseNoticeForms.setInputDocPath(path + "/" + fileName);
			showCauseNoticeForms.setUploadedDate(dateTime);
			showCauseNoticeForms.setDocSize(multiPartFile.getSize());
			showCauseNoticeForms.setFileExtension(multiPartFile.getContentType());
			showCauseNoticeForms.setComments(showCauseNoticeVO.getComments());
			showCauseNoticeForms.setShowCauseNotice(showCauseNotice);
			showCauseNoticeForms.setUserDtls(user);

			try {
				showCauseNoticeForms.setFileData(multiPartFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			showCauseNoticeFormsRepository.save(showCauseNoticeForms);
		} else {
			System.out.println("File is Empty**** " + multiPartFile.getSize());
		}

	}

	@Override
	public List<ShowCauseNoticeVO> getAllNoticeDtls() {
		List<ShowCauseNotice> allNoticeDtls = showCauseNoticeRepository.getAllNoticeDtls();
		if (allNoticeDtls == null) {
			return null;
		}
		return convertToDto(allNoticeDtls);
//		return allNoticeDtls.stream().map(allDtls -> convertToDto(allDtls)).collect(Collectors.toList());
	}

	private List<ShowCauseNoticeVO> convertToDto(List<ShowCauseNotice> allNoticeDtls) {
		Map<Integer, List<ShowCauseNotice>> groupedData = allNoticeDtls.stream()
				.collect(Collectors.groupingBy(ShowCauseNotice::getShowCauseNoticeId, Collectors.toList()));
		List<ShowCauseNoticeVO> listShowCauseNoticeData = new ArrayList<>();
		for (Entry<Integer, List<ShowCauseNotice>> showCauseNoticeList : groupedData.entrySet()) {
			ShowCauseNoticeVO showCauseNoticeVO = new ShowCauseNoticeVO();
			showCauseNoticeVO.setUnitName(showCauseNoticeList.getValue().get(0).getUnits().getUnitName());
			showCauseNoticeVO
					.setEntityName(showCauseNoticeList.getValue().get(0).getUnits().getEntitySummary().getEntityName());
			showCauseNoticeVO.setOwner("NA");
			showCauseNoticeVO.setReceivedFrom(showCauseNoticeList.getValue().get(0).getNoticeReceivedFrom());
			showCauseNoticeVO.setReceivedDate(showCauseNoticeList.getValue().get(0).getNoticeReceivedDate());
			showCauseNoticeVO.setNoticeReplyDeadline(showCauseNoticeList.getValue().get(0).getNoticeReplyDeadline());
			showCauseNoticeVO.setComments(showCauseNoticeList.getValue().get(0).getComment());
			showCauseNoticeVO.setSubject(showCauseNoticeList.getValue().get(0).getSubject());
			showCauseNoticeVO.setReferenceNo(showCauseNoticeList.getValue().get(0).getReferenceNo());
			showCauseNoticeVO.setLoginId(showCauseNoticeList.getValue().get(0).getUser().getLoginId());
			showCauseNoticeVO.setStatus(showCauseNoticeList.getValue().get(0).getStatus());
			System.out.println("Status: " + showCauseNoticeVO.getStatus());
			showCauseNoticeVO.setZoneName(showCauseNoticeList.getValue().get(0).getUnits().getRegions().getZoneName());
			showCauseNoticeVO.setFormatName(showCauseNoticeList.getValue().get(0).getFormat().getFormat());
			showCauseNoticeVO.setShowCauseNoticeId(showCauseNoticeList.getValue().get(0).getShowCauseNoticeId());
			for (ShowCauseNoticeForms forms : showCauseNoticeList.getValue().get(0).getShowCauseNoticeForms()) {
				showCauseNoticeVO.getDocument().add((forms.getInputDocName()));
			}
			listShowCauseNoticeData.add(showCauseNoticeVO);
		}
		return listShowCauseNoticeData;
	}

	@Override
	public boolean updateNoticeData(ShowCauseNoticeVO showCauseNoticeVO) {
		boolean flag = false;
		Optional<ShowCauseNotice> noticeIdExistence = showCauseNoticeRepository
				.findById(showCauseNoticeVO.getShowCauseNoticeId());
		
		ShowCauseNotice showCauseNoticeDtls = convertToEntity(showCauseNoticeVO);
		
		if (noticeIdExistence.isPresent()) {
			showCauseNoticeDtls.setNoticeCategory(noticeIdExistence.get().getNoticeCategory());
			showCauseNoticeDtls.setNoticeClassification(noticeIdExistence.get().getNoticeClassification());
			showCauseNoticeRepository.save(showCauseNoticeDtls);
			flag = true;
		} 
		return flag;
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
		Dept dept = deptRepository.findByDeptName("Legal");
		showCauseNotice.setDept(dept);
		User user = userRepository.findByLoginId(showCauseNoticeVO.getLoginId());
		showCauseNotice.setUser(user);
		return showCauseNotice;
	}

	@Override
	public void uploadFileandData(ShowCauseNoticeVO showCauseNoticeVO, MultipartFile file) {
		// uploading File
		String fileName = fileStorageService.storeFile(file);
		ShowCauseNoticeForms showCauseNoticeForms = new ShowCauseNoticeForms();
		showCauseNoticeForms.setInputDocName(fileName);
		showCauseNoticeForms.setInputDocPath(path + "/" + fileName);
		LocalDateTime dateTime = LocalDateTime.now();
		showCauseNoticeForms.setUploadedDate(dateTime);
		showCauseNoticeForms.setDocSize(file.getSize());
		showCauseNoticeForms.setFileExtension(file.getContentType());
		showCauseNoticeForms.setComments(showCauseNoticeVO.getCommentsDoc());
		List<ShowCauseNotice> showCauseNoticeList = showCauseNoticeRepository.findByID(showCauseNoticeVO.getShowCauseNoticeId());
		for(ShowCauseNotice showCauseNotice:showCauseNoticeList) {
			showCauseNoticeForms.setShowCauseNotice(showCauseNotice);
		}
		
//		showCauseNoticeForms.getShowCauseNotice().setShowCauseNoticeId(showCauseNoticeVO.getShowCauseNoticeId());
		User user = userRepository.findByLoginId(showCauseNoticeVO.getLoginId());
		showCauseNoticeForms.setUserDtls(user);
		try {
			showCauseNoticeForms.setFileData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		showCauseNoticeFormsRepository.save(showCauseNoticeForms);
	/*	baseResponseVO.setResponseCode(HttpStatus.OK.value());
		baseResponseVO.setResponseMessage("UPLOADED SUCCESSFULLY");
		baseResponseVO.setData(null); */
//		return baseResponseVO;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShowCauseNoticeVO> findShowCauseSummaryData(String entity, String zone, String unitName, String status,
			String format) {
		List<ShowCauseNotice> showCauseNoticeSummary = new ArrayList<>();
		Map<String, Object> parameterMap = new HashMap<>();
		List<String> whereClause = new ArrayList<>();
		StringBuilder reportQuery = new StringBuilder();

		reportQuery.append(
				"select scn from ShowCauseNotice as scn join ShowCauseNoticeForms as scf on scn.showCauseNoticeId = scf.showCauseNotice.showCauseNoticeId join Units as u on u.unitId = scn.units.unitId join Zone as r on u.regions.zoneId = r.zoneId join Format as f on f.formatId=scn.format.formatId join User as t on t.id = scn.user.id join EntitySummary as e on e.entityId = u.entitySummary.entityId");

		if (!entity.equals("ALL")) {
			System.out.println(entity);
			whereClause.add(" e.entityName=:entity ");
			parameterMap.put("entity", entity);
		}
		if (!zone.equals("ALL")) {
			whereClause.add(" r.zoneName=:zone ");
			parameterMap.put("zone", zone);
		}
		if (!unitName.equals("ALL")) {
			whereClause.add(" u.unitName=:unitName ");
			parameterMap.put("unitName", unitName);
		}
		if (!status.equals("ALL")) {
			whereClause.add(" scn.status=:status ");
			parameterMap.put("status", status);
		}
		if (!format.equals("ALL")) {
			whereClause.add(" f.format=:format ");
			parameterMap.put("format", format);
		}

		if (!entity.equals("ALL") || !zone.equals("ALL") || !unitName.equals("ALL") || !status.equals("ALL")
				|| !format.equals("ALL")) {
			reportQuery.append(" where " + StringUtils.join(whereClause, " and "));
		}

		Query jpaQuery = entityManager.createQuery(reportQuery.toString());
		LOGGER.info("Created Query::  " + reportQuery.toString());
		for (String key : parameterMap.keySet()) {
			jpaQuery.setParameter(key, parameterMap.get(key));
		}
		showCauseNoticeSummary = jpaQuery.getResultList();
		LOGGER.info("showCauseNoticeSummary Size:: " + showCauseNoticeSummary.size());
		return convertToDto(showCauseNoticeSummary);

	}

	@Override
	public List<ShowCauseNoticeForms> findDocumentNames(int id) {
		List<ShowCauseNoticeForms> allDocs = new ArrayList<ShowCauseNoticeForms>(); 
		allDocs= showCauseNoticeFormsRepository.findDocsBy(id);
		if(allDocs.isEmpty() || allDocs == null) {
			allDocs = showCauseNoticeFormsRepository.findByNoticeFormId(id);
		}
		return allDocs;
	}

	@Override
	public ShowCauseNoticeVO findNoticeDtls(int id) {
		List<ShowCauseNotice> noticeDtls = showCauseNoticeRepository.findNoticeDtls(id);
		if (noticeDtls.isEmpty() ||  noticeDtls == null  ) {
			noticeDtls  = showCauseNoticeRepository.findByID(id);
//			return null;
		}
		return convertToVO(noticeDtls);
	}

	private ShowCauseNoticeVO convertToVO(List<ShowCauseNotice> noticeDtls) {
		Map<Integer, List<ShowCauseNotice>> groupedData = noticeDtls.stream()
				.collect(Collectors.groupingBy(ShowCauseNotice::getShowCauseNoticeId, Collectors.toList()));
		ShowCauseNoticeVO showCauseNoticeVO = new ShowCauseNoticeVO();
		for (Entry<Integer, List<ShowCauseNotice>> showCauseNoticeList : groupedData.entrySet()) {

			showCauseNoticeVO.setUnitName(showCauseNoticeList.getValue().get(0).getUnits().getUnitName());
			showCauseNoticeVO.setEntityName(showCauseNoticeList.getValue().get(0).getUnits().getEntitySummary().getEntityName());
			showCauseNoticeVO.setZoneName(showCauseNoticeList.getValue().get(0).getUnits().getRegions().getZoneName());
			showCauseNoticeVO.setFormatName(showCauseNoticeList.getValue().get(0).getFormat().getFormat());
			showCauseNoticeVO.setOwner("NA");
			showCauseNoticeVO.setReceivedFrom(showCauseNoticeList.getValue().get(0).getNoticeReceivedFrom());
			showCauseNoticeVO.setReceivedDate(showCauseNoticeList.getValue().get(0).getNoticeReceivedDate());
			showCauseNoticeVO.setReceivedDt(showCauseNoticeList.getValue().get(0).getNoticeReceivedDate().toString());
			showCauseNoticeVO.setNoticeReplyDeadline(showCauseNoticeList.getValue().get(0).getNoticeReplyDeadline());
			showCauseNoticeVO.setNoticeRplyDeadline(showCauseNoticeList.getValue().get(0).getNoticeReplyDeadline().toString());
			System.out.println(showCauseNoticeVO.getNoticeReplyDeadline());
			
			/*
			 * DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); String strDate =
			 * dateFormat.format(showCauseNoticeList.getValue().get(0).getIssueDate());
			 * System.out.println("String Date:: " +strDate); DateFormat df = new
			 * SimpleDateFormat("dd-MM-yyyy"); Date d1 = df.parse(strDate);
			 * System.out.println("Date in dd-MM-yyyy HH:mm:ss format is: "+df.format(d1));
			 */
	         
			showCauseNoticeVO.setIssueDate(showCauseNoticeList.getValue().get(0).getIssueDate());
			showCauseNoticeVO.setIssueDt(showCauseNoticeList.getValue().get(0).getIssueDate().toString());
			System.out.println(showCauseNoticeVO.getIssueDate());
			showCauseNoticeVO.setComments(showCauseNoticeList.getValue().get(0).getComment());
			showCauseNoticeVO.setSubject(showCauseNoticeList.getValue().get(0).getSubject());
			showCauseNoticeVO.setReferenceNo(showCauseNoticeList.getValue().get(0).getReferenceNo());
			showCauseNoticeVO.setLoginId(showCauseNoticeList.getValue().get(0).getUser().getLoginId());
			showCauseNoticeVO.setStatus(showCauseNoticeList.getValue().get(0).getStatus());
			showCauseNoticeVO.setActionTaken(showCauseNoticeList.getValue().get(0).getActionTaken());
			
			showCauseNoticeVO.setShowCauseNoticeId(showCauseNoticeList.getValue().get(0).getShowCauseNoticeId());
			
			
			//setting showCausenoticeForm id for downloading or deleting record in jqgrid
//			showCauseNoticeVO.setShowCauseNoticeFormId(showCauseNoticeList.getValue().get(0).getShowCauseNoticeForms().get(0).getShowCauseNoticeFormsId());
			

			/*for (ShowCauseNoticeForms forms : showCauseNoticeList.getValue().get(0).getShowCauseNoticeForms()) {
				
				showCauseNoticeVO.getDocument().add((forms.getInputDocName()));
				System.out.println(showCauseNoticeVO.getDocument().size());
				
//				showCauseNoticeVO.getCommentsDoc().add((forms.getComments()));
//				showCauseNoticeVO.getFileSize().add((forms.getDocSize()));
//				System.out.println(showCauseNoticeVO.getFileSize().size());
			}*/

		}
		return showCauseNoticeVO;

	}

	@Override
	public List<ShowCauseNoticeVO> findNoticeFormDtls(int showCauseNoticeId) {
		List<ShowCauseNoticeForms> noticeDocsDtls = showCauseNoticeFormsRepository.findDocsBy(showCauseNoticeId);
		if (noticeDocsDtls == null) {
			return null;
		}
		return convertToNoticeDocsVO(noticeDocsDtls);
	}

	private List<ShowCauseNoticeVO> convertToNoticeDocsVO(List<ShowCauseNoticeForms> noticeDocsDtls) {
		Map<Integer, List<ShowCauseNoticeForms>> groupedData = noticeDocsDtls.stream()
				.collect(Collectors.groupingBy(ShowCauseNoticeForms::getShowCauseNoticeFormsId, Collectors.toList()));
		List<ShowCauseNoticeVO> showCauseNoticeList = new ArrayList<>();
		
		for (Entry<Integer, List<ShowCauseNoticeForms>> showCauseNoticeDocsList : groupedData.entrySet()) {
			ShowCauseNoticeVO showCauseNoticeVO = new ShowCauseNoticeVO();
			showCauseNoticeVO.setDocName(showCauseNoticeDocsList.getValue().get(0).getInputDocName());
			showCauseNoticeVO.setShowCauseNoticeFormId(showCauseNoticeDocsList.getValue().get(0).getShowCauseNoticeFormsId());
			showCauseNoticeVO.setShowCauseNoticeId(showCauseNoticeDocsList.getValue().get(0).getShowCauseNotice().getShowCauseNoticeId());
			showCauseNoticeVO.setCommentsDoc((showCauseNoticeDocsList.getValue().get(0).getComments()));
			showCauseNoticeVO.setFileSize((showCauseNoticeDocsList.getValue().get(0).getDocSize()));
			showCauseNoticeList.add(showCauseNoticeVO);
		}
		
		
		return showCauseNoticeList;
	}

	@Override
	public int deleteDocument(int id) {
		int isDeleted = showCauseNoticeFormsRepository.deleteDocById(id);
		return isDeleted;
	}

	@Override
	public List<ShowCauseNoticeVO> getUnitDtlsByZone(String zoneNameVal, String entityNameVal) {
		List<Units> unitsList = unitsRepository.getUnitDtlsByZone(zoneNameVal, entityNameVal);
		List<ShowCauseNoticeVO> unitData = new ArrayList<>();
		for(Units units: unitsList) {
			ShowCauseNoticeVO showCauseNoticeVO = new ShowCauseNoticeVO();
			showCauseNoticeVO.setUnitName(units.getUnitName());
			unitData.add(showCauseNoticeVO);
		}
		return unitData;
	}

}
