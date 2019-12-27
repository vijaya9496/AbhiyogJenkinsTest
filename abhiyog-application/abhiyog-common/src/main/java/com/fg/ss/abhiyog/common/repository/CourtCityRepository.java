package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.CourtCity;

@Repository
public interface CourtCityRepository extends JpaRepository<CourtCity, Integer>{

	@Query(value="select cc from CourtCity cc where cc.courtCity=:courtForum")
	CourtCity findByCourtCity(@Param("courtForum")String courtForum);

}
