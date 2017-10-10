package com.sunday.threaddesignpattern.practise11_balking;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class BalkingClient {
    public static void main(String[] args) {

        BalkingData balkingData =new BalkingData("D:\\MyCode\\currency\\src\\main\\java\\com\\sunday\\javathread\\practise11_balking\\text","========begin") ;
        new CustomerThread(balkingData).start();
        new WaiterThread(balkingData).start();

    }


}
