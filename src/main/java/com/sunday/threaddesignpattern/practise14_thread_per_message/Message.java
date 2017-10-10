package com.sunday.threaddesignpattern.practise14_thread_per_message;

/**
 * Created by HenDiao on 2017/10/4.
 */
public class Message {
    private  final  String value;

    public Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
