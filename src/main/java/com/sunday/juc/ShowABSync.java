package com.sunday.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by HenDiao on 2017/10/16.
 */
public class ShowABSync {

    private static final Object MONITORA = new Object();

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger bool = new AtomicInteger(0);

        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        synchronized (MONITORA) {
                            System.out.println("A");
                            MONITORA.notifyAll();
                            MONITORA.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                    }

                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        synchronized (MONITORA) {
                            System.out.println("B");
                            MONITORA.notifyAll();
                            MONITORA.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                    }
                }
            }
        };

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();


    }
}
