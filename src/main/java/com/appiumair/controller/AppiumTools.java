package com.appiumair.controller;

import com.appiumair.util.PropertieTools;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Appium启动和停止工具类
 */
public class AppiumTools {

    // 全局Appium server端口号
    private static AtomicInteger appiumServerPort = new AtomicInteger(4723);
    private static AppiumDriverLocalService appiumService;
    private DesiredCapabilities capabilities;
    private AppiumServiceBuilder builder;

    /**
     * 启动Appium服务
     */
    public void startAppiumServer() {

        // Android启动参数
        capabilities = new DesiredCapabilities();

        // appium启动参数
        builder = new AppiumServiceBuilder();
        builder.withIPAddress(PropertieTools.getAppiumServerIpAddress());
        builder.usingPort(Integer.parseInt(appiumServerPort.toString()));

        // 临时保存用于端口是否被占用判断，别无他用
        int port = Integer.parseInt(appiumServerPort.toString());
        appiumServerPort.getAndIncrement();

        builder.withCapabilities(capabilities);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

        appiumService = AppiumDriverLocalService.buildService(builder);

        if (checkAppiumServerIsRunning(port)) {
            appiumService.start();
        }
    }

    /**
     * 停止Appium服务
     */
    public void stopAppiumServer(int port) {
        if (!checkAppiumServerIsRunning(port)){
            appiumService.stop();
        }
    }

    /**
     * 检查Appium服务是否正在运行
     * 返回true说明Appium服务没有启动
     */
    public boolean checkAppiumServerIsRunning(int port) {
        boolean flag;
        ServerSocket serverSocket;
        try {
            // 创建该端口号的Socket服务，如果没有报错，说明Appium没有启动
            serverSocket = new ServerSocket(port);
            serverSocket.close();
            flag = true;
        } catch (IOException e) {
            // 如果报错了，说明该端口号已经启动
            flag = false;
        }
        return flag;
    }

    /**
     * 检查应该启动多少个Appium服务
     */
    public Integer checkStartHowManyAppium() {
        int num = 0;
        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            InputStream is = process.getInputStream();
            Scanner sc = new Scanner(is).useDelimiter("\\A");
            StringBuffer sb = new StringBuffer();
            while (sc.hasNext()) {
                sb.append(sc.next());
            }
            String[] devices = sb.toString().split("device");
            num = devices.length - 2;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * 返回最后一个端口号，就是返回一共启动了多少个服务
     * 每个服务启动，端口自动+1
      */
    public int getPort() {
        return Integer.parseInt(appiumServerPort.toString());
    }

    /**
     * 自动获取所有手机serial number
     */
    public String[] getAndroidPhoneSerialNumber() {
        String[] arr = null;
        try {
            // 获取手机序列号
            Process serialno = Runtime.getRuntime().exec("adb devices");
            //Process version = Runtime.getRuntime().exec("adb -s cf60255e shell getprop ro.build.version.release");
            //Process name = Runtime.getRuntime().exec("adb -s 4P8H5PR8YPPZ5PHI shell getprop ro.product.name");
            //Process brand = Runtime.getRuntime().exec("adb -s 4P8H5PR8YPPZ5PHI shell getprop ro.product.brand");
            //Process process = Runtime.getRuntime().exec("adb -s 4P8H5PR8YPPZ5PHI shell getprop ro.serialno");
            InputStream is = serialno.getInputStream();
            Scanner sc = new Scanner(is).useDelimiter("\\A");
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) {
                sb.append(sc.next());
            }
            String str = sb.toString();

            // 替换掉换行符
            String so = str.replace("List of devices attached","").replaceAll(System.getProperty("line.separator"), "");
            // 替换掉空格和device字符串
            arr = so.split("\tdevice");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }


    /**
     * 获取手机操作系统版本
     */
    public String getAndroidOSVersion(String serialNumber) {
        String str = null;
        try {
            Process version = Runtime.getRuntime().exec("adb -s " + serialNumber +" shell getprop ro.build.version.release");
            InputStream is = version.getInputStream();
            Scanner sc = new Scanner(is).useDelimiter("\\A");

            while (sc.hasNext()) {
                str = sc.next().replaceAll(System.getProperty("line.separator"), "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
