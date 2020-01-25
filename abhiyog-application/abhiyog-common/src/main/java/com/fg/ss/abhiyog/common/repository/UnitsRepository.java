package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.Units;

@Repository
public interface UnitsRepository extends JpaRepository<Units, Integer> {

	@Query(value = "select u from Units as u inner join EntitySummary as e on e.entityId = u.entitySummary.entityId inner join Zone as r on r.zoneId = u.regions.zoneId inner join UnitHeads as h on h.units.unitId = u.unitId inner join User as t on t.id = h.user.id")
	List<Units> getUnitSummary();

	@Query(value = "select u  from Units u where u.unitName=:unitName")
	Units findUnitByName(@Param("unitName")String unitName);

	@Query(value = "select u from Units u, EntitySummary e, Zone r where e.entityId = u.entitySummary.entityId and r.zoneId = u.regions.zoneId and u.unitName=:unitName")
	Units getUnitDtls(@Param("unitName")String unitName);

	@Query(value = "select u from Units as u, EntitySummary as e, Zone as r where e.entityId = u.entitySummary.entityId and  "
			+ " r.zoneId = u.regions.zoneId and r.zoneName=:zoneName and e.entityName=:entityName and "
			+ " u.unitName=:unitName")
	Units getUnitDtlsByEntityName(@Param("entityName") String entityName, @Param("zoneName") String zoneName,
			@Param("unitName") String unitName);

	@Query(value = "select u from Units as u join LitigationUnits as lu on u.unitId = lu.units.unitId join Litigation as lt on lt.litigationOId = lu.litigation.litigationOId join EntitySummary as e on e.entityId = u.entitySummary.entityId join Zone as r on r.zoneId = u.regions.zoneId where lt.litigationId =:litigationId")
	List<Units> findDtlsByLitigationId(@Param("litigationId")String litigationId);


}
