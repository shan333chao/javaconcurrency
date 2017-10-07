package com.sunday.concurrency.practise5_single_threaded_execution;

/**
 * Created by HenDiao on 2017/10/1.
 */
public class User extends  Thread {
    private  final String myName;
    private final String myAddress;
    private  final  Gate gate;

    public User(String myName, String myAddress, Gate gate) {
        this.myName = myName;
        this.myAddress = myAddress;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(myName+" Begin");
        while (true){
            this.gate.pass(myName,myAddress);
        }

    }
}
