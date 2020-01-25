package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.Witness;

@Repository
public interface WitnessRepository extends JpaRepository<Witness, Integer>{

	@Query(value="select w from Witness as w join Litigation as lt on w.litigation.litigationOId = lt.litigationOId where lt.litigationId=:litigationId")
	List<Witness> findAllByLitigationId(@Param("litigationId")String litigationId);

}
