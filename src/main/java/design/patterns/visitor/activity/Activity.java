package design.patterns.visitor.activity;

import design.patterns.visitor.visitor.Visitor;

public interface Activity {

    void accept(Visitor visitor);
}
