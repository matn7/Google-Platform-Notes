package design.patterns.visitor;

import design.patterns.visitor.activity.Activity;
import design.patterns.visitor.activity.Squash;
import design.patterns.visitor.activity.Treadmill;
import design.patterns.visitor.activity.Weights;
import design.patterns.visitor.visitor.Visitor;
import design.patterns.visitor.visitor.VisitorImpl;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Treadmill treadmill = new Treadmill(200);
        Squash squash = new Squash(45);
        Weights weights = new Weights(50, 10);

        Visitor visitor = new VisitorImpl();

        treadmill.accept(visitor);
        squash.accept(visitor);
        weights.accept(visitor);

        System.out.println("-------------------");

        List<Activity> activityList = Arrays.asList(treadmill, squash, weights);
        activityList.forEach(activity -> activity.accept(visitor));
    }

}
