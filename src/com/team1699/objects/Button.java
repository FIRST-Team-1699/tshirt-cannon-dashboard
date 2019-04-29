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
    private boolean hovered;

    public Button(final BufferedImage pressedImg, final BufferedImage releasedImg, final String transition, final int x, final int y){
        this.pressedImg = pressedImg;
        this.releasedImg = releasedImg;
        this.transition = transition;
        this.x = x;
        this.y = y;
        this.bounds = new Rectangle(x, y, releasedImg.getWidth(), releasedImg.getHeight());
        hovered = false;
    }

    @Override
    public void tick() {
        hovered = contains(MouseManager.getInstance().getMouseX(), MouseManager.getInstance().getMouseY());

        if(hovered && MouseManager.getInstance().isLeftPressed()){
            StateManager.getInstance().setCurrentState(transition);
        }
    }

    @Override
    public void render(Graphics g) {
        if(hovered){
            g.drawImage(pressedImg, x, y, null);
        }else{
            g.drawImage(releasedImg, x, y, null);
        }
    }

    private boolean contains(final int mouseX, final int mouseY){
        return (mouseX >= bounds.x && mouseX <= (bounds.x + bounds.width)) && (mouseY >= bounds.y && mouseY <= (bounds.y + bounds.height));
    }
}
