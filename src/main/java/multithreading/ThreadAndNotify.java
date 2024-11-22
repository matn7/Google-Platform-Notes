package multithreading;

public class ThreadAndNotify {
    public static void main(String[] args) {
        Calculator thread = new Calculator();
        thread.start();
        synchronized (thread) {
            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("resume");
        System.out.println(thread.sum);
    }
}

class Calculator extends Thread {
    long sum;

    public void run() {
        synchronized (this) {
            for (int i = 0; i < 1000000; i++) {
                sum += i;
            }
            notify();
        }
    }
}
