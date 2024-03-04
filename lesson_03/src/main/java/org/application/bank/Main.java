package org.application.bank;

public class Main {
    static void transfer(Account account1, Account account2, int amount) throws InterruptedException {
        if (account1.getBalance() < amount) {
            throw new RuntimeException("Can't be sent");
        }
        System.out.println("Trying to lock account " + account1);
        try {
            if (account1.getLock().tryLock()) {
                System.out.println("Account is locked " + account1);
                Thread.sleep(300);
                System.out.println("Trying to lock account " + account2);
                try {
                    if (account2.getLock().tryLock()) {
                        System.out.println("Account is locked " + account2);
                        Thread.sleep(500);
                        account1.withdraw(amount);
                        account2.deposit(amount);
                        System.out.println("Successfully");
                    } else {
                        System.out.println("Can't get lock from " + account2);
                    }
                } finally {
                    account2.getLock().unlock();
                }
            } else {
                System.out.println("Can't get lock from " + account1);
            }
        } finally {
            if (account1.getLock().isHeldByCurrentThread()) {
                account1.getLock().unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account account1 = new Account(500);
        Account account2 = new Account(300);

        transfer(account1, account2, 100);
        new Thread(() -> {
            try {
                transfer(account2, account1, 100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
