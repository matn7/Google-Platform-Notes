package com.pandatronik.command.workshop.command;

import com.pandatronik.command.workshop.Robot;

public class RobotDrillCommand implements Command {

    private Robot robot;

    public RobotDrillCommand(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void execute() {
        this.robot.drill();
    }

    @Override
    public void undo() {

    }
}
