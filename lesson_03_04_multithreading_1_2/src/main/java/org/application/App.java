package org.application;

public class App {
    static MyRunnable runnable = new MyRunnable();
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable1 = () -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
                System.out.println(Thread.currentThread().getName() + " index " + i);
            }
        };

        Thread thread = new Thread(runnable1);
        thread.start();

        thread.join();

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            System.out.println(Thread.currentThread().getName() + " index " + i);
        }

    }
}
