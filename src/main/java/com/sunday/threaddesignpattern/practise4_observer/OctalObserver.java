package com.sunday.threaddesignpattern.practise4_observer;

public class OctalObserver extends  Observer {

    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal string +"+Integer.toOctalString(subject.getState()));
    }
}
