package com.appiumair.bean;

/**
 * 启动Appium所需要的参数
 * 所有启动需要的参数全部封装在这里
 */
public class AppiumParamter {
    private String udid;
    private String platformVersion;
    private String appPackage;
    private String appActivity;
    private String deviceName;
    private Integer port;

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public AppiumParamter() {
    }

    public AppiumParamter(String udid, String platformVersion, String appPackage, String appActivity, String deviceName, Integer port) {
        this.udid = udid;
        this.platformVersion = platformVersion;
        this.appPackage = appPackage;
        this.appActivity = appActivity;
        this.deviceName = deviceName;
        this.port = port;
    }
}
