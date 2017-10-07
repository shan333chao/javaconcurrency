package com.sunday.concurrency.practise1_singletion;

import java.util.stream.IntStream;

public class SingletionObject7
{

    /**
     * double check singletion  add  volaite object
     */


    private SingletionObject7() {
    }
    private  enum Singleton{
        INSTANCE;
        private final SingletionObject7 instance;
        Singleton(){
            instance=new SingletionObject7();
        }
        public SingletionObject7 getInstance(){
            return instance;
        }
    }


    public static SingletionObject7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }


    public static void main(String[] args) {
        IntStream.range(1,100).forEach(i->new Thread(String.valueOf(i)){
            @Override
            public void run() {
                System.out.println(SingletionObject7.getInstance());
            }
        }.start());
    }

}


