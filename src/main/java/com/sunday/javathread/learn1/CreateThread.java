package com.sunday.javathread.learn1;

import java.util.Arrays;

public class CreateThread {
    public static void main(String[] args) {
        Thread t=new Thread();
        Thread t1=new Thread();
        t1.start();
       // t.start();
        System.out.println("args = [" + t.getName() + "]");
        System.out.println("args = [" + t1.getName() + "]");
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup.getParent().getName());
        Thread[] threads = new Thread[threadGroup.activeCount()];
        int enumerate = threadGroup.enumerate(threads);
        Arrays.stream(threads).forEach(
                s-> System.out.println(s)
        );

    }
}
