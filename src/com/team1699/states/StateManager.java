package com.team1699.states;

import com.team1699.userIO.KeyManager;

import java.awt.Graphics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StateManager {

    private static StateManager instance;

    public static StateManager getInstance() {
        if (instance == null) {
            instance = new StateManager();
        }
        return instance;
    }

    private final Map<String, State> stateMap;
    private State currentState;

    private StateManager() {
        stateMap = new ConcurrentHashMap<>();
    }

    public synchronized void addState(final String stateName, final State state) {
        stateMap.put(stateName, state);
        System.out.println(stateName + " added to state map");
    }

    public synchronized void setCurrentState(final String name) {
        System.out.println("Set new state: " + name);
        currentState = stateMap.get(name);
        currentState.init();
    }

    public void tick() {
        KeyManager.getInstance().tick();
        try {
            currentState.tick();
        } catch (NullPointerException e) {
            System.err.println("Current State is Null");
        }
    }

    public void render(final Graphics g) {
        try {
            currentState.render(g);
        } catch (NullPointerException e) {
            System.err.println("Current State is Null");
        }
    }

    public State getCurrentState() {
        return currentState;
    }
}
