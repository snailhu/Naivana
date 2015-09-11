package com.nirvana.mongo.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractConverter<S, T> implements Converter<S, T> {

	@Override
	public List<T> convert(Collection<S> sources) {
		List<T> list = new ArrayList<T>();
		for (S source : sources) {
			list.add(convert(source));
		}
		return list;
	}
}
