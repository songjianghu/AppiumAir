package com.appiumair.report;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ExtentReport4 {
    public static ExtentReports extent = new ExtentReports("./report.html",false, NetworkMode.OFFLINE);
    public static ExtentTest test;

    @Test
    public void test(){
        test=extent.startTest("淘宝首页搜索");
        test.log(LogStatus.PASS,"首页搜索用例执行成功");
    }

    @AfterMethod
    public void afterMethod(){
        extent.endTest(test);
        extent.flush();
    }

}
