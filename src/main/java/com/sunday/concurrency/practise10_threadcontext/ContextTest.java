package com.sunday.concurrency.practise10_threadcontext;

import java.util.stream.IntStream;

/**
 * Created by HenDiao on 2017/10/3.
 * 使用Theadlocal 存储对象，让线程中的上下文保持一致，
 * 避免了每次的参数传递
 */
public class ContextTest {
    public static void main(String[] args) {


        IntStream.range(0,5).forEach(i->{
            new Thread(new ExecutionTask()).start();


        });
    }

}
