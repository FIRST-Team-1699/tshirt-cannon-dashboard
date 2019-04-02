package com.team1699.server;

import javax.net.ssl.*;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;

public class Server implements Runnable {

    //This class is the server used to communicate with a client running on the T-Shirt cannon

    private static Server instance;

    public static Server getInstance(){
        if(instance == null){
            instance = new Server();
        }
        return instance;
    }

    private Thread thread;
    private boolean running;
    private final int port;

    //TODO Make SSL Connection

    private Server(){
        this.port = 12345; //TODO Load port from config file

        KeyGenerator.generateKeyFile();
    }

    private SSLContext createSSLContext(){ //TODO Add file name to constants and change
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("mytestkey.jks"), "passphrase".toCharArray()); //TODO Change passphrase and file name

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, "passphrase".toCharArray());
            KeyManager[] km = keyManagerFactory.getKeyManagers();

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);
            TrustManager[] tm = trustManagerFactory.getTrustManagers();

            SSLContext sslContext = SSLContext.getInstance("TLSv1");
            sslContext.init(km, tm, null);
            return sslContext;
        }catch(NoSuchAlgorithmException | KeyManagementException | KeyStoreException | UnrecoverableKeyException | CertificateException | IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        while(running){
            //Run server
            //TODO Add handle client connect
            //TODO Only one client should ever connect. Handle other connections

            SSLContext sslContext = this.createSSLContext();

            try{
                SSLServerSocketFactory sslServerSocketFactory = null;
                if (sslContext != null) {
                    sslServerSocketFactory = sslContext.getServerSocketFactory();
                }

                SSLServerSocket sslServerSocket = null;
                if (sslServerSocketFactory != null) {
                    sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(port);
                }

                System.out.println("Server Started");

                while(running){
                    SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();

                    sslSocket.setEnabledCipherSuites(sslSocket.getSupportedCipherSuites());
                    sslSocket.startHandshake();

                    new Client().start(); //TODO Add a way to stop the server

                    SSLSession sslSession = sslSocket.getSession();
                    System.out.println("SSLSession :");
                    System.out.println("\tProtocol : " + sslSession.getProtocol());
                    System.out.println("Cipher suite : " + sslSession.getCipherSuite());

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));

                    String line;
                    while(running) {
                        line = bufferedReader.readLine();
                        if (line != null) {
                            System.out.println("Input: " + line);
                        }
                    }

                    printWriter.print("HTTP/1.1 200\r\n");
                    printWriter.flush();
                    sslSocket.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running){
            return;
        }
        running = false;
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
