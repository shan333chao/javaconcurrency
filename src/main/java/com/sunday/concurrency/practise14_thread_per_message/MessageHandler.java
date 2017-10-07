package com.sunday.concurrency.practise14_thread_per_message;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by HenDiao on 2017/10/4.
 */
public class MessageHandler {
    private  final  static Random random=new Random(System.currentTimeMillis());
    private final Executor executor= Executors.newFixedThreadPool(5);
    public   void request(Message msg){
        executor.execute(()->{
            String  value=msg.getValue();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println("thr message will be handle by "+Thread.currentThread().getName()+" " +value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    public  void shutdown(){
        System.out.println("shutdown");
        ((ExecutorService)executor).shutdown();
    }
}
