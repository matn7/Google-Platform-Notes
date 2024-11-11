package design.patterns.factory.abstractfactory;

public class BlueFactory extends Factory {
    @Override
    public InfantryUnit createInfantryunit(UnitType type) {
        switch (type) {
            case RIFLEMAN -> {
                return new Rifleman(25, 0, 5);
            }
            default -> throw new UnsupportedOperationException("Unknown type");
        }
    }

    @Override
    public MechanizedUnit createMechanizedUnit(UnitType type) {
        switch (type) {
            case TANK -> {
                return new Tank(100, 0, 25);
            }
            default -> throw new UnsupportedOperationException("Unknown type");
        }
    }

    @Override
    public AirUnit createAirUnit(UnitType type) {
        switch (type) {
            case HELICOPTER -> {
                return new Helicopter(50, 0, 25);
            }
            default -> throw new UnsupportedOperationException("Unknown type");
        }
    }
}
