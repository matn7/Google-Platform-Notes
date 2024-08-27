package com.pandatronik.factory;

import com.pandatronik.factory.units.*;

public class Main {

    public static void main(String[] args) {
        Factory factory = new UnitFactory();
        Unit tank = factory.createunit(UnitType.TANK);
        Unit infantryman = factory.createunit(UnitType.RIFLEMAN);


    }

}
