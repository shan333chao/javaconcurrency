package com.sunday.concurrency.practise4_observer;

public abstract class Observer {
    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    protected  Subject subject;
    public abstract void update();


}
