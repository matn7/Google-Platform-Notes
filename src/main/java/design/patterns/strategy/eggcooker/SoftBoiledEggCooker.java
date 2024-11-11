package design.patterns.strategy.eggcooker;

public class SoftBoiledEggCooker implements EggCooker {
    @Override
    public void cookEgg() {
        System.out.println("Soft eggs");
    }
}
