package interview.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingGarage {

    private final int floorCount;
    private final int spotsPerFloor;
    private final List<ParkingFloor> parkingFloors;

    public ParkingGarage(int floorCount, int spotsPerFloor) {
        this.floorCount = floorCount;
        this.spotsPerFloor = spotsPerFloor;
        this.parkingFloors = new ArrayList<>();
        for (int i = 0; i < floorCount; i++) {
            parkingFloors.add(new ParkingFloor(spotsPerFloor));
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : parkingFloors) {
            if (floor.parkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : parkingFloors) {
            if (floor.getVehicleSpots(vehicle) != null) {
                floor.removeVehicle(vehicle);
                return true;
            }
        }
        return false;
    }
}
