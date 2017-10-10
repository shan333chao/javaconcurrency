package com.sunday.javathread.learn1.chapter10;

import java.util.stream.IntStream;

public class LockTest {
    public static void main(String[] args) {

                final  BooleanLock booleanLock=new BooleanLock();
        IntStream.range(1,4).forEach(i->{
            new Thread("T"+i){
                @Override
                public void run() {
                    try {
                        booleanLock.lock(10L);
                        System.out.println(Thread.currentThread().getName()+"have the lock monitor..");
                        work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }catch (Lock.TimeOutException e) {
                        System.out.println(Thread.currentThread().getName()+" timeout.");
                    }
                    booleanLock.unlock();
                }
            }.start();
            booleanLock.unlock();
        });
    }
private  static  void work() throws InterruptedException {
    System.out.println(Thread.currentThread().getName()+"is working now...");
        Thread.sleep(10000);
}
}

