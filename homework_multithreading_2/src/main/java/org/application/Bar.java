package org.application;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Bar {
    private final BlockingQueue<String> orders = new ArrayBlockingQueue<>(10);
    private final CopyOnWriteArrayList<String> readyOrders = new CopyOnWriteArrayList<>();
    private final int bartender = 2;
    private final int clients = 7;

    public BlockingQueue<String> getOrders() {
        return orders;
    }

    public CopyOnWriteArrayList<String> getReadyOrders() {
        return readyOrders;
    }

    public void acceptOrder() {
        for (int i = 0; i < clients; i++) {
            new Thread(new Client(this), String.format("Client " + (i + 1))).start();
        }
    }

    public void preparationDrinks() {
        for (int i = 0; i < bartender; i++) {
            new Thread(new Bartender(this, bartender, clients), String.format("Bartender " + (i + 1))).start();
        }
    }

    public void delivery() {
        System.out.println("Try delivered");
        if (readyOrders.isEmpty()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (String order : readyOrders) {
            System.out.println(order + " was delivered");
        }

    }
}
