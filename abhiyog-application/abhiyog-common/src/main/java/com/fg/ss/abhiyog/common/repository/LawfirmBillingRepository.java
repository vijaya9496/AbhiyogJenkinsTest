package com.fg.ss.abhiyog.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.LawfirmBilling;

@Repository
public interface LawfirmBillingRepository extends JpaRepository<LawfirmBilling, Integer>{

	@Query(value="select lb from LawfirmBilling lb,BillingType bt,Litigation lt where bt.billingTypeId = lb.billingType.billingTypeId and lt.litigationId =:litigationId")
	List<LawfirmBilling> findByLitigationId(@Param("litigationId")String litigationId);
	
	@Query(value="select lb from LawfirmBilling as lb join BillingType as bt on lb.billingType.billingTypeId = bt.billingTypeId join Litigation as lt on lt.litigationOid = lb.litigation.litigationOid where lb.litigation.litigationOid =:id")
	List<LawfirmBilling> getLawfirmBillingDtls(@Param("id")int id);

}
