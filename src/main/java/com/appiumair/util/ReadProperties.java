package com.appiumair.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @module 读取properties配置文件
 */
public class ReadProperties {
	
	private Properties propertie = new Properties();
	
	public ReadProperties(String path){
		InputStream inputStream = ReadProperties.class.getClassLoader().getResourceAsStream(path);
        try {
        	propertie.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getValue(String key) {
		if (propertie.containsKey(key)) {
			String value = propertie.getProperty(key);
			return value;
		} else {
			return "";
		}
	}
}
