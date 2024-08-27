package com.pandatronik.command.workshop.command;

import com.pandatronik.command.workshop.Robot;

public class RobotTurnOnCommand implements Command {

    private Robot robot;

    public RobotTurnOnCommand(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void execute() {
        this.robot.turnOn();
    }

    @Override
    public void undo() {
        this.robot.turnOff();
    }
}
