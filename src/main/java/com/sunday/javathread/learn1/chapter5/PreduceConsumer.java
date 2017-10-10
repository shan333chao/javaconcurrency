package com.sunday.javathread.learn1.chapter5;

public class PreduceConsumer
{
    private    int i=1;
    private  final  Object LOCK=new Object();
    private  void produce(){
        synchronized (LOCK){
            System.out.println("p->"+i++);
        }
    }
    private  void consume(){
        synchronized (LOCK){
            System.out.println("c->"+i);
        }
    }

    public static void main(String[] args) {
    PreduceConsumer pc=new PreduceConsumer();
        new Thread("p"){
            @Override
            public void run() {
                while(true)
                pc.produce();
            }
        }.start();
        new Thread("C"){
            @Override
            public void run() {
                while(true)
                pc.consume();
            }
        }.start();
    }
}
