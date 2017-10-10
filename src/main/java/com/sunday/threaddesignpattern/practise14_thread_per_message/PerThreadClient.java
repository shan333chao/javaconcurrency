package com.sunday.threaddesignpattern.practise14_thread_per_message;

import java.util.stream.IntStream;

/**
 * Created by HenDiao on 2017/10/4.
 */
public class PerThreadClient {
    public static void main(String[] args) {
        final  MessageHandler messageHandler=new MessageHandler();
        IntStream.rangeClosed(0,10).forEach(i->{
            messageHandler.request(new Message("message - "+i));
        });
        messageHandler.shutdown();
    }
}
