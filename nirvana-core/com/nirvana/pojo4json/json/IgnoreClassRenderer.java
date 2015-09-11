package com.nirvana.pojo4json.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class IgnoreClassRenderer extends SimpleRenderer {

	public IgnoreClassRenderer() {
		Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new TypeClassAdapter()).create();
		setGson(gson);
	}

}
