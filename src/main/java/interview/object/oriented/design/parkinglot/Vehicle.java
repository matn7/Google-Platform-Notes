package interview.object.oriented.design.parkinglot;

public class Vehicle {

    private final int spotSize;

    public Vehicle(int spotSize) {
        this.spotSize = spotSize;
    }

    public int getSpotSize() {
        return spotSize;
    }
}
