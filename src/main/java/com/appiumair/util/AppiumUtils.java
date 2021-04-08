package com.appiumair.util;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

/**
 * Appium各种操作工具类
 */
public class AppiumUtils {

    /**
     * @module 上滑操作
     * @param driver
     */
    public static void swipeUp(WebDriver driver) {
        int width=driver.manage().window().getSize().width;
        int height=driver.manage().window().getSize().height;
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        PointOption pointOption= PointOption.point(width/2, height*3/4);
        touchAction.press(pointOption).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(width/2,height/4)).release().perform();
    }

    /**
     * @module 下滑操作
     * @param driver
     */
    public static void swipeDown(WebDriver driver) {
        int width=driver.manage().window().getSize().width;
        int height=driver.manage().window().getSize().height;
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        PointOption pointOption= PointOption.point(width/2, height/4);
        touchAction.press(pointOption).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(width/2,height*3/4)).release().perform();
    }

    /**
     * @module 左滑操作
     * @param driver
     */
    public static void swipeLeft(WebDriver driver) {
        int width=driver.manage().window().getSize().width;
        int height=driver.manage().window().getSize().height;
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        PointOption pointOption= PointOption.point(width*4/5, height/2);
        touchAction.press(pointOption).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(width/5,height/2)).release().perform();
    }

    /**
     * @module 右滑操作
     * @param driver
     */
    public static void swipeRight(WebDriver driver) {
        int width=driver.manage().window().getSize().width;
        int height=driver.manage().window().getSize().height;
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        PointOption pointOption= PointOption.point(width/5, height/4);
        touchAction.press(pointOption).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(width*4/5,height/2)).release().perform();
    }
}
