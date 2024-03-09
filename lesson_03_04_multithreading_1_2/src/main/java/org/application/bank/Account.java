package org.application.bank;

public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
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
