package com.appiumair.controller;

import java.util.concurrent.CountDownLatch;

public interface CountDownLatchLocalJS {
    public static CountDownLatch countDownLatch = new CountDownLatch(AppiumTest.androidInfoMap.size());
}
