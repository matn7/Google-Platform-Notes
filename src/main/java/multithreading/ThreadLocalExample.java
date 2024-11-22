package multithreading;

public class ThreadLocalExample {

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        threadLocal.set(45);

        Thread thread1 = new Thread(() -> {
            threadLocal.set(10);
            System.out.println("Thread1: " + threadLocal.get());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread2: " + threadLocal.get());
        });

        thread1.start();
        thread2.start();
    }

}
