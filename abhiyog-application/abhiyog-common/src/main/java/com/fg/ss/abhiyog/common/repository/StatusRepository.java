package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>{

	@Query(value="select s from Status s where s.status=:status")
	Status findByStatusName(@Param("status")String status);

	
}
