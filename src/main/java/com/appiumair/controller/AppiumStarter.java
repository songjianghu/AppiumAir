package com.appiumair.controller;

import com.appiumair.util.PropertieTools;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Appium启动工具类
 */
public class AppiumStarter {

	/**
	 * @module 启动Appium
	 */
	public static AndroidDriver appiumClientStarter(String udid, String deviceName, String platformVersion, String appPackage, String appActivity, int port) throws MalformedURLException {

		// 1.添加配置，创建DesiredCapabilities对象
		DesiredCapabilities dc = new DesiredCapabilities();
		// 指定测试设备的名称
		dc.setCapability("deviceName", deviceName);
		// 屏蔽权限
		dc.setCapability("autoGrantPermissions", true);
		// 设置自动化程序名称
		dc.setCapability("automationName", "Appium");
		// 设置uuid
		dc.setCapability("udid", udid);
		// 添加操作系统配置
		dc.setCapability("platformName", "Android");

		dc.setCapability("newCommandTimeout", 18000);
		// 自动化引擎
		dc.setCapability("automationName", "uiautomator2");
		// 不清理应用的数据，默认是清理的,不要在会话前重置应用状态
		dc.setCapability("noReset", true);
		// Android是否删除应用，IOS是否删除整个模拟器目录
		dc.setCapability("fullReset", false);
		// session覆盖
		dc.setCapability("sessionOverride", true);
		// 是否启动Unicode输入法
		//dc.setCapability("unicodeKeyboard", true);
		// 结束后是否切换回默认输入法
		//dc.setCapability("resetKeyboard", true);
		// 添加操作系统版本设置
		dc.setCapability("platformVersion", platformVersion);
		// 指定想要测试应用的包名
		dc.setCapability("appPackage", appPackage);
		// 指定想要测试应用的入口activity
		dc.setCapability("appActivity", appActivity);

		// 2.创建驱动...URL是appium的固定地址；指定appium通讯的地址，将相对应的配置传入到驱动里边
		AndroidDriver driver = new AndroidDriver(new URL(PropertieTools.getAppiumClientUrl() + ":" + port + "/wd/hub"), dc);
		// 隐式等待
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * 停止AndroidDriver
	 * @param driver
	 */
	public static void stopAndroidDriver(AndroidDriver driver) {
		try {
			driver.quit();
		}catch (Exception e) {
		}
	}

}