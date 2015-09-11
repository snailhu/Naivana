package com.nirvana.pojo4json.json;

import com.google.gson.reflect.TypeToken;

public interface Parser {

	public <T> T parseJSON(String jsonString, Class<T> beanClass);

	public <T> T parseJSON(String jsonString, TypeToken<T> token);

}
