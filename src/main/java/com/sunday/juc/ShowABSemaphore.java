package com.sunday.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by HenDiao on 2017/10/19.
 */
public class ShowABSemaphore {
    public static void main(String[] args) {
        final SemaphoreExample.SemaphoreLock lock = new SemaphoreExample.SemaphoreLock();
        final AtomicInteger bool = new AtomicInteger(0);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        if (bool.compareAndSet(0, 1))
                            System.out.println("A");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {

                while (true) {
                    try {
                        lock.lock();
                        if (bool.compareAndSet(1, 0))
                            System.out.println("B");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }

            }
        }.start();
    }

    static class SemaphoreLock {
        private final Semaphore semaphore = new Semaphore(0);

        public void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public void unlock() {
            semaphore.release();
        }
    }
}
