package com.pandatronik.visitor.activity;

import com.pandatronik.visitor.visitor.Visitor;

public interface Activity {

    void accept(Visitor visitor);
}
