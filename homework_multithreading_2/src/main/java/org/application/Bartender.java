package org.application;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Bartender implements Runnable {
    private final Bar bar;
    private volatile String drink;
    private final int bartender;
    private final int clients;

    public Bartender(Bar bar, int bartender, int clients) {
        this.bar = bar;
        this.bartender = bartender;
        this.clients = clients;
    }

    @Override
    public void run() {
        BlockingQueue<String> orders = bar.getOrders();
        getOrder(orders);
    }

    private void getOrder(BlockingQueue<String> orders) {
        for (int i = 0; i < getCountOfOrders(); i++) {
            try {
                String order = orders.poll(1, TimeUnit.SECONDS);
                if (order == null) {
                    break;
                }
                getString(order);
                bar.getReadyOrders().add(prepareDrink());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void getString(String order) {
        String[] strings = order.split(" ");
        drink = strings[strings.length - 1];
        System.out.printf(Thread.currentThread().getName() + " received an order for brewing %s%n", drink);
    }

    private int getCountOfOrders() {
        if (clients == bartender) return 1;
        if (clients % bartender == 0) return clients / bartender;
        return (clients / bartender) + 1;
    }

    private String prepareDrink() {
        System.out.printf("%s is ready!%n", drink);
        return drink;
    }
}
