package com.nirvana.pojo4json.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SimpleParser implements Parser {

	private Gson gson = new Gson();

	@Override
	public <T> T parseJSON(String jsonString, Class<T> beanClass) {
		T obj = gson.fromJson(jsonString, beanClass);
		return obj;
	}

	@Override
	public <T> T parseJSON(String jsonString, TypeToken<T> token) {
		T obj = gson.fromJson(jsonString, token.getType());
		return obj;
	}

	protected void setGson(Gson gson) {
		this.gson = gson;
	};
}
