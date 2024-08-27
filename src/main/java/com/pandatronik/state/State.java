package com.pandatronik.state;

public interface State {
    void insertTheCoin(CoffeeMachine coffeeMachine);

    void pushTheButton(CoffeeMachine coffeeMachine);

    void takeACup(CoffeeMachine coffeeMachine);

    void returnTheCoin(CoffeeMachine coffeeMachine);
}
