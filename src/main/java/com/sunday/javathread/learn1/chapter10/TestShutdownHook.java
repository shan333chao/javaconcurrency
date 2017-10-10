package com.sunday.javathread.learn1.chapter10;

public class TestShutdownHook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("is shutdown now");
        }));
        new Thread(()->{
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis());
            } 
        }).start();
    }
}
