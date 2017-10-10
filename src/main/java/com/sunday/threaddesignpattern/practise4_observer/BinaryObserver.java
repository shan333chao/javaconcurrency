package com.sunday.threaddesignpattern.practise4_observer;

public class BinaryObserver extends  Observer {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary string +"+Integer.toBinaryString(subject.getState()));
    }
}
