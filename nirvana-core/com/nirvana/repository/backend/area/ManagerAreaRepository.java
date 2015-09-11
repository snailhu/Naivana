package com.nirvana.repository.backend.area;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.repository.NirvanaRepository;

public interface ManagerAreaRepository extends NirvanaRepository<ManagerArea, Integer> {

	@Query("from ManagerArea ma where ma.bigarea.id=?")
	public List<ManagerArea> findByBigAreaId(int areaId);

	@Query("select ma from ManagerArea ma where ma.areaCode=?1")
	public ManagerArea findByAreaCode(String areaCode);
}
