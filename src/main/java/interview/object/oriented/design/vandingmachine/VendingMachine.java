package interview.object.oriented.design.vandingmachine;

import java.util.List;
import java.util.Map;

public class VendingMachine implements Selector {

    private final Inventory<Coin> coinInventory = new Inventory<>();
    private final Inventory<Item> itemInventory = new Inventory<>();
    private int totalSales;
    private int currentBalance;

    private Item currentItem;

    public VendingMachine() {
        initMachine();
    }

    private void initMachine() {
        System.out.println("Initializing the vending machine with coins and items ...");
    }

    @Override
    public int checkPriceBtn(Item item) {
        return 0;
    }

    @Override
    public void insertCoinBtn(Coin coin) {

    }

    @Override
    public Map<Item, List<Coin>> buyBtn() {
        return null;
    }

    @Override
    public List<Coin> refundBtn() {
        return null;
    }

    @Override
    public void resetBtn() {

    }
}
