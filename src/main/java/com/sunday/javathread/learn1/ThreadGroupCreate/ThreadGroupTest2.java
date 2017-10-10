package com.sunday.javathread.learn1.ThreadGroupCreate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by HenDiao on 2017/9/3.
 */
public class ThreadGroupTest2 {

    public static void main(String[] args) {
        ThreadGroup tg1=new  ThreadGroup("tg1");
        Thread t1 = new Thread(tg1,"t1"){
            @Override
            public void run() {
                while (true){
                    try {
                        System.out.println(getThreadGroup().getName());
                        System.out.println(getThreadGroup().getParent().isDaemon());
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();

        ThreadGroup tg2=new ThreadGroup("tg2");
        Thread t2 = new Thread(tg2, "t2") {
            @Override
            public void run() {
                Thread [] threads=new Thread[tg1.activeCount()];
                tg1.enumerate(threads);
                Arrays.asList(threads).forEach(System.out::println);

            }
        };

        t2.start();
    }
}
