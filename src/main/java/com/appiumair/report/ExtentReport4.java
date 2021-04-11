package com.appiumair.report;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ExtentReport4 {
    public ExtentReports extentReports = new ExtentReports("./report.html",false, NetworkMode.OFFLINE);
    public ExtentTest extentTest;

    @Test
    public void run(){
        extentTest = extentReports.startTest("淘宝首页搜索");
        extentTest.log(LogStatus.PASS,"首页搜索用例执行成功");
    }

    @AfterMethod
    public void after(){
        extentReports.endTest(extentTest);
        extentReports.flush();
    }

}
