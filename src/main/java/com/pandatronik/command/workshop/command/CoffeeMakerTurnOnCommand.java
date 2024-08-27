package com.pandatronik.command.workshop.command;

import com.pandatronik.command.workshop.CoffeeMaker;

public class CoffeeMakerTurnOnCommand implements Command {

    private CoffeeMaker coffeeMaker;

    public CoffeeMakerTurnOnCommand(CoffeeMaker coffeeMaker) {
        this.coffeeMaker = coffeeMaker;
    }

    @Override
    public void execute() {
        this.coffeeMaker.turnOn();
    }

    @Override
    public void undo() {
        this.coffeeMaker.turnOff();
    }
}
