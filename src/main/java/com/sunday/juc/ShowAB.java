package com.sunday.juc;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by HenDiao on 2017/10/13.
 */
public class ShowAB {
    private static final Lock lock=new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger bool= new AtomicInteger(0);

        Thread t1 = new Thread() {
            @Override
            public void run() {
                while(true){
                    try {
                        lock.lock();
                        if (bool.compareAndSet(0,1))
                            System.out.println("A");
                    }finally {
                        lock.unlock();
                    }

                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                while(true){
                    try {

                        lock.lock();
                    if (bool.compareAndSet(1,2))
                        System.out.println("B");
                    }finally {
                        lock.unlock();
                    }
                }
            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                while(true){
                    try {

                        lock.lock();
                        if (bool.compareAndSet(2,0))
                            System.out.println("C");
                    }finally {
                        lock.unlock();
                    }
                }
            }
        };
        t1.start();
        t2.start();
        t3.start();
        t3.join();
        t1.join();
        t2.join();
    }
 }

