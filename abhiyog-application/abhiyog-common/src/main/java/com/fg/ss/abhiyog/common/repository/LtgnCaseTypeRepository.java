package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.LtgnCaseType;

@Repository
public interface LtgnCaseTypeRepository extends JpaRepository<LtgnCaseType, Integer>{

	@Query(value="select ct from LtgnCaseType ct where ct.caseType = :categoryName")
	LtgnCaseType findByCaseType(@Param("categoryName")String categoryName);

}
