package com.team1699.server;

public class Client implements Runnable{

    private Thread thread;
    private boolean running;

    protected Client(){

    }

    @Override
    public void run() {
        while(running){
            //Run client
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
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
