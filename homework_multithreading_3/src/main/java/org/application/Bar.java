package org.application;

import java.util.concurrent.*;

public class Bar {
    private final ExecutorService clientExecutor;
    private final ExecutorService bartenderExecutor;

    public Bar(int bartender, int clients) {
        this.clientExecutor = Executors.newFixedThreadPool(clients);
        this.bartenderExecutor = Executors.newFixedThreadPool(bartender);
    }

    public Future<String> acceptOrder() {
        return clientExecutor.submit(new Client());
    }

    public Future<String> preparationDrinks(String drink) {
        Bartender bartender = new Bartender(drink);
        return bartenderExecutor.submit(bartender);
    }

    public void delivery(String drinkFuture) throws ExecutionException, InterruptedException {
        System.out.println(preparationDrinks(drinkFuture).get() + " was delivered");
    }

    public void shutdown() {
        clientExecutor.shutdown();
        bartenderExecutor.shutdown();
    }
}

