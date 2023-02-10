package com.example.demo.test;

import com.example.demo.security.SecurityHelper;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Test {
    public static void main(String[] args) throws Exception {
//        SSLContext context = SSLContext.getInstance("TLS");
//        context.init(null, null, null);
//
//        SSLSocketFactory factory = (SSLSocketFactory) context.getSocketFactory();
//        SSLSocket socket = (SSLSocket) factory.createSocket();
//
//        String[] protocols = socket.getSupportedProtocols();
//
//        System.out.println("Supported Protocols: " + protocols.length);
//        for (int i = 0; i < protocols.length; i++) {
//            System.out.println(" " + protocols[i]);
//        }
//
//        protocols = socket.getEnabledProtocols();
//
//        System.out.println("Enabled Protocols: " + protocols.length);
//        for (int i = 0; i < protocols.length; i++) {
//            System.out.println(" " + protocols[i]);
//        }

        String md5of32Str = SecurityHelper.getMD5of32Str(0 + "ea30a6c4e5e1473c9da6abdc941d1c4a" + "e_query_private_material");
        System.out.println(md5of32Str);
    }
}
