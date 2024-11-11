package design.patterns.builder.house;

public class SmallHouseBuilder implements HouseBuilder {
    private HouseClassic house;

    public SmallHouseBuilder() {
        this.house = new HouseClassic();
    }

    @Override
    public void buildWalls() {
        this.house.setWalls("Small Walls");
    }

    @Override
    public void buildFloors() {
        this.house.setFloors("Small Floors");
    }

    @Override
    public void buildRooms() {
        this.house.setRooms("Small Rooms");
    }

    @Override
    public void buildRoof() {
        this.house.setRoofs("Small Roof");
    }

    @Override
    public void buildWindow() {
        this.house.setWindows("Small Window");
    }

    @Override
    public void buildDoors() {
        this.house.setDoors("Small Door");
    }

    @Override
    public void buildGarage() {
        this.house.setGarage("Small Garage");
    }

    @Override
    public HouseClassic getHouse() {
        return house;
    }
}
