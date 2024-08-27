package com.pandatronik.strategy;

import com.pandatronik.strategy.chef.Chef;
import com.pandatronik.strategy.eggcooker.HardBoiledEggCooker;
import com.pandatronik.strategy.eggcooker.SoftBoiledEggCooker;

public class Main {

    public static void main(String[] args) {
        Chef chef = new Chef("Majka");
        chef.setEggCooker(new HardBoiledEggCooker());
        chef.cook();

        chef.setEggCooker(new SoftBoiledEggCooker());
        chef.cook();
    }

}
