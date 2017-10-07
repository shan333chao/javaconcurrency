package com.sunday.concurrency.practise7_future_asynctask;

import java.util.function.Consumer;

/**
 * Created by HenDiao on 2017/10/2.
 */
public class FutureService {

    public <T> Future<T>  submit (final FutureTask<T> task){
    AsyncFuture<T> asyncFuture=new AsyncFuture<>();
    new Thread(()->{
        T result=task.call();
        asyncFuture.done(result);
    }).start();
        return asyncFuture;

    }

    public <T> Future<T>  submit (final FutureTask<T> task, final Consumer<T> consumer){
        AsyncFuture<T> asyncFuture=new AsyncFuture<>();
        new Thread(()->{
            T result=task.call();
            asyncFuture.done(result);
            consumer.accept(result);
        }).start();
        return asyncFuture;

    }
}
