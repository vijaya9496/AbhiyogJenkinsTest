package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer>{

	@Query(value="select c from Claim c where c.claim=:possibleClaimRange")
	Claim findByClaim(@Param("possibleClaimRange")String possibleClaimRange);

}
