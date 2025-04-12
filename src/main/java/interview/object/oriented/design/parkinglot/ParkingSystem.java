package interview.object.oriented.design.parkinglot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ParkingSystem {

    private final ParkingGarage parkingGarage;
    private final int hourlyRate;
    private final Map<Integer, Integer> timeParked;

    public ParkingSystem(ParkingGarage parkingGarage, int hourlyRate) {
        this.parkingGarage = parkingGarage;
        this.hourlyRate = hourlyRate;
        this.timeParked = new HashMap<>();
    }

    public boolean parkVehicle(Driver driver) {
        Calendar now = Calendar.getInstance();
        int currentHour = now.get(Calendar.HOUR_OF_DAY);
        boolean isParked = parkingGarage.parkVehicle(driver.getVehicle());
        if (isParked) {
            timeParked.put(driver.getId(), currentHour);
        }
        return isParked;
    }

    public boolean removeVehicle(Driver driver) {
        if (!timeParked.containsKey(driver.getId())) {
            return false;
        }
        Calendar now = Calendar.getInstance();
        int currentHour = now.get(Calendar.HOUR_OF_DAY);
        int parked = currentHour - timeParked.get(driver.getId());;
        driver.charge(parked * hourlyRate);
        timeParked.remove(driver.getId());
        return parkingGarage.removeVehicle(driver.getVehicle());
    }
}
