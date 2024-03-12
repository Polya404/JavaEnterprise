package org.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int clients = 7000;
        int bartender = 7;
        Bar bar = null;
        try {
            bar = new Bar(bartender, clients);
            List<Future<String>> drinkFutures = new ArrayList<>();
            for (int i = 0; i < clients; i++) {
                Future<String> orderFuture = bar.acceptOrder();
                drinkFutures.add(orderFuture);
            }

            for (Future<String> orderFuture : drinkFutures) {
                bar.delivery(orderFuture.get());
            }
        } finally {
            Objects.requireNonNull(bar).shutdown();
        }
    }
}

