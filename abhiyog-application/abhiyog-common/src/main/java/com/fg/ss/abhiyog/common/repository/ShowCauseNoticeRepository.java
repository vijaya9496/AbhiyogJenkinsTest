package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.ShowCauseNotice;



@Repository
public interface ShowCauseNoticeRepository extends JpaRepository<ShowCauseNotice, Integer>{

//	@Query(value="select scn from ShowCauseNotice scn, ShowCauseNoticeForms scf, Units u, Format f, User t where scn.showCauseNoticeId = scf.showCauseNotice.showCauseNoticeId and u.unitId = scn.units.unitId and f.formatId = scn.format.formatId and t.id=scn.user.id")
	@Query(value="select scn from ShowCauseNotice as scn join ShowCauseNoticeForms as scf on scn.showCauseNoticeId = scf.showCauseNotice.showCauseNoticeId join Units as u on u.unitId = scn.units.unitId join Zone as r on u.regions.zoneId = r.zoneId join Format as f on f.formatId=scn.format.formatId join User as t on t.id = scn.user.id")
	List<ShowCauseNotice> getAllNoticeDtls();
	
	@Query(value="select scn from ShowCauseNotice scn where scn.showCauseNoticeId=:id")
	List<ShowCauseNotice> findByID(@Param("id")int id);

	@Query(value="Select scn from ShowCauseNotice scn, ShowCauseNoticeForms scf where scn.showCauseNoticeId = scf.showCauseNotice.showCauseNoticeId and scn.showCauseNoticeId =:id ")
	List<ShowCauseNotice> findNoticeDtls(@Param("id")int id);

	

}
