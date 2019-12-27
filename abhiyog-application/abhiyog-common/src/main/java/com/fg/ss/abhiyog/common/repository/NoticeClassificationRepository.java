package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.NoticeClassification;



@Repository
public interface NoticeClassificationRepository extends JpaRepository<NoticeClassification, Integer>{

	@Query(value="select cs from NoticeClassification cs where cs.noticeClassification=:noticeClassification")
	NoticeClassification findByClassificationName(@Param("noticeClassification")String noticeClassification);

}
