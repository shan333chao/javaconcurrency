package com.sunday.concurrency.practise1_singletion;

public class SingletionObject6 {
    /**
     * double check singletion  add  volaite object
     */


    private SingletionObject6() {
    }

    private static class InstanceHolder {
        private final static SingletionObject6 instance = new SingletionObject6();
    }

    public static SingletionObject6 getInstance() {


        return InstanceHolder.instance;
    }

}


