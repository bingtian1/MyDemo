//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.security;

import com.sun.crypto.provider.SunJCE;
import java.io.IOException;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Slf4j
public final class KDES {
    public static int DES = 1;
    public static int DESEDE = 2;
    public static int BLOWFISH = 3;
    private Cipher p_Cipher;
    private SecretKey p_Key;
    private String p_Algorithm;
    BASE64Decoder decoder = new BASE64Decoder();
    BASE64Encoder encoder = new BASE64Encoder();

    private void selectAlgorithm(int al) {
        switch (al) {
            case 1:
            default:
                this.p_Algorithm = "DES";
                break;
            case 2:
                this.p_Algorithm = "DESede";
                break;
            case 3:
                this.p_Algorithm = "Blowfish";
        }

    }

    public KDES(int algorithm) throws Exception {
        this.selectAlgorithm(algorithm);
        Security.addProvider(new SunJCE());
        this.p_Cipher = Cipher.getInstance(this.p_Algorithm);
    }

    public KDES() throws Exception {
        this.selectAlgorithm(1);
        Security.addProvider(new SunJCE());
        this.p_Cipher = Cipher.getInstance(this.p_Algorithm);
    }

    public void setKey(String key) {
        if (key == null || key.equals("")) {
            key = "desdesde";
        }

        int n = key.length() % 8;
        if (n != 0) {
            for(int i = 8; i > n; --i) {
                key = key + "0";
            }
        }

        this.p_Key = new SecretKeySpec(key.getBytes(), this.p_Algorithm);
    }

    private SecretKey checkKey() {
        try {
            if (this.p_Key == null) {
                KeyGenerator keygen = KeyGenerator.getInstance(this.p_Algorithm);
                this.p_Key = keygen.generateKey();
            }
        } catch (Exception var2) {
            log.error("生成加密key失败", var2);
        }

        return this.p_Key;
    }

    public String encode(String data) throws Exception {
        this.p_Cipher.init(1, this.checkKey());
        return new String(this.byte2hex(this.p_Cipher.doFinal(data.getBytes("GBK"))));
    }

    public String decode(String encdata, String enckey) throws Exception {
        this.setKey(enckey);
        this.p_Cipher.init(2, this.p_Key);
        return new String(this.p_Cipher.doFinal(this.hex2byte(encdata)), "GBK");
    }

    public String byte2hex(byte[] b) {
        return this.encoder.encode(b);
    }

    public byte[] hex2byte(String hex) throws IOException {
        return this.decoder.decodeBuffer(hex);
    }
}
