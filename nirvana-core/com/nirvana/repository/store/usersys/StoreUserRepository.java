package com.nirvana.repository.store.usersys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.store.usersys.StoreUser;

public interface StoreUserRepository extends JpaRepository<StoreUser, Long> {

	@Query("select su from StoreUser su left join su.store ss where ss.id=?")
	public StoreUser getStoreUserByStoreId(Long id);

	@Query("select su from StoreUser su where su.phone=?1")
	public StoreUser findByBindNum(String bindNum);

	@Query("select su from StoreUser su where su.username=?1")
	public StoreUser findByUserName(String username);
	
}
