package com.team1699.states;

import com.team1699.objects.Barrel;
import com.team1699.utils.BarrelState;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class DashboardState implements State {

    private final List<Barrel> barrelList; //List is prob best structure for now, but evaluate when actually used
    private static final int BARREL_NUMBER = 7; //TODO Move to a better place

    public DashboardState(){
        StateManager.getInstance().addState("DashboardState", this);

        barrelList = new ArrayList<>();
        for(int i = 0; i < BARREL_NUMBER; i++){
            System.out.println("Barrel " + i + " added.");
            barrelList.add(new Barrel(i, BarrelState.LOADED)); //TODO Eval init barrel state
        }
    }

    @Override
    public void tick() {
        barrelList.forEach(Barrel::tick);
    }

    @Override
    public void render(final Graphics g) {
        barrelList.forEach(e -> e.render(g));
    }
}
