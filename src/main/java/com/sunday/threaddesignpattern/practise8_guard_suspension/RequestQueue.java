package com.sunday.threaddesignpattern.practise8_guard_suspension;

import java.util.LinkedList;

/**
 * Created by HenDiao on 2017/10/2.
 * 确保挂起设计模式
 * Guarded Supenseion
 */

public class RequestQueue {
    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest() {
        synchronized (queue) {

            try {
                while (queue.size() <= 0) {

                    queue.wait();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
            return queue.removeFirst();
        }

    }
    public  void putRequest(Request req){

        synchronized (queue){
            queue.addLast(req);
            queue.notifyAll();
        }
    }
}
