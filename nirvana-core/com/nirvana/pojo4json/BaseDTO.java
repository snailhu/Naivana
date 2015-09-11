package com.nirvana.pojo4json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;

/**
 * 所有pojo4json都应继承此虚类。
 * 
 * 用作将domain实体的一部分字段通过Json输出， 需实现transfer方法。
 * */
public abstract class BaseDTO<Domain> implements Converter<Domain, BaseDTO<Domain>> {

	@Override
	public abstract BaseDTO<Domain> convert(Domain domain);

	@Override
	public List<BaseDTO<Domain>> convert(List<Domain> domains) {
		if(domains==null)
			return null;
		List<BaseDTO<Domain>> dtos = new ArrayList<BaseDTO<Domain>>();
		for (Domain domain : domains) {
			dtos.add(this.convert(domain));
		}
		return dtos;
	}

	@Override
	public List<BaseDTO<Domain>> convert(Set<Domain> domains) {
		if(domains==null)
			return null;
		List<BaseDTO<Domain>> dtos = new ArrayList<BaseDTO<Domain>>();
		for (Domain domain : domains) {
			dtos.add(this.convert(domain));
		}
		return dtos;
	}

	@Override
	public DPage<BaseDTO<Domain>> convert(Page<Domain> page) {
		if(page==null)
			return null;
		DPage<BaseDTO<Domain>> dPage = new DPage<BaseDTO<Domain>>();
		dPage.setTotalElements(page.getTotalElements());
		dPage.setTotalPages(page.getTotalPages());
		dPage.setData(this.convert(page.getContent()));
		return dPage;
	}

	@Override
	public Map<String, List<BaseDTO<Domain>>> convert(Map<String, List<Domain>> map) {
		if(map==null)
			return null;
		Map<String, List<BaseDTO<Domain>>> map2 = new HashMap<String, List<BaseDTO<Domain>>>();
		for (String str : map.keySet()) {
			map2.put(str, this.convert(map.get(str)));
		}
		return map2;
	}

}
