package com.sanjin.util;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @description: 文件操作工具类
 * @author: sanjin
 * @date: 2019.3.12
 */
@Slf4j
public class FileUtils {

    private static Random random = new Random();

    private static SimpleDateFormat simpleDateFormat = null;

    // 上传文件目录名称 总路径 ： classpath:public/upload
    private static String uploadDirName = "upload";

    private static SimpleDateFormat getSimpleDateFormat() {
        if (simpleDateFormat == null) {
            // 20190208193922
            String pattern = "yyyyMMddHHmmss";
            simpleDateFormat = new SimpleDateFormat(pattern);
        }
        return simpleDateFormat;
    }

    /**
     * 生成格式为 yyyyMMddHHmmss(日期时间) + 18297 (5位随机数)
     * @return
     */
    private static String generateUniqueFileName(String prefix) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat();
        String format = simpleDateFormat.format(date);
        int ranNum = random.nextInt(10000);
        return format + ranNum + "." + prefix;
    }

    /**
     * 检车是否存在某个文件夹
     * @param checkDirName 检测的目录名称
     * @return
     */
    private static void checkPath(String checkDirName) {
        if (checkDirName == null) {
            return;
        }
        File file = new File(checkDirName);
        if (!file.exists()) {
            boolean flag = file.mkdir();
            if (!flag)
                log.error("【FileUtils.chechPath】无法创建目标目录");

        }

    }

    /**
     * 保存文件，调用者指定存储位置文件目录
     * @param is 文件输入流
     * @param dirPath 文件所在目录
     * @param prefix 文件后缀名
     * @return 文件名
     */
    public static String save(InputStream is, String dirPath ,String prefix) {
        if (is == null) {
            return "";
        }
        // TODO 上传文件安全问题
        checkPath(dirPath);
        String fileName = generateUniqueFileName(prefix);

        File file = new File(dirPath + fileName);
        while (file.exists()) {
            fileName = generateUniqueFileName(prefix);
            file = new File(dirPath+fileName);
        }

        OutputStream os = null;
        try {

            os = new FileOutputStream(file);
            byte[] flush = new byte[1024];

            int len = -1;
            while ((len = is.read(flush)) != -1) {
                os.write(flush,0,len);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            release(is, os);
        }
        return uploadDirName + "/" + fileName;
    }

    /**
     * 释放资源
     * @param is
     */
    public static void release(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放资源
     * @param os
     */
    public static void release(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放资源
     * @param is
     * @param os
     */
    public static void release(InputStream is, OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
