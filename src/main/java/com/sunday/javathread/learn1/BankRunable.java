package com.sunday.javathread.learn1;

import java.util.concurrent.atomic.AtomicInteger;

public class BankRunable implements Runnable {
    private final static int maxNum = 500;
    private final static AtomicInteger index = new AtomicInteger(0);
    private final Object MONITOR = new Object();

    @Override
    public   void run() {
        while (true) {

            if (ticket()) break;

        }
    }

    private synchronized boolean ticket() {
        if (index.get() >= maxNum) {
            return true;
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " current num is " + index.addAndGet(1));
        return false;
    }
}
