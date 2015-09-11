package com.nirvana.repository.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.AgentDealerCategory;

public interface AgentDealerCategoryRepository extends JpaRepository<AgentDealerCategory, Long> {

	@Query("from AgentDealerCategory dc where dc.area.id=?1 and dc.categoryName = ?2")
	public AgentDealerCategory findByAgentAreaIdAndCategoryName(int areaId, String category);

}
