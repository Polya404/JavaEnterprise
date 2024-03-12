package org.application.waitNotify;

public class Waiter implements Runnable{
    Message msg;

    public Waiter(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        synchronized (msg){
            try {
                System.out.println("Waiting until notifying");
                msg.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
