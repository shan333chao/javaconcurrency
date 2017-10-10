package com.sunday.threaddesignpattern.practise6_immutable;

import java.util.stream.IntStream;

/**
 * Created by HenDiao on 2017/10/2.
 */
public class ImmutableClient {
    public static void main(String[] args) {
        Person person = new Person("shanchao", "xa");
        IntStream.range(0,5).forEach(i->new UsePersonThread(person).start());
    }
}
