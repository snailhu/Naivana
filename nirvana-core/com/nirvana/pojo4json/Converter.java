package com.nirvana.pojo4json;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;

public interface Converter<Domain, DTO> {

	public DTO convert(Domain domain);

	public List<DTO> convert(List<Domain> domains);

	public List<DTO> convert(Set<Domain> page);

	public DPage<DTO> convert(Page<Domain> page);

	public Map<String, List<DTO>> convert(Map<String, List<Domain>> map);

}
