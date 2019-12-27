package com.fg.ss.abhiyog.common.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.EntitySummary;

@Repository
public interface EntityRepository extends JpaRepository<EntitySummary, Integer> {

	@Query(value = "select e from EntitySummary e where e.entityName=:entityName")
	EntitySummary getEntityByName(@Param("entityName")String entityName);

	@Modifying
	@Transactional
	@Query(value = "update EntitySummary e set e.entityName=:updatedEntityName where e.entityName=:entityName")
	int update(@Param("entityName")String entityName, @Param("updatedEntityName")String updatedEntityName);

}
