package com.sunday.concurrency.practise4_observer;

public class ObserverClient {


    public static void main(String[] args) {
        final Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);
        System.out.println("+++++++++++++++++");
        subject.setState(10);

        System.out.println("+++++++++++++++++");

        subject.setState(12);
        System.out.println("+++++++++++++++++");
        subject.setState(15);
    }
}
