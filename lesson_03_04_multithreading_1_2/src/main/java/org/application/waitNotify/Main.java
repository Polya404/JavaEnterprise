package org.application.waitNotify;

public class Main {
    public static void main(String[] args) {
        Message message = new Message("test");
        Waiter waiter = new Waiter(message);

        new Thread(waiter, "waiter").start();

        Waiter waiter1 = new Waiter(message);

        new Thread(waiter1, "waiter1").start();

        Notifier notifier = new Notifier(message);

        new Thread(notifier, "notifier").start();


        System.out.println("All threads were done");
    }
}
