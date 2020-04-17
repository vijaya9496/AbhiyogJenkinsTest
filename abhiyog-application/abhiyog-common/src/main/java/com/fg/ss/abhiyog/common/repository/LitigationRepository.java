package com.fg.ss.abhiyog.common.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.Litigation;
import com.fg.ss.abhiyog.common.vo.DashboardDtlVO;
import com.fg.ss.abhiyog.common.vo.DashboardVO;

@Repository
public interface LitigationRepository extends JpaRepository<Litigation, Integer>{

	@Query(value="select * from ltgn_litigation l order by l.litigationid desc limit 1", nativeQuery=true)
	Litigation getLitigationId();

	@Query(value="select lt from Litigation as lt join Units as u on u.unitId = lt.units.unitId join EntitySummary as e on e.entityId = u.entitySummary.entityId join Zone as r on r.zoneId = u.regions.zoneId join Risk as rs on rs.riskId = lt.risk.riskId join Claim as c on c.claimId = lt.claim.claimId join Status as s on s.statusId = lt.status.statusId join CounterPartyDtls cpd on cpd.id = lt.counterPartyDtls.id join CustomerType as ct on ct.customerTypeId = lt.customerType.customerTypeId where lt.deleteStatus != 1 and lt.resultId = 0 order by lt.litigationId desc")
	List<Litigation> findAllLitigationSummary();

	@Query(value="select lt from Litigation as lt where lt.litigationId=:litigationId")
	Litigation findByLitigationId(@Param("litigationId")String litigationId);

	@Modifying
	@Transactional
	@Query(value="update Litigation lt set lt.resultId=:resultId, lt.reOpenComments=:comments, lt.disposedDate=:disposedDate where lt.litigationOid=:litigationOId")
	int updateLitigation(@Param("resultId")int resultId, @Param("comments")String comments, @Param("disposedDate")LocalDate disposedDate, @Param("litigationOId")int litigationOId);

	@Query(value = "select lt from Litigation as lt join Status as s on s.statusId = lt.status.statusId where lt.litigationId = :litigationId")
	Litigation getStatusById(@Param("litigationId")String litigationId);

	@Modifying
	@Transactional
	@Query(value="update Litigation lt set lt.fileAvailable=:fileAvailable where lt.litigationOid=:litigationId")
	int updateFileStatusByLitigationId(@Param("litigationId")int litigationId, @Param("fileAvailable")String fileAvailable);

	@Query(value="select lt from Litigation as lt join Risk as rs on rs.riskId = lt.risk.riskId join Claim as c on c.claimId = lt.claim.claimId join LawFirm as lf on lf.lawfirmId = lt.lawFirm.lawfirmId join State as s on s.stateId = lt.state.stateId join CourtType as ct on ct.courtTypeId = lt.courtType.courtTypeId join CourtCity as cct on cct.courtCityId = lt.courtCity.courtCityId join UnderAct as ua on ua.underActId = ua.underAct.underActId join LtgnCaseType as lct on lct.caseTypeId = lt.ltgnCaseType.caseTypeId where lf.lawfirmId = lt.lawFirmSenior.lawfirmId and lt.litigationId=:litigationId")
	List<Litigation> getCaseDtls(@Param("litigationId")String litigationId);

	@Query(value= "select lt from Litigation as lt join Units as u on lt.units.unitId=u.unitId join EntitySummary as e on e.entityId = u.entitySummary.entityId join Zone as r on r.zoneId = u.regions.zoneId join Format as f on f.formatId = lt.format.formatId join Status as s on s.statusId = lt.status.statusId join LitigationMatterByAgainst as lma  on lma.litigation.litigationOid = lt.litigationOid join LtgnRepresentativeMaster as lrm on lrm.representativeId = lma.ltgnRepresentativeMaster.representativeId where lt.litigationId=:litigationId")
	List<Litigation> getAllDetails(@Param("litigationId")String litigationId);

