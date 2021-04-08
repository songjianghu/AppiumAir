package com.appiumair.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 手机录屏工具类
 */
public class RecordVideo {
	
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
			rt.exec("adb -s " + udid + " pull /sdcard/" + className + "_" +udid + "_" + uuid + ".mp4 ./Spark/video/");
			TimeUnit.SECONDS.sleep(3);

			// 删除录制的视频文件，可能删除不了
			rt.exec("adb -s " + udid + " rm -f /sdcard/" + className + "_" + udid + "_" + uuid + ".mp4");
	  	}
	    catch (IOException | InterruptedException e) {
	    	 e.printStackTrace();
	    }
	}
}
