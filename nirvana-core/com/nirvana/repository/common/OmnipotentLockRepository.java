package com.nirvana.repository.common;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.common.LockType;
import com.nirvana.domain.common.OmnipotentLock;
import com.nirvana.repository.NirvanaRepository;

public interface OmnipotentLockRepository extends NirvanaRepository<OmnipotentLock, Integer> {

	@Query("from OmnipotentLock o where o.type=?1")
	public OmnipotentLock findByLockType(LockType type);

	@Query("from OmnipotentLock o where o.type=?1")
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	public OmnipotentLock lock(LockType type);

}
