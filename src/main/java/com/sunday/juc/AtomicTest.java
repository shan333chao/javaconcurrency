package com.sunday.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by HenDiao on 2017/10/10.
 */
public class AtomicTest {
    public static void main(String[] args) {
        AtomicInteger integer=new AtomicInteger();
        System.out.println(integer.get());
        integer=new AtomicInteger(10);
        System.out.println(integer.get());

        ///set
        integer.set(12);
        System.out.println(integer.get());
        ///设置了值 但是只有到使用的时候 才设置
        integer.lazySet(13);
        System.out.println(integer.get());
        ///自增后 再使用
        System.out.println(    integer.incrementAndGet());
        ///先使用后自增
        System.out.println(    integer.getAndIncrement());
        System.out.println(integer.get());
        //自减一 再使用
        System.out.println(integer.decrementAndGet());

        System.out.println(integer.getAndSet(6));
        System.out.println(integer.getAndAdd(6));
        System.out.println(integer.getAndAdd(6));
        System.out.println(integer.get());


    }
}
