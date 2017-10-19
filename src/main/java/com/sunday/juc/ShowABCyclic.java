package com.sunday.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by HenDiao on 2017/10/18.
 *
 */
public class ShowABCyclic {
    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("done");
            }
        });
        final AtomicInteger bool = new AtomicInteger(0);
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (bool.compareAndSet(0, 1)) {
                            System.out.println("A");
                            cyclicBarrier.await();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
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
                        if (bool.compareAndSet(1, 2)) {
                            System.out.println("B");
                            cyclicBarrier.await();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } finally {

                    }
                }
            }
        };
        new Thread() {
            @Override
            public void run() {
                while (true){
                    if (bool.compareAndSet(2,0)){
                        try {
                            System.out.println("C");
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }else {
                        if (cyclicBarrier.getNumberWaiting() == 3) {
                            cyclicBarrier.reset();
                        }
                    }
                }
            }
        }.start();
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
    }

}
