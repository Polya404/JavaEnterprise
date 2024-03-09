package org.application.bank;

public class Main {
    static void transfer(Account account1, Account account2, int amount) throws InterruptedException {
        if (account1.getBalance() < amount) {
            throw new RuntimeException("Can't be sent");
        }
        synchronized (account1){
            Thread.sleep(100);
            synchronized (account2){
                Thread.sleep(100);
                account1.withdraw(amount);
                account2.deposit(amount);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account account1 = new Account(500);
        Account account2 = new Account(300);

        transfer(account1, account2, 100);
        new Thread(() -> {
            try {
                transfer(account1, account2, 100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
