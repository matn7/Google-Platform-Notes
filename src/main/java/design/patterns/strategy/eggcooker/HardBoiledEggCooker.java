package design.patterns.strategy.eggcooker;

public class HardBoiledEggCooker implements EggCooker {
    @Override
    public void cookEgg() {
        System.out.println("Hard eggs");
    }
}
