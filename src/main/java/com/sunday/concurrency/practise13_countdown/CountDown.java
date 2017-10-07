package com.sunday.concurrency.practise13_countdown;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class CountDown {
    private  final  int total;
    private  int counter=0;
    public CountDown(int total) {
        this.total = total;
    }

    public  void down(){
        synchronized (this){
            counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this){
            while (counter!=total){
                this.wait();
            }
        }
    }
}
