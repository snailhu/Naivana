package com.nirvana.repository.dealer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nirvana.domain.dealer.CheckHistoryItem;

public interface CheckHistoryItemRepository extends JpaRepository<CheckHistoryItem, Long> {

}
