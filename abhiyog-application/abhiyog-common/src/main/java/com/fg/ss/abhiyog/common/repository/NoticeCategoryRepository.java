package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.NoticeCategory;



@Repository
public interface NoticeCategoryRepository extends JpaRepository<NoticeCategory, Integer>{

	@Query(value="select ct from NoticeCategory ct where ct.noticeCategory=:noticeCategoryName")
	NoticeCategory findByCategoryName(@Param("noticeCategoryName")String noticeCategoryName);

}
