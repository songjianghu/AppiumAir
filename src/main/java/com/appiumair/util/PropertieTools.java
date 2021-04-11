package com.appiumair.util;

/**
 * @module 配置文件对应的实体类
 */
public class PropertieTools {

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

	public static String getReportName(){
		String reportName = readProperties.getValue("reportName");
		return reportName;
	}

	public static String getReportUrl(){
		String reportUrl = readProperties.getValue("reportUrl");
		return reportUrl;
	}

	public static String getImgUrl(){
		String imgUrl = readProperties.getValue("imgUrl");
		return imgUrl;
	}

	public static String getVideoUrl(){
		String videoUrl = readProperties.getValue("videoUrl");
		return videoUrl;
	}

	public static String getReportTempUrl(){
		String reportTempUrl = readProperties.getValue("reportTempUrl");
		return reportTempUrl;
	}

}