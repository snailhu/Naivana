package com.nirvana.pojo4json.json;

import com.google.gson.Gson;

public class SimpleRenderer implements Renderer {

	private Gson gson = new Gson();

	@Override
	public final String render(Object object) {
		String string = gson.toJson(object);
		return string;
	}

	@Override
	public final <T> String render(Object object, Class<T> t) {
		String string = gson.toJson(object, t);
		return string;
	}

	protected void setGson(Gson gson) {
		this.gson = gson;
	};

}
