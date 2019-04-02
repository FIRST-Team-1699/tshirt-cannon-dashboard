package com.team1699.objects;

import com.team1699.graphics.Assets;
import com.team1699.utils.BarrelState;

import java.awt.Color;
import java.awt.Graphics;

public class Barrel extends DrawableObject {

    //This class represents a single barrel that will be drawn to the screen

    private final int barrelNumber;
    private BarrelState barrelState;

    public Barrel(final int barrelNumber, final BarrelState initBarrelState){
        this.barrelNumber = barrelNumber;
        this.barrelState = initBarrelState;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.drawImage(Assets.barrelEmpty, 100 + (50 * barrelNumber), 0, null);
    }
}
