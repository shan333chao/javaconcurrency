package com.sunday.threaddesignpattern.practise11_balking;

import java.io.IOException;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class WaiterThread extends Thread {

    private  final BalkingData balkingData;

    public WaiterThread(BalkingData balkingData) {
        super("waiter");
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
        for(int i=0;i<200;i++){
            try {
                balkingData.save();
                Thread.sleep(1_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
