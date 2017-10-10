package com.sunday.javathread.learn1.ThreadApi;

import java.util.Optional;
import java.util.stream.IntStream;

public class SimpleThread3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            IntStream.range(1,1_000).forEach(i->System.out.println(Thread.currentThread().getName()+"-->"+i));
        });

        Thread thread2 = new Thread(() -> {
            IntStream.range(1,1_000).forEach(i->System.out.println(Thread.currentThread().getName()+"-->"+i));
        });
        thread.start();
        thread2.start();
        thread.join();
      thread2.join();
        Optional.of("all thread finished").ifPresent(System.out::println);
        IntStream.range(1,1_000).forEach(i->System.out.println(Thread.currentThread().getName()+"-->"+i));
    }
}
