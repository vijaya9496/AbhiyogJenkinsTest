package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.Litigation;

@Repository
public interface LitigationRepository extends JpaRepository<Litigation, Integer>{

	@Query(value="select * from ltgn_litigation l order by l.litigation_id desc limit 1", nativeQuery=true)
	Litigation getLitigationId();

}
