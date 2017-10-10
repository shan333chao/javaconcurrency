package com.sunday.threaddesignpattern.practise11_balking;

import java.io.IOException;
import java.util.Random;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class CustomerThread extends Thread {
    private  final  BalkingData balkingData;
    private  final Random random=new Random();
    public CustomerThread(BalkingData balkingData) {
        super("Customer");
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
        try {
            balkingData.save();
            for (int i=0;i<20;i++){
                balkingData.change("no."+i);
                Thread.sleep(random.nextInt(1000));
                balkingData.save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.run();
    }
}
