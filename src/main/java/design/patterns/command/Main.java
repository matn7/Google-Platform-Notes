package design.patterns.command;

import design.patterns.command.workshop.Robot;
import design.patterns.command.workshop.WorkshopApp;
import design.patterns.command.workshop.command.RobotTurnOnCommand;

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
