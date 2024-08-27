package com.pandatronik.builder.house;

public class HouseDirector {

    private HouseBuilder houseBuilder;

    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public void buildHouse() {
        houseBuilder.buildWalls();
        houseBuilder.buildFloors();
        houseBuilder.buildRooms();
        houseBuilder.buildRoof();
        houseBuilder.buildWindow();
        houseBuilder.buildDoors();
        houseBuilder.buildGarage();
    }

    public HouseClassic getHouse() {
        return this.houseBuilder.getHouse();
    }

}
