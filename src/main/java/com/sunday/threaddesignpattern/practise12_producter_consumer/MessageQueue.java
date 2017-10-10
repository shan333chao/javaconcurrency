package com.sunday.threaddesignpattern.practise12_producter_consumer;

import java.util.LinkedList;

/**
 * Created by HenDiao on 2017/10/3.
 */
final public class MessageQueue {
    private final LinkedList<Message> messageQueue;
    private static final int MAX_SIZE = 100;
    private final int limit;

    public MessageQueue() {
        this(MAX_SIZE);
    }

    public MessageQueue(final int limit) {
        this.messageQueue = new LinkedList<Message>();
        this.limit = limit;
    }

    public void put(Message msg) throws InterruptedException {
        synchronized (messageQueue) {
            while (messageQueue.size() > limit) {
                messageQueue.wait();
            }
            messageQueue.addLast(msg);
            messageQueue.notifyAll();
        }
    }

    public  Message take() throws InterruptedException {
        synchronized (messageQueue){
            while (messageQueue.isEmpty())
            {
                messageQueue.wait();
            }

            Message message = messageQueue.removeFirst();
            messageQueue.notifyAll();
            return  message;
        }
    }

    public  int getMaxLimit(){
        return this.limit;
    }
    public  int getMessageSize(){
        synchronized (messageQueue){
            return messageQueue.size();
        }
    }

}
