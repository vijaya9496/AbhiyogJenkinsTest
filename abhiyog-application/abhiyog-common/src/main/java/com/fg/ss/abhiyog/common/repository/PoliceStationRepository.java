package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.PoliceStation;

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation, Integer>{

	@Query(value="select ps from PoliceStation ps, City c where c.cityId=ps.city.cityId and c.cityName=:cityName")
	PoliceStation getPoliceDtlsByCityName(@Param("cityName")String cityName);

}
