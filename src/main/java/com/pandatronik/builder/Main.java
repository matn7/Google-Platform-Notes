package com.pandatronik.builder;

import com.pandatronik.builder.house.*;

public class Main {

    public static void main(String[] args) {
        House house1 = new House.HouseBuilder()
                .buildWalls("Walls")
                .buildFloors("Floors")
                .build();

        System.out.println(house1);

        SmallHouseBuilder smallHouseBuilder = new SmallHouseBuilder();
        BigHouseBuilder bigHouseBuilder = new BigHouseBuilder();

        HouseDirector smallHouseDirector = new HouseDirector(smallHouseBuilder);
        HouseDirector bigHouseDirector = new HouseDirector(bigHouseBuilder);

        smallHouseDirector.buildHouse();
        bigHouseDirector.buildHouse();

        HouseClassic smallHouse = smallHouseDirector.getHouse();
        HouseClassic bigHouse = bigHouseDirector.getHouse();

        System.out.println(smallHouse);
        System.out.println(bigHouse);
    }
}
