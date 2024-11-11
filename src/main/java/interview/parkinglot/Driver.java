package interview.parkinglot;

public class Driver {

    private final int id;
    private final Vehicle vehicle;
    private int paymentDue;

    public Driver(int id, Vehicle vehicle) {
        this.id = id;
        this.vehicle = vehicle;
        this.paymentDue = 0;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getId() {
        return id;
    }

    public void charge(int amount) {
        paymentDue += amount;
    }
}
