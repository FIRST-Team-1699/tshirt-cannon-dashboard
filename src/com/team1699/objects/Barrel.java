package com.team1699.objects;

import com.team1699.Dashboard;
import com.team1699.graphics.Assets;
import com.team1699.states.DashboardState;
import com.team1699.utils.BarrelState;

import java.awt.Graphics;

public class Barrel extends DrawableObject {

    //This class represents a single barrel that will be drawn to the screen
    private static final int BARREL_CENTER_X = 390;
    private static final int BARREL_CENTER_Y = 290;
    private static final int BARREL_RADIUS = 75;
    private static final double SCALAR = DashboardState.BARREL_NUMBER % 2 == 0 ? (2 * Math.PI) / DashboardState.BARREL_NUMBER : (2 * Math.PI) / (DashboardState.BARREL_NUMBER + 1); //2 pi radians divided by total number of barrels (+1 for spacing reasons)

    private final int barrelNumber, barrelX, barrelY;
    private BarrelState barrelState;
    private int tickCount = 0;

    public Barrel(final int barrelNumber, final BarrelState initBarrelState){
        this.barrelNumber = barrelNumber;
        this.barrelState = initBarrelState;
        barrelX = (int) (BARREL_RADIUS * Math.sin(barrelNumber * SCALAR) + BARREL_CENTER_X);
        barrelY = (int) (BARREL_RADIUS * Math.cos(barrelNumber * SCALAR) + BARREL_CENTER_Y);
    }

    @Override
    public void tick() {
        //Code to test barrel switching
//        tickCount++;
//        if(tickCount % 6000 == 0){
//            if(barrelState == BarrelState.LOADED){
//                barrelState = BarrelState.EMPTY;
//            }else if(barrelState == BarrelState.EMPTY){
//                barrelState = BarrelState.ERROR;
//            }else if(barrelState == BarrelState.ERROR){
//                barrelState = BarrelState.LOADED;
//            }
//            tickCount = 0;
//        }
    }

    @Override
    public void render(Graphics g) {
        switch(barrelState){
            case EMPTY:
                g.drawImage(Assets.barrelEmpty, barrelX, barrelY, null);
                break;
            case LOADED:
                g.drawImage(Assets.barrelLoaded, barrelX, barrelY, null);
                break;
            case ERROR:
                g.drawImage(Assets.barrelError, barrelX, barrelY, null);
                break;
                default:
                    System.out.println("Unexpected Barrel State");
        }
    }

    public BarrelState getBarrelState() {
        return barrelState;
    }

    public void setBarrelState(final BarrelState barrelState){
        this.barrelState = barrelState;
    }
}
