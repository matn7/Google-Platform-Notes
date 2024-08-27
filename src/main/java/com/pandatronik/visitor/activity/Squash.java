package com.pandatronik.visitor.activity;

import com.pandatronik.visitor.visitor.Visitor;

public class Squash implements Activity {

    private int minutesPlayed;

    public Squash(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
