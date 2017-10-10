package com.sunday.javathread.learn1;

public class Bank2 {
    public static void main(String[] args) {
        final BankRunable bankRunable=new BankRunable();
        new Thread(bankRunable,"bank1").start();
        new Thread(bankRunable,"bank2").start();
        new Thread(bankRunable,"bank3").start();
    }

}
