package design.patterns.command.workshop.command;

import design.patterns.command.workshop.Robot;

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
