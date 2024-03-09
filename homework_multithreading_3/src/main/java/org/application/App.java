package org.application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int numClients = 7;
        Bar bar = new Bar(3, numClients);

        List<Future<String>> drinkFutures = new ArrayList<>();
        for (int i = 0; i < numClients; i++) {
            Future<String> orderFuture = bar.acceptOrder();
            drinkFutures.add(orderFuture);
        }

        for (Future<String> orderFuture : drinkFutures) {
            bar.delivery(orderFuture.get());
        }

        bar.shutdown();
    }
}

