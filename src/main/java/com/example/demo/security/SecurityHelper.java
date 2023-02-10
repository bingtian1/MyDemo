//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.security;

import com.example.demo.utils.Base64Helper;
import com.example.demo.utils.StringHelper;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

@Slf4j
public class SecurityHelper {
    private static final int FINAL_EIGHT = 8;
    private static final int FINAL_TWENTYFOUR = 24;
    private static String key = "B49A86FA425D439dB510A234A3E25A3E";
    private static final String DEFAULT_ENCODE = "utf-8";

    public SecurityHelper() {
    }

    public static String getMD5of32Str(String inbuf) {
        MD5 md5 = new MD5();
        return md5.getMD5ofStr(inbuf);
    }

    public static String getMD5of16Str(String inbuf) {
        MD5 md5 = new MD5();
        String str = md5.getMD5ofStr(inbuf);
        if (!StringHelper.isEmpty(str)) {
            str = str.substring(8, 24);
        }

        return str;
    }

    public static String getSHA512Str(String inbuf) {
        SHA512 sha512 = new SHA512();
        return sha512.getSHA512ofStr(inbuf);
    }

    public static String encode(String originStr) {
        return encode(originStr, key);
    }

    public static String encode(String originStr, String key) {
        try {
            KDES kDes = new KDES();
            kDes.setKey(key);
            return kDes.encode(originStr);
        } catch (Exception var3) {
            log.error("加密出错", var3);
            return "";
        }
    }

    public static String decode(String originStr) {
        return decode(originStr, key);
    }

    public static String decode(String originStr, String key) {
        try {
            KDES kDes = new KDES();
            kDes.setKey(key);
            return kDes.decode(originStr, key);
        } catch (Exception var3) {
            log.error("解密出错", var3);
            return "";
        }
    }

    public static String encryptByAes(String plainText, String key) throws Exception {
        byte[] b = AesUtil.encrypt(plainText.getBytes("utf-8"), key.getBytes("utf-8"));
        return b != null ? Base64Helper.encode(b) : "";
    }

    public static String encryptByDes(String plainText, String key) throws Exception {
        DES des = new DES(key);
        return des.encrypt(plainText, "utf-8");
    }

    public static String decryptByAes(String cipherText, String key) throws Exception {
        byte[] b = Base64Helper.decode(cipherText);
        String result = "";
        if (b != null) {
            result = new String(AesUtil.decrypt(b, key.getBytes("utf-8")), "utf-8");
        }

        return result;
    }

    public static String decryptByDes(String cipherText, String key) throws Exception {
        DES des = new DES(key);
        return des.decrypt(cipherText, "utf-8");
    }

    public static String base64(String text) throws Exception {
        return Base64Helper.encode(text);
    }

    public static String debase64(String base64) throws Exception {
        return Base64Helper.decodeToString(base64);
    }

    public static String md5(String plainText) throws Exception {
        String resultString = "";
        MessageDigest md = MessageDigest.getInstance("MD5");
        resultString = byteArrayToHex(md.digest(plainText.getBytes("utf-8")));
        return resultString;
    }

    public static String byteArrayToHex(byte[] arr) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString(arr[i] & 255 | 256).substring(1, 3));
        }

        return sb.toString().toUpperCase();
    }

    public static byte[] hexToByteArray(String hex) {
        try {
            byte[] arrB = hex.getBytes("utf-8");
            int iLen = arrB.length;
            byte[] arrOut = new byte[iLen / 2];

            for(int i = 0; i < iLen; i += 2) {
                String strTmp = new String(arrB, i, 2);
                arrOut[i / 2] = (byte)Integer.parseInt(strTmp, 16);
            }

            return arrOut;
        } catch (Exception var6) {
            return new byte[0];
        }
    }
}
