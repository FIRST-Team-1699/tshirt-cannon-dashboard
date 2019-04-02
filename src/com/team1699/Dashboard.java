package com.team1699;

import com.team1699.graphics.Window;
import com.team1699.states.DashboardState;
import com.team1699.states.StateManager;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Dashboard implements Runnable {

    //This is the main class that will run the Dashboard

    private static Dashboard instance;

    public static Dashboard getInstance(){
        if(instance == null){
            instance = new Dashboard();
        }
        return instance;
    }

    //Thread vars
    private Thread thread;
    private boolean running;

    //Graphics vars
    private BufferStrategy bs;
    private Graphics g;

    private Dashboard(){
        //Inits states
        new DashboardState();

        //Sets current state to dashboard TODO Change to another state if needed
        StateManager.getInstance().setCurrentState("DashboardState");

        Window.getInstance(); //Inits window
    }

    @Override
    public void run() {
        while(running){
            //Run Dashboard
            tick();
            render();
        }
    }

    private void tick(){
        StateManager.getInstance().tick();
    }

    private void render(){
        //Generates stuff to allow drawing to the screen
        bs = Window.getInstance().getCanvas().getBufferStrategy();

        if(bs == null){
            Window.getInstance().getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        //Clears previous screen
        g.clearRect(0, 0, (int) Window.getInstance().getSize().getWidth(), (int) Window.getInstance().getSize().getHeight());

        //All actual rendering should occur below this point
        StateManager.getInstance().render(g);

        bs.show();
        g.dispose();
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