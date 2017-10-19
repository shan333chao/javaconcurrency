package com.sunday.juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by HenDiao on 2017/10/19.
 */
public class ExchangeExample3 {
    public static void main(String[] args) {
        final Exchanger<Integer> exchanger=new Exchanger<>() ;

        new Thread(){
            @Override
            public void run() {
                AtomicReference<Integer> value=new AtomicReference<Integer>(1) ;

                try {
                    while(true){
                        value.set(exchanger.exchange(value.get()));
                        System.out.println("Thread a has value: "+value.get());
                        TimeUnit.SECONDS.sleep(3);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                AtomicReference<Integer> value=new AtomicReference<Integer>(2) ;

                try {
                    while(true){
                        value.set(exchanger.exchange(value.get()));
                        System.out.println("Thread B has value: "+value.get());
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
