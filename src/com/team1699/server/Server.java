package com.team1699.server;

import com.team1699.states.DashboardState;
import com.team1699.states.State;
import com.team1699.states.StateManager;
import com.team1699.utils.BarrelState;

import javax.net.ssl.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;

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
            byte[] input = new byte[128];
            String clientMsg = "";
            try{
                int inputLength = in.read(input);
                clientMsg = Base64.getEncoder().encodeToString(input);
            } catch(IOException e){
                e.printStackTrace();
            }

            //Data format from client - Barrel# BarrelState (0 - Empty, 1 - Full, 2 - Error) - Ex. 3 0 - Barrel 3 is empty
            if(!clientMsg.equals("")){ //We got a reply TODO I think. Need to check that it will actually do/not do things when we think it will
                String[] splitInput = clientMsg.split(" ");
                //TODO try catch parses
                int barrelNum = Integer.parseInt(splitInput[0]);
                int barrelStateNum = Integer.parseInt(splitInput[1]);

                BarrelState barrelState;

                switch (barrelStateNum) {
                    case 0:
                        barrelState = BarrelState.EMPTY;
                        break;
                    case 1:
                        barrelState = BarrelState.LOADED;
                        break;
                    default:
                        barrelState = BarrelState.ERROR;
                        break;
                }

                if(StateManager.getInstance().getCurrentState() instanceof DashboardState){
                    ((DashboardState) StateManager.getInstance().getCurrentState()).setBarrelState(barrelNum, barrelState);
                }
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
