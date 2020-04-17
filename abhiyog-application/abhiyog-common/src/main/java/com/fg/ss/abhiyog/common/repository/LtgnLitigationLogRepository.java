package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.LtgnLitigationLog;

@Repository
public interface LtgnLitigationLogRepository extends JpaRepository<LtgnLitigationLog, Integer>{

	@Query(value="select ltLog from LtgnLitigationLog as ltLog join Litigation lt on lt.litigationOid = ltLog.litigation.litigationOid join User as user on user.id = ltLog.user.id where lt.litigationOid =:id")
	List<LtgnLitigationLog> getHistorySummary(@Param("id")int id);

	@Query(value="select ltLog from LtgnLitigationLog ltLog join Litigation lt on lt.litigationOid = ltLog.litigation.litigationOid where lt.litigationId=:litigationId")
	LtgnLitigationLog findByLitigationId(@Param("litigationId")String litigationId);

	
	/*@Query(value="select lt from Litigation as lt join LtgnLitigationLog ltlog on lt.litigationOid = ltlog.litigation.litigationOid where lt.litigationOid = :id")
	List<LtgnLitigationLog> getHistorySummary(@Param("id")int id);*/
	
}
