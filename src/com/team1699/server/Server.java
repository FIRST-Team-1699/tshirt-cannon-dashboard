package com.team1699.server;

import javax.net.ssl.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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

    private Socket socket;
    private ServerSocket server;
    private DataInputStream in;
    private DataOutputStream out;

    private Server(){
        this.port = 12345; //TODO Load port from config file

        try{
            server = new ServerSocket(port);
            System.out.println("----------Server Started----------");

            System.out.println("----------Waiting for Client----------");

            socket = server.accept();
            System.out.println("----------Client Connected----------");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            start();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(running){
            //TODO Check data coming from client. May have to parse byte array

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
