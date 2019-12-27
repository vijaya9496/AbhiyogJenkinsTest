package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.LtgnRepresentativeMaster;

@Repository
public interface LtgnRepresentativeMasterRepository extends JpaRepository<LtgnRepresentativeMaster, Integer>{

	@Query(value="select rm from LtgnRepresentativeMaster rm where rm.representativeName=:matterBy")
	LtgnRepresentativeMaster findByRepresentativeName(@Param("matterBy")String matterBy);

}
