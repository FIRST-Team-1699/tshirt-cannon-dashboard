package com.team1699.userIO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private static KeyManager instance;

    public static KeyManager getInstance(){
        if(instance == null){
            instance = new KeyManager();
        }
        return instance;
    }

    private final boolean[] keys;
    public boolean w, a, s, d;

    private KeyManager(){
        keys = new boolean[256];
    }

    public void tick(){
        w = keys[KeyEvent.VK_W];
        s = keys[KeyEvent.VK_S];
        a = keys[KeyEvent.VK_A];
        d = keys[KeyEvent.VK_D];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
