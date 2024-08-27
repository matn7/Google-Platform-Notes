package com.pandatronik.command;

import com.pandatronik.command.workshop.Robot;
import com.pandatronik.command.workshop.WorkshopApp;
import com.pandatronik.command.workshop.command.*;

public class Main {

    public static void main(String[] args) {

        Robot robot = new Robot();
        WorkshopApp workshopApp = new WorkshopApp();
//        workshopApp.addToQueue(new RobotTurnOnCommand(robot));
//        workshopApp.addToQueue(new RobotCutCommand(robot));
//        workshopApp.addToQueue(new RobotDrillCommand(robot));
//        workshopApp.addToQueue(new RobotTurnOffCommand(robot));
//
//        workshopApp.run();
//
//        CoffeeMaker coffeeMaker = new CoffeeMaker();
//        workshopApp.addToQueue(new CoffeeMakerTurnOnCommand(coffeeMaker));
//        workshopApp.addToQueue(new CoffeeMakerTurnOffCommand(coffeeMaker));
//        workshopApp.run();

        workshopApp.addToQueue(new RobotTurnOnCommand(robot));
        workshopApp.run();
        workshopApp.undoLastCommand();
    }

}
