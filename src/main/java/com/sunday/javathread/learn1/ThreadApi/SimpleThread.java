package com.sunday.javathread.learn1.ThreadApi;

import java.util.Optional;

public class SimpleThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.setPriority(1);
        t1.start();
        Optional.of(t1.getName()).ifPresent(System.out::println);
        Optional.of(t1.getId()).ifPresent(System.out::println);
        Optional.of(t1.getPriority()).ifPresent(System.out::println);
    }
}
