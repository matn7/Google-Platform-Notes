package multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue {

    public static void main(String[] args) throws InterruptedException {
        BoundedQueue queue1 = new BoundedQueue(5);
        queue1.produce(new Item());
        queue1.produce(new Item());
        queue1.produce(new Item());
        queue1.produce(new Item());
        queue1.produce(new Item());
        System.out.println();
        queue1.consume();
        queue1.produce(new Item());
    }

    Queue<Item> queue = new LinkedList<>();
    private Semaphore full = new Semaphore(0);
    private Semaphore empty;
    private Lock lock = new ReentrantLock();

    public BoundedQueue(int capacity) {
        this.empty = new Semaphore(capacity);
    }

    public void produce(Item item) throws InterruptedException {
        empty.acquire();
        lock.lock();
        try {
            queue.offer(item);
            System.out.println();
        } finally {
            lock.unlock();
        }
        full.release();
    }

    public Item consume() throws InterruptedException {
        Item item;
        full.acquire();
        lock.lock();
        try {
            item = queue.poll();
        } finally {
            lock.unlock();
        }
        empty.release();
        return item;
    }

    static class Item {

    }

}
