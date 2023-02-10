//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.security;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
    public static final String KEY_ALGORITHM = "AES";
    public static final int KEY_LENGTH = 128;
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static byte[] initKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    private static Key toKey(byte[] keyArr) throws Exception {
        Key secretKey = new SecretKeySpec(keyArr, "AES");
        return secretKey;
    }

    public static byte[] encrypt(byte[] data, byte[] keyArr) throws Exception {
        Key key = toKey(keyArr);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, key);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, byte[] keyArr) throws Exception {
        Key key = toKey(keyArr);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(2, key);
        return cipher.doFinal(data);
    }

    private AesUtil() {
    }
}
