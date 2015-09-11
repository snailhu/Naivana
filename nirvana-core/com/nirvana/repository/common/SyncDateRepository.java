package com.nirvana.repository.common;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.common.SyncDate;
import com.nirvana.domain.common.SyncType;

public interface SyncDateRepository extends JpaRepository<SyncDate, Integer> {

	@Query("from SyncDate s where s.type=?1")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public SyncDate findByTypePessimisticLocked(SyncType type);

	@Query("from SyncDate s where s.type=?1")
	public SyncDate findByType(SyncType type);

}
