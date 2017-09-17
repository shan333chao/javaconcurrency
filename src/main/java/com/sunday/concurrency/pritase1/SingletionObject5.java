package com.sunday.concurrency.pritase1;

public class SingletionObject5 {
    /**
     * double check singletion  add  volaite object
     */
    private  static volatile SingletionObject5 instance;

    private SingletionObject5(){}

    public   static SingletionObject5 getInstance(){
        if (null==instance){
            synchronized (SingletionObject5.class){
                if (null==instance){
                    instance=new SingletionObject5();
                }
            }
        }

        return SingletionObject5.instance;
    }

}


