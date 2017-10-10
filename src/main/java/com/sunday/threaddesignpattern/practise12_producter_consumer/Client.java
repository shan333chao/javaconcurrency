package com.sunday.threaddesignpattern.practise12_producter_consumer;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class Client {
    public static void main(String[] args) {
        final MessageQueue queue = new MessageQueue();
        new ProducerThread(queue, 1).start();
        new ProducerThread(queue, 2).start();
     //   new ProducerThread(queue, 3).start();

        new ConsumerThread(queue,1).start();
        new ConsumerThread(queue,2).start();
    }
}
