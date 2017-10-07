package com.sunday.concurrency.practise1_singletion;

public class SingletionObject1 {
    /**
     * can`t not lazy load
     */
    private  static  final SingletionObject1 instance=new SingletionObject1();
    private  SingletionObject1(){

    }
    private static  SingletionObject1 getInstance(){
        return instance;
    }
}
