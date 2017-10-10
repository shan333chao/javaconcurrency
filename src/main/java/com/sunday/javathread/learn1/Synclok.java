package com.sunday.javathread.learn1;

public class Synclok {
    static {
        synchronized (Synclok.class){
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public   synchronized static    void m1(){


            System.out.println(Thread.currentThread().getName()+"m1");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


    }

    public synchronized static void m2(){
        System.out.println(Thread.currentThread().getName()+"m2");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public   static void m3(){
        System.out.println(Thread.currentThread().getName()+"m3");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
