package design.patterns.ex2;

public abstract class Extra implements Order {
    protected Order order;
    protected String label;
    protected double price;

    public Extra(Order order, String label, double price) {
        this.order = order;
        this.label = label;
        this.price = price;
    }

    // price delegated to other implementation
    public abstract double getPrice();

    public String getLabel() {
        return order.getLabel() + ", " + this.label;
    }
}
