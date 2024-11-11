package design.patterns.strategy;

import design.patterns.strategy.chef.Chef;
import design.patterns.strategy.eggcooker.HardBoiledEggCooker;
import design.patterns.strategy.eggcooker.SoftBoiledEggCooker;

public class Main {

    public static void main(String[] args) {
        Chef chef = new Chef("Majka");
        chef.setEggCooker(new HardBoiledEggCooker());
        chef.cook();

        chef.setEggCooker(new SoftBoiledEggCooker());
        chef.cook();
    }

}
