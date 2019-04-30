package com.team1699.states;

import java.awt.Graphics;


//TODO add init method
public interface State {
    void init();
    void tick();
    void render(final Graphics g);
}
