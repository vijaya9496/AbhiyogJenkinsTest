package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

	@Query(value="select c from City c where c.cityName=:cityName")
	City findByCityName(@Param("cityName")String cityName);

	@Query(value="select c from City c, State s where c.cityName=:cityName and s.stateName=:stateName")
	City findByCityStateName(@Param("cityName")String cityName, @Param("stateName")String stateName);

}
