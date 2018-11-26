package com.wangtiansoft.KingDarts.common.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesUtils {

	
	private static Properties props = null;
	
	public static String getProperty(String key){
		try {
			if(props == null){
				props = PropertiesLoaderUtils.loadAllProperties("system.properties");
			}
			return props.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getProperty(String file,String key){
		try {
			Properties properties = PropertiesLoaderUtils.loadAllProperties(file+".properties");
			return properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static Properties getPro(String file){
		try {
			return PropertiesLoaderUtils.loadAllProperties(file+".properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
