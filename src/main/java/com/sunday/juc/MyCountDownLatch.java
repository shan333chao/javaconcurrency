package com.sunday.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by HenDiao on 2017/10/18
 * CountDownLatch 不能reset，是工作线程之间互不关心
 * CyclicBarrie 可以循环使用 是工作线程之间必须等到同一个共同的点才去执行某个动作
 * .
 */
public class MyCountDownLatch extends CountDownLatch {

    public MyCountDownLatch(int count, Runnable runnable) {
        super(count);
        this.runnable = runnable;
    }

    private final Runnable runnable;

    public void countDown() {
        super.countDown();
        if (getCount() == 0) {
            this.runnable.run();
        }
    }

    public static void main(String[] args) {
        MyCountDownLatch myCountDownLatch = new MyCountDownLatch(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        });
        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "Finished!");
                    myCountDownLatch.countDown();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "Finished!");
                    myCountDownLatch.countDown();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
