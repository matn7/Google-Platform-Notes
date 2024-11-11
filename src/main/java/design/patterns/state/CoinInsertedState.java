package design.patterns.state;

public class CoinInsertedState implements State {
    @Override
    public void insertTheCoin(CoffeeMachine coffeeMachine) {
        System.out.println("Coin already inserted");
    }

    @Override
    public void pushTheButton(CoffeeMachine coffeeMachine) {
        System.out.println("Pouring coffee");
        coffeeMachine.state = new CupFullState();
    }

    @Override
    public void takeACup(CoffeeMachine coffeeMachine) {
        System.out.println("Push the button");
    }

    @Override
    public void returnTheCoin(CoffeeMachine coffeeMachine) {
        System.out.println("Return coin");
        coffeeMachine.state = new NoCoinState();
    }
}
