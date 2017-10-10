package com.sunday.threaddesignpattern.practise13_countdown;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class JDKCountDown {
    private static final Random random=new Random(System.currentTimeMillis());


    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch=new CountDownLatch(5);
        System.out.println("准备多线程处理任务");
        //the first phase.
        IntStream.range(0,5).forEach(i->{
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" is working .");
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            },String.valueOf(i)).start();
        });
        latch.await();
        //The second phase.
        System.out.println("全部结束 准备第二阶段任务");
        System.out.println(".................");
        System.out.println("finished");

    }
}
