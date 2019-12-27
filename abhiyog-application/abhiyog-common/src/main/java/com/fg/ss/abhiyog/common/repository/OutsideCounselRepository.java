package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.LawFirm;



@Repository
public interface OutsideCounselRepository extends JpaRepository<LawFirm, Integer>{

	@Query(value="select l from LawFirm l where l.lawfirm=:lawfirm")
	LawFirm findByLawfirm(@Param("lawfirm")String lawfirm);

	
}
