package com.appiumair.util;

/**
 * @module 配置文件对应的实体类
 */
public class PropertieTools {

	public static void main(String[] args) {
		String appActivity = PropertieTools.getAppActivity();
		System.out.println(appActivity);
	}

	static ReadProperties readProperties = new ReadProperties("config/config.properties");
	
	public static String getAppPackage(){
		String appPackage = readProperties.getValue("appPackage");
		return appPackage;
	}
	
	public static String getAppActivity(){
		String appActivity = readProperties.getValue("appActivity");
		return appActivity;
	}
	
	public static String getAppiumClientUrl(){
		String appiumClientUrl = readProperties.getValue("appiumClientUrl");
		return appiumClientUrl;
	}
	
	public static String getAppiumServerIpAddress(){
		String appiumServerIpAddress = readProperties.getValue("appiumServerIpAddress");
		return appiumServerIpAddress;
	}
	
}