package com.sunday.concurrency.practise7_future_asynctask;

/**
 * Created by HenDiao on 2017/10/2.
 */
public interface Future<T> {
    T get() throws  InterruptedException;

}