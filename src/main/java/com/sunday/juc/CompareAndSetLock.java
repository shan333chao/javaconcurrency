package com.sunday.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by HenDiao on 2017/10/11.
 */
public class CompareAndSetLock {
    private final AtomicInteger  value=new AtomicInteger(0);
    private Thread thread;
    public  void tryLock() throws Exception {
        if (!value.compareAndSet(0,1)){
            throw new Exception("get lock exception");
        }else {
            thread=Thread.currentThread();
        }
    }

    public void unLock(){
        if (0==value.get()){
              return;
        }
        if (thread==Thread.currentThread())
            value.compareAndSet(1,0);
    }
}
