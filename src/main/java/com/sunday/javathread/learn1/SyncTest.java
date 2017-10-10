package com.sunday.javathread.learn1;

public class SyncTest {


    public static void main(String[] args) {
        Synclok sl=new Synclok();
        new Thread("t1"){
            @Override
            public void run() {
                Synclok.m1();
            }
        }.start();
        new Thread("t2"){
            @Override
            public void run() {
                Synclok.m2();
            }
        }.start();


        new Thread("t3"){
            @Override
            public void run() {
                Synclok.m3();
            }
        }.start();
    }
}
