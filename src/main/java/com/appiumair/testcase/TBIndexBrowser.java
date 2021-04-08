package com.appiumair.testcase;

import com.appiumair.controller.TestCase;
import com.appiumair.report.ExtentReport;
import com.appiumair.util.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import java.util.concurrent.TimeUnit;

/**
 * 测试用例：首页浏览
 */
public class TBIndexBrowser extends ExtentReport implements TestCase {
    /**
     * @module 上下滑动浏览
     * @param driver
     * @throws InterruptedException
     */
    @Override
    public void run(AndroidDriver driver) throws InterruptedException {
        for (int i = 0; i < 1; i++) {
            //driver.navigate().back();
            TimeUnit.MILLISECONDS.sleep(500);
            AppiumUtils.swipeUp(driver);
            TimeUnit.MILLISECONDS.sleep(500);
            AppiumUtils.swipeUp(driver);
            TimeUnit.MILLISECONDS.sleep(500);
            AppiumUtils.swipeUp(driver);
            TimeUnit.MILLISECONDS.sleep(500);
            AppiumUtils.swipeUp(driver);
            TimeUnit.MILLISECONDS.sleep(500);
            TimeUnit.MILLISECONDS.sleep(500);
            AppiumUtils.swipeDown(driver);
            TimeUnit.MILLISECONDS.sleep(500);
            AppiumUtils.swipeDown(driver);
            TimeUnit.MILLISECONDS.sleep(500);
            AppiumUtils.swipeDown(driver);
            TimeUnit.MILLISECONDS.sleep(500);
            AppiumUtils.swipeDown(driver);
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}
