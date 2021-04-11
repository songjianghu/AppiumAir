package com.appiumair.util;

import com.appiumair.controller.AppiumTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

/**
 * APK安装器
 */
public class APKInstaller {

    static final Logger logger = LoggerFactory.getLogger(APKInstaller.class);

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        boolean installResult = APKInstaller.class.getDeclaredConstructor().newInstance().doInstall("jd_lite20210323.apk");
    }

    private AppiumTools appiumTools;
    String projectRootDir = null;
    String[] serialNumber;

    public boolean doInstall(String apk) throws IOException {
        boolean execResult = false;
        projectRootDir = System.getProperty("user.dir");
        appiumTools = new AppiumTools();
        serialNumber = appiumTools.getAndroidPhoneSerialNumber();
        String apkPath = projectRootDir + "/target/classes/apk/" + apk;
        for (int i=0; i<serialNumber.length; i++) {
            Process process = Runtime.getRuntime().exec("adb -s " + serialNumber[i] + " install " + apkPath);
            InputStream is = process.getInputStream();
            Scanner sc = new Scanner(is).useDelimiter("\\A");
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) {
                sb.append(sc.next());
            }
            boolean success = sb.toString().contains("Success");
            if (success) {
                //System.out.println("安装成功");
                logger.info("安装成功");
            }else {
                //System.out.println("安装失败");
                logger.error("安装失败");
            }

        }
        return execResult;
    }

}
