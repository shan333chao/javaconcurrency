package com.sunday.javathread;

public class TryConcurrency {

    public static void main(String[] args) throws InterruptedException {

    new Thread("Thread-->readFromDatabase"){
        @Override
        public void run() {
            readFromDatabase();
        }
    }.start();

        new Thread("Thread-->writetoFile"){
            @Override
            public void run() {
                writetoFile();
            }
        }.start();

//        Thread t1=new Thread(){
//            @Override
//            public void run(){
//                for ( int i=0;i<1000;i++){
//                    printMsg("task 1=》"+i);
//                }
//            }
//        };
//        t1.start();
//        for ( int j=0;j<100;j++){
//            printMsg("task 2=》"+j);
//        }





    }

    private static void writetoFile() {
        try {
            printMsg("begin write data to file");
            Thread.sleep(1000);
            printMsg("read file done and start handle it");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void readFromDatabase() {
        try {
            printMsg("begin read data from db");
            Thread.sleep(1000*300L);
            printMsg("read data done and start handle it");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printMsg(String s) {
        System.out.println("s = [" + s + "]");
    }
}
