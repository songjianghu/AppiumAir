package com.appiumair.util;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidBatteryInfo;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebDriver;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Appium各种操作工具类
 */
public class AppiumUtils {
    public AppiumUtils() {}

    /**
     * @param driver
     * @module 上滑操作
     */
    public static void swipeUp(WebDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        PointOption pointOption = PointOption.point(width / 2, height * 3 / 4);
        touchAction.press(pointOption).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(width / 2, height / 4)).release().perform();
    }

    /**
     * @param driver
     * @module 下滑操作
     */
    public static void swipeDown(WebDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        PointOption pointOption = PointOption.point(width / 2, height / 4);
        touchAction.press(pointOption).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(width / 2, height * 3 / 4)).release().perform();
    }

    /**
     * @param driver
     * @module 左滑操作
     */
    public static void swipeLeft(WebDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        PointOption pointOption = PointOption.point(width * 4 / 5, height / 2);
        touchAction.press(pointOption).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(width / 5, height / 2)).release().perform();
    }

    /**
     * @param driver
     * @module 右滑操作
     */
    public static void swipeRight(WebDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        PointOption pointOption = PointOption.point(width / 5, height / 4);
        touchAction.press(pointOption).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(width * 4 / 5, height / 2)).release().perform();
    }

    /**
     * 执行测试前，检查各设备电量情况，电量不足发出警告
     * @param driver
     * @return 电量状态是否允许可继续执行
     */
    public static Double checkBattery(AndroidDriver driver) {
        String message = "";
        double batteryLevel = driver.getBatteryInfo().getLevel();
        String batteryState = driver.getBatteryInfo().getState().name();
        return batteryLevel;
    }

    /**
     * 执行测试前，检查网络状态
     * @param driver
     * @return 网络连接状态
     */
    public static String checkNetwork(AndroidDriver driver) {
        List<String> networkList = new ArrayList<>();
        String message = "";
        boolean wiFiEnabled = driver.getConnection().isWiFiEnabled();
        if(!true == wiFiEnabled) {
            message = "ERROR";
        }
        if (true == wiFiEnabled) {
            message = "WIFI";
        }
        return message;
    }
}
