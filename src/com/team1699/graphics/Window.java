package com.team1699.graphics;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

public class Window {

    //This is the class used to create the window all objects will be drawn to

    private static Window instance;

    public static Window getInstance() {
        if(instance == null){
            instance = new Window();
        }
        return instance;
}

    private final Dimension size;
    private final String title;

    private final JFrame frame;
    private final Canvas canvas;

    private Window(){
        this.size = new Dimension(800, 600); //TODO Change size
        this.title = "T-Shirt Cannon Dashboard";

        frame = new JFrame(title);
        frame.setSize(size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        //TODO Add icon

        canvas = new Canvas();
        canvas.setFocusable(false);

        frame.add(canvas);
        canvas.setPreferredSize(size);
        canvas.setMaximumSize(size);
        canvas.setMinimumSize(size);

        frame.pack();
    }

    public JFrame getFrame(){
        return frame;
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public Dimension getSize(){
        return size;
    }
}
