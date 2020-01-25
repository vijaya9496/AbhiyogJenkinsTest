package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.LitigationUnits;

@Repository
public interface LitigationUnitsRepository extends JpaRepository<LitigationUnits, Integer>{

	@Query(value="select lu from LitigationUnits as lu join Litigation as lt on lt.litigationOId= lu.litigation.litigationOId where lt.litigationId=:litigationId")
	LitigationUnits findUnitDetails(@Param("litigationId")String litigationId);

}
