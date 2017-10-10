package com.sunday.javathread.learn1.chacter4;

public class Demoen {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "-start");
                    Thread.sleep(190_000);
                    System.out.println(Thread.currentThread().getName() + "-end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.setDaemon(true);
        t.start();
        System.out.println(Thread.currentThread().getName());
    }
}
