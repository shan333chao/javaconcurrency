package com.sunday.concurrency.practise9_threadlocal;

import java.util.Random;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class ThreadLocalSimulatorTest {
    private static   ThreadLocalSimulator<String> threadLocal=new ThreadLocalSimulator<>();
    private  static Random random=new Random();
    public static void main(String[] args) {
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

    }

}
