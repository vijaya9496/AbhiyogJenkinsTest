package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.LitigationHistoryTLog;

@Repository
public interface LitigationHistoryTLogRepository extends JpaRepository<LitigationHistoryTLog, Integer>{

	@Query(value="select ltHLog from LitigationHistoryTLog ltHLog join LtgnLitigationLog ltLog on ltLog.litigationLogId =ltHLog.ltgnLitigationLog.litigationLogId join Litigation lt on lt.litigationOid =  ltLog.litigation.litigationOid where lt.litigationOid=:litigationOId")
	List<LitigationHistoryTLog> findActivityLogByLitigationId(@Param("litigationOId")int litigationOId);

}
