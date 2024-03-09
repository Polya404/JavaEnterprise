package org.application;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class Bar {
    private final ExecutorService clientExecutor;
    private final ExecutorService bartenderExecutor;
    ThreadFactory bartenderFactory = new ThreadFactoryBuilder().setNameFormat("Bartender-%d").build();
    ThreadFactory clientFactory = new ThreadFactoryBuilder().setNameFormat("Client-%d").build();

    public Bar(int bartender, int clients) {
        this.clientExecutor = Executors.newFixedThreadPool(clients, clientFactory);
        this.bartenderExecutor = Executors.newFixedThreadPool(bartender, bartenderFactory);
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

