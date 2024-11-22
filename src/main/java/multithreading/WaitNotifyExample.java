package multithreading;

public class WaitNotifyExample {

    public static void main(String[] args) {
        final Object lock = new Object();
        Thread producer = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Producer producing");
                try {
                    lock.wait(); // release a lock and wait
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer resumed");
            }
        });

        Thread consumer = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Consumer is notifying");
                lock.notify(); // notify waiting thread
            }
        });

        // lock in case of consumer start first
        consumer.start();
        producer.start();
    }

}