	@Query(value="select lt from Litigation as lt join Units as u on lt.units.unitId=u.unitId "
			+ "join EntitySummary as e on e.entityId = u.entitySummary.entityId "
			+ "join Zone as r on r.zoneId = u.regions.zoneId join Format as f on f.formatId = lt.format.formatId "
			+ "join Status as s on s.statusId = lt.status.statusId join Risk as rs on rs.riskId = lt.risk.riskId "
			+ "join Claim as c on c.claimId = lt.claim.claimId join LawFirm as lf on lf.lawfirmId = lt.lawFirm.lawfirmId "
			+ "join State as st on st.stateId = lt.state.stateId join CourtType as ct on ct.courtTypeId = lt.courtType.courtTypeId "
			+ "join CourtCity as cct on cct.courtCityId = lt.courtCity.courtCityId join UnderAct as ua on ua.underActId = ua.underAct.underActId "
			+ "join LtgnCaseType as lct on lct.caseTypeId = lt.ltgnCaseType.caseTypeId join CustomerType as ct on ct.customerTypeId = lt.customerType.customerTypeId "
			+ "join LitigationMatterByAgainst as lma on lma.litigation.litigationOid = lt.litigationOid "
			+ "join LtgnRepresentativeMaster as lrm on lrm.representativeId = lma.ltgnRepresentativeMaster.representativeId where lt.litigationId=:litigationId")
	List<Litigation> showLitigationDetails(@Param("litigationId")String litigationId);


	@Query(value="select lt from Litigation lt, LtgnRepresentativeMaster as lrm where lt.litigationOid=:litigationId and lrm.representativeName=:matterByAgainst")
	Litigation getDtlsByLitigationRepresentativeName(@Param("matterByAgainst")String matterByAgainst, @Param("litigationId")int litigationId);

