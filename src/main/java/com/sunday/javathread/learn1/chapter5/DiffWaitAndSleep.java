package com.sunday.javathread.learn1.chapter5;

import java.util.stream.Stream;

public class DiffWaitAndSleep {
private final static  Object LOCK=new Object();
    public static void main(String[] args) {
        Stream.of("T!","T2").forEach(name->{
            new Thread(name){
                @Override
                public void run() {
                   m2();
                }
            }.start();
        });
    }

    public  static  void m1(){
        synchronized (LOCK){
            try {
                System.out.println("The Thread is "+Thread.currentThread().getName());
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public  static  void m2(){
        synchronized (LOCK){
            try {
                System.out.println("The Thread is "+Thread.currentThread().getName());
                LOCK.wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
