package com.nirvana.erp.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.erp.domain.PepsiOrderHistory;
import com.nirvana.erp.domain.PepsiOrderHistoryId;

public interface PepsiOrderHistoryRepository extends JpaRepository<PepsiOrderHistory, PepsiOrderHistoryId> {

	@Query("from PepsiOrderHistory p where p.id.updateDate>?1 and p.id.updateDate<=?2")
	public List<PepsiOrderHistory> findByDateToDate(Timestamp last, Timestamp now);

	@Query("select max(p.id.updateDate) from PepsiOrderHistory p")
	public Timestamp findMaxTime();
}
