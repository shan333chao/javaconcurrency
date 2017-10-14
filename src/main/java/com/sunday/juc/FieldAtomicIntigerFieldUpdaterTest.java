package com.sunday.juc;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by HenDiao on 2017/10/13.
 */
public class FieldAtomicIntigerFieldUpdaterTest {
    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<TestMe> updater=AtomicIntegerFieldUpdater.newUpdater(TestMe.class,"i");
        TestMe me=new TestMe() ;
        updater.compareAndSet(me,0,1);
        TestMe2 test=new TestMe2();
        test.i=5;
        AtomicReferenceFieldUpdater<TestMe2,Integer> updater1=AtomicReferenceFieldUpdater.newUpdater(TestMe2.class,Integer.class,"i");
        updater1.compareAndSet(test,null,1);
    }

    static  class TestMe2{
      public   volatile  Integer i;

    }


}
