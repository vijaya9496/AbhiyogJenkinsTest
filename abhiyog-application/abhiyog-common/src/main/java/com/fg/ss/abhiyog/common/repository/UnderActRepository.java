package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.UnderAct;

@Repository
public interface UnderActRepository extends JpaRepository<UnderAct, Integer>{

	@Query(value="select ua from UnderAct ua where ua.underAct=:underActName")
	UnderAct findByUnderAct(@Param("underActName")String underActName);

	
}
