package com.pandatronik.state;

public class NoCoinState implements State {
    @Override
    public void insertTheCoin(CoffeeMachine coffeeMachine) {
        System.out.println("Coin inserted");
        coffeeMachine.state = new CoinInsertedState();
    }

    @Override
    public void pushTheButton(CoffeeMachine coffeeMachine) {
        System.out.println("Insert a coin");
    }

    @Override
    public void takeACup(CoffeeMachine coffeeMachine) {
        System.out.println("Insert a coin");
    }

    @Override
    public void returnTheCoin(CoffeeMachine coffeeMachine) {
        System.out.println("Insert a coin");
    }
}
