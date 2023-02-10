//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.security;

import com.example.demo.utils.Base64Helper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Slf4j
public class AES {
    private String key = "B49A86FA425D439dB510A234A3E25A3E";
    private static final String AES = "AES";
    private static final String ALGORITHM = "SHA1PRNG";

    public AES() {
    }

    public AES(String key) {
        this.key = key;
    }

    public static byte[] encrypt(byte[] byteContent, byte[] password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password);
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, key);
        byte[] result = cipher.doFinal(byteContent);
        return result;
    }

    public static byte[] decrypt(byte[] content, byte[] password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password);
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, key);
        byte[] result = cipher.doFinal(content);
        return result;
    }

    /** @deprecated */
    public final String decrypt(String data) {
        try {
            return new String(decrypt(this.hex2byte(data), this.key.getBytes()));
        } catch (Exception var3) {
            log.error("解密出错，返回null", var3);
            return null;
        }
    }

    public final String decrypt(String data, String charsetName) {
        try {
            return new String(decrypt(this.hex2byte(data), this.key.getBytes()), charsetName);
        } catch (Exception var4) {
            log.error("解密出错，返回null", var4);
            return null;
        }
    }

    /** @deprecated */
    public final String encrypt(String data) {
        try {
            return this.byte2hex(encrypt(data.getBytes(), this.key.getBytes()));
        } catch (Exception var3) {
            log.error("加密出错，返回null", var3);
            return null;
        }
    }

    public final String encrypt(String data, String charsetName) {
        try {
            return this.byte2hex(encrypt(data.getBytes(charsetName), this.key.getBytes()));
        } catch (Exception var4) {
            log.error("加密出错，返回null", var4);
            return null;
        }
    }

    private String byte2hex(byte[] b) {
        return Base64Helper.encode(b);
    }

    private byte[] hex2byte(String hex) throws IOException {
        return Base64Helper.decode(hex);
    }

    public void setKey(String key) {
        this.key = key;
    }
}
