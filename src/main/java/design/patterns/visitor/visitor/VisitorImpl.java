package design.patterns.visitor.visitor;

import design.patterns.visitor.activity.Squash;
import design.patterns.visitor.activity.Treadmill;
import design.patterns.visitor.activity.Weights;

public class VisitorImpl implements Visitor {

    @Override
    public void visit(Treadmill treadmill) {
        System.out.println("Calories burned running: " + treadmill.getDistance() * 3);
    }

    @Override
    public void visit(Squash squash) {
        System.out.println("Calories burned squash: " + squash.getMinutesPlayed() * 2);
    }

    @Override
    public void visit(Weights weights) {
        System.out.println("Calories burned weight: " + (weights.getWeight() * weights.getReps()) * 0.01);
    }
}
