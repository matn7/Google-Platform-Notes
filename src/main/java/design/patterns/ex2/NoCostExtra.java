package design.patterns.ex2;

public class NoCostExtra extends Extra {

    public NoCostExtra(Order order, String label, double price) {
        super(order, label, price);
    }

    @Override
    public double getPrice() {
        return order.getPrice();
    }
}
