package design.patterns.command.workshop.command;

import design.patterns.command.workshop.Robot;

public class RobotTurnOffCommand implements Command {

    private Robot robot;

    public RobotTurnOffCommand(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void execute() {
        this.robot.turnOff();
    }

    @Override
    public void undo() {
        this.robot.turnOn();
    }
}
