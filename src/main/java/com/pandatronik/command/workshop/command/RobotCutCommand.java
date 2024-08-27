package com.pandatronik.command.workshop.command;

import com.pandatronik.command.workshop.Robot;

public class RobotCutCommand implements Command {

    private Robot robot;

    public RobotCutCommand(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void execute() {
        this.robot.cut();
    }

    @Override
    public void undo() {

    }
}
