package com.team1699.objects;

import com.team1699.states.StateManager;
import com.team1699.userIO.MouseManager;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Button extends DrawableObject{

    private final BufferedImage pressedImg, releasedImg;
    private final String transition;
    private final Rectangle bounds;
    private final int x, y;
    private boolean pressed;

    public Button(final BufferedImage pressedImg, final BufferedImage releasedImg, final String transition, final int x, final int y){
        this.pressedImg = pressedImg;
        this.releasedImg = releasedImg;
        this.transition = transition;
        this.x = x;
        this.y = y;
        this.bounds = new Rectangle(x, y, releasedImg.getWidth(), releasedImg.getHeight());
        pressed = false;
    }

    @Override
    public void tick() {
        //TODO Make it so button picture changes
        if(bounds.contains(MouseManager.getInstance().getMouseX(), MouseManager.getInstance().getMouseY()) && MouseManager.getInstance().isLeftPressed()){
            pressed = true;
        }

        if(pressed){
            //TODO Change sleep to make it feel better
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StateManager.getInstance().setCurrentState(transition);
        }
    }

    @Override
    public void render(Graphics g) {
        if(pressed){
            g.drawImage(pressedImg, x, y, null);
        }else{
            g.drawImage(releasedImg, x, y, null);
        }
    }
}
