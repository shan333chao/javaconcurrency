package com.sunday.concurrency.practise12_producter_consumer;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class ConsumerThread  extends  Thread{
    private  final  MessageQueue messageQueue;
    private  final  static Random random=new Random(System.currentTimeMillis());


    public ConsumerThread(MessageQueue messageQueue,int seq) {
        super("Consumer- "+seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
       while (true){
           try {
               Message take = messageQueue.take();
               if ( null!=take){
                   System.out.println(Thread.currentThread().getName()+" get message "+take.getData());
               }
               TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}
