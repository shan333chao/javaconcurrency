package com.sunday.juc.utils;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by HenDiao on 2017/10/16.
 */
public class CountDownLatchExample1 {

    private  static Random random=new Random(System.currentTimeMillis());
    private  static ExecutorService executor= Executors.newFixedThreadPool(4);
    private static final CountDownLatch latch=new CountDownLatch(11);
    public static void main(String[] args) throws InterruptedException {
        Integer [] data=query();
        for (int i=0;i<11;i++){
            executor.submit(new SimpleRunnable(data,i,latch));
        }
        latch.await();
        executor.shutdown();


        System.out.println("all works is deo: "+ Joiner.on(",").join(data));


    }

    public static Integer[] query(){
        return new Integer[]{1,2,3,4,5,6,7,8,9,0,10};
    }
    static  class SimpleRunnable implements  Runnable{

        private final  Integer[] data;
        private  final int  index;
        public SimpleRunnable(Integer[] data, int index,CountDownLatch latch) {
            this.data = data;
            this.index = index;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int value=data[index];
            if(value%2==0){
                data[index]=value*2;
            }else {
                data[index]=value*10;
            }
            latch.countDown();
            System.out.println(Thread.currentThread().getName()+" work finish index: "+index);
        }
    }
}
