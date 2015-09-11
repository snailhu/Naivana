package com.nirvana.repository.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.AgentStoreCategory;

public interface AgentStoreCategoryRepository extends JpaRepository<AgentStoreCategory, Long> {

	@Query("from AgentStoreCategory sc where sc.area.id=?1 and sc.categoryName = ?2")
	public AgentStoreCategory findByAgentAreaIdAndCategoryName(int areaId, String category);

}
