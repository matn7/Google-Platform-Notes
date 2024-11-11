package design.patterns.command.workshop.command;

import design.patterns.command.workshop.Robot;

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
