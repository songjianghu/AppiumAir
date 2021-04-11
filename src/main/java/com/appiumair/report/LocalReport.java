package com.appiumair.report;

import java.io.*;

/**
 * 加载本地JS文件，解决JS文件路途遥远导致报告打开缓慢的问题
 */
public class LocalReport {
    public static void localJS() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader("Spark/temp/index.html"));
            bw = new BufferedWriter(new FileWriter("Spark/index.html"));
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("<script src=\"https://cdn.rawgit.com/extent-framework/extent-github-cdn/7cc78ce/spark/js/jsontree.js\"></script>")) {
                    line = "<script src='/appiumair/js/jsontree.js'></script>";
                }
                line = line.trim();
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
