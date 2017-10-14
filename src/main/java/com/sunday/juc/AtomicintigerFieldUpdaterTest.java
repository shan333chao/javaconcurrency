package com.sunday.juc;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by HenDiao on 2017/10/13.
 */
public class AtomicintigerFieldUpdaterTest {
    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<TestDemo> updater=  AtomicIntegerFieldUpdater.newUpdater(TestDemo.class,"i");
        final TestDemo demo=new TestDemo() ;
        for (int i=0;i<2;i++   ){
            new Thread(){
                @Override
                public void run() {
                   final int Max=20;
                    for (int k=0;k<Max;k++){
                        int andIncrement = updater.getAndIncrement(demo);
                        System.out.println(Thread.currentThread().getName()+" i:\t"+andIncrement);
                    }
                }
            }.start();
        }
    }

    static class TestDemo{
        volatile  int i;
    }
}
