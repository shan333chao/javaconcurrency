package com.sunday.concurrency.practise8_guard_suspension;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class Request {
  final   private String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue(){

        return value;
    }
}
