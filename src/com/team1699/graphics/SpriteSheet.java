package com.team1699.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    //This class manages sprite sheets

    private final BufferedImage sheet;

    public SpriteSheet(final BufferedImage sheet){
        this.sheet = sheet;
    }

    public BufferedImage crop(final int x, final int y, final int width, final int height){
        return sheet.getSubimage(x, y, width, height);
    }
}
