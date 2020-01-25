package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.LtgnLitigationLog;

@Repository
public interface LtgnLitigationLogRepository extends JpaRepository<LtgnLitigationLog, Integer>{

	@Query(value="select ltLog from LtgnLitigationLog as ltLog join Litigation lt on lt.litigationOId = ltLog.litigation.litigationOId join User as user on user.id = ltLog.user.id where lt.litigationId =:litigationId")
	List<LtgnLitigationLog> getHistoryDtls(@Param("litigationId")String litigationId);

	@Query(value="select ltLog from LtgnLitigationLog ltLog join Litigation lt on lt.litigationOId = ltLog.litigation.litigationOId where lt.litigationId=:litigationId")
	LtgnLitigationLog findByLitigationId(@Param("litigationId")String litigationId);
	
}
