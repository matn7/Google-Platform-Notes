package com.pandatronik.state;

public class CoffeeMachine {

    State state;

    public CoffeeMachine() {
        this.state = new NoCoinState();
    }

    public void insertTheCoin() {
        state.insertTheCoin(this);
    }

    public void pushTheButton() {
        state.pushTheButton(this);

    }

    public void takeACup() {
        state.takeACup(this);
    }

    public void returnTheCoin() {
        state.returnTheCoin(this);
    }

}
