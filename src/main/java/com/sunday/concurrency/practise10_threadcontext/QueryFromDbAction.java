package com.sunday.concurrency.practise10_threadcontext;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class QueryFromDbAction {
    public void execute() {


        try {
            Thread.sleep(1000);
            String name = "shandy"+Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
