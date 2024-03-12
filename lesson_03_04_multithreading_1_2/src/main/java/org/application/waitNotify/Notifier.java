package org.application.waitNotify;

public class Notifier implements Runnable {
    private Message msg;

    public Notifier(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        synchronized (msg) {
            msg.notifyAll();
            System.out.println("Notifier was done");
        }
    }
}
