package com.sunday.javathread.learn1;

public class Bank  extends  Thread {
    private  final static int maxNum=50;
    private   static  int index=1;

    private  final  String name;
    public  Bank(String bankname){
        this.name=bankname;
    }
    @Override
    public void run() {
        while(index<=maxNum){

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            index++;
            System.out.println("bank:"+name+" current num is "+(index++));
        }
    }
}
