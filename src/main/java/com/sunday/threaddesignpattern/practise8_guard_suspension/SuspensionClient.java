package com.sunday.threaddesignpattern.practise8_guard_suspension;

/**
 * Created by HenDiao on 2017/10/2.
 * 确保 挂起
 * 线程执行时，如果没做完，其他任务来了就让请他等待
 */
public class SuspensionClient {
    public static void main(String[] args) {
        final RequestQueue queue = new RequestQueue();

        new ClientThread(queue, "sunday").start();

        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();
        //serverThread.join();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        serverThread.close();
    }
}
