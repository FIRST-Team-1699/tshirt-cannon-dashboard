package com.team1699.server;

import com.team1699.states.DashboardState;
import com.team1699.states.State;
import com.team1699.states.StateManager;
import com.team1699.utils.BarrelState;
import com.team1699.utils.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
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

    private boolean isConnected;

    private Server(){
        this.port = 12345; //TODO Load port from config file
        this.isConnected = false;
    }

    //TODO Handle disconnect
    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            System.out.println("----------Server Started----------");
        } catch(BindException e) {
            System.out.println("----------Server Already Created----------");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            System.out.println("----------Waiting for Client----------");

            socket = server.accept();
            System.out.println("----------Client Connected----------");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            this.isConnected = true;
        }  catch(IOException e){
            e.printStackTrace();
        }

        while(running){
            //TODO Check data coming from client. May have to parse byte array
            byte[] input = new byte[128];
            String clientMsg = "";
            try{
                int inputLength = in.read(input);
                clientMsg = new String(input);
                System.out.println(clientMsg);
            } catch(SocketException e){
                this.isConnected = false;
                StateManager.getInstance().setCurrentState("ConnectingState");
            } catch(IOException e){
                e.printStackTrace();
            }

            //TODO Add exception? that is thrown when invalid text is passed to server
            //Data format from client - Barrel# BarrelState (0 - Empty, 1 - Full, 2 - Error) - Ex. 3 0 - Barrel 3 is empty
            if(!clientMsg.equals("")){ //We got a reply
                String[] splitInput = clientMsg.split(" ");
                //TODO try catch parses
                int barrelNum = Integer.parseInt(splitInput[0]);
                int barrelStateNum = Integer.parseInt(Utils.cleanTextContent(splitInput[1]));

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
                    System.out.println("----------Switching from " + ((DashboardState) StateManager.getInstance().getCurrentState()).getBarrelState(barrelNum) + " to " + barrelState + "----------");
                    ((DashboardState) StateManager.getInstance().getCurrentState()).setBarrelState(barrelNum, barrelState);
                }
            }
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    public boolean isRunning() {
        return running;
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
            thread.interrupt();
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
