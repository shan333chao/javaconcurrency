package com.sunday.threaddesignpattern.practise6_immutable;

/**
 * Created by HenDiao on 2017/10/2.
 */
public class UsePersonThread  extends  Thread{
    private  Person person;

    public UsePersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+" print"+person.toString());

        }
    }
}
