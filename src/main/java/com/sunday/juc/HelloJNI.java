package com.sunday.juc;

/**
 * Created by HenDiao on 2017/10/14.
 */
public class HelloJNI {
    static {
        System.loadLibrary("hello");

    }
    private  native  void hi();

    public static void main(String[] args) {
        new HelloJNI().hi();
    }
}
