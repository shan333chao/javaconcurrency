package com.sunday.javathread.learn1;

public class DeadLock {
    private  OtherService otherService ;
    private final  Object lock=new Object();

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    public  void mi(){
        synchronized (lock){
            System.out.println("m1");
            otherService.s1();
        }
    }
    public  void m2(){
        synchronized (lock){
            System.out.println("m2");
        }
    }
}
