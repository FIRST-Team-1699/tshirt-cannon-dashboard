package com.team1699.states;

import com.team1699.objects.Barrel;
import com.team1699.utils.BarrelState;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DashboardState implements State {

    private final Map<Integer, Barrel> barrelMap;
    public static final int BARREL_NUMBER = 7; //TODO Move to a better place

    private boolean shotReady;

    public DashboardState(){
        StateManager.getInstance().addState("DashboardState", this);

        this.shotReady = false;

        barrelMap = new ConcurrentHashMap<>();
        for(int i = 1; i < BARREL_NUMBER + 1; i++){
            System.out.println("Barrel " + i + " added.");
            barrelMap.put(i, new Barrel(i, BarrelState.ERROR)); //TODO Eval init barrel state
        }
    }

    @Override
    public void init(){

    }

    @Override
    public void tick() {
        barrelMap.forEach((k, v) -> v.tick());
    }

    @Override
    public void render(final Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 800, 600);
        barrelMap.forEach((k, v) -> v.render(g));
        g.setColor(Color.lightGray);
        Font f = new Font("Dialog", Font.PLAIN, 24);
        g.setFont(f);
        g.drawString("Shot Ready:", 335, 100);
        if(shotReady){
            g.setColor(Color.GREEN);
        }else{
            g.setColor(Color.RED);
        }
        g.fillRect(380, 110, 50, 50);
    }

    public BarrelState getBarrelState(final int barrelNumber){
        return barrelMap.get(barrelNumber).getBarrelState();
    }

    public void setBarrelState(final int barrelNumber, final BarrelState state){
        barrelMap.get(barrelNumber).setBarrelState(state);
    }
}
