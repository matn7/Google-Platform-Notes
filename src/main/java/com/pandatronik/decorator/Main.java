package com.pandatronik.decorator;

public class Main {

    public static void main(String[] args) {
        generateMap();
    }

    private static void generateMap() {
        Terrain terrain = new Plain();
        Terrain terrain1 = new Hill();
        Terrain terrain2 = new SwampDecorator(new Hill());
        Terrain terrain3 = new SwampDecorator(new ForestDecorator(new Plain()));
        Terrain terrain4 = new RoadDecorator(new Hill());

        System.out.println(terrain2.fuelCost());
        System.out.println(terrain3.fuelCost());
        System.out.println(terrain4.fuelCost() + ": " + terrain4.getDescription());
    }

}
