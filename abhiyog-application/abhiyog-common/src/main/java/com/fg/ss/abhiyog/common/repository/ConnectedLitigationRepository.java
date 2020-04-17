package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.ConnectedLitigation;

@Repository
public interface ConnectedLitigationRepository extends JpaRepository<ConnectedLitigation, Integer>{

	@Query(value="select cl from ConnectedLitigation as cl inner join Litigation as lt on lt.litigationOid = cl.litigation.litigationOid where cl.litigation.litigationOid =:id")
	List<ConnectedLitigation> getConnectedLitigationDtls(@Param("id") int id);
}
