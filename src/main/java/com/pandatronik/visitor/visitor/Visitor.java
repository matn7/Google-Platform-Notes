package com.pandatronik.visitor.visitor;

import com.pandatronik.visitor.activity.Squash;
import com.pandatronik.visitor.activity.Treadmill;
import com.pandatronik.visitor.activity.Weights;

public interface Visitor {

    void visit(Treadmill treadmill);
    void visit(Squash squash);
    void visit(Weights weights);

}
