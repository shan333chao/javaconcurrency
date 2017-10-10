package com.sunday.javathread.learn1;

public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService=new OtherService();
        DeadLock deadLock=new DeadLock(otherService);
        otherService.setDeadLock(deadLock);
        new Thread(){
            @Override
            public void run() {
                deadLock.mi();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while (true)
                otherService.s2();
            }
        }.start();
    }
}
