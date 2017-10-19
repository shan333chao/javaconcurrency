package com.sunday.juc;

import java.util.concurrent.Exchanger;

/**
 * Created by HenDiao on 2017/10/19.
 * Exchange 中的交换两个对象 是同一个对象，会有线程安全问题
 * Exchange 交换的对象不是一个拷贝 而是指向同一个对象的引用
 */
public class ExchangeExample2 {
    public static void main(String[] args) {
        final Exchanger<Object> exchanger=new Exchanger<>() ;

        new Thread(){
            @Override
            public void run() {
                Object a=new Object() ;
                System.out.println("A send the A object"+a);
                try {
                    Object exchange = exchanger.exchange(a);
                    System.out.println("a received the A obj"+exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                Object object=new Object() ;
                System.out.println("B send the B object"+object);
                try {
                    Object exchange = exchanger.exchange(object);
                    System.out.println("B received the B obj"+exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
