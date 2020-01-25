package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.TblMailLog;

@Repository
public interface TblMailLogRepository extends JpaRepository<TblMailLog, Integer>{

	@Query(value = "select ml from TblMailLog ml order by ml.id desc")
	List<TblMailLog> findAllDesc();

}
