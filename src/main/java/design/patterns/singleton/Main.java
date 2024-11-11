package design.patterns.singleton;

public class Main {

    public static void main(String[] args) {
        GameEngine gameEngine1 = GameEngine.getInstance();
        GameEngine gameEngine2 = GameEngine.getInstance();

        System.out.println(gameEngine1 == gameEngine2);
    }

}
