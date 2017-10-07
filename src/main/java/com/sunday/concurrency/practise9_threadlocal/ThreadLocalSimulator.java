package com.sunday.concurrency.practise9_threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HenDiao on 2017/10/3.
 * 始终与当前线程作为key值
 */
public class ThreadLocalSimulator<T> {
    private  final Map<Thread,T> storage=new HashMap<>();

    public void set (T t){

        synchronized (storage){

            Thread key =Thread.currentThread();
            storage.put(key,t);
        }
    }

    public  T get(){

        synchronized (storage){

           T t= storage.get(Thread.currentThread());
           if (t==null){

               return initValue();
           }
           return  t;

        }
    }

    protected T initValue() {
        return null;


    }



}
