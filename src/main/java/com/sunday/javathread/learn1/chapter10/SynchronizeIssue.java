package com.sunday.javathread.learn1.chapter10;

public class SynchronizeIssue {

    public static void main(String[] args) throws InterruptedException {
     Thread t1=new Thread(){
         @Override
         public void run() {
             System.out.println("T1");
             SynchronizeIssue.run();
         }
     };     t1.start();
     Thread.sleep(10000);

        Thread t2=new Thread(){
            @Override
            public void run() {
                System.out.println("t2");
                SynchronizeIssue.run();
            }
        };
        t2.start();
        Thread.sleep(2000);
        t2.interrupt();
        System.out.println(t2.isInterrupted());
    }
    private synchronized   static  void run(){
        System.out.println(Thread.currentThread().getName()+" running");
        while(true){

        }
    }
}
