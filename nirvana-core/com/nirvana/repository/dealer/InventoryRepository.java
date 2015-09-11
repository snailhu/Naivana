package com.nirvana.repository.dealer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.InventPK;
import com.nirvana.domain.dealer.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, InventPK> {

	public Page<Inventory> findByPkDealerId(long dealerId, Pageable page);

	public Page<Inventory> findByPkDealerIdAndPkProductBrandName(long dealerId, String name, Pageable page);

	public Inventory findByPkDealerIdAndPkProductCode(long dealerId, String productCode);

	public Page<Inventory> findByPkDealerAndPkProductBrandName(Dealer dealer, String name, Pageable page);

	public Page<Inventory> findByPkDealer(Dealer dealer, Pageable page);

	public Inventory findByPkDealerAndPkProductCode(Dealer dealer, String code);

	@Modifying
	@Query("update Inventory set salesVol=salesVol+?1 where pk=?2")
	public void updateSalesVol(int amount, InventPK pk);

	@Modifying
	@Query("update Inventory set amounts=amounts+?1 where pk=?2")
	public void updateAmount(int amount, InventPK pk);
}
