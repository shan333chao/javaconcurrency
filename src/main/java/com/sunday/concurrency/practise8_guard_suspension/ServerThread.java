package com.sunday.concurrency.practise8_guard_suspension;

import java.util.Random;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class ServerThread  extends  Thread{
    private  final  RequestQueue queue;
    private  final Random random;
    private  volatile boolean flag=true;
    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (flag){
            Request request = queue.getRequest();
            if ( request==null){
                System.out.println("Received the empty request ingore");
                continue;
            }
            System.out.println("Server-->"+request.getValue());
            try {
                Thread.sleep( random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }


        }
    }

    public  void close(){
        this.flag=false;
        this.interrupt();
    }
}
