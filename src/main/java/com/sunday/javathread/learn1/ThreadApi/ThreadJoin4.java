package com.sunday.javathread.learn1.ThreadApi;

import java.util.Optional;

public class ThreadJoin4 {
    public static void main(String[] args) throws InterruptedException {
        Thread m1 = new Thread(new CaptureThread("M1", 11000));
        Thread m2 = new Thread(new CaptureThread("M1", 12000));
        Thread m3 = new Thread(new CaptureThread("M1", 13000));


        m1.start();
        m2.start();
        m3.start();
        m1.join();
        m2.join();
        m3.join();


        Optional.of("all thread finished").ifPresent(System.out::println);

    }



}
