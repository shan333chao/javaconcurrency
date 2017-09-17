package com.sunday.concurrency.pritase1;

public class SingletionObject2 {

    private  static  SingletionObject2 instance;
    private SingletionObject2(){}
    public synchronized static SingletionObject2 getInstance(){
        if (null==instance){
            instance=new SingletionObject2();
        }
        return SingletionObject2.instance;
    }




}
