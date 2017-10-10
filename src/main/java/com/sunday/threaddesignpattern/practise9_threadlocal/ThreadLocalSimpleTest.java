package com.sunday.threaddesignpattern.practise9_threadlocal;

import java.util.Random;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class ThreadLocalSimpleTest {

    private static   ThreadLocal<String> threadLocal=new ThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("sandy");
        Thread.sleep(1000);
        Random random=new Random();
        System.out.println(threadLocal.get());
        Thread thread = new Thread() {
            @Override
            public void run() {

                try {
                    threadLocal.set("Thread1");
                    Thread.sleep(random.nextInt(1000));
                    System.out.println(threadLocal.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {

                try {
                    threadLocal.set("Thread2");
                    Thread.sleep(random.nextInt(1000));
                    System.out.println(threadLocal.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread2.start();
        thread.start();
        thread.join();
        thread2.join();
    }


}
