package com.pandatronik.singleton;

public enum GameEngineEnum {
    INSTANCE("Duke");

    private int hp = 100;
    private String characterName = "";

    private GameEngineEnum(String name) {
        this.characterName = name;
    }

    public void run() {
        while (true) {
            // waiting for input
            // change game state
            // render graphic
        }
    }
}
