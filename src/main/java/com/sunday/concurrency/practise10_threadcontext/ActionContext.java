package com.sunday.concurrency.practise10_threadcontext;

/**
 * Created by HenDiao on 2017/10/3.
 */
public final class ActionContext {

    private static final ThreadLocal<Context> threadlocal = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    private static class ContextHolder {

        private static final ActionContext context = new ActionContext();
    }

    public static ActionContext getActionContext() {

        return ContextHolder.context;
    }

    public Context getContext() {
        return threadlocal.get();

    }


}
