package com.nirvana.repository.dealer.usersys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.dealer.usersys.DealerUser;

public interface DealerUserRepository extends JpaRepository<DealerUser, Long> {

	@Query("select du from DealerUser du where du.phone=?1")
	public DealerUser findByBindNum(String bindNum);

	@Query("select du from DealerUser du where du.username=?1")
	public DealerUser findByUsername(String username);



}
