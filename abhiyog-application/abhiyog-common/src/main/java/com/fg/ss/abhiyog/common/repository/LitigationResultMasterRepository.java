package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.LitigationResultMaster;

@Repository
public interface LitigationResultMasterRepository extends JpaRepository<LitigationResultMaster, Integer>{

	@Query(value="select r from LitigationResultMaster r where r.result=:resultName")
	LitigationResultMaster findByResultName(@Param("resultName")String resultName);

}
