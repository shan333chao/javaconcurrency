package com.sunday.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by HenDiao on 2017/10/18.
 */
public class CyclicBarrierTest {


    public static void main(String[] args) throws InterruptedException {
        ///任务分片处理
        final   CyclicBarrier cyclic = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("alldone!");
            }
        });
        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t complated!");
                    cyclic.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"\t complated!");
                    cyclic.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        while (true){
            ///获取已经await过的任务
            System.out.println(cyclic.getNumberWaiting());
            System.out.println(cyclic.getParties());
            TimeUnit.SECONDS.sleep(1);
        }

    }
}
