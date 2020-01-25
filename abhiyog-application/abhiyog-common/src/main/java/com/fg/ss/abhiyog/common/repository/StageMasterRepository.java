package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.StageMaster;

@Repository
public interface StageMasterRepository extends JpaRepository<StageMaster, Integer>{

	@Query(value="select sm from StageMaster sm where sm.stage=:stage")
	StageMaster findByStageName(@Param("stage")String stage);

}
