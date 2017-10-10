package com.sunday.threaddesignpattern.practise12_producter_consumer;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class ProducerThread extends Thread {
    private  final  MessageQueue messageQueue;
    private  final  static Random random=new Random(System.currentTimeMillis());
    private  final  static AtomicInteger counter=new AtomicInteger(0);
    public ProducerThread(MessageQueue messageQueue,int seq) {
        super("PRODUCER"+seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Message message = new Message("Message- " + counter.getAndIncrement());
                messageQueue.put(message);
                System.out.println(Thread.currentThread().getName()+" put message:"+message.getData());
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
