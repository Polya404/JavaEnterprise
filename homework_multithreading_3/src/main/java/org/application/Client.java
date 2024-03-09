package org.application;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class Client implements Callable<String> {
    private final Random random = new Random();
    private final List<String> drinks = List.of("beer", "tea", "whisky", "coffee", "water", "lemonade", "juice", "cocktail");

    @Override
    public String call() {
        return makeOrder(drinks.get(random.nextInt(8)));
    }

    private String makeOrder(String drink) {
        System.out.printf(Thread.currentThread().getName() + " order %s%n", drink);
        return String.format(Thread.currentThread().getName() + " order %s", drink);
    }
}

