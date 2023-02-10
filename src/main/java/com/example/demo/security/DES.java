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
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

@Slf4j
public class DES {
    private String key = "B49A86FA425D439dB510A234A3E25A3E";
    private static final String DES = "DES";

    public DES() {
    }

    public DES(String key) {
        this.key = key;
    }

    public byte[] encrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(1, securekey, sr);
        return cipher.doFinal(src);
    }

    public byte[] decrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, securekey, sr);
        return cipher.doFinal(src);
    }

    /** @deprecated */
    public final String decrypt(String data) {
        try {
            return new String(this.decrypt(this.hex2byte(data), this.key.getBytes()));
        } catch (Exception var3) {
            log.error("解密出错，返回null", var3);
            return null;
        }
    }

    public final String decrypt(String data, String charsetName) {
        try {
            return new String(this.decrypt(this.hex2byte(data), this.key.getBytes()), charsetName);
        } catch (Exception var4) {
            log.error("解密出错，返回null", var4);
            return null;
        }
    }

    /** @deprecated */
    public final String encrypt(String data) {
        try {
            return this.byte2hex(this.encrypt(data.getBytes(), this.key.getBytes()));
        } catch (Exception var3) {
            log.error("加密出错，返回null", var3);
            return null;
        }
    }

    public final String encrypt(String data, String charsetName) {
        try {
            return this.byte2hex(this.encrypt(data.getBytes(charsetName), this.key.getBytes()));
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
