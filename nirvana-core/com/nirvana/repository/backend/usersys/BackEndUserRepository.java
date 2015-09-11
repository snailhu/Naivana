package com.nirvana.repository.backend.usersys;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.usersys.BackEndUser;
import com.nirvana.repository.NirvanaRepository;

public interface BackEndUserRepository extends NirvanaRepository<BackEndUser, Long> {

	@Query("from BackEndUser bn where bn.accountNonLocked=false")
	public List<BackEndUser> getAllBackEndUser();

	@Query("select bu from BackEndUser bu where bu.username=?1")
	public BackEndUser findByUsername(String username);

	/** Ð´Ëø¡£ */
	@Query("select bu from BackEndUser bu where bu.username=?1")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public BackEndUser findByUsernameLocked(String username);

	@Query("select bu from BackEndUser bu where bu.phone=?1")
	public BackEndUser findByBindNum(String bindNum);

}
