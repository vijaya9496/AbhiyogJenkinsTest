package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.LitigationMatterByAgainst;

@Repository
public interface LitigationMatterByAgainstRepository extends JpaRepository<LitigationMatterByAgainst, Integer>{

	@Query(value="select lma from LitigationMatterByAgainst as lma join Litigation as lt on lt.litigationOid = lma.litigation.litigationOid join LtgnRepresentativeMaster as lrm  on  lrm.representativeId = lma.ltgnRepresentativeMaster.representativeId where lrm.representativeName =:matterByAgainst and lt.litigationOid =:litigationId")
	LitigationMatterByAgainst findMatterByAgainst(@Param("matterByAgainst")String matterByAgainst, @Param("litigationId")int litigationId);

	@Query(value="select lma from LitigationMatterByAgainst as lma join Litigation as lt on lt.litigationOid = lma.litigation.litigationOid join LtgnRepresentativeMaster as lrm  on  lrm.representativeId = lma.ltgnRepresentativeMaster.representativeId where lt.litigationOid =:litigationId")
	List<LitigationMatterByAgainst> findMatterByAgainstByLitigationId(@Param("litigationId")int litigationId);

	@Modifying
	@Transactional
	@Query(value="delete lma from litigation_matterbyagainst lma inner join litigationrepresentativemaster lrm  on lrm.representativeoid = lma.matterbyagainstoid where lrm.representativename=:representName",nativeQuery=true )
	int deleteMappedRepresentativeName(@Param("representName")String representName);


}
