package com.appiumair.report;

import com.appiumair.util.PropertieTools;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * 测试报告
 */
public class ExtentReport {
    public ExtentReport() {}
    public static ExtentReports extent = new ExtentReports();
    public static ExtentSparkReporter spark = new ExtentSparkReporter(PropertieTools.getReportTempUrl() + PropertieTools.getReportName());

}

