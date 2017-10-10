package com.sunday.threaddesignpattern.practise10_threadcontext;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class QueryFromHTTPAction {
    public void execute() {


        try {
            Context context = ActionContext.getActionContext().getContext();
            Thread.sleep(1000);
            String name = context.getName();
            String cardId = getCardId(name);
            context.setCardId(cardId);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getCardId(String name) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "3213213214345346" + Thread.currentThread().getId();
    }
}
