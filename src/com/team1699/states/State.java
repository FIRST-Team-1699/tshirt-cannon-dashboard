package com.team1699.states;

import java.awt.Graphics;

public interface State {
    void tick();
    void render(final Graphics g);
}
