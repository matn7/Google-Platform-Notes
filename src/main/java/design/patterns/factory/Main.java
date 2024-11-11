package design.patterns.factory;

import design.patterns.factory.units.Factory;
import design.patterns.factory.units.Unit;
import design.patterns.factory.units.UnitFactory;
import design.patterns.factory.units.UnitType;

public class Main {

    public static void main(String[] args) {
        Factory factory = new UnitFactory();
        Unit tank = factory.createunit(UnitType.TANK);
        Unit infantryman = factory.createunit(UnitType.RIFLEMAN);


    }

}
