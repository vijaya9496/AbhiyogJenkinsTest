package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.CourtType;

@Repository
public interface CourtTypeRepository extends JpaRepository<CourtType, Integer>{

	@Query(value="select ct from CourtType ct where ct.courtType=:courtTypeName")
	CourtType findByCourtType(@Param("courtTypeName")String courtTypeName);

}
