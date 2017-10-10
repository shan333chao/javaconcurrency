package com.sunday.javathread.learn1.ThreadApi;

import java.util.Optional;
import java.util.stream.IntStream;

public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {

            IntStream.range(1,1_000).forEach((i)->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"-->"+i);


            });
        });
        thread.join(100);

        Optional.of("all thread finished").ifPresent(System.out::println);
        IntStream.range(1,1_000).forEach(i->System.out.println(Thread.currentThread().getName()+"-->"+i));

    }
}
