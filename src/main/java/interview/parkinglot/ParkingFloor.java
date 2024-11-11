package interview.parkinglot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ParkingFloor {

    private final int spotCount;
    private final Map<Vehicle, int[]> vehicleMap;
    private final int[] spots;

    public ParkingFloor(int spotCount) {
        this.spotCount = spotCount;
        this.vehicleMap = new HashMap<>();
        this.spots = new int[spotCount];
    }

    public boolean parkVehicle(Vehicle vehicle) {
        int size = vehicle.getSpotSize();
        int l = 0;
        int r = 0;
        while (r < spots.length) {
            if (spots[r] != 0) {
                l = r + 1;
            }
            if (r - l + 1 == size) {
                // we found enough spots, park the vehicle
                for (int k = l; k < r + 1; k++) {
                    spots[k] = 1;
                }
                vehicleMap.put(vehicle, new int[] {l, r});
                return true;
            }
            r++;
        }
        return false;
    }

    public void removeVehicle(Vehicle vehicle) {
        int[] partSpot = vehicleMap.get(vehicle);
        int start = partSpot[0];
        int end = partSpot[1];
        for (int i = start; i < end + 1; i++) {
            spots[i] = 0;
        }
        vehicleMap.remove(vehicle);
    }

    public int[] getParkingSpots() {
        return spots;
    }

    public int[] getVehicleSpots(Vehicle vehicle) {
        if (!vehicleMap.containsKey(vehicle)) {
            return null;
        }
        return vehicleMap.get(vehicle);
    }


}
