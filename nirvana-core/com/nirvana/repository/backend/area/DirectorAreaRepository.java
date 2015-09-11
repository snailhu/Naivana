package com.nirvana.repository.backend.area;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.repository.NirvanaRepository;

public interface DirectorAreaRepository extends NirvanaRepository<DirectorArea, Integer> {

	@Query("from DirectorArea da where da.managerArea.id=?")
	public List<DirectorArea> findByManagerAreaId(int areaId);

	@Query("select da from DirectorArea da where da.areaCode=?1")
	public DirectorArea findByAreaCode(String areaCode);

}
