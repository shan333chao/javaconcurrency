package com.sunday.concurrency.practise7_future_asynctask;

import java.util.concurrent.TimeUnit;

/**
 * Created by HenDiao on 2017/10/2.
 */
public class SyncInvoker {
    public static void main(String[] args) throws InterruptedException {
      FutureService futureService=new FutureService();
        Future<String> stringFuture = futureService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Finish";
        },System.out::println);
        System.out.println("do some thing");
        Thread.sleep(1000);
        System.out.println("do some thing");
        String s = stringFuture.get();

        System.out.println("result"+s);
        System.out.println("do some thing");

    }
    private  static  String get() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        return "Finish";
    }
}
