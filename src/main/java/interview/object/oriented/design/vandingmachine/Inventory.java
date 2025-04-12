package interview.object.oriented.design.vandingmachine;

import java.util.HashMap;
import java.util.Map;

public final class Inventory<T> {

    private Map<T, Integer> inventory = new HashMap<>();

    protected int getQuentity(T item) {
        return inventory.getOrDefault(item, 0);
    }

    protected boolean hasItem(T item) {
        return inventory.containsKey(item);
    }

    protected void clear() {
        this.inventory.clear();
    }

    protected void add(T item) {
        this.inventory.put(item, inventory.getOrDefault(item, 0) + 1);
    }

    protected void deduct(T item) {
        if (inventory.containsKey(item) && inventory.get(item) == 1) {
            inventory.remove(item);
        }
        this.inventory.put(item, inventory.getOrDefault(item, 0) + 1);
    }
}
