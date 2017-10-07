package com.sunday.concurrency.practise10_threadcontext;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class ExecutionTask implements  Runnable {

    private QueryFromDbAction queryAction =new QueryFromDbAction();
    private QueryFromHTTPAction queryFromHTTPAction=new QueryFromHTTPAction();

    @Override
    public void run() {
        Context context = ActionContext.getActionContext().getContext();
        queryAction.execute();
        System.out.println("name query success");
        queryFromHTTPAction.execute();
        System.out.println("cardid query success");

        System.out.println(context);
    }
}
