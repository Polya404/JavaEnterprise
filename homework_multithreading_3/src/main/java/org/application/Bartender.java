package org.application;

import java.util.concurrent.Callable;

public class Bartender implements Callable<String> {
    private volatile String drink;
    private final String order;

    public Bartender(String order) {
        this.order = order;
    }

    @Override
    public String call() {
        return getOrder(order);
    }

    private String getOrder(String order) {
        getString(order);
        return prepareDrink();
    }

    private void getString(String order) {
        String[] strings = order.split(" ");
        drink = strings[strings.length - 1];
        System.out.printf(Thread.currentThread().getName() + " received an order for brewing %s%n", drink);
    }

    private String prepareDrink() {
        System.out.printf("%s is ready!%n", drink);
        return drink;
    }
}

