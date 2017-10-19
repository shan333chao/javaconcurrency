package com.sunday.juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created by HenDiao on 2017/10/19.
 * 1、如果 一对线程没有达到 交换点 这个线程将会变成waiting
 * 2、使用Exchanger 线程必须成对
 */
public class ExchangerExample {
    public static void main(String[] args) {
        final Exchanger<String> exchanger=new Exchanger<>() ;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    //System.out.println(Thread.currentThread().getName()+" started!");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        String result= exchanger.exchange("A");
                        System.out.println(Thread.currentThread().getName()+" : "+result);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        },"===A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    //System.out.println(Thread.currentThread().getName()+" started!");
                    try {

                        String result= exchanger.exchange("B");         TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName()+" : "+result);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        },"===B").start();
    }
}
