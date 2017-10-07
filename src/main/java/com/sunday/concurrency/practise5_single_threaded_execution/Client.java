package com.sunday.concurrency.practise5_single_threaded_execution;

/**
 * Created by HenDiao on 2017/10/1.
 */
public class Client {

    public static void main(String[] args) {
        Gate gate = new Gate();
        User sh = new User("shanchao", "sh", gate);
        User bj = new User("beibei", "bj", gate);
        User xzh = new User("xuhongjie", "xz", gate);
        sh.start();
        bj.start();
        xzh.start();
    }
}
