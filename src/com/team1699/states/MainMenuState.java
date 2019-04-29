package com.team1699.states;

import com.team1699.graphics.Assets;
import com.team1699.objects.Button;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class MainMenuState implements State{

    private final List<Button> buttons;

    public MainMenuState(){
        StateManager.getInstance().addState("MainMenuState", this);

        //Button List
        buttons = new ArrayList<>();

        //Add buttons TODO Change positions to make it look nice
        buttons.add(new Button(Assets.mathButtonPressed, Assets.mathButtonReleased, "MathState", 100, 100));
        buttons.add(new Button(Assets.dashButtonPressed, Assets.dashButtonReleased, "DashboardState", 400, 100));
    }

    @Override
    public void tick() {
        buttons.forEach(Button::tick);
    }

    @Override
    public void render(Graphics g) {
        buttons.forEach(e -> e.render(g));
    }
}
