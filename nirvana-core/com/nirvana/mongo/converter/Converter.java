package com.nirvana.mongo.converter;

import java.util.Collection;
import java.util.List;

public interface Converter<S, T> {

	public T convert(S source);

	public List<T> convert(Collection<S> sources);

}
