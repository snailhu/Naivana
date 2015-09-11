package com.nirvana.repository.dealer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.dealer.DealerSerialNumber;
import com.nirvana.repository.NirvanaRepository;

public interface DealerSerialNumberRepository extends NirvanaRepository<DealerSerialNumber, Long> {

	@Query("update DealerSerialNumber dsn set dsn.serialNum=dsn.serialNum+1 where dsn.id=?1")
	@Modifying
	public int updateSerialNum(Long id);

}