	@Modifying
	@Transactional
	@Query(value="update Litigation lt set lt.deleteStatus = 1 where lt.litigationOid = :litigationOId")
	int updateDeleteStatus(@Param("litigationOId")int litigationOId);

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
			"where lt.nextDateOfHearing between CURDATE() and adddate(CURDATE(),7)")
	List<Litigation> findNextSevenDaysHearingDateDtls();

	@Query(value="select count(lt) from Litigation as lt, Zone r, Units u where u.unitId = lt.units.unitId and r.zoneId = u.regions.zoneId and r.zoneName = :zoneName")
	int findCountofCasesByZoneName(@Param("zoneName")String zoneName);

	@Query(value="select count(lt) from Litigation as lt, EntitySummary as e, Units as u where u.unitId = lt.units.unitId and e.entityId = u.entitySummary.entityId and e.entityName=:entityName")
	int findCountofCasesByEntityName(@Param("entityName")String entityName);

	@Query(value="select count(lt) from Litigation as lt, LtgnCaseType as lct where lct.caseTypeId = lt.ltgnCaseType.caseTypeId and lct.caseType=:caseType")
	int findCountofCasesByCaseType(@Param("caseType")String caseType);

	@Query(value="select count(lt) from Litigation as lt, LitigationMatterByAgainst as lma, LtgnRepresentativeMaster as lrm where lrm.representativeId = lma.ltgnRepresentativeMaster.representativeId and lt.litigationOid = lma.litigation.litigationOid and lrm.representativeName=:litigationStatistic")
	int findLitigationByStatistics(@Param("litigationStatistic")String litigationStatistic);

	@Query(value="select lt from Litigation as lt join Units as u on u.unitId = lt.units.unitId join EntitySummary as e on e.entityId = u.entitySummary.entityId join Zone as r on r.zoneId = u.regions.zoneId join Risk as rs on rs.riskId = lt.risk.riskId join Claim as c on c.claimId = lt.claim.claimId join Status as s on s.statusId = lt.status.statusId join CounterPartyDtls cpd on cpd.id = lt.counterPartyDtls.id join CustomerType as ct on ct.customerTypeId = lt.customerType.customerTypeId where deleteStatus = 1 order by lt.litigationId desc")
	List<Litigation> findRestoreLitigationSummary();

	@Modifying
	@Transactional
	@Query(value="update Litigation lt set lt.deleteStatus = 0 where lt.litigationOid = :loId")
	int updateRestoreLitigationData(@Param("loId")int loId);

	@Query(value="select lt from Litigation as lt join Witness as wt on lt.litigationOid = wt.litigation.litigationOid where lt.litigationOid =:id")
	List<Litigation> getWitnessDtls(@Param("id")int id);

	



	@Query(value="select unitoid,entityoid,regionoid,unitname, regionname, sum(upcoming) as upcoming, sum(notupdated) as notupdated, count(*) as total from\r\n" + 
			"(select u.unitoid,u.entityoid,u.regionoid,lt.litigationid, u.unitname, r.regionname, e.entityname, max(ltlog.date_of_hearing), \r\n" + 
			"(case when (max(ltlog.date_of_hearing) > curdate()) then 0  else 1 end) as upcoming,\r\n" + 
			"(case when (max(ltlog.date_of_hearing) < curdate()) then 0 else 1 end) as notupdated\r\n" + 
			"from regions  as r inner join units as u on r.regionoid = u.regionoid inner join ltgn_litigation as lt \r\n" + 
			"on u.unitoid = lt.unitoid inner join litigationunits as lu on lt.litigation_oid = lu.litigation_oid inner join ltgn_litigation_log \r\n" + 
			"as ltlog on ltlog.litigation_oid = lt.litigation_oid inner join entity as e on u.entityoid = e.entityoid\r\n" + 
			"where  lt.deletestatus = 0 and lt.disposeddt is null group by u.unitoid,u.entityoid,u.regionoid,lt.litigationid,  u.unitname, r.regionname, u.entityoid, e.entityname) as derivedtbl_1\r\n" + 
			"group by unitoid,entityoid,regionoid,unitname, regionname, entityname", nativeQuery=true)
	List<DashboardVO> getDashboardSummary();


	/*@Query(value="SELECT UnitOID, EntityOID, RegionOID, UnitName, RegionName, EntityName, SUM(upcoming) AS Upcoming, SUM(NotUpdated) AS NotUpdated, COUNT(*) AS Total"
			+"FROM (SELECT Units.UnitOID, Units.EntityOID, Units.RegionOID, LTGN_LITIGATION.LitigationID, Units.UnitName, Regions.RegionName,"
			+"MAX(LTGN_LITIGATION_LOG.DATE_OF_HEARING) AS Expr1, Units.EntityOID AS Expr2, Entity.EntityName,"
            +"(CASE WHEN (MAX(LTGN_LITIGATION_LOG.DATE_OF_HEARING) < GETDATE()) THEN 0 ELSE 1 END) AS upcoming,"
            +"(CASE WHEN (MAX(LTGN_LITIGATION_LOG.DATE_OF_HEARING) > GETDATE()) THEN 0 ELSE 1 END) AS NotUpdated"
            +"FROM  Regions INNER JOIN Units ON Regions.RegionOID = Units.RegionOID "
            +"INNER JOIN LTGN_LITIGATION INNER JOIN LitigationUnits ON LTGN_LITIGATION.LITIGATION_OID = LitigationUnits.LITIGATION_OID ON Units.UnitOID = LitigationUnits.UnitOID"
            +"INNER JOIN LTGN_LITIGATION_LOG ON LTGN_LITIGATION.LITIGATION_OID = LTGN_LITIGATION_LOG.LITIGATION_OID"
            +"INNER JOIN Entity ON Units.EntityOID = Entity.EntityOID  WHERE (LTGN_LITIGATION.DeleteStatus IS NULL) AND (LTGN_LITIGATION.DisposedDt IS NULL)"
            +"GROUP BY Units.UnitOID, Units.EntityOID, Units.RegionOID, LTGN_LITIGATION.LitigationID, Units.UnitName, Regions.RegionName, Units.EntityOID,"
            +"Entity.EntityName) AS derivedtbl_1 GROUP BY UnitOID, EntityOID, RegionOID, UnitName, RegionName, EntityName", nativeQuery=true)
			List<DashboardVO> getDashboardSummary();
	 */




	@Query(value="select derivedtbl_2.unitoid, derivedtbl_2.entityoid, derivedtbl_2.regionoid,derivedtbl_2.litigation_oid as litigationoid, derivedtbl_2.litigationid, derivedtbl_2.unitname, derivedtbl_2.regionname, derivedtbl_2.expr2, " +
			" derivedtbl_2.entityname, derivedtbl_2.claim, derivedtbl_2.risk, derivedtbl_2.remark, derivedtbl_2.litigationlogoid, " +
			" derivedtbl_2.hearingdt1 as hearingdate, ltgn_litigation_log_1.stage, ltgn_customer.customer_name as customername, ltgn_litigation_1.court, ltgn_litigation_1.case_type_oid, ltgn_litigation_1.courtcityoid, " +
			" ltgn_litigation_1.courttypeoid, courtcity.courtcity, ltgn_litigation_1.case_number as casenumber, ltgn_litigation_1.against_party_client_type as againstpartyclienttype, derivedtbl_2.status" +
			" from ( select unitoid, entityoid, regionoid, litigation_oid,litigationid, unitname, regionname, expr2, entityname, max(hearingdt) as hearingdt1, " +
			" case when ( max(hearingdt) < curdate() ) then 'Pending' else 'Upcoming' end as status, claim, risk, remark, max(litigation_log_oid) as litigationlogoid" +
			" from ( select units.unitoid, units.entityoid, units.regionoid,ltgn_litigation.litigation_oid, ltgn_litigation.litigationid, units.unitname, regions.regionname, units.entityoid as expr2, entity.entityname," +
			" ltgn_litigation_log.date_of_hearing as hearingdt, ltgn_litigation.claimoid, claim.claim, ltgn_litigation_log.litigation_log_oid, risk.risk, ltgn_litigation.remark" +
			" from regions inner join units on regions.regionoid = units.regionoid inner join ltgn_litigation on units.unitoid = ltgn_litigation.unitoid " +
			" inner join litigationunits on ltgn_litigation.litigation_oid = litigationunits.litigation_oid inner join ltgn_litigation_log on ltgn_litigation.litigation_oid = ltgn_litigation_log.litigation_oid " +
			" inner join entity on units.entityoid = entity.entityoid inner join claim on ltgn_litigation.claimoid = claim.claimoid inner join risk on ltgn_litigation.riskoid = risk.riskoid" +
			" where ( ltgn_litigation.deletestatus =0 ) and ( ltgn_litigation.disposeddt is null ) and ( units.unitoid = :unitoId ) ) as derivedtbl_1 group by unitoid, entityoid, " +
			" regionoid,litigation_oid, litigationid, unitname, regionname, expr2, entityname, claim, risk, remark ) as derivedtbl_2 inner join ltgn_litigation_log as ltgn_litigation_log_1 on derivedtbl_2.litigationlogoid = ltgn_litigation_log_1.litigation_log_oid " +
			" inner join ltgn_litigation as ltgn_litigation_1 on ltgn_litigation_log_1.litigation_oid = ltgn_litigation_1.litigation_oid inner join ltgn_customer on ltgn_litigation_1.customer_oid = ltgn_customer.customer_oid " +
			" inner join courtcity on ltgn_litigation_1.courtcityoid = courtcity.courtcityoid"
				, nativeQuery=true)
	List<DashboardDtlVO> getDashboardDetails(@Param("unitoId")int unitoId);


	



	/*@Query(value="select lt from Litigation as lt join Units as u on u.unitId = lt.units.unitId join EntitySummary as e on e.entityId = u.entitySummary.entityId join Zone as r on r.zoneId = u.regions.zoneId join Risk as rs on rs.riskId = lt.risk.riskId join Claim as c on c.claimId = lt.claim.claimId join Status as s on s.statusId = lt.status.statusId join CounterPartyDtls cpd on cpd.id = lt.counterPartyDtls.id join CustomerType as ct on ct.customerTypeId = lt.customerType.customerTypeId join Format as f on f.formatId = lt.format.formatId "
			+ "where r.zoneName=:zone OR :zone IS NULL and f.format=:format OR :format IS NULL and e.entityName=:entity OR :entity IS NULL and cpd.customerName=:counterParty OR :counterParty IS NULL and lt.ltgnCaseType.caseType=:category OR :category IS NULL and "
			+ "c.claim=:possibleClaim OR :possibleClaim IS NULL and lt.state.stateName=:state OR :state IS NULL and lt.lawFirm.lawfirm=:lawfirmIndividual OR :lawfirmIndividual IS NULL and "
			+ "lt.courtType.courtType=:courtType OR :courtType IS NULL  and lt.underAct.underAct=:underActs OR :underActs IS NULL and rs.risk=:risk OR :risk IS NULL and "
			+ "s.status=:status OR :status IS NULL and lt.ltgnRepresentativeMaster.representativeName=:matterByAgainst OR :matterByAgainst IS NULL  and lt.ltgnRepresentativeMaster.representativeName=:litigationByAgainst OR :litigationByAgainst IS NULL")
	List<Litigation> findLitigationSummaryByFieldSelection(@Param("zone")String zone, @Param("format")String format, @Param("entity")String entity, 
			@Param("counterParty")String counterParty, @Param("category")String category, @Param("possibleClaim")String possibleClaim, @Param("state")String state, @Param("lawfirmIndividual")String lawfirmIndividual,
			@Param("courtType")String courtType, @Param("underActs")String underActs, @Param("risk")String risk, @Param("status")String status, @Param("matterByAgainst")String matterByAgainst,
			@Param("litigationByAgainst")String litigationByAgainst);*/



}
