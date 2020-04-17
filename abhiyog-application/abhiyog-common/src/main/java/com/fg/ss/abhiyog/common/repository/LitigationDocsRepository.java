package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.LitigationDocs;

@Repository
public interface LitigationDocsRepository extends JpaRepository<LitigationDocs, Integer>{

	@Query(value="select ld from LitigationDocs as ld join Litigation as lt on lt.litigationOid = ld.litigation.litigationOid where ld.litigation.litigationOid =:id")
	List<LitigationDocs> getDocumentSummary(@Param("id")int id);

	@Modifying
	@Transactional
	@Query(value="delete ld from litigationdocs as ld join ltgn_litigation as lt on ld.litigationoid = lt.litigation_oid where ld.litigationdocoid=:id", nativeQuery=true)
	int deleteDocById(@Param("id")int id);

}
