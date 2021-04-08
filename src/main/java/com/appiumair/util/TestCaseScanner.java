package com.appiumair.util;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 测试用例扫描工具
 */
public class TestCaseScanner {

    private Map<String, String> testCaseMap = new ConcurrentHashMap();

    public Map doScan(String packageName) {

        // 将包名称转换成路径名称
        String pathName = packageName.replace(".", "/");

        File curFile = new File(System.getProperty("user.dir") + "/target/classes/" + pathName);

        // 获取文件下的所有文件
        File[] files = curFile.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                // 判断该文件是否是.class文件,若是则进行处理
                if (fileName.endsWith(".class")) {
                    fileName = fileName.substring(0,fileName.indexOf("."));
                    testCaseMap.put(fileName, packageName + "." + fileName);
                }
            }
        }
        return testCaseMap;
    }
}
