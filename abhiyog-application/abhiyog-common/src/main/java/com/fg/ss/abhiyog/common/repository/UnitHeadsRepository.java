package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.UnitHeads;



@Repository
public interface UnitHeadsRepository extends JpaRepository<UnitHeads, Integer>{

	@Modifying
	@Transactional
	@Query(value="insert into unitheads (user_id,unit_id) values (?1, ?2)", nativeQuery=true)
	int saveUnitHeadData(int user_id, int unitId);

	@Query(value="select h from UnitHeads h where h.user.id=:id and h.units.unitId=:unitId")
	UnitHeads getDtlsByUserID(@Param("id")int id, @Param("unitId")int unitId);

	@Query(value="select h from UnitHeads h, User t Where t.id=h.user.id and h.units.unitId=:unitId")
	List<UnitHeads> getDtlsByUnitID(@Param("unitId")int unitId);

	@Modifying
	@Transactional
	@Query(value="delete h from unitheads h inner join ltgn_user u on u.user_id=h.user_id where u.first_name=:firstName and u.last_name=:lastName",nativeQuery=true)
	int deleteUserId(@Param("firstName")String firstName, @Param("lastName") String lastName);

}
