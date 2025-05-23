package interview.object.oriented.design.vandingmachine;

public enum Item {
    SKITTLES("Skittles", 15), TWIX("Twix", 35);

    private final String name;
    private final int price;

    private Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
