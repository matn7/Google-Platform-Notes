package com.pandatronik.command.workshop.command;

import com.pandatronik.command.workshop.CoffeeMaker;

public class CoffeeMakerTurnOffCommand implements Command {

    private CoffeeMaker coffeeMaker;

    public CoffeeMakerTurnOffCommand(CoffeeMaker coffeeMaker) {
        this.coffeeMaker = coffeeMaker;
    }

    @Override
    public void execute() {
        this.coffeeMaker.turnOff();
    }

    @Override
    public void undo() {
        this.coffeeMaker.turnOn();
    }
}
