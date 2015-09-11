package com.nirvana.repository.backend.statistics;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.statistics.MonthAgentStatistics;

public interface MonthAgentStatisticsRepository extends JpaRepository<MonthAgentStatistics, Long> {

	@Query("select mas from MonthAgentStatistics mas  where mas.area.id=?1 and mas.date>=?2 and mas.date<=?3 order by mas.date")
	public List<MonthAgentStatistics> findByAgentAreaIdAndMonthDate(Integer agentAreaId, Integer startDate, Integer endDate);

}
