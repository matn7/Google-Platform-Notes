package design.patterns.factory.abstractfactory;

public class RedFactory extends Factory {
    @Override
    public InfantryUnit createInfantryunit(UnitType type) {
        switch (type) {
            case RIFLEMAN -> {
                return new Rifleman(20, 0, 6);
            }
            default -> throw new UnsupportedOperationException("Unknown type");
        }
    }

    @Override
    public MechanizedUnit createMechanizedUnit(UnitType type) {
        switch (type) {
            case TANK -> {
                return new Tank(75, 0, 35);
            }
            default -> throw new UnsupportedOperationException("Unknown type");
        }
    }

    @Override
    public AirUnit createAirUnit(UnitType type) {
        switch (type) {
            case HELICOPTER -> {
                return new Helicopter(20, 0, 30);
            }
            default -> throw new UnsupportedOperationException("Unknown type");
        }
    }
}
