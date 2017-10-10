package com.sunday.threaddesignpattern.practise10_threadcontext;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class Context {
    public String getName() {
        return name;
    }

    private String name;

    public String getCardId() {
        return cardId;
    }

    private  String cardId;
    public void setName(String name) {
        this.name = name;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "Context{" +
                "name='" + name + '\'' +
                ", cardId='" + cardId + '\'' +
                '}';
    }
}
