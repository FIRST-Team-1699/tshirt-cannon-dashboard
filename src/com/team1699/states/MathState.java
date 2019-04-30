package com.team1699.states;

import java.awt.Graphics;

public class MathState implements State{

    public MathState(){
        StateManager.getInstance().addState("MathState", this);
    }

    @Override
    public void init(){

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
