package com.team1699.objects;

import java.awt.Graphics;

public interface DrawableObject {
    //render is used for code relating to drawing the object to the screen
    //tick is used for all other actions that should occur each time the screen loops
    public abstract void tick();
    public abstract void render(Graphics g);
}
