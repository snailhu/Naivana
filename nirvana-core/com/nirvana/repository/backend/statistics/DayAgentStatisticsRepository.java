package com.nirvana.repository.backend.statistics;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.statistics.DayAgentStatistics;

public interface DayAgentStatisticsRepository extends JpaRepository<DayAgentStatistics, Integer> {

	@Query("select das from DayAgentStatistics das left join das.area da where da.id=?1 and das.date>=?2 and das.date<=?3 order by das.date")
	public List<DayAgentStatistics> fingByAreaIdAndDate(Integer areaId, Integer startDate, Integer endDate);

	@Query("select das from DayAgentStatistics das  where das.area.id=?1 and das.date>=?2 and das.date<=?3 order by das.date")
	public List<DayAgentStatistics> findByAgentAreaIdAndDayDate(Integer agentAreaId, Integer startDate, Integer endDate);

	public DayAgentStatistics findByAreaAndDate(AgentArea area,Integer date);
	
	public int countByDate(Integer date);
}
