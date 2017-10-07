package com.sunday.concurrency.practise1_singletion;

public class SingletionObject4 {
    /**
     * double check singletion object
     */
    private  static SingletionObject4 instance;

    private SingletionObject4(){}

    public   static SingletionObject4 getInstance(){
        if (null==instance){
            synchronized (SingletionObject4.class){
                if (null==instance){
                    instance=new SingletionObject4();
                }
            }
        }

        return SingletionObject4.instance;
    }

}


