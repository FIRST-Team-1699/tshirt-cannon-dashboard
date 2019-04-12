package com.team1699.graphics;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage barrelLoaded, barrelEmpty, barrelError;

    public static void init(){
        System.out.println("---------Loading Images---------");
        SpriteSheet testSheet = new SpriteSheet(ImageLoader.loadImage("/images/sheet.png"));

        barrelEmpty = testSheet.crop(96, 0, 32, 32);
        barrelLoaded = testSheet.crop(64, 0, 32, 32);
        barrelError = testSheet.crop(0, 32, 32, 32);
    }

}
