package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.BillingType;

@Repository
public interface BillingTypeRepository extends JpaRepository<BillingType, Integer>{

	@Query(value="select bt from BillingType bt where bt.billingType=:billingType")
	BillingType findByBillingType(@Param("billingType")String billingType);

}
