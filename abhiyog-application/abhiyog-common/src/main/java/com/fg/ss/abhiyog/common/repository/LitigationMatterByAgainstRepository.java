package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.LitigationMatterByAgainst;

@Repository
public interface LitigationMatterByAgainstRepository extends JpaRepository<LitigationMatterByAgainst, Integer>{

	@Query(value="select lma from LitigationMatterByAgainst as lma join Litigation as lt on lt.litigationOId = lma.litigation.litigationOId join LtgnRepresentativeMaster as lrm  on  lrm.representativeId = lma.ltgnRepresentativeMaster.representativeId where lrm.representativeName =:matterByAgainst and lt.litigationId =:litigationId")
	LitigationMatterByAgainst findMatterByAgainst(@Param("matterByAgainst")String matterByAgainst, @Param("litigationId")String litigationId);

	@Query(value="select lma from LitigationMatterByAgainst as lma join Litigation as lt on lt.litigationOId = lma.litigation.litigationOId join LtgnRepresentativeMaster as lrm  on  lrm.representativeId = lma.ltgnRepresentativeMaster.representativeId where lt.litigationId =:litigationId")
	List<LitigationMatterByAgainst> findMatterByAgainstByLitigationId(@Param("litigationId")String litigationId);

	@Query(value="delete lma from litigation_matterbyagainst lma inner join litigationrepresentativemaster lrm  on lrm.representative_id = lma.representative_id where lrm.representative_name=:representName",nativeQuery=true )
	int deleteMappedRepresentativeName(@Param("representName")String representName);


}
