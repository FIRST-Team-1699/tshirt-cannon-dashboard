package com.team1699.server;

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

    private Server(){

    }

    @Override
    public void run() {
        while(running){
            //Run server
            //TODO Add handle client connect
            //TODO Only one client should ever connect. Handle other connections
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
