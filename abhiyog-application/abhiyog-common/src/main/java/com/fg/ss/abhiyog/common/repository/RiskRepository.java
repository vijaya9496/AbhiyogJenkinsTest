package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.Risk;

@Repository
public interface RiskRepository extends JpaRepository<Risk, Integer>{

	@Query(value="select r from Risk r where r.risk=:riskAssesment")
	Risk findByRisk(@Param("riskAssesment")String riskAssesment);

}
