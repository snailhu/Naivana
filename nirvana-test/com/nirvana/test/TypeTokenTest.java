package com.nirvana.test;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.nirvana.pojo4json.json.SimpleParser;
import com.nirvana.pojo4json.json.SimpleRenderer;
import com.nirvana.service.pojo.InventoryCheck;

public class TypeTokenTest {

	public static void main(String[] args) {

		SimpleRenderer renderer = new SimpleRenderer();
		SimpleParser parser = new SimpleParser();

		String str = "[{\"productCode\":\"1201\",\"price\":\"12\",\"amounts\":\"95\"}]";

		System.out.println(str);

		List<InventoryCheck> checks1 = parser.parseJSON(str, new TypeToken<List<InventoryCheck>>() {});

		System.out.println(checks1.get(0).getProductCode());
		System.out.println(checks1.get(0).getAmounts());
		System.out.println(checks1.get(0).getPrice());

	}
}
