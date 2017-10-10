package com.sunday.javathread.learn1.ThreadException;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by HenDiao on 2017/9/3.
 */
public class Test2 {
    private static final int a=10;
    private static final int b=0;
    public  void test(){
        Thread t = new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
                int result = a / b;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.setUncaughtExceptionHandler((thead,e)->{
            System.out.println(e);
            System.out.println(thead);
        });
        t.start();
        Arrays.asList(Thread.currentThread().getStackTrace()).stream().filter(e->!e.isNativeMethod()).forEach(e-> Optional.of(e.getClassName()+":"+e.getMethodName()+"--"+e.getLineNumber()).ifPresent(System.out::println)) ;
    }
}
