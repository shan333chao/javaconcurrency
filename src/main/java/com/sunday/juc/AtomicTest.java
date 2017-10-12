package com.sunday.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by HenDiao on 2017/10/10.
 * Atomic
 * 1 可见性
 * 2 有序性
 * 3 原子性
 *  volatile 修饰变量 保证前两者
 *  CAS 算法 也就是cpu级别的同步指令 相当于乐观锁 他可以检测到其他线程对共享内存的变化状态
 *
 *  for(;;){
 *      int current=get();
 *      int next=current+1;
 *     if(compareAndSet(current，next)){
 *         return next;
 *     }
 *  }
 *  最快失败策略
 *  CAS 轻量级锁，带来了一个严重问题，ABA问题
 *
 *
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
