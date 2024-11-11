package design.patterns.visitor.visitor;

import design.patterns.visitor.activity.Squash;
import design.patterns.visitor.activity.Treadmill;
import design.patterns.visitor.activity.Weights;

public interface Visitor {

    void visit(Treadmill treadmill);
    void visit(Squash squash);
    void visit(Weights weights);

}
