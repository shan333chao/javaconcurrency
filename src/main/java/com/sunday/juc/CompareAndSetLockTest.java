package com.sunday.juc;

/**
 * Created by HenDiao on 2017/10/11.
 */
public class CompareAndSetLockTest {

    private final static CompareAndSetLock lock=new CompareAndSetLock() ;
    public static void main(String[] args) {

        for (int i=0;i<6;i++){
            new Thread(){
                @Override
                public void run() {
                    try {

                        doSomeThing();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }
    }


    public static   void doSomeThing() throws InterruptedException {
        try {
            lock.tryLock();
            System.out.println(Thread.currentThread().getName()+" get lock ");
            Thread.sleep(14500L);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+" release lock ");
            lock.unLock();
        }

    }
}
