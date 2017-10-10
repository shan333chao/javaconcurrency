package com.sunday.javathread.learn1.ThreadApi;

public class ThreadCloseForce {
    public static void main(String[] args) {
        ThreadService service=new ThreadService();
        long start=System.currentTimeMillis();
        service.execute(()->{
            //read data
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown(10000);
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
