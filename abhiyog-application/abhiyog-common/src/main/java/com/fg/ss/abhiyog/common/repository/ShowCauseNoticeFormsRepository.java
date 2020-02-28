package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.ShowCauseNotice;
import com.fg.ss.abhiyog.common.model.ShowCauseNoticeForms;



@Repository
public interface ShowCauseNoticeFormsRepository extends JpaRepository<ShowCauseNoticeForms, Integer>{

	@Query(value="Select snf from ShowCauseNoticeForms snf join ShowCauseNotice scn on scn.showCauseNoticeId = snf.showCauseNotice.showCauseNoticeId where scn.showCauseNoticeId=:id")
	List<ShowCauseNoticeForms> findDocsBy(@Param("id")int id);

	@Query(value="Select snf from ShowCauseNoticeForms snf join ShowCauseNotice scn on scn.showCauseNoticeId = snf.showCauseNotice.showCauseNoticeId where snf.showCauseNoticeFormsId=:id")
	List<ShowCauseNoticeForms> findByNoticeFormId(@Param("id")int id);

	@Modifying
	@Transactional
	@Query(value="delete snf from showcausenoticeforms snf join showcausenotice scn on scn.showcausenoticeoid = snf.showcausenoticeoid where snf.showcausenoticeformsoid=:id", nativeQuery =true)
	int deleteDocById(@Param("id")int id);
	
	

}
