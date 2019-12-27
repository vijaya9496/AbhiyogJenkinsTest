package com.fg.ss.abhiyog.common.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

@Service
public class UnitsSummaryService implements IUnitsSummaryService {

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

	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();

	@Override
	public UnitSummaryVO getUnitSummary() {
		List<Units> unitSummary = unitsRepository.getUnitSummary();
//		System.out.println("unitSummary size:: " +unitSummary.size());
		if (unitSummary == null) {
			return null;
		}

		UnitSummaryVO unitSummaryDTO = convertToDTO(unitSummary);
		return unitSummaryDTO;

	}

	private UnitSummaryVO convertToDTO(List<Units> unitSummary) {
		UnitSummaryVO unitSummaryDto = new UnitSummaryVO();
		for (Units unitSummaryDtls : unitSummary) {
			unitSummaryDto.setEntityName(unitSummaryDtls.getEntitySummary().getEntityName());
			unitSummaryDto.setZoneName(unitSummaryDtls.getRegions().getZoneName());
			unitSummaryDto.setUnitName(unitSummaryDtls.getUnitName());
		}
		for (int i = 0; i < unitSummary.size(); i++) {
			unitSummaryDto.getUnitHead().add((unitSummary.get(i).getUnitHeads().get(i).getUser().getLoginId()));
		}
		return unitSummaryDto;
	}

	/*
	 * @Override public Units findUnitByName(UnitSummaryVO unitSummaryVO) { // Units
	 * units =
	 * unitsRepository.findUnitByName(unitSummaryVO.getEntityName(),unitSummaryVO.
	 * getUnitName(), unitSummaryVO.getZoneName()); return null; }
	 */

	@Override
	public BaseResponseVO saveFormData(UnitSummaryVO unitSummaryVO) {
		String countryName = "India";
		Units units = new Units();
		UnitHeads unitHeads = new UnitHeads();
		units.setUnitName(unitSummaryVO.getUnitName());
		EntitySummary entityDtls = entityRepository.getEntityByName(unitSummaryVO.getEntityName());
		units.setEntitySummary(entityDtls);
		Zone regionDtls = zoneRepository.findByZoneName(unitSummaryVO.getZoneName());
		units.setRegions(regionDtls);
		Country countryDtls = countryRepository.findByName(countryName);
		units.setCountry(countryDtls);
		User userDtls = userRepository.findByLoginId(unitSummaryVO.getLoginId());
		unitHeads.setUser(userDtls);
		unitHeads.setUnits(units);
		unitsRepository.save(units);
		unitHeadsRepository.save(unitHeads);
		baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
		baseResponseVO.setResponseMessage("UNIT/LOCATION CREATED SUCCESSFULLY");
		return baseResponseVO;
	}

	@Override
	public Units findUnitByName(String unitName) {
		Units unitsDtls = unitsRepository.findUnitByName(unitName);
		return unitsDtls;
	}

	@Override
	public BaseResponseVO updateCompanyUnitDetails(UnitSummaryVO unitSummaryVO) {
//		System.out.println("name:: " +unitSummaryVO.getName());
		// fetching units details
		Units unitsEntity = unitsRepository.getUnitDtls(unitSummaryVO.getUnitName());
		UnitHeads unitHeads = new UnitHeads();
		ArrayList<String> loginIdList = new ArrayList<String>();
		if (unitsEntity != null) {
			System.out.println("Size:: " + unitSummaryVO.getUnitHeadNames().length);
			// fetching unitHeads Details based on unitID
			List<UnitHeads> listUserDtls = unitHeadsRepository.getDtlsByUnitID(unitsEntity.getUnitId());
			// comparing with above fetched values with newly updated values
			for (UnitHeads listDtls : listUserDtls) {
				if (!Arrays.asList(unitSummaryVO.getUnitHeadNames()).contains(listDtls.getUser().getLoginId()))
					loginIdList.add(listDtls.getUser().getLoginId());
			}
			System.out.println("loginIdList::" + loginIdList.size());
			// delete row using remain list values
			for (String loginId : loginIdList) {
				int isDeleted = unitHeadsRepository.deleteUserId(loginId);
				if (isDeleted > 0) {
					System.out.println("DELETED SUCCESSFULLY");
				}
			}

			// insert rows in unitheads
			for (String loginId : unitSummaryVO.getUnitHeadNames()) {
				System.out.println("name:: " + loginId);
				User user = userRepository.findByLoginId(loginId);
				if (user != null) {
					System.out.println("UserID::" + user.getId());
					unitHeads = unitHeadsRepository.getDtlsByUserID(user.getId());
					if (unitHeads == null) {
						int isInserted = unitHeadsRepository.saveUnitHeadData(user.getId(), unitsEntity.getUnitId());
						if (isInserted > 0) {
							baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
							baseResponseVO.setResponseMessage("SUCCESSFULLY USER MAPPED WITH UNITNAME");
						} else {
							baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
							baseResponseVO.setResponseMessage("UNABLE TO ADD DATA");
						}
					} else {
						baseResponseVO.setResponseCode(HttpStatus.CREATED.value());
						baseResponseVO.setResponseMessage("UNITNAME ALREADY MAPPED");
					}

				} else {
					baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
					baseResponseVO.setResponseMessage("UNABLE TO MAP UNIT HEAD NAME TO UNITNAME");
				}
			}

		} else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("UNITNAME NOT EXISTED. PLEASE ADD");
		}
		return baseResponseVO;
	}

}
