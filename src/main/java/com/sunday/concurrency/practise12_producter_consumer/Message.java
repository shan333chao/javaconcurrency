package com.sunday.concurrency.practise12_producter_consumer;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class Message
{
    private  String data;

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
