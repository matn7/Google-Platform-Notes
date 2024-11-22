package multithreading;

import java.util.ArrayList;
import java.util.List;

public class ShoeWarehouse {

    public static void main(String[] args) {
        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();
        // Creating two threads
        Thread producerThread = new Thread(() -> {
            // Simulate receiving orders
            for (int i = 0; i < 5; i++) {
                shoeWarehouse.receiveOrder(new Order("Order " + (i + 1)));
                try {
                    // Simulating some delay between receiving orders
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer");

        Thread consumerThread = new Thread(() -> {
            // Simulate fulfilling orders
            for (int i = 0; i < 5; i++) {
                shoeWarehouse.fullfilOrder();
                try {
                    // Simulating some delay between fulfilling orders
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer");

        // Start both threads
        producerThread.start();
        consumerThread.start();

        // Wait for both threads to finish
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private List<Order> shippingItems;
    public static final String[] PRODUCT_LIST = {"Running Shoes", "Sandals", "Boots", "Slippers", "High Tops"};

    public ShoeWarehouse() {
        this.shippingItems = new ArrayList<>();
    }

    public synchronized void receiveOrder(Order item) {
        // Waiting for order to come (consume order)
        while (shippingItems.size() > 5) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        shippingItems.add(item);
        System.out.println("Incoming: " + item);
        notifyAll();
    }

    public synchronized Order fullfilOrder() {
        while (shippingItems.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Order item = shippingItems.remove(0);
        System.out.println(Thread.currentThread().getName() + " fulfilled: " + item);
        notifyAll();
        return item;
    }

    static class Order {
        String item;

        public Order(String item) {
            this.item = item;
        }
    }
}
