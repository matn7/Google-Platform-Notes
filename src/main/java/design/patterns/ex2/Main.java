package design.patterns.ex2;

public class Main {

    public static void main(String[] args) {
        Order fourPizza = new Pizza("4 seasons", 10);
        fourPizza = new RegularExtra(fourPizza, "pepperoni", 4);
        fourPizza = new DoubleExtra(fourPizza, "mozarella", 2);

        System.out.println(fourPizza.getPrice() + " : " + fourPizza.getLabel());
    }

}
