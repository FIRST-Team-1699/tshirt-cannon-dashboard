package com.team1699.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    //This class loads images

    public static BufferedImage loadImage(final String path){
        BufferedImage returnImage = null;
        try{
            returnImage = ImageIO.read(ImageLoader.class.getResource(path));
        }catch(IOException e){
            e.printStackTrace();
        }
        return returnImage;
    }
}
