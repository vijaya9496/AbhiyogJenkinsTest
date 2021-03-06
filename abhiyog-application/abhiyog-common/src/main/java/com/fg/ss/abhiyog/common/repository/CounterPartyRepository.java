package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.CounterPartyDtls;



@Repository
public interface CounterPartyRepository extends JpaRepository<CounterPartyDtls, Integer>{

	@Query(value="select c from CounterPartyDtls c where c.customerName=:counterPartyName")
	CounterPartyDtls findByCustomerName(@Param("counterPartyName")String counterPartyName);

	

}
