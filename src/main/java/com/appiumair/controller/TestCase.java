package com.appiumair.controller;

import io.appium.java_client.android.AndroidDriver;

/**
 * 测试用例接口类
 */
public interface TestCase {
   public void run(AndroidDriver driver) throws InterruptedException;
}
