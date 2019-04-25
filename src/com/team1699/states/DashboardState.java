package com.team1699.states;

import com.team1699.objects.Barrel;
import com.team1699.utils.BarrelState;

import java.awt.Graphics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DashboardState implements State {

    private final Map<Integer, Barrel> barrelMap;
    private static final int BARREL_NUMBER = 7; //TODO Move to a better place

    public DashboardState(){
        StateManager.getInstance().addState("DashboardState", this);

        barrelMap = new ConcurrentHashMap<>();
        for(int i = 1; i < BARREL_NUMBER + 1; i++){
            System.out.println("Barrel " + i + " added.");
            barrelMap.put(i, new Barrel(i, BarrelState.LOADED)); //TODO Eval init barrel state
        }
    }

    @Override
    public void tick() {
        barrelMap.forEach((k, v) -> v.tick());
    }

    @Override
    public void render(final Graphics g) {
        barrelMap.forEach((k, v) -> v.render(g));
    }

    public BarrelState getBarrelState(final int barrelNumber){
        return barrelMap.get(barrelNumber).getBarrelState();
    }

    public void setBarrelState(final int barrelNumber, final BarrelState state){
        barrelMap.get(barrelNumber).setBarrelState(state);
    }
}
