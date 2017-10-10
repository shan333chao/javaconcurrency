package com.sunday.threaddesignpattern.practise2_wait_set;

public class WaitSet {

    private static final Object LOCK = new Object();

    private static void work() {
        synchronized (LOCK) {
            System.out.println("begining....");
            try {
                System.out.println("Thread will comming....");
                LOCK.wait();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread will out....");
        }
    }

    /**
     * 1 所有的对象都会有 wait set 用来存放调用了该对象wai他放他之后进入Block状态线程
     * 2.线程被notify之后不一定立即 得到执行
     * 3.线程从wait set中被唤醒的顺序不一定是 FIFO
     * 4 线程被唤醒后必须重新获取锁
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
//        IntStream.range(1, 10).forEach(i -> new Thread(String.valueOf(i)) {
//                    @Override
//                    public void run() {
//                       synchronized (LOCK){
//                           try {
//                               Optional.of(Thread.currentThread().getName()+" will come to wait set").ifPresent(System.out::println);
//                               LOCK.wait();
//                               Optional.of(Thread.currentThread().getName()+"   come to runable").ifPresent(System.out::println);
//                           } catch (InterruptedException e) {
//                               e.printStackTrace();
//                           }
//                       }
//                    }
//                }.start()
//        );
//        Thread.sleep(1000);

        new Thread(){
            @Override
            public void run() {
                work();
            }
        }.start();
        Thread.sleep(1000);
        synchronized (LOCK){
            LOCK.notify();
        }

//        IntStream.range(1, 10).forEach(i->{
//            synchronized (LOCK){
//                LOCK.notify();
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//
//        });
    }

}
