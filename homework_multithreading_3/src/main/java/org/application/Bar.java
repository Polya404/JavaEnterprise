package org.application;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class Bar {
    private final ExecutorService clientExecutor;
    private final ExecutorService bartenderExecutor;

    public Bar(int bartender, int clients) {
        this.clientExecutor = createExecutorService(clients, createThreadFactory("Client-%d"));
        this.bartenderExecutor = createExecutorService(bartender, createThreadFactory("Bartender-%d"));
    }

    public Future<String> acceptOrder() {
        return clientExecutor.submit(new Client());
    }

    private Future<String> preparationDrinks(String drink) {
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

    private ExecutorService createExecutorService(int numOfThreads, ThreadFactory threadFactory) {
        return Executors.newFixedThreadPool(numOfThreads, threadFactory);
    }

    private ThreadFactory createThreadFactory(String name) {
        return new ThreadFactoryBuilder().setNameFormat(name).build();
    }
}

