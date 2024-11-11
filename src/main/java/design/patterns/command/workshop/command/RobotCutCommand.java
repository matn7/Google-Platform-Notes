package design.patterns.command.workshop.command;

import design.patterns.command.workshop.Robot;

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
