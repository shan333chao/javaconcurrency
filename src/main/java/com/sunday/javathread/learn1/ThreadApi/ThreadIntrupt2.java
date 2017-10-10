package com.sunday.javathread.learn1.ThreadApi;

public class ThreadIntrupt2 {

    public static void main(String[] args) {
        Worker work=new Worker();
        work.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

     work.interrupt();

    }

    private static   class Worker extends Thread{
        @Override
        public void run() {

            while(true){
                if (Thread.interrupted())
                break;
            }
            System.out.println("interputed");
        }
    }





}
