package com.nirvana.repository.backend.area;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.repository.NirvanaRepository;

public interface AgentAreaRepository extends NirvanaRepository<AgentArea, Integer> {

	@Query("from AgentArea aa where aa.directorArea.id=?1")
	public List<AgentArea> findByDirectorAreaId(int areaId);

	@Query("from AgentArea aa where aa.directorArea.managerArea.id=?1")
	public Page<AgentArea> findByManagerAreaId(int areaId, Pageable pageable);

	@Query("select aa from AgentArea aa where aa.areaCode=?1")
	public AgentArea findByAreaCode(String areaCode);

	@Query("from AgentArea aa where aa.directorArea.id=?1")
	public Page<AgentArea> findPageByDirectorAreaId(Integer dirAreaId, Pageable pageable);

	@Query("from AgentArea aa where aa.directorArea.managerArea.bigarea.id=?1")
	public Page<AgentArea> findPageByBigAreaId(Integer bigAreaId, Pageable pageable);

}
