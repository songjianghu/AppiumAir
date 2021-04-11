package com.appiumair.util;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 手机录屏工具类
 */
public class RecordVideo {
	public RecordVideo() {}

	/**
	 * 录制手机执行测试用例的视频
	 */
	public static Process startRecord(String className, String udid, String uuid) {
	    Runtime rt = Runtime.getRuntime();
		Process process = null;
	    try {
			process = rt.exec("adb -s " + udid + " shell screenrecord --bit-rate 6000000 --time-limit 180 --verbose /sdcard/" + className + "_" +udid + "_" + uuid + ".mp4");
			TimeUnit.SECONDS.sleep(1);
		}catch (IOException | InterruptedException e) {
	    	 e.printStackTrace();
	  	}
	    return process;
	}

	/**
	 * 移动录制的视频到项目指定目录
	 */
	public static void pushVideo(String className, String udid, Process process, String uuid) {
	    try {
			// 停止录制，等待视频写入磁盘
			process.destroy();
			TimeUnit.SECONDS.sleep(3);

			Runtime rt = Runtime.getRuntime();
			rt.exec("adb -s " + udid + " pull /sdcard/" + className + "_" +udid + "_" + uuid + ".mp4 " + PropertieTools.getVideoUrl());
			TimeUnit.SECONDS.sleep(3);

			// 删除录制的视频文件
			rt.exec("adb -s " + udid + " shell rm -f /sdcard/" + className + "_" + udid + "_" + uuid + ".mp4");
	  	}
	    catch (IOException | InterruptedException e) {
	    	 e.printStackTrace();
	    }
	}

	/**
	 * 截图功能
	 */
	public static void screenshot(AndroidDriver driver,String className, String udid, String uuid) {
		try {
			File screenShotFile = driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShotFile, new File(PropertieTools.getImgUrl() + className +"_" + udid + "_" + uuid + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
