package org.application.bank;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final ReentrantLock lock = new ReentrantLock();
    private int balance;
    private final AtomicInteger failedTransactionCount = new AtomicInteger();

    public AtomicInteger getFailedTransactionCount() {
        return failedTransactionCount;
    }

    public Account(int balance) {
        this.balance = balance;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public void withdraw(int amount) {
        System.out.println("withdraw");
        balance -= amount;
    }

    public void deposit(int amount) {
        System.out.println("deposit");
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}
