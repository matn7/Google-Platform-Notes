package com.pandatronik.visitor;

import com.pandatronik.visitor.activity.Activity;
import com.pandatronik.visitor.activity.Squash;
import com.pandatronik.visitor.activity.Treadmill;
import com.pandatronik.visitor.activity.Weights;
import com.pandatronik.visitor.visitor.Visitor;
import com.pandatronik.visitor.visitor.VisitorImpl;

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
