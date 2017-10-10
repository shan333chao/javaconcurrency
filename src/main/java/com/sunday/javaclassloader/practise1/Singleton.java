package com.sunday.javaclassloader.practise1;

/**
 * Created by HenDiao on 2017/10/5.
 */
public class Singleton {
    private  static   Singleton instance=new Singleton() ;

    public  static  int x;
    public static  int y;

    private  Singleton(){
        x++;
        y++;
    }

    /**
     * 为类的静态变量分配内存，并将其初始化为默认值
     * 1、instance =null
     * 2、x=0
     * 3、y=0
     * 初始化：为类的静态变量赋予正确的初始值 instance=new Singleton();
     * Singleton constructor:
     *      x++=>x=1
     *      y++=>y=1
     *
     * 为静态变量分配
     * x=0;
     * y=0
     *
     *
     * @return
     */

    public  static  Singleton getInstance(){
        return  instance;
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance.x);
        System.out.println(instance.y);
    }
}
