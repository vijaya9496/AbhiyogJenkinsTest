package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.CustomerType;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerType, Integer>{

	@Query(value="select ct from CustomerType ct where ct.customerType=:customerType")
	CustomerType findByCustomerType(@Param("customerType")String customerType);

	
}
