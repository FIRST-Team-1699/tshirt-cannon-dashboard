package com.team1699.server;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

public class KeyGenerator {

    protected static void generateKeyFile() {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("mytestkey.jks"), "passphrase".toCharArray());

            CertAndKeyGen gen = new CertAndKeyGen("RSA", "SHA1WithRSA");
            gen.generate(1024);

            Key key = gen.getPrivateKey();
            X509Certificate cert = gen.getSelfCertificate(new X500Name("CN=ROOT"), (long) 365 * 24 * 3600);

            X509Certificate[] chain = new X509Certificate[1];
            chain[0] = cert;

            keyStore.setKeyEntry("mykey", key, "passphrase".toCharArray(), chain);

            keyStore.store(new FileOutputStream("mytestkey.jks"), "passphrase".toCharArray());
        }catch(FileNotFoundException e){
            try{
                KeyStore keyStore = KeyStore.getInstance("JKS");
                keyStore.load(null,null);

                keyStore.store(new FileOutputStream("mytestkey.jks"), "passphrase".toCharArray());
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}