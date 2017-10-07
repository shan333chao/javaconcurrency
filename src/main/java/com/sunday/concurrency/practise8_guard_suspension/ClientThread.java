package com.sunday.concurrency.practise8_guard_suspension;

import java.util.Random;

/**
 * Created by HenDiao on 2017/10/2.
 */
public class ClientThread extends  Thread{
    private  final  RequestQueue queue;
    private  final Random random;
    private  final  String sendValue;
    public  ClientThread(RequestQueue queue,String sendvalue){
        this.queue=queue;
            this.sendValue=sendvalue;
        random=new Random(System.currentTimeMillis());
    }
    @Override
    public void run() {
        for (int i=0;i<10;i++){
            System.out.println("Client  ->request "+sendValue);
            queue.putRequest(new Request(sendValue));
            try {
                Thread.sleep( random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
