package com.nirvana.repository.backend.area;

import org.springframework.data.jpa.repository.Query;

import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.repository.NirvanaRepository;

public interface BigAreaRepository extends NirvanaRepository<BigArea, Integer> {

	@Query("select ba from BigArea ba where ba.areaCode=?1")
	public BigArea findByAreaCode(String areaCode);

}
