package com.sunday.javathread.learn1;

import java.util.Optional;

public class CallNumber {

    public static void main(String[] args) {
//        Bank b=new Bank("shop1");
//
//        Bank b2=new Bank("shop2");
//
//        Bank b3=new Bank("shop3");
//        b2.start();
//        b.start();
//        b3.start();
//
        CalcatorStratery cs=new SimpleCalculatorStratery();
        TaxCalaculator txtc=new  TaxCalaculator(1000,3000,(s,b)->{
            return s*0.1+b*0.15;
        });
        Optional.of( txtc.calcTax()).ifPresent(System.out::println);
    }


}
