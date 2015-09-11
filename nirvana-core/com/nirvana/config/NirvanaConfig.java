package com.nirvana.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class NirvanaConfig {

	private static Properties prop = null;

	static {
		prop = new Properties();

		InputStream fis;
		try {
			fis = NirvanaConfig.class.getClassLoader().getResourceAsStream("nirvana.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}
