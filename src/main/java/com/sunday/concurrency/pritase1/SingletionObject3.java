package com.sunday.concurrency.pritase1;

public class SingletionObject3 {

    private  static  SingletionObject3 instance;

    private SingletionObject3(){}

    public synchronized static SingletionObject3 getInstance(){
        if (null==instance){
            instance=new SingletionObject3();
        }
        return SingletionObject3.instance;
    }

}


