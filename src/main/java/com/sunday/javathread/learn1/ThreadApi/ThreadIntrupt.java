package com.sunday.javathread.learn1.ThreadApi;

public class ThreadIntrupt {
    private static    Object monitor=new Object();
    public static void main(String[] args) throws InterruptedException {
       Thread t1=  new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (monitor){
                        try {
                            monitor.wait(10);
                          //  System.out.println(">>>>"+ this.isInterrupted());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
       t1.start();
       Thread.sleep(100);
        System.out.println(t1.isInterrupted());
        t1.interrupt();
        System.out.println(t1.isInterrupted());
        t1.stop();
    }

}
