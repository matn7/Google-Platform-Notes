package com.pandatronik.builder.house;

public interface HouseBuilder {

    void buildWalls();
    void buildFloors();
    void buildRooms();
    void buildRoof();
    void buildWindow();
    void buildDoors();
    void buildGarage();

    HouseClassic getHouse();
}
