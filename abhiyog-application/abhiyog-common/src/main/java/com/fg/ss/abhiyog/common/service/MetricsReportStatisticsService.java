package com.fg.ss.abhiyog.common.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fg.ss.abhiyog.common.model.EntitySummary;
import com.fg.ss.abhiyog.common.model.LtgnCaseType;
import com.fg.ss.abhiyog.common.model.Zone;
import com.fg.ss.abhiyog.common.repository.EntityRepository;
import com.fg.ss.abhiyog.common.repository.LitigationRepository;
import com.fg.ss.abhiyog.common.repository.LtgnCaseTypeRepository;
import com.fg.ss.abhiyog.common.repository.ZonesRespository;

@Service
public class MetricsReportStatisticsService {
	
	@Autowired
	private ZonesRespository zonesRespository;
	
	@Autowired
	private LitigationRepository litigationRepository;
	
	@Autowired
	private EntityRepository entityRepository;
	
	@Autowired
	private LtgnCaseTypeRepository ltgnCaseTypeRepository;

	public List<Map<String, Integer>> getZoneWiseStatistics() {
		Map<String, Integer> zoneWiseStatistics = new HashMap<String, Integer>();
		List<Map<String, Integer>> zoneWiseStatisticsList = new ArrayList<Map<String,Integer>>();
		List<Zone> allZones = zonesRespository.findAll();
		for(Zone allZoneDtl: allZones) {
			int count = litigationRepository.findCountofCasesByZoneName(allZoneDtl.getZoneName());
			if(count != 0) {
				zoneWiseStatistics.put(allZoneDtl.getZoneName(), count);
			}
		}
		zoneWiseStatisticsList.add(zoneWiseStatistics);
		return zoneWiseStatisticsList;
	}

	public List<Map<String, Integer>> getEntityWiseStatistics() {
		Map<String, Integer> entityWiseStatistics = new HashMap<String, Integer>();
		List<Map<String, Integer>> entityWiseStatisticsList = new ArrayList<Map<String,Integer>>();
		List<EntitySummary> allEntities = entityRepository.findAll();
		for(EntitySummary entities: allEntities) {
			int count = litigationRepository.findCountofCasesByEntityName(entities.getEntityName());
			if(count != 0) {
				entityWiseStatistics.put(entities.getEntityName(), count);
			}
		}
		entityWiseStatisticsList.add(entityWiseStatistics);
		return entityWiseStatisticsList;
	}

	public List<Map<String, Integer>> getCaseTypeWiseStatistics() {
		Map<String, Integer> caseTypeWiseStatistics = new HashMap<String, Integer>();
		List<Map<String, Integer>> caseTypeWiseStatisticsList = new ArrayList<Map<String,Integer>>();
		List<LtgnCaseType> allCaseTypes = ltgnCaseTypeRepository.findAll();
		for(LtgnCaseType caseTypes: allCaseTypes) {
			int count = litigationRepository.findCountofCasesByCaseType(caseTypes.getCaseType());
			if(count!=0) {
				caseTypeWiseStatistics.put(caseTypes.getCaseType(), count);
			}
			
		}
		caseTypeWiseStatisticsList.add(caseTypeWiseStatistics);
		return caseTypeWiseStatisticsList;
	}

	public List<Map<String, Integer>> getLitigationByStatistics() {
		List<String> litigationStatistics = Arrays.asList("by","Against");
		Map<String, Integer> litigationWiseStatistics = new HashMap<String, Integer>();
		List<Map<String, Integer>> litigationWiseStatisticsList = new ArrayList<Map<String,Integer>>();
		for(String litigationStatistic: litigationStatistics) {
			int count = litigationRepository.findLitigationByStatistics(litigationStatistic);
			if(count != 0) {
				litigationWiseStatistics.put(litigationStatistic, count);
			}
		}
		litigationWiseStatisticsList.add(litigationWiseStatistics);
		return litigationWiseStatisticsList;
	}
	
	

}
