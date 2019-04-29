package com.team1699.graphics;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage barrelLoaded, barrelEmpty, barrelError, mathButtonPressed, mathButtonReleased, dashButtonPressed, dashButtonReleased;

    public static void init(){
        System.out.println("---------Loading Images---------");
        SpriteSheet testSheet = new SpriteSheet(ImageLoader.loadImage("/images/sheet.png"));
        SpriteSheet buttonSheet = new SpriteSheet(ImageLoader.loadImage("/images/buttons.png"));

        barrelEmpty = testSheet.crop(96, 0, 32, 32);
        barrelLoaded = testSheet.crop(64, 0, 32, 32);
        barrelError = testSheet.crop(0, 32, 32, 32);
        mathButtonReleased = buttonSheet.crop(0, 0, 200, 100);
        mathButtonPressed = buttonSheet.crop(200, 0, 200, 100);
        dashButtonReleased = buttonSheet.crop(0, 100, 200, 100);
        dashButtonPressed = buttonSheet.crop(200, 100, 200, 100);
    }

}
