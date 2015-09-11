package com.nirvana.repository.store;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nirvana.domain.store.StoreOrderItem;
import com.nirvana.domain.store.StoreOrderItemPK;

public interface StoreOrderItemRepository extends JpaRepository<StoreOrderItem, StoreOrderItemPK> {

}
