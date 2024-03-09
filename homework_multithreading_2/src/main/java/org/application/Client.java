package org.application;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

public class Client implements Runnable {
    private final Bar bar;
    private final Random random = new Random();
    private final List<String> drinks = List.of("beer", "tea", "whisky", "coffee", "water", "lemonade", "juice", "cocktail");

    public Client(Bar bar) {
        this.bar = bar;
    }
    @Override
    public void run() {
        String order = makeOrder(drinks.get(random.nextInt(8)));
        BlockingQueue<String> orders = bar.getOrders();
        orders.add(order);
    }

    private String makeOrder(String drink) {
        System.out.printf(Thread.currentThread().getName() + " order %s%n", drink);
        return String.format(Thread.currentThread().getName() + " order %s", drink);
    }
}
