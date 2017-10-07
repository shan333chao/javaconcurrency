package com.sunday.concurrency.practise15_two_phase_termination;

/**
 * Created by HenDiao on 2017/10/4.
 */
public class CounterTest {

    public static void main(String[] args) throws InterruptedException {
        CounterIncrement counterIncrement=new CounterIncrement() ;

        counterIncrement.start();
        Thread.sleep(10_000L);
        counterIncrement.close();
    }
}
