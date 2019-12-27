package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.Zone;



@Repository
public interface ZonesRespository extends JpaRepository<Zone, Integer>{

	@Query(value="select z from Zone z where z.zoneName=:zoneName")
	Zone findByZoneName(@Param("zoneName")String zoneName);

}
