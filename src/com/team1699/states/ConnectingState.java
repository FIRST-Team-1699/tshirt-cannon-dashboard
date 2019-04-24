package com.team1699.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ConnectingState implements State{

    public ConnectingState(){
        StateManager.getInstance().addState("ConnectingState", this);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        //TODO Add moving dots on loading...
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.white);
        Font f = new Font("Dialog", Font.PLAIN, 42);
        g.setFont(f);
        g.drawString("Connecting...", 300, 300);
    }
}
