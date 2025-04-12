package design.patterns.ex2;

public class DoubleExtra extends Extra {

    public DoubleExtra(Order order, String label, double price) {
        super(order, label, price);
    }

    @Override
    public double getPrice() {
        return this.price * 2 + order.getPrice();
    }
}
