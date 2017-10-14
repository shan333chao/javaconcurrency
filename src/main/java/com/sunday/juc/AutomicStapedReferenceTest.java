package com.sunday.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by HenDiao on 2017/10/13.
 */
public class AutomicStapedReferenceTest {

    private  static AtomicStampedReference<Integer> atomicRef=new AtomicStampedReference<Integer>(100,0) ;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    boolean b = atomicRef.compareAndSet(100, 101, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                    System.out.println(Thread.currentThread().getName()+":\t"+b);
                    boolean b1 = atomicRef.compareAndSet(101, 100, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                    System.out.println(Thread.currentThread().getName()+":\t"+b1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int stamp=atomicRef.getStamp();
                    System.out.println("current sleep:Step:"+stamp);
                    TimeUnit.SECONDS.sleep(2);
                    boolean b = atomicRef.compareAndSet(100, 101, stamp, stamp + 1);
                    System.out.println(Thread.currentThread().getName()+":\t"+b);
                    boolean b1 = atomicRef.compareAndSet(101, 100, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                    System.out.println(Thread.currentThread().getName()+":\t"+b1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }
}
