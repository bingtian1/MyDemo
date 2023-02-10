//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.security;

import java.security.MessageDigest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SHA512 {
    private static final char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public SHA512() {
    }

    private String getSHA512(byte[] source) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(source);
            byte[] tmp = md.digest();
            char[] str = new char[128];
            int k = 0;

            for(int i = 0; i < 64; ++i) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var8) {
            log.error("获取sha出错，返回null", var8);
            return null;
        }
    }

    public String getSHA512ofStr(String inbuf) {
        return this.getSHA512(inbuf.getBytes());
    }
}
