package com.sunday.javathread.learn1.chapter5;

public class ProduceConsumerV2 {

    private    int i=1;
    private  final  Object LOCK=new Object();
    private  volatile  boolean isProduce=false;
    private  void produce(){
        synchronized (LOCK){
            while (isProduce) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                System.out.println("p->"+i++);
                LOCK.notifyAll();
                isProduce=true;
        }
    }
    private  void consume(){
        synchronized (LOCK){
            while (  ! isProduce) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("c->" + i);
            LOCK.notifyAll();
            isProduce = false;
        }
    }

    public static void main(String[] args) {
        ProduceConsumerV2 pc=new ProduceConsumerV2();
        for (int i=0;i<10;i++){
            new Thread("p"+i) {
                @Override
                public void run() {
                    while (true)
                        pc.produce();
                }
            }.start();

            new Thread("c"+i) {
                @Override
                public void run() {
                    while (true)
                        pc.consume();
                }
            }.start();
        }


    }
}
