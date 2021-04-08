# AppiumAir

## 项目介绍
AppiumAir是基于Appium二次封装的Android自动化框架，多线程方式在多台手机上同时执行测试，自动获取已连接各设备信息，自动启动已连接设备相同多个appium服务，多个测试用例连续执行，用例执行失败自动截图、录制视频、收集报错信息，列表中高亮显示，全新报告模板，内容全面，样式新颖，手机连接可直接运行

[![](https://img.shields.io/badge/RELEASE-v3.0-red.svg)](https://gitee.com/songjianghu/AppiumAir.git)
[![](https://img.shields.io/badge/RELEASE-v2.0-blue.svg)](https://gitee.com/songjianghu/AppiumAir.git)
[![](https://img.shields.io/badge/RELEASE-v1.0-blue.svg)](https://gitee.com/songjianghu/AppiumAir.git)
![](https://img.shields.io/badge/JDK-1.8+-green.svg)
![](https://img.shields.io/badge/License-Apache%202.0-green.svg)

## Gitee Pages
- 测试报告演示：https://songjianghu.gitee.io/appiumair

## 主要功能
- 多线程方式在多台手机上同时执行测试，大幅提高测试效率
- 自动获取已连接各手机信息，自动启动已连接设备相同多个appium服务
- 多个测试用例连续执行，无需重新启动APP，缩短测试用例执行间隔
- 测试用例无需配置，自动扫描执行，让测试人员更加专注业务逻辑
- 用例执行失败自动截图、录制视频、收集报错信息，列表中高亮显示
- 使用ExtentReport报告插件二次改造，内容全面，样式新颖

## 环境配置
- 安装JDK8或以上，并配置JAVA_HOME环境变量
```
JAVA_HOME=C:\Program Files\Java\jdk1.8.0_141
PATH=%JAVA_HOME%\bin
```
- 安装SDK，并配置环境变量
```
ANDROID_HOME=D:\software\android-sdk-windows(SDK安装目录)
PATH=%ANDROID_HOME%\platform-tools
PATH=%ANDROID_HOME%\tools
PATH=%ANDROID_HOME%\build-tools\30.0.2
```

- 安装Maven3
```
MAVEN_HOME=D:\develop\apache-maven-3.6.3(Maven安装目录)
PATH=%MAVEN_HOME%\bin
```

- 配置setting.xml
```
<mirror>
    <id>aliyunmaven</id>
    <mirrorOf>*</mirrorOf>
    <name>阿里云公共仓库</name>
    <url>https://maven.aliyun.com/repository/public</url>
</mirror>
```

- 安装Node.js，windows系统不要安装在C盘(系统盘)，根据提示安装即可
- 下载地址：https://nodejs.org/en/
- 配置node全局模块安装目录，在node.js安装目录下新建两个文件夹node_global和node_cache，然后在cmd(管理员)命令下执行如下两个命令：
```
  npm config set prefix "D:\app\nodejs\node_global"
  npm config set cache "D:\app\nodejs\node_cache"
```

- 配置node.js环境变量
```
PATH=D:\app\nodejs
NODE_PATH=D:\app\nodejs\node_modules
PATH=D:\app\nodejs\node_global
PATH=D:\app\nodejs\node_modules\npm
```
- 安装Appium server
  网络较好，使用npm安装：
```  
npm install -g Appium
```
- 网络一般，可以使用cnpm安装appium server，需要先安装cnpm
```
npm install -g cnpm --registry=https://registry.npm.taobao.org
cnpm install -g appium
```

- 手机开启开发者模式，开启USB调试，开启USB安装，开启USB调试(安全设置)
- 下载apk安装包，下载地址：
```
https://market.m.taobao.com/app/fdilab/download-page/main/index.html
```
- 获取appPackage、appActivity，使用aapt命令从apk安装包中获取，找到package:name和launchable-activity: name
```  
aapt dump badging D:/app/com.taobao.taobao.apk
```

- 使用连接线连接手机：选择USB用途-->传输文件
- 测试手机是否连接成功
```
adb devices
```

- 开发工具： 使用IEDA或者Eclipse都可以

- IDEA下载地址：
```
https://www.jetbrains.com/idea/
```
- Eclipse下载地址
```
https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2021-03/R/eclipse-java-2021-03-R-win32-x86_64.zip
```

## 使用说明
- 代码仓库：
```
https://gitee.com/songjianghu/AppiumAir.git
```
- 检出代码： 
```
  git clone https://gitee.com/songjianghu/AppiumAir.git
```
- 代码启动入口类：AppiumTest
- APP配置文件：resources/config/config.properties
- 其他说明文档：在resources/doc目录下
- 测试报告位置： Spark/Spark.html

## 技术交流
[![加入QQ群](https://img.shields.io/badge/QQ群:AppiumAir-774757006-blue.svg)](https://qm.qq.com/cgi-bin/qm/qr?k=VLN_IPf76i_rfgzXOMhDc81xa7TrqFbz&jump_from=webapi)  

## License

本框架采用Apache-2.0开源许可进行编写。

## 开源支持

<a href="https://www.songjianghu.com"><img src="https://images.gitee.com/uploads/images/2020/0406/220236_f5275c90_5531506.png" width="100" heith="100"/></a>
