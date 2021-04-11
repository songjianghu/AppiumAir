package com.appiumair.controller;

import com.appiumair.bean.AppiumParamter;
import com.appiumair.report.LocalReport;
import com.appiumair.util.PropertieTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Appium多线程启动类：启动入口
 */
public class AppiumTest implements CountDownLatchLocalJS {

    static final Logger logger = LoggerFactory.getLogger(AppiumTest.class);
    public static AtomicInteger appiumClientPort = new AtomicInteger(4723);
    public static Map<String, String> androidInfoMap;
    private static AppiumTools appiumTools;

    public static void main(String[] args) {

        // 获取已连接了多少台Android手机
        appiumTools = new AppiumTools();
        // 用来存放Android手机的相关信息
        androidInfoMap = new ConcurrentHashMap<>();

        // 获取所有已连接手机的serial number
        String[] serialNumberArr = appiumTools.getAndroidPhoneSerialNumber();

        // 把获取到的serialnumber和version整合到一起
        for (int i = 0; i < serialNumberArr.length; i++) {
            // 根据serial number获取手机系统的版本号
            String version = appiumTools.getAndroidOSVersion(serialNumberArr[i]);
            // 然后把serial number和version对应起来
            androidInfoMap.put(serialNumberArr[i], version);
        }

        // 启动Appium server
        // Integer appiumNum = appiumTools.checkStartHowManyAppium();
        for (int i = 0; i < androidInfoMap.size(); i++) {
            appiumTools.startAppiumServer();
        }

        // 启动多线程
        Iterator<Map.Entry<String, String>> it = androidInfoMap.entrySet().iterator();
        while (it.hasNext()) {
            AppiumParamter appiumParamter = new AppiumParamter();
            String udid = it.next().getKey();
            String version = androidInfoMap.get(udid);
            appiumParamter.setUdid(udid);
            appiumParamter.setDeviceName(udid);
            appiumParamter.setPlatformVersion(version);
            appiumParamter.setAppPackage(PropertieTools.getAppPackage());
            appiumParamter.setAppActivity(PropertieTools.getAppActivity());

            // 每个线程对应启动Appium唯一的端口号
            appiumParamter.setPort(Integer.parseInt(appiumClientPort.toString()));
            appiumClientPort.getAndIncrement();

            AppiumThread appiumThread = new AppiumThread(appiumParamter);
            // 不同的线程传递不同的参数
            Thread t = new Thread(appiumThread,"T_" + appiumClientPort.toString());
            t.start();
        }
        try {
            // 解决JS路途遥远导致报告打开缓慢问题
            countDownLatch.await();
            LocalReport.localJS();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
