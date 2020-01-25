package com.fg.ss.abhiyog.common.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.Litigation;

@Repository
public interface LitigationRepository extends JpaRepository<Litigation, Integer>{

	@Query(value="select * from ltgn_litigation l order by l.litigation_id desc limit 1", nativeQuery=true)
	Litigation getLitigationId();

	@Query(value="select lt from Litigation as lt join Units as u on u.unitId = lt.units.unitId join EntitySummary as e on e.entityId = u.entitySummary.entityId join Zone as r on r.zoneId = u.regions.zoneId join Risk as rs on rs.riskId = lt.risk.riskId join Claim as c on c.claimId = lt.claim.claimId join Status as s on s.statusId = lt.status.statusId join CounterPartyDtls cpd on cpd.id = lt.counterPartyDtls.id join CustomerType as ct on ct.customerTypeId = lt.customerType.customerTypeId order by lt.litigationId desc")
	List<Litigation> findAllLitigationSummary();

	@Query(value="select lt from Litigation as lt where lt.litigationId=:litigationId")
	Litigation findByLitigationId(@Param("litigationId")String litigationId);

	@Modifying
	@Transactional
	@Query(value="update Litigation lt set lt.resultId=:resultId, lt.reOpenComments=:comments, lt.disposedDate=:disposedDate where lt.litigationId=:litigationId")
	int updateLitigation(@Param("resultId")int resultId, @Param("comments")String comments, @Param("disposedDate")Date disposedDate, @Param("litigationId")String litigationId);

	@Query(value = "select lt from Litigation as lt join Status as s on s.statusId = lt.status.statusId where lt.litigationId = :litigationId")
	Litigation getStatusById(@Param("litigationId")String litigationId);

	@Modifying
	@Transactional
	@Query(value="update Litigation lt set lt.fileAvailable=:fileAvailable where lt.litigationId=:litigationId")
	int updateFileStatusByLitigationId(@Param("litigationId")String litigationId, @Param("fileAvailable")String fileAvailable);

	@Query(value="select lt from Litigation as lt join Risk as rs on rs.riskId = lt.risk.riskId join Claim as c on c.claimId = lt.claim.claimId join LawFirm as lf on lf.lawfirmId = lt.lawFirm.lawfirmId join State as s on s.stateId = lt.state.stateId join CourtType as ct on ct.courtTypeId = lt.courtType.courtTypeId join CourtCity as cct on cct.courtCityId = lt.courtCity.courtCityId join UnderAct as ua on ua.underActId = ua.underAct.underActId join LtgnCaseType as lct on lct.caseTypeId = lt.ltgnCaseType.caseTypeId where lf.lawfirmId = lt.lawFirmSenior.lawfirmId and lt.litigationId=:litigationId")
	List<Litigation> getCaseDtls(@Param("litigationId")String litigationId);

	@Query(value= "select lt from Litigation as lt join Units as u on lt.units.unitId=u.unitId join EntitySummary as e on e.entityId = u.entitySummary.entityId join Zone as r on r.zoneId = u.regions.zoneId join Format as f on f.formatId = lt.format.formatId join Status as s on s.statusId = lt.status.statusId join LitigationMatterByAgainst as lma  on lma.litigation.litigationOId = lt.litigationOId join LtgnRepresentativeMaster as lrm on lrm.representativeId = lma.ltgnRepresentativeMaster.representativeId where lt.litigationId=:litigationId")
	List<Litigation> getAllDetails(@Param("litigationId")String litigationId);

