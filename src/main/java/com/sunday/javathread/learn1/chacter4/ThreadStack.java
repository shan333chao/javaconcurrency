package com.sunday.javathread.learn1.chacter4;

public class ThreadStack {
    public static final Object MONITOR=new Object();
    public static void main(String[] args) {
        Runnable runnable=()->{
            synchronized (MONITOR){
                try {
                    System.out.println(Thread.currentThread().getName()+">>out");
                    Thread.sleep(100_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

}
