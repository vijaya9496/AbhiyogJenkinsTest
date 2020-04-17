package com.fg.ss.abhiyog.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.fg.ss.abhiyog.common.model.Country;
import com.fg.ss.abhiyog.common.model.EntitySummary;
import com.fg.ss.abhiyog.common.model.UnitHeads;
import com.fg.ss.abhiyog.common.model.Units;
import com.fg.ss.abhiyog.common.model.User;
import com.fg.ss.abhiyog.common.model.Zone;
import com.fg.ss.abhiyog.common.repository.CountryRepository;
import com.fg.ss.abhiyog.common.repository.EntityRepository;
import com.fg.ss.abhiyog.common.repository.UnitHeadsRepository;
import com.fg.ss.abhiyog.common.repository.UnitsRepository;
import com.fg.ss.abhiyog.common.repository.UserRepository;
import com.fg.ss.abhiyog.common.repository.ZonesRespository;
import com.fg.ss.abhiyog.common.vo.BaseResponseVO;
import com.fg.ss.abhiyog.common.vo.UnitSummaryVO;
import com.fg.ss.abhiyog.common.vo.UserVO;

@Service
public class UnitsSummaryService implements IUnitsSummaryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UnitsSummaryService.class);

	@Autowired
	private UnitsRepository unitsRepository;

	@Autowired
	private EntityRepository entityRepository;

	@Autowired
	private ZonesRespository zoneRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private UnitHeadsRepository unitHeadsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager entityManager;

	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	@Override
	public List<UnitSummaryVO> getUnitSummary() {
		List<Units> unitSummary = unitsRepository.getUnitSummary();
//		System.out.println("unitSummary size:: " +unitSummary.size());
		if (unitSummary == null) {
			return null;
		}
		return convertToDTO(unitSummary);
	}

	private List<UnitSummaryVO> convertToDTO(List<Units> units) {
		Map<Integer, List<Units>> groupedData = units.stream()
				.collect(Collectors.groupingBy(Units::getUnitId, Collectors.toList()));
		List<UnitSummaryVO> listUnitSummaryData = new ArrayList<>();

		for (Entry<Integer, List<Units>> unitList : groupedData.entrySet()) {
			UnitSummaryVO unitSummaryDto = new UnitSummaryVO();
			unitSummaryDto.setUnitId(unitList.getValue().get(0).getUnitId());
			unitSummaryDto.setEntityName(unitList.getValue().get(0).getEntitySummary().getEntityName());
			unitSummaryDto.setZoneName(unitList.getValue().get(0).getRegions().getZoneName());
			unitSummaryDto.setUnitName(unitList.getValue().get(0).getUnitName());
			for (UnitHeads unitheads : unitList.getValue().get(0).getUnitHeads()) {
				System.out.println("FNAME::"+unitheads.getUser().getFirstName()+"LastNAME::"+unitheads.getUser().getLastName());
				unitSummaryDto.getUnitHead()
						.add(unitheads.getUser().getFirstName() + " " + unitheads.getUser().getLastName());
			}
			listUnitSummaryData.add(unitSummaryDto);
		}

		return listUnitSummaryData;
	}

	/*
	 * @Override public Units findUnitByName(UnitSummaryVO unitSummaryVO) { // Units
	 * units =
	 * unitsRepository.findUnitByName(unitSummaryVO.getEntityName(),unitSummaryVO.
	 * getUnitName(), unitSummaryVO.getZoneName()); return null; }
	 */

	@Override
	public void saveFormData(String entityName, String zoneName, String unitName, String loginId) {
		String countryName = "India";
		Units units = new Units();
		UnitHeads unitHeads = new UnitHeads();
		units.setUnitName(unitName);
		EntitySummary entityDtls = entityRepository.getEntityByName(entityName);
		units.setEntitySummary(entityDtls);
		Zone regionDtls = zoneRepository.findByZoneName(zoneName);
		units.setRegions(regionDtls);
		Country countryDtls = countryRepository.findByName(countryName);
		units.setCountry(countryDtls);
		User userDtls = userRepository.findByLoginId(loginId);
		unitHeads.setUser(userDtls);
		unitHeads.setUnits(units);
		unitsRepository.save(units);
		unitHeadsRepository.save(unitHeads);
	}

	@Override
	public Units findUnitByName(String unitName) {
		Units unitsDtls = unitsRepository.findUnitByName(unitName);
		return unitsDtls;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public int updateCompanyUnitDetails(UnitSummaryVO unitSummaryVO) {
//		System.out.println("name:: " +unitSummaryVO.getName());
		// fetching units details
		Units unitsEntity = unitsRepository.getUnitDtls(unitSummaryVO.getUnitName());
		UnitHeads unitHeads = new UnitHeads();
		int isInserted = 0;
		ArrayList<String> loginIdList = new ArrayList<String>();
		if (unitsEntity != null) {
			System.out.println("Size:: " + unitSummaryVO.getUnitHead().size());
			// fetching unitHeads Details based on unitID
			List<UnitHeads> listUserDtls = unitHeadsRepository.getDtlsByUnitID(unitsEntity.getUnitId());
			
			// comparing with above fetched values with newly updated values
			for (UnitHeads listDtls : listUserDtls) {
				System.out.println("firstName:: " +listDtls.getUser().getFirstName());
				System.out.println("LastName:: " +listDtls.getUser().getLastName());
				String fullName = listDtls.getUser().getFirstName() + " " + listDtls.getUser().getLastName();
				if (!(unitSummaryVO.getUnitHead()).contains(fullName))
					loginIdList.add(fullName);
			}
			System.out.println("loginIdList::" + loginIdList.size());
			// delete row using remain list values
			for (String loginId : loginIdList) {
				String userNames[] = loginId.split("\\s+");
				int isDeleted = unitHeadsRepository.deleteUserId(userNames[0], userNames[1]);
				if (isDeleted > 0) {
					System.out.println("DELETED SUCCESSFULLY");
				}
			}

			// insert rows in unitheads
			for (String loginId : unitSummaryVO.getUnitHead()) {
				System.out.println("name:: " + loginId);
				String[] names = loginId.split("\\s+");
//				User user = userRepository.findByLoginId(loginId);
				User user  = userRepository.findUserBy(names[0], names[1]);
				if (user != null) {
					System.out.println("UserID::" + user.getId());
					unitHeads = unitHeadsRepository.getDtlsByUserID(user.getId(), unitsEntity.getUnitId());
					if (unitHeads == null) {
						 isInserted = unitHeadsRepository.saveUnitHeadData(user.getId(), unitsEntity.getUnitId());
						
					} 
					/*else {
						baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
						baseResponseVO.setResponseMessage("UNITNAME ALREADY MAPPED");
					}*/

				} 
				/*else {
					baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
					baseResponseVO.setResponseMessage("UNABLE TO MAP UNIT HEAD NAME TO UNITNAME");
				}*/
			}

		} 
		/*else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("UNITNAME NOT EXISTED. PLEASE ADD");
		}*/
		return isInserted;
	}

	@Override
	public Units findExistenceUnitName(String entityName, String zoneName, String unitName) {
		Units units = unitsRepository.getUnitDtlsByEntityName(entityName, zoneName, unitName);
		return units;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UnitSummaryVO> findUnitSummaryData(String entity, String zone) {
		List<Units> unitSummary = new ArrayList<>();
		Map<String, Object> parameterMap = new HashMap<>();
		List<String> whereClause = new ArrayList<>();
		StringBuilder reportQuery = new StringBuilder();

		reportQuery.append(
				"select u from Units as u inner join EntitySummary as e on e.entityId = u.entitySummary.entityId inner join Zone as r on r.zoneId = u.regions.zoneId inner join UnitHeads as h on h.units.unitId = u.unitId inner join User as t on t.id = h.user.id");

		if (!entity.equals("ALL")) {
			System.out.println(entity);
			whereClause.add(" e.entityName=:entity ");
			parameterMap.put("entity", entity);
		}
		if (!zone.equals("ALL")) {
			whereClause.add(" r.zoneName=:zone ");
			parameterMap.put("zone", zone);
		}
		if (!entity.equals("ALL") || !zone.equals("ALL")) {
			reportQuery.append(" where " + StringUtils.join(whereClause, " and "));
		}

		Query jpaQuery = entityManager.createQuery(reportQuery.toString());
		LOGGER.info("Created Query::  " + reportQuery.toString());
		for (String key : parameterMap.keySet()) {
			jpaQuery.setParameter(key, parameterMap.get(key));
		}
		unitSummary = jpaQuery.getResultList();
		LOGGER.info("userSummary Size:: " + unitSummary.size());
		return convertToDTO(unitSummary);
//		return unitSummary.stream().map(allUserSummaryDtls ->convertToDto(allUserSummaryDtls)).collect(Collectors.toList());

	}

	@Override
	public UnitSummaryVO getUnitLocationDtls(int id) {
		List<Units> unitSummary = unitsRepository.getUnitLocationBy(id);
//		System.out.println("unitSummary size:: " +unitSummary.size());
		if (unitSummary == null) {
			return null;
		}
		System.out.println(unitSummary.size());
		return convertToVO(unitSummary);
	}

	private UnitSummaryVO convertToVO(List<Units> units) {
		Map<Integer, List<Units>> groupedData = units.stream()
				.collect(Collectors.groupingBy(Units::getUnitId, Collectors.toList()));
		UnitSummaryVO unitSummaryDto = new UnitSummaryVO();
		for (Entry<Integer, List<Units>> unitList : groupedData.entrySet()) {
			unitSummaryDto.setUnitId(unitList.getValue().get(0).getUnitId());
			unitSummaryDto.setEntityName(unitList.getValue().get(0).getEntitySummary().getEntityName());
			unitSummaryDto.setZoneName(unitList.getValue().get(0).getRegions().getZoneName());
			unitSummaryDto.setUnitName(unitList.getValue().get(0).getUnitName());
			for (UnitHeads unitheads : unitList.getValue().get(0).getUnitHeads()) {
				unitSummaryDto.getUnitHead()
						.add(unitheads.getUser().getFirstName() + " " + unitheads.getUser().getLastName());
			}
		}
		return unitSummaryDto;
	}

	@Override
	public List<UserVO> getUnitHeadNames() {
		List<User> unitHeadNamesList = userRepository.getUnitHeadNames();
		System.out.println("unitHeadNamesList size:: " + unitHeadNamesList.size());
		List<UserVO> userVOList = new ArrayList<>();
		for (User unitHeadNames : unitHeadNamesList) {
			UserVO userVO = new UserVO();
			userVO.setFullName(unitHeadNames.getFirstName() + " " + unitHeadNames.getLastName());
			userVOList.add(userVO);
		}
		return userVOList;

	}

	

}
