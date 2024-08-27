package com.pandatronik.factory.abstractfactory;

public abstract class Factory {
    abstract public InfantryUnit createInfantryunit(UnitType type);
    abstract public MechanizedUnit createMechanizedUnit(UnitType type);
    abstract public AirUnit createAirUnit(UnitType type);
}
