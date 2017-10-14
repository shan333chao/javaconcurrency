package com.sunday.juc;

/**
 * Created by HenDiao on 2017/10/14.
 */
public class Singleton {
    private Singleton(){

    }
    private static  class  Holder{
        public static  Singleton instance=new Singleton() ;
    }
    public Singleton getInstance(){
        return Holder.instance;
    }
    private void show(){
        System.out.println("show");
    }
    static {
        System.out.println("loaded");
    }
}
