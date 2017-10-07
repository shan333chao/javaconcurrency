package com.sunday.concurrency.practise5_single_threaded_execution;

/**
 * Created by HenDiao on 2017/10/1.
 */
public class Gate {
    private int counter;
    private String name="nobody";
    private String address="Nowhere";

    public synchronized void pass(String name,String address){
        this.counter++;
        this.name=name;
        this.address=address;
        verify();
    }

    private void verify() {
        if (this.name.charAt(0)!=this.address.charAt(0)){
            System.out.println("***************broken****"+this);

        }
    }

    @Override
    public String toString() {

        return "No."+counter+":"+name+","+address;
    }
}
