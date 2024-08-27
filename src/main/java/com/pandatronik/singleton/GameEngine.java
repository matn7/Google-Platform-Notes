package com.pandatronik.singleton;

import java.io.Serializable;

public class GameEngine implements Serializable {

    private static final long serialVersionUID = 23232323;

    public int hp = 100;
    private String characterName = "";

    private static GameEngine instance = new GameEngine();

    private GameEngine() {

    }

    public void run() {
        while (true) {
            // waiting for input
            // change game state
            // render graphic
        }
    }

    public static GameEngine getInstance() {
        return instance;
    }

    protected Object readResolve() {
        return getInstance();
    }

}
