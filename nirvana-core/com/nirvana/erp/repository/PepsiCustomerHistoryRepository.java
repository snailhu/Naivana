package com.nirvana.erp.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.erp.domain.PepsiCustomerHistory;
import com.nirvana.erp.domain.PepsiCustomerHistoryId;

public interface PepsiCustomerHistoryRepository extends JpaRepository<PepsiCustomerHistory, PepsiCustomerHistoryId> {

	@Query("from PepsiCustomerHistory nt where nt.id.updateDate>?1 and nt.id.updateDate<=?2")
	public List<PepsiCustomerHistory> findByDateToDate(Timestamp last, Timestamp now);

	@Query("select max(p.id.updateDate) from PepsiCustomerHistory p")
	public Timestamp findMaxTime();

}
