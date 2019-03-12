package com.sanjin.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description: 加密工具类
 * @author: sanjin
 * @date: 2019.3.12
 */
public class EncryptUtils {


    /**
     * byte[]字节数组 转换成 十六进制字符串
     *
     * @param arr 要转换的byte[]字节数组
     *
     * @return  String 返回十六进制字符串
     */
    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    /**
     * 普通md5加密
     * @param plaintext
     * @return
     */
    public static String md5(String plaintext) {
        String ciphertext = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.update(plaintext.getBytes());
            ciphertext = hex(md5.digest());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("加密失败");
            e.printStackTrace();
        }
        return ciphertext;
    }

    /**
     * 用户登陆，注册为密码进行加密
     * @param password
     * @return
     */
    public static String sign(String password) {
        return md5(password);
    }

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder(5);

        System.out.println(stringBuilder);
        stringBuilder.append(":dklsajlad:");
        System.out.println(stringBuilder);

    }
}
