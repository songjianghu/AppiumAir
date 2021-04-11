package com.appiumair.controller;

import com.appiumair.bean.AppiumParamter;
import com.appiumair.report.ExtentReport;
import com.appiumair.util.AppiumUtils;
import com.appiumair.util.RecordVideo;
import com.appiumair.util.TestCaseScanner;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Appium可多线程执行类
 */
public class AppiumThread extends ExtentReport implements Runnable,CountDownLatchLocalJS {

    private static ThreadLocal<AppiumParamter> threadLocalMap = new ThreadLocal<>();
    private AppiumParamter appiumParamter;
    private Map<String, String> testCaseMap = new ConcurrentHashMap();
    public AppiumThread(AppiumParamter appiumParamter) {
        this.appiumParamter = appiumParamter;
    }

    @Override
    public void run() {
        // 不同线程传递不同的手机信息AndroidInfo
        threadLocalMap.set(appiumParamter);
        try {
            AndroidDriver driver = AppiumStarter.appiumClientStarter(threadLocalMap.get().getUdid(),threadLocalMap.get().getDeviceName(), threadLocalMap.get().getPlatformVersion(),threadLocalMap.get().getAppPackage(),threadLocalMap.get().getAppActivity(),threadLocalMap.get().getPort());

            // 执行前检查电量和网络状态
            Double batteryLevel = AppiumUtils.checkBattery(driver);
            String networkState = AppiumUtils.checkNetwork(driver);
            String batteryIcon = "";
            String networkIcon = "";
            if (batteryLevel >= 0.9) {
                batteryIcon = "fa fa-battery-full";
            }
            if (batteryLevel >= 0.7 && batteryLevel < 0.9) {
                batteryIcon = "fa fa-battery-3";
            }
            if (batteryLevel >= 0.5 && batteryLevel < 0.7) {
                batteryIcon = "fa fa-battery-half";
            }
            if (batteryLevel >= 0.2 && batteryLevel < 0.5) {
                batteryIcon = "fa fa-battery-1";
            }
            if (batteryLevel >= 0.0 && batteryLevel <= 0.2) {
                batteryIcon = "fa fa-battery-empty";
            }
            if("WIFI".equals(networkState)) {
                networkIcon = "fa fa-wifi";
            }else if("ERROR".equals(networkState)) {
                networkIcon = "fa fa-warning";
            }

            // 测试用例入口，动态加载测试用例
            TestCaseScanner tcs = new TestCaseScanner();
            testCaseMap = tcs.doScan("com.appiumair.testcase");
            Iterator<Map.Entry<String, String>> it = testCaseMap.entrySet().iterator();
            String className = null;
            Process process = null;
            String uuid = null;
            while (it.hasNext()) {
                // 为报告生成随机字符串，解决打开报告看到的旧视频和旧图片问题
                uuid = UUID.randomUUID().toString().replace("-", "");
                extent.attachReporter(spark);

                className = it.next().getKey();
                String clazzFullName = testCaseMap.get(className);
                Class clazz = Class.forName(clazzFullName);
                TestCase testCase = (TestCase) clazz.getDeclaredConstructor().newInstance();
                try {
                    // 录制视频开始
                    process = RecordVideo.startRecord(className, appiumParamter.getUdid(), uuid);
                    // 执行测试
                    testCase.run(driver);
                    // 创建测试成功报告
                    extent.createTest(className + "：" + appiumParamter.getUdid() + "&nbsp;&nbsp;<i class='" + networkIcon + "'></i>&nbsp;&nbsp;<i class='" + batteryIcon + "'></i>").createNode("<video src='video/" + className + "_" + appiumParamter.getUdid() + "_" + uuid + ".mp4' width='300px' height='500px' preload controls></video>").pass("<span class='badge badge-primary'>测试通过</span>");
                    extent.flush();
                }catch (Exception e){
                    // 失败截图
                    RecordVideo.screenshot(driver, className, appiumParamter.getUdid(), uuid);
                    // 获取e.printStackTrace中的详细内容
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    e.printStackTrace(pw);
                    pw.flush();
                    sw.flush();
                    // 创建错误报告
                    extent.createTest("<span style='color:#ed1941'>" + className + "：" + appiumParamter.getUdid() + "&nbsp;&nbsp;</span><i class='" + networkIcon + "'></i>&nbsp;&nbsp;<i class='" + batteryIcon + "'></i>").createNode("<video src='video/" + className + "_" + appiumParamter.getUdid() + "_" + uuid + ".mp4' " + "style='float:left;width:300px;height:500px;'" + " preload controls></video><img style='float:left;width:300px;height:500px;' src='" + "img/" + className + "_" + appiumParamter.getUdid() + "_" + uuid + ".jpg" + "'>").fail("<pre>" + sw.toString() + "</pre>");
                    extent.flush();
                    sw.close();
                    pw.close();
                    // 既然报错了，多录制5秒钟，便于多留存一些证据
                    TimeUnit.SECONDS.sleep(5);
                }finally {
                    // 上传视频并清理掉视频文件
                    RecordVideo.pushVideo(className, appiumParamter.getUdid(), process, uuid);
                }
            }

            // 解决JS路途遥远导致报告打开缓慢问题
            countDownLatch.countDown();

            // 先停止AndroidDriver
            AppiumStarter.stopAndroidDriver(driver);
            TimeUnit.SECONDS.sleep(1);
            // 再关闭Appium服务端
            AppiumTools appiumTools = new AppiumTools();
            appiumTools.stopAppiumServer(appiumParamter.getPort());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

}
