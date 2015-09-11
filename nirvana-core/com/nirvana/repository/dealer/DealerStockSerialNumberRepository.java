package com.nirvana.repository.dealer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.dealer.DealerStockSerialNumber;
import com.nirvana.repository.NirvanaRepository;

public interface DealerStockSerialNumberRepository extends NirvanaRepository<DealerStockSerialNumber, Long> {

	@Query("update DealerStockSerialNumber dssn set dssn.serialNum=dssn.serialNum+1 where dssn.id=?1")
	@Modifying
	public int updateSerialNum(Long id);

}
