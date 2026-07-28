package com.utility;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtils {

	public static String readProperty(String key) throws Exception {
		String path = System.getProperty("user.dir") + "/src/main/resources/config_preprod.properties";
		FileInputStream fis = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}	
	
	
	
}