	@Query(value="select lt from Litigation as lt join Units as u on lt.units.unitId=u.unitId "
			+ "join EntitySummary as e on e.entityId = u.entitySummary.entityId "
			+ "join Zone as r on r.zoneId = u.regions.zoneId join Format as f on f.formatId = lt.format.formatId "
			+ "join Status as s on s.statusId = lt.status.statusId join Risk as rs on rs.riskId = lt.risk.riskId "
			+ "join Claim as c on c.claimId = lt.claim.claimId join LawFirm as lf on lf.lawfirmId = lt.lawFirm.lawfirmId "
			+ "join State as st on st.stateId = lt.state.stateId join CourtType as ct on ct.courtTypeId = lt.courtType.courtTypeId "
			+ "join CourtCity as cct on cct.courtCityId = lt.courtCity.courtCityId join UnderAct as ua on ua.underActId = ua.underAct.underActId "
			+ "join LtgnCaseType as lct on lct.caseTypeId = lt.ltgnCaseType.caseTypeId join CustomerType as ct on ct.customerTypeId = lt.customerType.customerTypeId "
			+ "join LitigationMatterByAgainst as lma on lma.litigation.litigationOId = lt.litigationOId "
			+ "join LtgnRepresentativeMaster as lrm on lrm.representativeId = lma.ltgnRepresentativeMaster.representativeId where lt.litigationId=:litigationId")
	List<Litigation> showLitigationDetails(@Param("litigationId")String litigationId);

	
	@Query(value="select lt from Litigation lt, LtgnRepresentativeMaster as lrm where lt.litigationId=:litigationId and lrm.representativeName=:matterByAgainst")
	Litigation getDtlsByLitigationRepresentativeName(@Param("matterByAgainst")String matterByAgainst, @Param("litigationId")String litigationId);

	@Modifying
	@Transactional
	@Query(value="update Litigation lt set lt.deleteStatus = 1 where lt.litigationId = :litigationId")
	int updateDeleteStatus(@Param("litigationId")String litigationId);
	
	@Query(value="select lt from Litigation as lt where lt.deleteStatus = 1")
	List<Litigation> getAllRestoreLitigationDtls();

	@Modifying
	@Transactional
	@Query(value="update Litigation lt set lt.deleteStatus = 0 where lt.litigationId =:lId")
	int addLitigationToRestore(@Param("lId")String lId);

	@Query(value="select lt from Litigation as lt join Status as s on s.statusId = lt.status.statusId "
			+ "join CounterPartyDtls as lc on lc.id = lt.counterPartyDtls.id join Units as u on "
			+ "u.unitId = lt.units.unitId join EntitySummary as e on e.entityId = u.entitySummary.entityId "
			+ "where lt.nextDateOfHearing between :fromDate and :toDate")
	List<Litigation> findHearingStatusDtlsByFromandToDate(@Param("fromDate")LocalDate fromDate, @Param("toDate")LocalDate toDate);

	
//	@Query(value="select lt from Litigation as lt join Units as u on u.unitId = lt.units.unitId join EntitySummary as e on e.entityId = u.entitySummary.entityId join Zone as z on z.zoneId = u.regions.zoneId join CounterPartyDtls as lc on lc.id = lt.counterPartyDtls.id join CourtCity as cc on cc.courtCityId = lt.courtCity.courtCityId join CourtType as ct on ct.courtTypeId = lt.courtType.courtTypeId where lt.nextDateOfHearing between CURDATE() and adddate(now(),7)")
	/*@Query(value="select * from ltgn_litigation as lt join units as u on u.unit_id = lt.unit_id join entity as e \r\n" + 
			"on u.entity_id = e.entity_id join regions as r on r.regions_id = u.regions_id join ltgn_customer as lc \r\n" + 
			"on lc.customer_id = lt.customer_id join courtcity as cc on cc.court_city_id = lt.court_city_id join \r\n" + 
			"courttype as ct on ct.court_type_id = lt.court_type_id\r\n" + 
			"where NEXT_DATE_OF_HEARING between CURDATE() and DATE_ADD(now(), INTERVAL 7 DAY)", nativeQuery =true)*/
	@Query(value="select lt from Litigation as lt join fetch lt.units u\r\n" + 
			"join fetch u.entitySummary e\r\n" + 
			"join fetch u.regions r\r\n" + 
			"join fetch lt.counterPartyDtls lc\r\n" + 
			"join fetch lt.courtCity cc\r\n" + 
			"join fetch lt.courtType ct\r\n" + 
			"where lt.nextDateOfHearing between CURDATE() and adddate(now(),7)")
	List<Litigation> findNextSevenDaysHearingDateDtls();
	
	

}
