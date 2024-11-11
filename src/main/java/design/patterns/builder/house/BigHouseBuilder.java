package design.patterns.builder.house;

public class BigHouseBuilder implements HouseBuilder {
    private HouseClassic house;

    public BigHouseBuilder() {
        this.house = new HouseClassic();
    }

    @Override
    public void buildWalls() {
        this.house.setWalls("Big Walls");
    }

    @Override
    public void buildFloors() {
        this.house.setFloors("Big Floors");
    }

    @Override
    public void buildRooms() {
        this.house.setRooms("Big Rooms");
    }

    @Override
    public void buildRoof() {
        this.house.setRoofs("Big Roof");
    }

    @Override
    public void buildWindow() {
        this.house.setWindows("Big Window");
    }

    @Override
    public void buildDoors() {
        this.house.setDoors("Big Door");
    }

    @Override
    public void buildGarage() {
        this.house.setGarage("Big Garage");
    }

    @Override
    public HouseClassic getHouse() {
        return house;
    }
}
