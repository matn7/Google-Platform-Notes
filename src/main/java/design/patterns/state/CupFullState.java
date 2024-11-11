package design.patterns.state;

public class CupFullState implements State {
    @Override
    public void insertTheCoin(CoffeeMachine coffeeMachine) {
        System.out.println("Take a Cup");
    }

    @Override
    public void pushTheButton(CoffeeMachine coffeeMachine) {
        System.out.println("Take a cup first");
    }

    @Override
    public void takeACup(CoffeeMachine coffeeMachine) {
        System.out.println("Cup taken");
        coffeeMachine.state = new NoCoinState();
    }

    @Override
    public void returnTheCoin(CoffeeMachine coffeeMachine) {
        System.out.println("Too late");
    }
}
