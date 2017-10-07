package com.sunday.concurrency.practise6_immutable;

/**
 * Created by HenDiao on 2017/10/2.
 */
final public class Person {
    private  final  String name;
    private final  String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public String getAddress() {

        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
