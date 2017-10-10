package com.sunday.javathread.learn1.ThreadGroupCreate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by HenDiao on 2017/9/3.
 */
public class ThreadGroupTest {
    public static void main(String[] args) {
        ThreadGroup group=new  ThreadGroup("tg1");
        Thread thread = new Thread(group,"t1"){
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
        thread.start();

        ThreadGroup tg2=new ThreadGroup("tg2");
        Thread t2 = new Thread(tg2, "t2") {
            @Override
            public void run() {
                Thread [] threads=new Thread[group.activeCount()];
                group.enumerate(threads);
                Arrays.asList(threads).forEach(System.out::println);

            }
        };

        t2.start();

        System.out.println(tg2.getName());
        System.out.println(tg2.getParent());
    }
}
