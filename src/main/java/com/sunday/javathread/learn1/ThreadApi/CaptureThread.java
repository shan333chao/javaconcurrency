package com.sunday.javathread.learn1.ThreadApi;

public class CaptureThread implements  Runnable {
    private String  computerName;
    private  long costTime;

    public CaptureThread(String computerName, long costTime) {
        this.computerName = computerName;
        this.costTime = costTime;
    }

    @Override
    public void run() {
        try {
            docapture();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private  void docapture() throws InterruptedException {
        System.out.println(computerName+"is rumming begin at:"+System.currentTimeMillis());
        Thread.sleep(this.costTime);
        System.out.println(computerName+"is rumming end at:"+System.currentTimeMillis());
    }
}
