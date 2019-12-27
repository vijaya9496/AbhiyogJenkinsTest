package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

	@Query(value="select s from State s where s.stateName=:stateName")
	State findByStateName(@Param("stateName")String stateName);

	
}
